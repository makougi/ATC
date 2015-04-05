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

    public CommandPanel(GameLogic gl, Color cl, int ph) {
        infoText = "";
        invalidCommand = false;
        color = cl;
        gameLogic = gl;

        panelHeight = ph / 3;
        panelWidth = panelHeight;

        stringY = panelHeight / 8;
        stringX = stringY/2;

        rowLength = panelWidth / 10;
        stringQueue = new ArrayDeque();
        stringQueue.add("");

        font = new Font("Arial", Font.BOLD, 12);

        initGamePanel();
        gl.getCommandParser().setCommandPanel(this);
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
        drawText(g);
        drawInfoText(g);

    }

    private void drawText(Graphics g) {
        int rowHeight = 0;
        g.setFont(font);
        for (String s : stringQueue) {
            g.drawString(s, stringX, stringY + rowHeight);
            rowHeight += 20;
        }
    }

    private void drawInfoText(Graphics g) {
        g.setFont(font);
        g.drawString(infoText, stringX, stringY);
    }

    public void keybEnter() {
        stringQueue.clear();
        stringQueue.add("");
        this.repaint();
    }

    public void keybBackspace() {
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

    public void keybCharacter(char c) {
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
