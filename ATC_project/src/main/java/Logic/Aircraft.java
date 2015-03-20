package Logic;

import java.util.Random;

public class Aircraft {

    private Random random;
    private String name;
    private int x;
    private int y;
    private int z;
    private int xspeed;
    private int yspeed;

    public Aircraft() {
        random = new Random();
        name = createName();
        setupInitialValues();
    }

    public void printRadarInfo() {
        System.out.println(name);
        System.out.println("x: " + x / 100);
        System.out.println("y: " + y / 100);
        System.out.println("alt: " + z);
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

    public void fly() {
        x = x + xspeed;
        y = y + yspeed;
    }

    private void setupInitialValues() {
        x = random.nextInt(1000000);
        y = random.nextInt(1000000);
        z = random.nextInt(1000) + 100;
        xspeed = 0;
        yspeed = 0;
        while (xspeed == 0 && yspeed == 0) {
            xspeed = 40 - random.nextInt(90);
            yspeed = 40 - random.nextInt(90);
        }
    }
}
