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

public class RadarPanel extends JPanel {

    private Image square;
    private int panelWidth;
    private int panelHeight;
    private int imageSize;
    private int zoom;//muuttuja näkymän zoomaamista varten
    private ArrayList<Aircraft> aircrafts;
    
    private int aX;
    private int aY;
    private int aZ;
    private String aName;
    
    private int fontScale;
    

    public RadarPanel(GameLogic gameLogic) {
        aircrafts = gameLogic.getAircrafts();//otetaan gamelogicista arraylist lentokoneista, jota käytetään niiden piirtämiseen
        panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 100;
        panelWidth = panelHeight;
        imageSize = 3;
        zoom = 1;
        fontScale = 4;

        initGamePanel();
    }

    private void initGamePanel() {
        loadImage();
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.black);
    }

    private void loadImage() {
        ImageIcon ii = new ImageIcon("Square.png");
        square = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawAircrafts(g);
    }

    private void drawAircrafts(Graphics g) {
        for (Aircraft a : aircrafts) {
            aX=a.getX();
            aY=a.getY();
            aZ=a.getZ();
            aName=a.getName();
            g.setColor(Color.yellow);
            g.drawImage(square, aX / zoom, aY / zoom, imageSize, imageSize, this);
            g.drawString(aName, aX-(fontScale*3), aY+(fontScale*5));
            g.drawString(""+aZ, aX-(fontScale*3), aY+(fontScale*8));
        }
    }

    public void drawUpdate() {
        this.repaint();
    }
}
