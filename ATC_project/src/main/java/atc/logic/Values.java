package atc.logic;

import java.util.Random;

/**
 *
 * @author Kimmo
 * <p>
 * Luokan tehtävänä on tuottaa tiettyjä arvoja. Se tuottaa lentokoneiden
 * tunnisteita ja lisäksi taulukon, jossa lentosuunnat on muunnettuna
 * x/y-suuntien etenemisnopeusarvoiksi
 */
public class Values {

    /**
     * luo satunnaisen lentokoneen tunnuksen siten, että ensimmäiset kolme
     * merkkiä ovat numeroita ja jälkimmäiset kolme kirjaimia. Jos sama tunniste
     * on jo sattumalta olemassa (epätodennäköistä), luodaan uudestaan
     * satunnainen tunniste.
     *
     * @param gl gamelogic-olio
     * @return lentokoneen tunniste
     */
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
            for (Aircraft a : gl.getAircrafts()) {
                if (a.getIdentifier().equals(identifier)) {
                    unique = false;
                    break;
                }
            }
        }
        return identifier;
    }

    /**
     * muuntaa lentosuunnat 0-359 x/y-koordinaatiston arvoiksi ja palauttaa ne
     * taulukkona, jossa indeksinä on lentosuunta ja arvoina x/y-arvot.
     * Minimiarvo -90. Maksimiarvo 90.
     * <p>
     * Esimerkki 1: suunta 0 eli suoraan ylöspäin on x/y-arvoina x=0 ja y=90. X
     * saa arvon 0, koska kun mennään suoraan ylöspäin, ei paikka x-akselilla
     * muutu. Y saa arvon 90, koska se on maksimi
     * <p>
     * Esimerkki 2: suunta 270 eli suoraan vasemmalle on x/y-arvoina x=-90 ja
     * y=0. X saa arvon -90, koska mennään suoraan vasemmalle. Y saa arvon 0,
     * koska paikka Y-akselilla ei muutu.
     *
     * @return x- ja y-arvojen taulukko indeksinä lentosuunnat
     */
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
