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

    public CommandPanel(GameLogic gl) {
        gameLogic = gl;

        panelHeight = (Toolkit.getDefaultToolkit().getScreenSize().height - 100) / 3;
        panelWidth = panelHeight;

        stringY = panelHeight / 10;
        stringX = stringY;

        rowLength = panelWidth / 10;
        stringQueue = new ArrayDeque();
        stringQueue.add("");

        font = new Font("Arial", Font.BOLD, 12);

        initGamePanel();
    }

    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.lightGray);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawText(g);
    }

    private void drawText(Graphics g) {
        int rowHeight = 0;
        g.setFont(font);
        for (String s : stringQueue) {
            g.drawString(s, stringX, stringY + rowHeight);
            rowHeight += 20;
        }
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
        if (stringQueue.peekLast().length() >= rowLength) {
            stringQueue.add("" + c);
        } else {
            String s = stringQueue.removeLast() + c;
            stringQueue.add(s);
            this.repaint();
        }
    }
}
