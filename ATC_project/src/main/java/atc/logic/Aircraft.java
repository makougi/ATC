package atc.logic;

import java.util.Random;

/**
 *
 * @author Kimmo
 * <p>
 * Lentokoneen toteuttava luokka
 */
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
    private int slowDescendValue;
    private int[][] headingXYValues;
    private int[][] history;
    private int historyLength;
    private int mode;
    private GameLogic gl;

    /**
     * konstruktori
     *
     * @param gameLogic gamelogic-olio
     * @param id lentokoneen tunniste1
     * @param initialX aloituspaikka
     * @param initialY aloituspaikka
     * @param initialAltitude aloituskorkeus
     * @param initialHeading aloitussuunta
     * @param initialSpeed aloitusnopeus
     */
    public Aircraft(GameLogic gameLogic, String id, int initialX, int initialY, int initialAltitude, int initialHeading, int initialSpeed) {
        x = initialX;
        y = initialY;
        altitude = initialAltitude;
        heading = initialHeading;
        speed = initialSpeed;
        altitudeCommand = altitude;
        headingCommand = heading;
        speedCommand = speed;
        gl = gameLogic;
        mode = 0;//mode 0 normal, mode 1 landing
        historyLength = 10;
        history = new int[historyLength][2];//recent x&y positions
        random = new Random();
        identifier = id;
        headingXYValues = Values.getHeadingXYValues();
        slowDescendValue = 0;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int m) {
        mode = m;
    }

    /**
     * asettaa korkeuskomennon
     *
     * @param c korkeuskomento
     */
    public void setAltitudeCommand(char[] c) {
        if (mode == 0) {
            altitudeCommand = Character.getNumericValue(c[0]) * 100 + Character.getNumericValue(c[1]) * 10 + Character.getNumericValue(c[2]);
            if (altitudeCommand < 10) {
                altitudeCommand = 10;
            }
        }
    }

    /**
     * asettaa suuntakomennon
     *
     * @param c suuntakomento
     */
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

    /**
     * asettaa nopeuskomennon
     *
     * @param c nopeuskomento
     */
    public void setSpeedCommand(char[] c) {
        if (mode == 0) {
            speedCommand = (Character.getNumericValue(c[0]) * 1000 + Character.getNumericValue(c[1]) * 100 + (Character.getNumericValue(c[2])) * 10 + Character.getNumericValue(c[3]));
            if (speedCommand < 160) {
                speedCommand = 160;
            }
        }
    }

    /**
     *
     * @return lentokoneen viimeisimmät sijainnit
     */
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

    /**
     *
     * @return korkeuskomento
     */
    public int getZCommand() {
        return altitudeCommand;
    }

    public int getSpeedCommand() {
        return speedCommand;
    }

    public int getHeadingCommand() {
        return headingCommand;
    }

    /**
     * päivittää lentokoneen sijainnin, korkeuden, nopeuden, sunnunan ja tiedot
     * edellisistä sijainneista
     */
    public void update() {
        if (speed == 0) {
            mode = 2;
            gl.landed(this);
            return;
        }
        if (mode == 1) {//if landing
            landingCommands();
        }
        updateHistory();
        updatePosition();
        updateAltitude();
        updateSpeed();
        updateHeading();
    }

    private void landingCommands() {
        if (y / gl.getZoom() > gl.getRunwayPosition()[1] + 100) {
            altitudeCommand = 10;
        } else {
            altitudeCommand = 0;
        }

        if (x / gl.getZoom() > gl.getRunwayPosition()[0]) {
            headingCommand = 355;
        }
        if (x / gl.getZoom() < gl.getRunwayPosition()[0]) {
            headingCommand = 5;
        }
        if (x / gl.getZoom() == gl.getRunwayPosition()[0]) {
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
        x += headingXYValues[heading][0] * speed / 10;
        y += headingXYValues[heading][1] * speed / 10;
    }

    private void updateAltitude() {
        if (mode != 1) {
            if (altitude < altitudeCommand) {
                altitude++;
            }
            if (altitude > altitudeCommand) {
                altitude--;
            }
        } else {
            if (altitude > altitudeCommand) {
                slowDescendValue++;
                if (slowDescendValue >= 4) {
                    altitude--;
                    slowDescendValue = 0;
                }
            }
        }
    }

    private void updateSpeed() {
        if (speed + 10 < speedCommand) {
            speed += 5;
        } else if (speed < speedCommand) {
            speed++;
        }
        if (speed - 10 > speedCommand) {
            speed -= 5;
        } else if (speed > speedCommand) {
            speed--;
        }
    }

    private void updateHeading() {
        int turningSpeed = 3;
        if (heading < headingCommand) {
            headingCommandIsBiggerThanHeading(turningSpeed);
        }
        if (heading > headingCommand) {
            headingCommandIsSmallerThanHeading(turningSpeed);
        }
    }

    private void headingCommandIsSmallerThanHeading(int turningSpeed) {
        if (360 + headingCommand - heading < heading - headingCommand) {//jos lyhyempi matka nollakohdan yli myötäpäivään
            heading += turningSpeed;
            if (heading > 359) {//jos menee nollakohdan yli
                heading -= 360;
                if (heading > headingCommand) {//jos menee headingCommandin yli
                    heading = headingCommand;
                }
            }
        } else {
            heading -= turningSpeed;
            if (heading < headingCommand) {//jos menee headingCommandin yli
                heading = headingCommand;
            }
        }
    }

    private void headingCommandIsBiggerThanHeading(int turningSpeed) {
        if (headingCommand - heading > 360 + heading - headingCommand) {//jos lyhyempi matka nollakohdan yli vastapäivään
            heading -= turningSpeed;
            if (heading < 0) {//jos menee nollakohdan yli
                heading += 360;
                if (heading < headingCommand) {//jos menee headingCommandin yli
                    heading = headingCommand;
                }
            }
        } else {
            heading += turningSpeed;
            if (heading > headingCommand) {//jos menee headingCommandin yli
                heading = headingCommand;
            }
        }
    }
}
