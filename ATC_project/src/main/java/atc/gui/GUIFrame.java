package atc.gui;

import atc.logic.Aircraft;
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
    InfoPanel1 infoPanel1;
    InfoPanel2 infoPanel2;
    KeyboardListener keyboardListener;
    int panelHeight;
    Aircraft[] aircraftsTooClose;

    public GUIFrame(GameLogic gl) {
        panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height-400;
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        radarPanel = new RadarPanel(gl,panelHeight);
        add(radarPanel);

        containerPanel = new ContainerPanel(gl,Color.gray,panelHeight);
        add(containerPanel);

        infoPanel2 = new InfoPanel2(gl,Color.lightGray,panelHeight);
        containerPanel.add(infoPanel2);

        infoPanel1 = new InfoPanel1(gl,Color.lightGray,panelHeight);
        containerPanel.add(infoPanel1);

        commandPanel = new CommandPanel(gl,Color.lightGray,panelHeight);
        containerPanel.add(commandPanel);
        
        keyboardListener = new KeyboardListener(gl.getCommandParser(),commandPanel);
        containerPanel.add(keyboardListener);

        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        gl.setGUIFrame(this);
    }
    public void gameOver(String[] s){
        commandPanel.gameOver(s);
    }
    
    public void updateInfoPanel2(){
        infoPanel2.drawUpdate();
    }
    public void updateInfoPanel1(){
        infoPanel1.drawUpdate();
    }

    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
}
