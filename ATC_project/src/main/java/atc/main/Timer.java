package atc.main;

import atc.gui.*;
import atc.logic.*;

public class Timer {

    GUIFrame guiFrame;
    GameLogic gameLogic;
    long timeA;
    int timeLength;
    int clockA;
    int clockATop;
    int clockB;
    int clockBTop;

    public Timer(GUIFrame gf, GameLogic gl) {
        guiFrame = gf;
        gameLogic = gl;
        timeLength = 10;
        clockA = 0;
        clockATop = 100;
        clockB = 0;
        clockBTop = 100;
        timerLoop();
    }

    public void timerLoop() {
        while (true) {
            timeA = System.currentTimeMillis() + timeLength;
            while (System.currentTimeMillis() < timeA) {
            }
            tick();
        }
    }

    private void tick() {
        clockA++;
        clockB++;
        if (clockA >= clockATop) {
            guiFrame.updateRadarPanel();
            guiFrame.updateInfoPanel();
            clockA = 0;
        }
        if (clockB >= clockBTop) {
            gameLogic.update();
            clockB = 0;
        }
    }
}
