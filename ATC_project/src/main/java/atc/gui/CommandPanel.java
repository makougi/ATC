package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayDeque;

/**
 *
 * @author Kimmo
 * <p>
 *
 * Luokka käyttäjän syötteen näyttämiseksi
 */
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

    /**
     *
     * Constructor
     *
     * @param gl GameLogic-olio
     * @param cl Tämän paneelin väri
     * @param ph Sen paneelin korkeus, jonka sisässä tämä paneeli on
     */
    public CommandPanel(GameLogic gl, Color cl, int ph) {
        gameOn = true;
        infoText = "";
        invalidCommand = false;
        color = cl;
        gameLogic = gl;

        panelHeight = ph / 3;
        panelWidth = ph / 3;

        stringY = panelHeight / 8;
        stringX = stringY / 2;

        rowLength = panelWidth / 10;
        stringQueue = new ArrayDeque();
        stringQueue.add("");

        font = new Font("Arial", Font.PLAIN, 12);

        initGamePanel();
        gl.getCommandParser().setCommandPanel(this);
    }

    /**
     *
     * @param s String-taulukko, jossa tietoja game overin aiheuttaneista
     * kahdesta lentokoneesta
     */
    public void gameOver(String[] s) {
        info = s;
        gameOn = false;
        this.repaint();
    }

    /**
     * Jos käyttäjän syöte on virheellinen
     */
    public void invalidCommand() {
        infoText = "Invalid command";
    }

    /**
     * Paneelin alustamista käsittelevä metodi
     */
    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(color);
    }

    /**
     * paintComponent
     *
     * @param g Grafiikkaolio
     */
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

    /**
     * Teksti, joka piirretään pelin päättyessä
     *
     * @param g Grafiikkaolio
     */
    private void drawGameOver(Graphics g) {
        font = new Font("Arial", Font.BOLD, 11);
        g.setFont(font);
        g.drawString("GAME OVER", 10, 20);
        g.drawString("Two aircrafts too close", 10, 40);
        g.drawString(info[0] + " and " + info[6], 10, 60);
        g.drawString("X: " + info[1] + " | " + info[7], 10, 80);
        g.drawString("Y: " + info[2] + " | " + info[8], 10, 100);
        g.drawString("Altitude: " + info[3] + " | " + info[9], 10, 120);
        g.drawString("Heading: " + info[4] + " | " + info[10], 10, 140);
        g.drawString("Speed: " + info[5] + " | " + info[11], 10, 160);
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

    /**
     * Kun käyttäjä painaa enteriä, teksti poistetaan
     */
    public void keybEnter() {
        if (gameOn) {
            stringQueue.clear();
            stringQueue.add("");
            this.repaint();
        }
    }

    /**
     * Kun käyttäjä painaa backspacea, merkki poistetaan
     */
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

    /**
     * Kun käyttäjä syöttää merkin, se tulostetaan ruudulle
     *
     * @param c käyttäjän syöttämä merkki
     */
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
