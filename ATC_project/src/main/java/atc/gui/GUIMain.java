package atc.gui;

import javax.swing.JFrame;
import atc.logic.GameLogic;
import atc.gui.RadarPanel;

public class GUIMain extends JFrame {

    RadarPanel radarPanel;

    public GUIMain(GameLogic gl) {
        radarPanel = new RadarPanel(gl);
        add(radarPanel);
        
        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
}
