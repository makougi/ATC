package atc.logic;

import java.util.ArrayList;

public class GameLogic {

    Aircraft aircraft;
    ArrayList<Aircraft> aircrafts;

    public GameLogic() {
        aircrafts = new ArrayList();

        for (int i = 0; i < 30; i++) {
            aircrafts.add(new Aircraft());
        }
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
