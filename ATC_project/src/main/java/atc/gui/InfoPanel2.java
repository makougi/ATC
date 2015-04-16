package atc.gui;

import atc.logic.Aircraft;
import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class InfoPanel2 extends JPanel {

    GameLogic gl;
    Aircraft a;
    private int panelWidth;
    private int panelHeight;
    private Font font;

    public InfoPanel2(GameLogic gameLogic, Color c, int ph) {
        font = new Font("Courier", Font.BOLD, 10);
        gl = gameLogic;

        panelHeight = ph / 3;
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
        g.setFont(font);
        int textX = 10;
        int textY = 20;
        int rowHeight = 14;
        int rowCounter = 2;
        String eta;
        String[] entryPoint = {"NW1", "N  ", "E  ", "NW2"};
        g.drawString("LANDED " + gl.getLanded() + "  FAILED " + gl.getLost(), textX, textY + (rowHeight * 0));
        g.drawString("ETA   ID       ENTRY   ALTITUDE", textX, textY + (rowHeight * 2));
        for (int[] flight : gl.getSchedule()) {
            rowCounter++;
            if ((flight[1] - gl.getScheduleClock()) / 10 * 10 >= 10) {
                eta = "" + (flight[1] - gl.getScheduleClock()) / 10 * 10;
            } else {
                eta = "0" + (flight[1] - gl.getScheduleClock()) / 10 * 10;
            }
            g.drawString(eta + "    " + gl.getIdentifier(flight[0]) + "   " + entryPoint[flight[2]] + "     FL" + flight[3], textX, textY + (rowHeight * rowCounter));
            if (panelHeight - (textY + (rowHeight * rowCounter)) < 20) {
                break;
            }
        }
    }

    public void drawUpdate() {
        this.repaint();
    }
}
