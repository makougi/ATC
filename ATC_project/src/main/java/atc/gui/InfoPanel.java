package atc.gui;

import atc.logic.Aircraft;
import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class InfoPanel extends JPanel {

    GameLogic gl;
    Aircraft a;
    private int panelWidth;
    private int panelHeight;
    private Font font;

    public InfoPanel(GameLogic gameLogic, Color c, int ph) {
        font = new Font("Arial", Font.BOLD, 14);
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
        a = gl.getCommandParser().getAircraft();
        int textX = 10;
        int textY = 20;
        int rowHeight = 20;
        if (a != null) {
            drawIdentifier(g, textX, textY, rowHeight);
            drawSpeed(g, textX, textY, rowHeight);
            drawHeading(g, textX, textY, rowHeight);
            drawAltitude(g, textX, textY, rowHeight);
        }
    }

    private void drawIdentifier(Graphics g, int textX, int textY, int rowHeight) {
        g.drawString(("Identifier: "+a.getIdentifier()).toUpperCase(), textX, textY + (rowHeight * 0));
    }

    private void drawSpeed(Graphics g, int textX, int textY, int rowHeight) {
        if (a.getSpeed() != a.getSpeedCommand()) {
            g.drawString(("Speed: " + a.getSpeed() + " kts >>> " + a.getSpeedCommand() + " kts").toUpperCase(), textX, textY + (rowHeight * 1));
        } else {
            g.drawString(("Speed: " + a.getSpeed() + " kts = " + a.getSpeedCommand() + " kts").toUpperCase(), textX, textY + (rowHeight * 1));
        }
    }

    private void drawHeading(Graphics g, int textX, int textY, int rowHeight) {
        if (a.getHeading() != a.getHeadingCommand()) {
            if (a.getHeading() >= 100) {
                g.drawString(("Heading: " + a.getHeading() + " >>> " + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            } else if (a.getHeading() >= 10) {
                g.drawString(("Heading: 0" + a.getHeading() + " >>> 0" + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            } else {
                g.drawString(("Heading: 00" + a.getHeading() + " >>> 00" + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            }
        } else {
            if (a.getHeading() >= 100) {
                g.drawString(("Heading: " + a.getHeading() + " = " + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            } else if (a.getHeading() >= 10) {
                g.drawString(("Heading: 0" + a.getHeading() + " = 0" + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            } else {
                g.drawString(("Heading: 00" + a.getHeading() + " = 00" + a.getHeadingCommand()).toUpperCase(), textX, textY + (rowHeight * 2));
            }
        }
    }

    private void drawAltitude(Graphics g, int textX, int textY, int fontHeight) {
        if (a.getZ() != a.getZCommand()) {
            g.drawString(("Altitude: FL" + a.getZ() + " >>> FL" + a.getZCommand()).toUpperCase(), textX, textY + (fontHeight * 3));
        } else {
            g.drawString(("Altitude: FL" + a.getZ() + " = FL" + a.getZCommand()).toUpperCase(), textX, textY + (fontHeight * 3));
        }
    }

    public void drawUpdate() {
        this.repaint();
    }
}
