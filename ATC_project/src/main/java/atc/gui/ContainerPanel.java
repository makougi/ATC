package atc.gui;

import javax.swing.JPanel;
import java.awt.Graphics;

import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

/**
 *
 * @author Kimmo
 * <p>
 * Luokka paneelia varten, jonka sisään tulevat pienemmät paneelit
 */
public class ContainerPanel extends JPanel {

    private GameLogic gameLogic;
    private int panelWidth;
    private int panelHeight;
    private Color color;

    /**
     * Konstruktori
     *
     * @param gl GameLogic-olio
     * @param cl tämän paneelin haluttu väri
     * @param ph tämän paneelin haluttu korkeus
     */
    public ContainerPanel(GameLogic gl, Color cl, int ph) {
        color = cl;
        gameLogic = gl;

        panelHeight = ph;
        panelWidth = panelHeight / 3;

        initGamePanel();
    }

    private void initGamePanel() {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(color);
    }
}
