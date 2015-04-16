package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayDeque;

public class CommandPanel extends JPanel {

    GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;
    private int stringX;
    private int stringY;
    private ArrayDeque<String> stringQueue;
    private int rowLength;
    private Font font;
    private Color color;
    private Boolean invalidCommand;
    private String infoText;
    private Boolean gameOn;
    private String[] info;

    public CommandPanel(GameLogic gl, Color cl, int ph) {
        gameOn = true;
        infoText = "";
        invalidCommand = false;
        color = cl;
        gameLogic = gl;

        panelHeight = ph / 3;
        panelWidth = panelHeight;

        stringY = panelHeight / 8;
        stringX = stringY / 2;

        rowLength = panelWidth / 10;
        stringQueue = new ArrayDeque();
        stringQueue.add("");

        font = new Font("Arial", Font.PLAIN, 12);

        initGamePanel();
        gl.getCommandParser().setCommandPanel(this);
    }

    public void gameOver(String[] s) {
        info = s;
        gameOn = false;
        this.repaint();
    }

    public void invalidCommand() {
        infoText = "Invalid command";
    }

    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(color);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (gameOn) {
            drawText(g);
            drawInfoText(g);
        } else {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {
        g.drawString("GAME OVER", 10, 20);
        g.drawString("Two aircrafts too close", 10, 40);
        g.drawString(info[0]+" and "+info[6], 10, 60);
        g.drawString("X: "+info[1]+" | "+info[7], 10, 60);
        g.drawString("Y: "+info[2]+" | "+info[8], 10, 80);
        g.drawString("Altitude: "+info[3]+" | "+info[9], 10, 100);
        g.drawString("Heading: "+info[4]+" | "+info[10], 10, 120);
        g.drawString("Speed: "+info[5]+" | "+info[11], 10, 140);
    }

    private void drawText(Graphics g) {
        if (gameOn) {
            int rowHeight = 0;
            g.setFont(font);
            for (String s : stringQueue) {
                g.drawString(s, stringX, stringY + rowHeight);
                rowHeight += 20;
            }
        }
    }

    private void drawInfoText(Graphics g) {
        if (gameOn) {
            g.setFont(font);
            g.drawString(infoText, stringX, stringY);
        }
    }

    public void keybEnter() {
        if (gameOn) {
            stringQueue.clear();
            stringQueue.add("");
            this.repaint();
        }
    }

    public void keybBackspace() {
        if (gameOn) {
            if (stringQueue.size() == 1 && stringQueue.peekFirst().length() == 0) {//if empty
                //do nothing
            } else if (stringQueue.size() == 1 && stringQueue.peekFirst().length() == 1) {
                stringQueue.clear();
                stringQueue.add("");
            } else if (stringQueue.peekLast().length() == 1) {
                stringQueue.removeLast();
            } else {
                String s = stringQueue.peekLast().substring(0, stringQueue.peekLast().length() - 1);
                stringQueue.removeLast();
                stringQueue.add(s);
            }
            this.repaint();
        }
    }

    public void keybCharacter(char c) {
        if (gameOn) {
            if (infoText.length() != 0) {
                infoText = "";
            }
            if (stringQueue.peekLast().length() >= rowLength) {
                stringQueue.add("" + c);
            } else {
                String s = stringQueue.removeLast() + c;
                stringQueue.add(s);
                this.repaint();
            }
        }
    }
}
