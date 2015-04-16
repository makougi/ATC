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
    private char[] threeChar;//heading or altitude
    private char[] fourChar;//speed or altitude
    private char[] fiveChar;//speed

    public CommandParser(GameLogic gl) {
        twoChar = new char[2];
        threeChar = new char[3];
        fourChar = new char[4];
        fiveChar = new char[5];
        gameLogic = gl;
        input = new ArrayDeque();
        temp = new ArrayDeque();
    }

    public Aircraft getAircraft() {
        return aircraft;
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
                    threeChar[0] = '0';
                    threeChar[1] = twoChar[0];
                    threeChar[2] = twoChar[1];
                    aircraft.setAltitudeCommand(threeChar);
                    System.out.println(threeChar);
                }
            } else if (temp.peekFirst().size() == 3) {
                for (int i = 0; i < 3; i++) {
                    threeChar[i] = temp.peekFirst().remove();
                }
                if (Character.isDigit(threeChar[0]) && Character.isDigit(threeChar[1]) && Character.isDigit(threeChar[2])) {
                    aircraft.setHeadingCommand(threeChar);
                } else if (threeChar[0] == 'A' && Character.isDigit(threeChar[1]) && Character.isDigit(threeChar[2])) {
                    threeChar[0] = '0';
                    aircraft.setAltitudeCommand(threeChar);
                }
            } else if (temp.peekFirst().size() == 4) {
                for (int i = 0; i < 4; i++) {
                    fourChar[i] = temp.peekFirst().remove();
                }
                if (Character.isDigit(fourChar[0]) && Character.isDigit(fourChar[1]) && Character.isDigit(fourChar[2]) && Character.isDigit(fourChar[3])) {
                    aircraft.setSpeedCommand(fourChar);
                } else if (fourChar[0] == 'S' && Character.isDigit(fourChar[1]) && Character.isDigit(fourChar[2]) && Character.isDigit(fourChar[3])) {
                    fourChar[0] = '0';
                    aircraft.setSpeedCommand(fourChar);
                } else if ((fourChar[0] == 'A') && Character.isDigit(fourChar[1]) && Character.isDigit(fourChar[2]) && Character.isDigit(fourChar[3])) {
                    threeChar[0] = fourChar[1];
                    threeChar[1] = fourChar[2];
                    threeChar[2] = fourChar[3];
                    aircraft.setAltitudeCommand(threeChar);
                }
            } else if (temp.peekFirst().size() == 5) {
                for (int i = 0; i < 5; i++) {
                    fiveChar[i] = temp.peekFirst().remove();
                }
                if ((fiveChar[0] == 'S') && Character.isDigit(fiveChar[1]) && Character.isDigit(fiveChar[2]) && Character.isDigit(fiveChar[3]) && Character.isDigit(fiveChar[4])) {
                    fourChar[0] = fiveChar[1];
                    fourChar[1] = fiveChar[2];
                    fourChar[2] = fiveChar[3];
                    fourChar[3] = fiveChar[4];
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
