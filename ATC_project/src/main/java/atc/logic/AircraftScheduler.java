/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

import atc.gui.GUIFrame;
import atc.gui.RadarPanel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kimmo
 */
public class AircraftScheduler {

    private RadarPanel radarPanel;
    private ArrayDeque<int[]> schedule;
    private Random random;
    private int zoom;
    private ArrayList<Aircraft> aircrafts;
    private ArrayList<String> allIdentifiers;
    private GameLogic gl;
    public int scheduleScope;
    private int scheduleScopeTop;
    public int scheduleClock;
    public int timeOfNextScheduleEntry;
    private int averageFrequency;
    private GUIFrame guiFrame;

    public AircraftScheduler(GameLogic gameLogic, ArrayList<Aircraft> as, int zm) {
        gl = gameLogic;
        aircrafts = as;
        allIdentifiers = new ArrayList<String>();
        random = new Random();
        zoom = zm;
        scheduleScope = 0;
        scheduleScopeTop = 300;//seconds that the schedule shows in to the future
        averageFrequency = 30;//new flight every x seconds in average
        schedule = new ArrayDeque<int[]>();
        scheduleClock = 0;
        timeOfNextScheduleEntry = random.nextInt(4) + 5;
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

        aircrafts.add(
                new Aircraft(gl, allIdentifiers.get(schedule.peekFirst()[0]), x, y, z, heading, speed));
    }

    private void scheduleNewFlight() {
        allIdentifiers.add(Values.createIdentifier(gl));
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

    /**
     * Timer-luokan olio kutsuu tätä metodia keskimäärin kerran sekunnissa,
     * jolloin scheduleclock muuttujan arvoa lisätään yhdellä ja samalla
     * kutsutaan aikataulun päivittävää metodia.
     */
    public void updateScheduleClock() {
        scheduleClock++;
        updateSchedule();
    }

    private void updateSchedule() {

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

}
