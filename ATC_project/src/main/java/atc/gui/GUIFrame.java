package atc.gui;

import javax.swing.JFrame;
import atc.logic.GameLogic;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class GUIFrame extends JFrame {

    MainPanel mainPanel;
    RadarPanel radarPanel;
    CommandPanel commandPanel;
    Dimension screenSize;
    int screenX;
    int screenY;

    public GUIFrame(GameLogic gl) {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenX = screenSize.width;
        screenY = screenSize.height;

        mainPanel = new MainPanel();
        add(mainPanel);

        mainPanel.setLayout(new BorderLayout());

        commandPanel = new CommandPanel(gl);
        mainPanel.add(commandPanel, BorderLayout.LINE_END);

        radarPanel = new RadarPanel(gl);
        mainPanel.add(radarPanel, BorderLayout.LINE_START);

        pack();
        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
    }

    public void updateRadarPanel() {
        radarPanel.drawUpdate();
    }
    public int getScreenX(){
        return screenX;
    }
    public int getScreenY(){
        return screenY;
    }
}
