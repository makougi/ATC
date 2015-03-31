package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;

public class CommandPanel extends JPanel {

    GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;

    public CommandPanel(GameLogic gl) {
        gameLogic = gl;

        panelWidth = 100;
        panelHeight = 100;
        
        initGamePanel();
    }

    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.red);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawSomething(g);
    }

    private void drawSomething(Graphics g) {

    }

    public void drawUpdate() {
        this.repaint();
    }
}
