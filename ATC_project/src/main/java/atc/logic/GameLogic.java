package atc.logic;

import java.util.ArrayList;

public class GameLogic {

    private Aircraft aircraft;
    private ArrayList<Aircraft> aircrafts;
    private CommandParser commandParser;

    public GameLogic() {

        aircrafts = new ArrayList();

        for (int i = 0; i < 1; i++) {
            aircrafts.add(new Aircraft(this));
        }
        commandParser = new CommandParser(this);

    }

    public CommandParser getCommandParser() {
        return commandParser;
    }

    public void update() {
        updateAircrafts();
        System.out.println(this.getAircrafts().get(0).getHeading());
    }

    private void updateAircrafts() {
        for (Aircraft a : aircrafts) {
            a.update();
        }
    }

    public ArrayList<Aircraft> getAircrafts() {
        return aircrafts;
    }
}
