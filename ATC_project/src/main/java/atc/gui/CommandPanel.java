package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.Toolkit;

public class CommandPanel extends JPanel {

    GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;

    public CommandPanel(GameLogic gl) {
        gameLogic = gl;

        panelHeight = (Toolkit.getDefaultToolkit().getScreenSize().height-100)/3;
        panelWidth = panelHeight;

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
