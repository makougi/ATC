package atc.logic;

import java.util.ArrayList;

public class GameLogic {

    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private CommandParser commandParser;

    public GameLogic() {

        aircrafts = new ArrayList();

        for (int i = 0; i < 30; i++) {
            aircrafts.add(new Aircraft());
        }
        commandParser = new CommandParser(this);

    }

    public CommandParser getCommandParser() {
        return commandParser;
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
