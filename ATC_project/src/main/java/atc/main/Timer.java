package atc.main;

import atc.gui.*;
import atc.logic.*;
import java.util.Random;

/**
 *
 * @author Kimmo
 * <p>
 * Luokan tehtävänä on tarjota pelilogiikalle kello, jonka perusteella
 * toimintoja voidaan aikatauluttaa.
 */
public class Timer {

    private GUIFrame guiFrame;
    private GameLogic gameLogic;
    private long timeA;
    private int timeLength;
    private int clockA;
    private int clockATop;
    private int clockB;
    private int clockBTop;
    private int clockC;
    private int clockCTop;
    private Boolean gameOn;

    /**
     * konstruktori
     *
     * @param gf GUIFrame-olio
     * @param gl GameLogic-olio
     */
    public Timer(GUIFrame gf, GameLogic gl) {
        guiFrame = gf;
        gameLogic = gl;
        timeLength = 100;
        clockA = 0;
        clockATop = 10;
        clockB = 0;
        clockBTop = 10;
        clockC = 0;
        clockCTop = 10;
        gameOn = true;
        gameLogic.setTimer(this);
        timerLoop();
    }

    /**
     * metodi pysäyttää kellon
     */
    public void stop() {
        gameOn = false;
    }

    private void timerLoop() {
        while (gameOn) {
            timeA = System.currentTimeMillis() + timeLength;
            while (System.currentTimeMillis() < timeA) {
            }
            tick();
        }
    }

    private void tick() {
        clockA++;
        clockB++;
        clockC++;
        if (clockA >= clockATop) {
            guiFrame.updateRadarPanel();
            guiFrame.updateInfoPanel1();
            clockA = 0;
        }
        if (clockB >= clockBTop) {
            gameLogic.update();
            clockB = 0;
        }
        if (clockC >= clockCTop) {
            gameLogic.updateScheduleClock();
            clockC = 0;
        }
    }
}
