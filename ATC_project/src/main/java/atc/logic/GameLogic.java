package atc.logic;

import java.util.ArrayList;

public class GameLogic {

    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private CommandParser commandParser;
    private int zoom;
    private int[] runwayPosition = {300, 300};;

    public GameLogic() {
        zoom = 1000;
        aircrafts = new ArrayList();

        for (int i = 0; i < 1; i++) {
            aircrafts.add(new Aircraft(this));
        }
        commandParser = new CommandParser(this);
    }

    private void checkIfGoodToLand(Aircraft a) {
        if (a.getX() > runwayPosition[0] - (25000 / zoom)
                && a.getX() < runwayPosition[0] + (25000 / zoom)
                && a.getY() > runwayPosition[1] + (100000 / zoom)
                && a.getY() < runwayPosition[1] + (200000 / zoom)
                && a.getHeading() >= 350
                && a.getHeading() <= 10
                && a.getSpeed() <= 240
                && a.getZ() <= 20) {
            a.setMode(1);
        }

    }

    public void removeAircraft(Aircraft a) {
        aircrafts.remove(a);
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
        for (Aircraft a : aircrafts) {
            if (a.getMode() != 1) {
                checkIfGoodToLand(a);
            }
            a.update();
        }
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }
}
