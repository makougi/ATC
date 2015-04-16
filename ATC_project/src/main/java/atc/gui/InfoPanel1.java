package atc.gui;

import atc.logic.Aircraft;
import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class InfoPanel1 extends JPanel {

    GameLogic gl;
    Aircraft a;
    private int panelWidth;
    private int panelHeight;
    private Font font;
    private int mode;

    public InfoPanel1(GameLogic gameLogic, Color c, int ph) {
        mode = 0;
        font = new Font("Arial", Font.PLAIN, 12);
        gl = gameLogic;

        panelHeight = ph / 3;
        panelWidth = ph / 3;

        initGamePanel(c);
    }

    public void setMode(int i) {
        mode = i;
    }

    private void initGamePanel(Color c) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(c);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (mode == 0) {
            drawAircraftInfo(g);
        } else {
            drawListAll(g);
        }

    }

    private void drawListAll(Graphics g) {
        Font font2 = new Font("Courier", Font.BOLD, 10);
        g.setFont(font2);
        int row = 0;
        int column = 0;
        int height = 16;
        g.drawString("AIRCRAFTS ON THE SCREEN", 10, 20);
        for (Aircraft a : gl.getAircrafts()) {
            g.drawString(a.getIdentifier() + " x" + a.getX() / 10000 + " y" + a.getY() / 10000, 10 + column, 36 + row);
            if (column == 0) {
                column = 100;
            } else {
                column = 0;
                row += height;
            }
        }
    }

    private void drawAircraftInfo(Graphics g) {
        g.setFont(font);
        a = gl.getCommandParser().getAircraft();
        int textX = 10;
        int textY = 20;
        int rowHeight = 20;
        if (a != null) {
            drawIdentifier(g, textX, textY, rowHeight);
            if (a.getMode() != 2) {
                drawSpeed(g, textX, textY, rowHeight);
                drawHeading(g, textX, textY, rowHeight);
                drawAltitude(g, textX, textY, rowHeight);
                if (a.getMode() == 1) {
                    drawLanding(g, textX, textY, rowHeight);
                }
            } else {
                drawLanded(g, textX, textY, rowHeight);
            }
        }
    }

    private void drawIdentifier(Graphics g, int textX, int textY, int rowHeight) {
        g.drawString(("Identifier: " + a.getIdentifier()).toUpperCase(), textX, textY + (rowHeight * 0));
    }

    private void drawSpeed(Graphics g, int textX, int textY, int rowHeight) {
        if (a.getSpeed() != a.getSpeedCommand()) {
            g.drawString(("Speed: " + a.getSpeed() + " kts >>> " + a.getSpeedCommand() + " kts").toUpperCase(), textX, textY + (rowHeight * 1));
        } else {
            g.drawString(("Speed: " + a.getSpeed() + " kts = " + a.getSpeedCommand() + " kts").toUpperCase(), textX, textY + (rowHeight * 1));
        }
    }

    private void drawHeading(Graphics g, int textX, int textY, int rowHeight) {
        int h = a.getHeading();
        int hc = a.getHeadingCommand();
        String heading;
        String headingCommand;
        if (h >= 100) {
            heading = "" + h;
        } else if (h >= 10) {
            heading = "0" + h;
        } else {
            heading = "00" + h;
        }
        if (hc >= 100) {
            headingCommand = "" + hc;
        } else if (hc >= 10) {
            headingCommand = "0" + hc;
        } else {
            headingCommand = "00" + hc;
        }
        if (h == hc) {
            g.drawString(("Heading: " + heading + " = " + headingCommand).toUpperCase(), textX, textY + (rowHeight * 2));
        } else {
            g.drawString(("Heading: " + heading + " >>> " + headingCommand).toUpperCase(), textX, textY + (rowHeight * 2));
        }
    }

    private void drawAltitude(Graphics g, int textX, int textY, int fontHeight) {
        if (a.getZ() != a.getZCommand()) {
            g.drawString(("Altitude: FL" + a.getZ() + " >>> FL" + a.getZCommand()).toUpperCase(), textX, textY + (fontHeight * 3));
        } else {
            g.drawString(("Altitude: FL" + a.getZ() + " = FL" + a.getZCommand()).toUpperCase(), textX, textY + (fontHeight * 3));
        }
    }

    private void drawLanding(Graphics g, int textX, int textY, int fontHeight) {
        g.drawString("--LANDING--", textX, textY + (fontHeight * 4));
    }

    private void drawLanded(Graphics g, int textX, int textY, int fontHeight) {
        g.drawString("LANDED", textX, textY + (fontHeight * 1));
    }

    public void drawUpdate() {
        this.repaint();
    }
}
