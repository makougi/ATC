package old.Main;

import old.Logic.GameLogic;
import old.Panel.GamePanel;
import javax.swing.JFrame;
import java.awt.EventQueue;

public class Main extends JFrame {
    
    public Main() {
        initUI();
    }

    public void initUI() {
        GameLogic gameLogic = new GameLogic();
        add(new GamePanel(gameLogic));//luo uusi paneeli ja anna sille gamelogic josta saa arraylistin lentokoneista

        pack();

        setTitle("ATC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                Main ex = new Main();
//                ex.setVisible(true);
//            }
//        });
//    }
}
