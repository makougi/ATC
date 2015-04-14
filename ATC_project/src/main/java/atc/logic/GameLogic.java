package atc.logic;

import java.util.ArrayList;

public class GameLogic {

    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private CommandParser commandParser;
    private int zoom;
    private int[] runwayPosition = {300, 300};

    ;

    public GameLogic() {
        zoom = 1000;
        aircrafts = new ArrayList();

        for (int i = 0; i < 1; i++) {
            aircrafts.add(new Aircraft(this));
        }
        commandParser = new CommandParser(this);
    }

    private void checkIfGoodForLanding(Aircraft a) {
//        System.out.println("X " + a.getX() / zoom);
//        System.out.println("pitää olla > " + (runwayPosition[0] - (25000 / zoom)));
//        System.out.println("pitää olla < " + (runwayPosition[0] + (25000 / zoom)));
//        System.out.println("Y " + a.getY() / zoom);
//        System.out.println("pitää olla > " + (runwayPosition[1] + (100000 / zoom)));
//        System.out.println("pitää olla < " + (runwayPosition[1] + (200000 / zoom)));
//        System.out.println("Heading " + a.getHeading());
//        System.out.println("pitää olla >= 350");
//        System.out.println("pitää olla <= 10");
//        System.out.println("Speed " + a.getSpeed());
//        System.out.println("pitää olla <= 240");
//        System.out.println("Altitude " + a.getZ());
//        System.out.println("pitää olla <= 20");
//        System.out.println("mode: " + a.getMode());
//        System.out.println("--------------------");
//
//        if (a.getX() / zoom > runwayPosition[0] - (25000 / zoom)) {System.out.println("1/8 ok");
//            if (a.getX() / zoom < runwayPosition[0] + (25000 / zoom)) {System.out.println("2/8 ok");
//                if (a.getY() / zoom > runwayPosition[1] + (100000 / zoom)) {System.out.println("3/8 ok");
//                    if (a.getY() / zoom < runwayPosition[1] + (200000 / zoom)) {System.out.println("4/8 ok");
//                        if (a.getHeading() >= 350) {System.out.println("5/8 ok");
//                            if (a.getHeading() <= 10) {System.out.println("6/8 ok");
//                                if (a.getSpeed() <= 240) {System.out.println("7/8 ok");
//                                    if (a.getZ() <= 20) {System.out.println("8/8 ok");
//                                        a.setMode(1);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println("-------------------------------------------------");

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
                checkIfGoodForLanding(a);
            }
            a.update();
        }
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }
}
