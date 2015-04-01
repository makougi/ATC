package atc.gui;

import javax.swing.JFrame;
import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class GUIFrame extends JFrame {

    RadarPanel radarPanel;
    ContainerPanel containerPanel;
    CommandPanel commandPanel;
    InfoPanel infoPanel1;
    InfoPanel infoPanel2;

    public GUIFrame(GameLogic gl) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        radarPanel = new RadarPanel(gl);
        add(radarPanel);

        containerPanel = new ContainerPanel(gl);
        add(containerPanel);

        infoPanel1 = new InfoPanel(gl,Color.cyan);
        containerPanel.add(infoPanel1);

        infoPanel2 = new InfoPanel(gl,Color.pink);
        containerPanel.add(infoPanel2);

        commandPanel = new CommandPanel(gl);
        containerPanel.add(commandPanel);

        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
}
