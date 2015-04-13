package atc.logic;

import java.util.Random;

public class Aircraft {

    private Random random;
    private String identifier;
    private int x;
    private int y;
    private int altitude;
    private int heading;
    private int speed;
    private int altitudeCommand;
    private int headingCommand;
    private int speedCommand;
    private int[][] headingXYValues;
    private int[][] history;
    private int historyLength;
    private int mode;
    private GameLogic gl;

    public Aircraft(GameLogic gameLogic) {
        gl = gameLogic;
        mode = 0;//mode 0 normal, mode 1 landing
        historyLength = 10;
        history = new int[historyLength][2];//recent x&y positions
        random = new Random();
        identifier = Values.createIdentifier(gl);
        headingXYValues = Values.getHeadingXYValues();
        setupInitialValues();
    }
    public int getMode(){
        return mode;
    }
    
    public void setMode(int m){
        mode = m;
    }

    public void setAltitudeCommand(char[] c) {
        if (mode == 0) {
            altitudeCommand = Character.getNumericValue(c[0]) * 10 + Character.getNumericValue(c[1]);
            if (altitudeCommand < 10) {
                altitudeCommand = 10;
            }
        }
    }

    public void setHeadingCommand(char[] c) {
        if (mode == 0) {
            headingCommand = (Character.getNumericValue(c[0])) * 100 + (Character.getNumericValue(c[1]) * 10 + Character.getNumericValue(c[2]));
        }
        while (headingCommand > 359) {
            headingCommand -= 360;
        }
        while (headingCommand < 0) {
            headingCommand += 360;
        }
    }

    public void setSpeedCommand(char[] c) {
        if (mode == 0) {
            speedCommand = 0;
            if (Character.isDigit(c[0])) {
                speedCommand = (Character.getNumericValue(c[0])) * 1000;
            }
            speedCommand += (Character.getNumericValue(c[1]) * 100 + (Character.getNumericValue(c[2])) * 10 + Character.getNumericValue(c[3]));
            if (speedCommand < 160) {
                speedCommand = 160;
            }
        }
    }

    public int[][] getHistory() {
        return history;
    }

    public String getIdentifier() {
        return identifier;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return altitude;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHeading() {
        return heading;
    }

    public void update() {
        if (speed == 0){
            gl.removeAircraft(this);
            return;
        }
        if (mode == 1) {//if landing
            mode1Commands();
        }
        updateHistory();
        updatePosition();
        updateAltitude();
        updateSpeed();
        updateHeading();
    }

    private void mode1Commands() {//if landing
        altitudeCommand = 0;

        if (x > gl.getRunwayPosition()[0]) {
            headingCommand = 355;
        }
        if (x < gl.getRunwayPosition()[0]) {
            headingCommand = 5;
        }
        if (x == gl.getRunwayPosition()[0]) {
            headingCommand = 0;
        }
        if (altitude > 0) {
            speedCommand = 140;
        } else {
            speedCommand = 0;
        }
    }

    private void updateHistory() {
        for (int i = historyLength - 1; i > 0; i--) {
            history[i][0] = history[i - 1][0];
            history[i][1] = history[i - 1][1];
        }
        history[0][0] = x;
        history[0][1] = y;
    }

    private void updatePosition() {
        x += headingXYValues[heading][0] * speed;
        y += headingXYValues[heading][1] * speed;
    }

    private void updateAltitude() {
        if (altitude < altitudeCommand) {
            altitude++;
        }
        if (altitude > altitudeCommand) {
            altitude--;
        }
    }

    private void updateSpeed() {
        if (speed < speedCommand) {
            speed++;
        }
        if (speed > speedCommand) {
            speed--;
        }
    }

    private void updateHeading() {//tätä pitää vielä kehittää
        if (heading < headingCommand) {
            if (heading + 10 > 359) {
                heading = headingCommand;
            } else {
                heading = heading + 10;
            }

        }
        if (heading > headingCommand) {
            if (heading - 10 < 0) {
                heading = headingCommand;
            } else {
                heading = heading - 10;
            }
        }

        //varmistus:
        while (heading < 0) {
            heading += 360;
        }
        while (heading > 359) {
            heading -= 360;
        }
    }

    private void setupInitialValues() {
        x = random.nextInt(300000) + 200000;
        y = random.nextInt(300000) + 200000;
        altitude = random.nextInt(50) + 30;
        speed = random.nextInt(40) + 16;
        heading = random.nextInt(360);
        altitudeCommand = altitude;
        speedCommand = speed;
        headingCommand = heading;
    }
}
