package atc.gui;

import atc.logic.Aircraft;
import javax.swing.JFrame;
import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

/**
 *
 * @author Kimmo
 * <p>
 * Luokka pelin framea varten
 */
public class GUIFrame extends JFrame {

    RadarPanel radarPanel;
    ContainerPanel containerPanel;
    CommandPanel commandPanel;
    InfoPanel1 infoPanel1;
    InfoPanel2 infoPanel2;
    KeyboardListener keyboardListener;
    int panelHeight;
    Aircraft[] aircraftsTooClose;

    /**
     * Konstruktori
     *
     * @param gl GameLogic-olio
     */
    public GUIFrame(GameLogic gl) {
        panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height - 100;

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        radarPanel = new RadarPanel(gl, panelHeight);
        add(radarPanel);

        containerPanel = new ContainerPanel(gl, Color.gray, panelHeight);
        add(containerPanel);

        infoPanel2 = new InfoPanel2(gl, Color.lightGray, panelHeight);
        containerPanel.add(infoPanel2);

        infoPanel1 = new InfoPanel1(gl, Color.lightGray, panelHeight);
        containerPanel.add(infoPanel1);

        commandPanel = new CommandPanel(gl, Color.lightGray, panelHeight);
        containerPanel.add(commandPanel);

        keyboardListener = new KeyboardListener(gl.getCommandParser(), commandPanel, infoPanel1);
        containerPanel.add(keyboardListener);

        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        gl.setGUIFrame(this);
    }

    /**
     * pelin loppumista käsittelevä metodi
     *
     * @param s String-taulukko, jossa tietoa kahdesta game overin
     * aiheuttaneesta lentokoneesta
     */
    public void gameOver(String[] s) {
        commandPanel.gameOver(s);
    }

    /**
     * metodi infoPanel2:n päivittämistä varten
     */
    public void updateInfoPanel2() {
        infoPanel2.drawUpdate();
    }

    /**
     * metodi infoPanel1:n päivittämistä varten
     */
    public void updateInfoPanel1() {
        infoPanel1.drawUpdate();
    }

    /**
     * metodi radarPanelin päivittämistä varten
     */
    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
}
