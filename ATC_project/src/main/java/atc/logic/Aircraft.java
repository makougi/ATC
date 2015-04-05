package atc.logic;

import java.util.Random;

public class Aircraft {

    private Random random;
    private String identifier;
    private int x;
    private int y;
    private int altitude;
    private int xspeed;
    private int yspeed;
    private int heading;
    private int speed;
    private int altitudeCommand;
    private int headingCommand;
    private int speedCommand; 

    public Aircraft() {
        random = new Random();
        identifier = createName();
        setupInitialValues();
    }
    public void setAltitudeCommand(char[] c){
        altitudeCommand = Character.getNumericValue(c[0])*10+Character.getNumericValue(c[1]);
        System.out.println(identifier+" setAlt "+altitudeCommand);
    }
    public void setHeadingCommand(char[] c){
        headingCommand = (Character.getNumericValue(c[0]))*100+(Character.getNumericValue(c[1])*10+Character.getNumericValue(c[2]));
        System.out.println(identifier+" setHeading "+headingCommand);
    }
    public void setSpeedCommand(char[] c){
        speedCommand = 0;
        if (Character.isDigit(c[0])){
            speedCommand = (Character.getNumericValue(c[0]))*1000;
        }
        speedCommand+=(Character.getNumericValue(c[1])*100+(Character.getNumericValue(c[2]))*10+Character.getNumericValue(c[3]));
        System.out.println(identifier+" setSpeed "+speedCommand);
    }
    
    public String getIdentifier(){
        return identifier;
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return altitude;
    }

    public void printRadarInfo() {
        System.out.println(identifier);
        System.out.println("x: " + x / 100);
        System.out.println("y: " + y / 100);
        System.out.println("alt: " + altitude);
        System.out.println();
    }

    private String createName() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 3; i++) {
            sb.append(random.nextInt(10));
        }
        sb.append(characters.charAt(random.nextInt(characters.length())));
        sb.append(characters.charAt(random.nextInt(characters.length())));
        sb.append(characters.charAt(random.nextInt(characters.length())));
        return sb.toString();//palautetaan luotu tunnus
    }

    public void move() {
        x = x + xspeed;
        y = y + yspeed;
    }

    private void setupInitialValues() {
        x = random.nextInt(500)+100;
        y = random.nextInt(500)+100;
        altitude = random.nextInt(1000) + 100;
        xspeed = 0;
        yspeed = 0;
        while (xspeed == 0 && yspeed == 0) {
            xspeed = 2 - random.nextInt(5);
            yspeed = 2 - random.nextInt(5);
        }
    }
}
