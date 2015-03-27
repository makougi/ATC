package atc.logic;

import atc.logic.Aircraft;
import java.util.ArrayList;

public class GameLogic {

    Aircraft aircraft;
    ArrayList<Aircraft> aircrafts;

    public GameLogic() {
        aircrafts = new ArrayList();

//        for (int i = 0; i < 3; i++) {
//            aircraft = new Aircraft();
//            aircrafts.add(aircraft);
//        }
    }

    public void update() {
        moveAircrafts();
    }

    private void moveAircrafts() {
        for (Aircraft a : aircrafts) {
            a.move();
        }
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }
}
