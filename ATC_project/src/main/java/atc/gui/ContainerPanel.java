package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ContainerPanel extends JPanel {

    GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;
    private Color color;

    public ContainerPanel(GameLogic gl,Color cl, int ph) {
        color = cl;
        gameLogic = gl;

        panelHeight = ph;
        panelWidth = panelHeight/3;

        initGamePanel();
    }

    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(color);
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
