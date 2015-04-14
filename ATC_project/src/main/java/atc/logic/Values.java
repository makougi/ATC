package atc.logic;

import java.util.Random;

public class Values {

    public static String createIdentifier(GameLogic gl) {
        Boolean unique = false;
        Random random = new Random();
        StringBuilder sb = new StringBuilder(6);
        String identifier = new String();
        while (unique == false) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            for (int i = 0; i < 3; i++) {
                sb.append(random.nextInt(10));
            }
            sb.append(characters.charAt(random.nextInt(characters.length())));
            sb.append(characters.charAt(random.nextInt(characters.length())));
            sb.append(characters.charAt(random.nextInt(characters.length())));
            identifier = sb.toString();
            unique = true;
            for(Aircraft a:gl.getAircrafts()){
                if (a.getIdentifier().equals(identifier)){
                    unique = false;
                    break;
                }
            }
        }
        return identifier;
    }

    public static int[][] getHeadingXYValues() {        //speed ratio for directions x and y depending on heading
        int[][] headingXY = new int[360][2];
        int x = 0;
        int y = -90;

        for (int i = 0; i < 360; i++) {
            headingXY[i][0] = x;
            headingXY[i][1] = y;

            if (i < 90) {
                x++;
                y++;
            }
            if (i >= 90 && i < 180) {
                x--;
                y++;
            }
            if (i >= 180 && i < 270) {
                x--;
                y--;
            }
            if (i >= 270) {
                x++;
                y--;
            }
        }
        return headingXY;
    }
}
