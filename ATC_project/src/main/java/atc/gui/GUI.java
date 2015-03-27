package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;

public class GUI extends JPanel {

    GameLogic gameLogic;

    private int timerTickCalculator;
    private int updateRatio;

    public GUI(GameLogic gl) {
        gameLogic = gl;
        timerTickCalculator = 0;
        updateRatio = 4;
    }

    public void Run() {

    }

    public void timerTick() {
        timerTickCalculator++;
        if (timerTickCalculator >= updateRatio) {
            update();
            timerTickCalculator = 0;
        }
    }

    private void update() {

    }
}
