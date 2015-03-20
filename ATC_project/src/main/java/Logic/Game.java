package Logic;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Game {

    Aircraft aircraft;
    ArrayList<Aircraft> aircrafts;

    public Game() {
        aircrafts = new ArrayList();

        for (int i = 0; i < 3; i++) {
            aircraft = new Aircraft();
            aircrafts.add(aircraft);
        }

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1); //taimeri tutkakuvan päivitystä varten
        executor.scheduleAtFixedRate(radar, 0, 3, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(move, 0, 100, TimeUnit.MILLISECONDS);

    }
    Runnable radar = new Runnable() {//päivittää tutkakuvan
        public void run() {
            for (Aircraft a : aircrafts) {
                a.printRadarInfo();
            }
        }
    };
    Runnable move = new Runnable() {//liikuttaa lentokoneita
        public void run() {
            for (Aircraft a : aircrafts) {
                a.fly();
            }
        }
    };

}
