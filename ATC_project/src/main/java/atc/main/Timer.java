package atc.main;

import atc.gui.*;
import atc.logic.*;
import java.util.Random;

public class Timer {

    GUIFrame guiFrame;
    GameLogic gameLogic;
    long timeA;
    int timeLength;
    int clockA;
    int clockATop;
    int clockB;
    int clockBTop;
    int clockC;
    int clockCTop;
    Boolean gameOn;

    public Timer(GUIFrame gf, GameLogic gl) {
        guiFrame = gf;
        gameLogic = gl;
        timeLength = 10;
        clockA = 0;
        clockATop = 100;
        clockB = 0;
        clockBTop = 100;
        clockC = 0;
        clockCTop = 100;
        gameOn = true;
        gl.setTimer(this);
        timerLoop();
    }
    public void stop(){
        gameOn = false;
    }
    
    public void timerLoop() {
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
        if (clockC >= clockCTop){
            gameLogic.updateScheduleClock();
            clockC = 0;
        }
    }
}
