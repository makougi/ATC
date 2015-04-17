package atc.gui;

import atc.logic.Aircraft;
import atc.logic.GameLogic;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Kimmo
 * <p>
 * Luokka vastaa tutkakuvan näyttämisestä
 */
public class RadarPanel extends JPanel {

    private Image square;
    private Image squareHistory;
    private Image whiteDot;
    private int panelWidth;
    private int panelHeight;
    private int imageSize;
    private int zoom;
    private ArrayList<Aircraft> aircrafts;

    private int aX;
    private int aY;
    private int aZ;
    private int aS;
    private int aH;
    private String aIdentifier;
    private int[][] aHistory;

    private int fontScale;
    private GameLogic gl;

    /**
     * konstruktori
     *
     * @param gameLogic gamelogic-olio
     * @param ph haluttu paneelin korkeus
     */
    public RadarPanel(GameLogic gameLogic, int ph) {
        gl = gameLogic;
        panelHeight = ph;
        panelWidth = panelHeight;
        aircrafts = gameLogic.getAircrafts();//otetaan gamelogicista arraylist lentokoneista, jota käytetään niiden piirtämiseen
        imageSize = 4;
        zoom = gl.getZoom();
        fontScale = 4;
        initGamePanel();
    }

    private void initGamePanel() {
        loadImage();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        gl.setRadarPanel(this);
        setBackground(Color.black);
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("Square.png");
        square = ii.getImage();
        ii = new ImageIcon("SquareHistory.png");
        squareHistory = ii.getImage();
        ii = new ImageIcon("WhiteDot.png");
        whiteDot = ii.getImage();
    }

    /**
     * paintcomponent
     *
     * @param g grafiikkaolio
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAircrafts(g);
        drawRunway(g);
    }

    private void drawRunway(Graphics g) {
        int pos = 100000 / zoom;
        g.drawImage(whiteDot, gl.getRunwayPosition()[0], gl.getRunwayPosition()[1], imageSize / 2 + 1, imageSize / 2 + 1, this);
        for (int i = 0; i < 10; i++) {
            g.drawImage(whiteDot, gl.getRunwayPosition()[0], gl.getRunwayPosition()[1] + pos, imageSize / 2 + 1, imageSize / 2 + 1, this);
            pos += 10000 / zoom;
        }
    }

    private void drawAircrafts(Graphics g) {
        for (Aircraft a : aircrafts) {
//        for (Iterator<Aircraft> it = aircrafts.iterator(); it.hasNext();){
//            Aircraft a = it.next();

            aX = a.getX() / zoom;
            aY = a.getY() / zoom;
            aZ = a.getZ();
            aS = a.getSpeed();
            aH = a.getHeading();
            aIdentifier = a.getIdentifier();
            aHistory = a.getHistory();
            g.setColor(Color.green);
            g.drawImage(square, aX, aY, imageSize, imageSize, this);
            for (int i = 0; i < aHistory.length; i++) {
                g.drawImage(squareHistory, (aHistory[i][0] / zoom) + ((imageSize - 1) / 2), aHistory[i][1] / zoom, imageSize / 2, imageSize / 2, this);
            }
            g.drawString(aIdentifier, aX - (fontScale * 4), aY + (fontScale * 5));
            if (a.getMode() != 1) {
                if (aH >= 100) {
                    g.drawString("" + aH, aX - (fontScale * 2), aY + (fontScale * 8));
                } else if (aH >= 10) {
                    g.drawString("0" + aH, aX - (fontScale * 2), aY + (fontScale * 8));
                } else {
                    g.drawString("00" + aH, aX - (fontScale * 2), aY + (fontScale * 8));
                }
            } else {
                g.setColor(Color.yellow);
                g.drawString("L", aX, aY + (fontScale * 8));
                g.setColor(Color.green);
            }
            g.drawString("" + aZ, aX - (fontScale * 2), aY + (fontScale * 11));
        }
    }

    /**
     * metodi päivittää tutkakuvan
     */
    public void drawUpdate() {
        this.repaint();
    }
}
