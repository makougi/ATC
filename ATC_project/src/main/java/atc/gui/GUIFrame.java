package atc.gui;

import javax.swing.JFrame;
import atc.logic.GameLogic;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

public class GUIFrame extends JFrame {

    RadarPanel radarPanel;
    ContainerPanel containerPanel;
    CommandPanel commandPanel;
    InfoPanel infoPanel1;
    InfoPanel infoPanel2;
    KeyboardListener keyboardListener;
    int panelHeight;

    public GUIFrame(GameLogic gl) {
        panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height-200;
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        radarPanel = new RadarPanel(gl,panelHeight);
        add(radarPanel);

        containerPanel = new ContainerPanel(gl,Color.yellow,panelHeight);
        add(containerPanel);

        infoPanel1 = new InfoPanel(gl,Color.cyan,panelHeight);
        containerPanel.add(infoPanel1);

        infoPanel2 = new InfoPanel(gl,Color.pink,panelHeight);
        containerPanel.add(infoPanel2);

        commandPanel = new CommandPanel(gl,Color.lightGray,panelHeight);
        containerPanel.add(commandPanel);
        
        keyboardListener = new KeyboardListener(gl.getCommandParser(),commandPanel);
        containerPanel.add(keyboardListener);

        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
}
