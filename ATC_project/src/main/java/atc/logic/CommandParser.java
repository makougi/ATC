package atc.logic;

import java.util.ArrayDeque;
import atc.gui.CommandPanel;

public class CommandParser {

    private ArrayDeque<Character> input;
    private ArrayDeque<ArrayDeque<Character>> temp;
    private CommandPanel commandPanel;
    private String identifier;
    private Boolean invalidCommand;
    private GameLogic gameLogic;
    private Aircraft aircraft;
    private char[] twoChar;//flight level
    private char[] threeChar;//heading
    private char[] fourChar;//speed

    public CommandParser(GameLogic gl) {
        twoChar = new char[2];
        threeChar = new char[3];
        fourChar = new char[4];
        gameLogic = gl;
        input = new ArrayDeque();
        temp = new ArrayDeque();
    }

    public void setCommandPanel(CommandPanel cp) {
        commandPanel = cp;
    }

    private void parseCommands() {
        while (!temp.isEmpty()) {
            if (temp.peekFirst().size() == 2) {
                for (int i = 0; i < 2; i++) {
                    twoChar[i] = temp.peekFirst().remove();
                }
                if (Character.isDigit(twoChar[0]) && Character.isDigit(twoChar[1])) {
                    aircraft.setAltitudeCommand(twoChar);
                }
            } else if (temp.peekFirst().size() == 3) {
                for (int i = 0; i < 3; i++) {
                    threeChar[i] = temp.peekFirst().remove();
                }
                if (Character.isDigit(threeChar[0]) && Character.isDigit(threeChar[1]) && Character.isDigit(threeChar[2])) {
                    aircraft.setHeadingCommand(threeChar);
                }
            } else if (temp.peekFirst().size() == 4) {
                for (int i = 0; i < 4; i++) {
                    fourChar[i] = temp.peekFirst().remove();
                }
                if ((Character.isDigit(fourChar[0])||fourChar[0]=='S') && Character.isDigit(fourChar[1]) && Character.isDigit(fourChar[2]) && Character.isDigit(fourChar[3])) {
                    aircraft.setSpeedCommand(fourChar);
                }
            }
            temp.remove();
        }
    }

    private void parseIdentifier() {
        for (Character c : temp.peekFirst()) {
            identifier += c;
        }
        temp.removeFirst();
        if (identifier.length() != 6) {
            commandPanel.invalidCommand();
            return;
        }
        for (Aircraft a : gameLogic.getAircrafts()) {
            if (a.getIdentifier().equals(identifier)) {
                aircraft = a;
            }
        }
        if (aircraft == null) {
            commandPanel.invalidCommand();
            return;
        }
        parseCommands();
    }

    private void initiateCommandParsing() {
        aircraft = null;
        identifier = "";

        parseIdentifier();
    }

    private void parseInput() {
        temp.clear();
        temp.add(new ArrayDeque());
        for (Character c : input) {
            if (c == ' ') {
                temp.add(new ArrayDeque());
            } else {
                temp.peekLast().add(c);
            }
        }
        input.clear();
        initiateCommandParsing();
    }

    public void keybEnter() {
        parseInput();
    }

    public void keybBackspace() {
        input.pollLast();
    }

    public void keybCharacter(char c) {
        input.add(c);
    }
}
