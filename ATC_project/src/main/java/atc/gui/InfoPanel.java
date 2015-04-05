package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class InfoPanel extends JPanel {

    GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;

    public InfoPanel(GameLogic gl, Color c,int ph) {
        gameLogic = gl;

        panelHeight = ph/3;
        panelWidth = panelHeight;

        initGamePanel(c);
    }

    private void initGamePanel(Color c) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(c);
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
