package Logic;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

import Panel.*;

public class GameLogic {

    Aircraft aircraft;
    ArrayList<Aircraft> aircrafts;
    GamePanel gamePanel;

    public GameLogic() {
        aircrafts = new ArrayList();

        for (int i = 0; i < 3; i++) {
            aircraft = new Aircraft();
            aircrafts.add(aircraft);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1); //taimeri tutkakuvan päivitystä varten
        executor.scheduleAtFixedRate(radar, 0, 3, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(move, 0, 10, TimeUnit.MILLISECONDS);
    }
    
    public void setGamePanel(GamePanel gp){
        gamePanel = gp;
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }

    Runnable radar = new Runnable() {//päivittää tutkakuvan
        @Override
        public void run() {
            for (Aircraft a : aircrafts) {
                a.printRadarInfo();
            }

            Dimension reso = Toolkit.getDefaultToolkit().getScreenSize();
            Double resoH = reso.getHeight();
            Double resoW = reso.getWidth();
            String resoS = Double.toHexString(resoW) + " * " + Double.toString(resoH);
            System.out.println(resoS);
            //TÄHÄN REPAINT
        }
    };
    Runnable move = new Runnable() {//liikuttaa lentokoneita
        @Override
        public void run() {
            for (Aircraft a : aircrafts) {
                a.fly();
            }
        }
    };

}
