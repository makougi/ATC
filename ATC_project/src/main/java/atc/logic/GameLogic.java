package atc.logic;

import atc.gui.GUIFrame;
import atc.gui.InfoPanel2;
import atc.gui.RadarPanel;
import atc.main.Timer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class GameLogic {

    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private int scheduleClock;
    private ArrayDeque<int[]> schedule;
    private int timeOfNextScheduleEntry;
    private int scheduleScope;
    private int scheduleScopeTop;
    private int averageFrequency;
    private CommandParser commandParser;
    private int zoom;
    private int[] runwayPosition = {300, 300};
    private int landed;
    private int lost;
    private Random random;
    private RadarPanel radarPanel;
    private GUIFrame guiFrame;
    private ArrayList<String> allIdentifiers;
    private ArrayList<Aircraft> removeList;
    private HashMap<Integer, Aircraft> positionMap;
    private Aircraft[] tooClose;
    private Timer timer;

    public GameLogic() {
        tooClose = new Aircraft[2];
        positionMap = new HashMap();
        removeList = new ArrayList();
        allIdentifiers = new ArrayList();
        scheduleScope = 0;
        scheduleScopeTop = 300;//seconds that the schedule shows in to the future
        averageFrequency = 30;//new flight every x seconds in average
        random = new Random();
        schedule = new ArrayDeque<int[]>();
        scheduleClock = 0;
        timeOfNextScheduleEntry = random.nextInt(4) + 5;
        zoom = 1000;
        aircrafts = new ArrayList();
        commandParser = new CommandParser(this);
    }

    public void setTimer(Timer t) {
        timer = t;
    }

    private void gameOver() {
        timer.stop();
        String[] info = {tooClose[0].getIdentifier(),
            "" + tooClose[0].getX() / 10000,
            "" + tooClose[0].getY() / 10000,
            "" + tooClose[0].getZ(),
            "" + tooClose[0].getHeading(),
            "" + tooClose[0].getSpeed(),
            tooClose[1].getIdentifier(),
            "" + tooClose[1].getX() / 10000,
            "" + tooClose[1].getY() / 10000,
            "" + tooClose[1].getZ(),
            "" + tooClose[1].getHeading(),
            "" + tooClose[1].getSpeed()};
        guiFrame.gameOver(info);
    }

    public int getLost() {
        return lost;
    }

    public int getLanded() {
        return landed;
    }

    public String getIdentifier(int i) {
        return allIdentifiers.get(i);
    }

    public ArrayDeque<int[]> getSchedule() {
        return schedule;
    }

    public void setGUIFrame(GUIFrame gf) {
        guiFrame = gf;
    }

    public int getScheduleClock() {
        return scheduleClock;
    }

    public void setRadarPanel(RadarPanel rp) {
        radarPanel = rp;
    }

    private void addNewAircraft() {
        int x;
        int y;
        int z = schedule.peekFirst()[3];
        int heading;
        int speed = random.nextInt(200) + 220;

        if (schedule.peekFirst()[2] == 0) {
            x = radarPanel.getSize().width * 1 / 20 * zoom;
            y = radarPanel.getSize().height * 1 / 20 * zoom;
            heading = 90;
        } else if (schedule.peekFirst()[2] == 1) {
            x = radarPanel.getSize().width * 15 / 20 * zoom;
            y = radarPanel.getSize().height * 1 / 20 * zoom;
            heading = 180;
        } else if (schedule.peekFirst()[2] == 2) {
            x = radarPanel.getSize().width * 19 / 20 * zoom;
            y = radarPanel.getSize().height * 10 / 20 * zoom;
            heading = 270;
        } else {
            x = radarPanel.getSize().width * 1 / 20 * zoom;
            y = radarPanel.getSize().height * 18 / 20 * zoom;
            heading = 90;
        }
        aircrafts.add(new Aircraft(this, allIdentifiers.get(schedule.peekFirst()[0]), x, y, z, heading, speed));
    }

    private void scheduleNewFlight() {
        allIdentifiers.add(Values.createIdentifier(this));
        int[] flight = new int[4];
        flight[0] = allIdentifiers.size() - 1;
        flight[1] = scheduleScope + scheduleClock;//time when aircraft enters radar
        flight[2] = random.nextInt(4);//place of entry
        flight[3] = random.nextInt(100) + 50;//initial altitude
        for (int[] f : schedule) {
            if (flight[2] == f[2] && (flight[3] < f[3] + 10 && flight[3] > f[3] - 10)) {//jos lentokone tulisi liian lähelle toista lentokonetta
                return;//älä luo lentoa
            }
        }
        schedule.add(flight);
    }

    public void updateScheduleClock() {
        scheduleClock++;
        
        if (scheduleScope < scheduleScopeTop) {
            scheduleScope++;
        }

        if (scheduleClock == timeOfNextScheduleEntry) {
            scheduleNewFlight();
            timeOfNextScheduleEntry += random.nextInt(averageFrequency * 2) + 1;
        }
        if (!schedule.isEmpty()) {
            if (schedule.peekFirst()[1] == scheduleClock) {
                addNewAircraft();
                schedule.pollFirst();
            }
        }
        guiFrame.updateInfoPanel2();
    }

    private boolean AircraftsTooClose(Aircraft a) {
        int divider = 10000;
        int differenceXY = 2;
        int xyz = a.getX() / 100000 + a.getY() / 100000;
        if (positionMap.containsKey(xyz)) {
            if (positionMap.get(xyz).getX() / divider > (a.getX() / divider) - differenceXY && positionMap.get(xyz).getX() / divider < (a.getX() / divider) + differenceXY
                    && positionMap.get(xyz).getY() / divider > (a.getY() / divider) - differenceXY && positionMap.get(xyz).getY() / divider < (a.getY() / divider) + differenceXY
                    && (positionMap.get(xyz).getZ() < (a.getZ() + 10) && positionMap.get(xyz).getZ() > (a.getZ() - 10))) {//varmistetaan, että lentokoneet ovat lähekkäin eikä vain sama hash-koodi
                tooClose[0] = positionMap.get(xyz);
                tooClose[1] = a;
                return true;
            }
        }
        positionMap.put(xyz, a);
        return false;
    }

    private void checkIfOutside(Aircraft a) {
        if (a.getX() < 0
                || a.getX() > guiFrame.getSize().height * zoom
                || a.getY() < 0
                || a.getY() > guiFrame.getSize().height * zoom) {
            lost++;
            removeList.add(a);
        }
    }

    private void checkIfGoodForLanding(Aircraft a) {
        if (a.getX() / zoom > runwayPosition[0] - (25000 / zoom)
                && a.getX() / zoom < runwayPosition[0] + (25000 / zoom)
                && a.getY() / zoom > runwayPosition[1] + (100000 / zoom)
                && a.getY() / zoom < runwayPosition[1] + (200000 / zoom)
                && (a.getHeading() >= 350 || a.getHeading() <= 10)
                && a.getSpeed() <= 240
                && a.getZ() <= 20) {
            a.setMode(1);
        }
    }

    public void landed(Aircraft a) {
        landed++;
        removeList.add(a);
    }

    public int getZoom() {
        return zoom;
    }

    public int[] getRunwayPosition() {
        return runwayPosition;
    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public void update() {
        updateAircrafts();
    }

    private void updateAircrafts() {
        positionMap.clear();
        for (Aircraft a : aircrafts) {
            if (a.getMode() != 1) {
                checkIfGoodForLanding(a);
                checkIfOutside(a);
            }
            if (AircraftsTooClose(a)) {
                gameOver();
            }
            a.update();
        }
        for (Aircraft a : removeList) {
            aircrafts.remove(a);
        }
        removeList.clear();
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }
}
