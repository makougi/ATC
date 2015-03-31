package atc.gui;

import atc.logic.Aircraft;
import atc.logic.GameLogic;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;

import java.util.ArrayList;

public class RadarPanel extends JPanel {

    private Image square;
    private int panelWidth;
    private int panelHeight;
    private int imageSize;
    private int zoom;//muuttuja näkymän zoomaamista varten
    private ArrayList<Aircraft> aircrafts;

    public RadarPanel(GameLogic gameLogic) {
        aircrafts = gameLogic.getAircrafts();//otetaan gamelogicista arraylist lentokoneista, jota käytetään niiden piirtämiseen
        panelWidth = 500;
        panelHeight = 500;
        imageSize = 20;
        zoom = 1;

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
            g.drawImage(square, a.getX() / zoom, a.getY() / zoom, imageSize, imageSize, this);
        }
    }
    public void drawUpdate(){
        this.repaint();
    }
}
