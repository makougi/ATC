package atc.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.util.ArrayDeque;
import atc.logic.CommandParser;

/**
 *
 * @author Kimmo
 * <p>
 * keyboardlistener-luokka
 */
@SuppressWarnings("serial")
public class KeyboardListener extends JPanel {

    String keyboardInput;
    char keyboardInputAsChar;
    CommandPanel commandPanel;
    InfoPanel1 infoPanel1;
    String digitsAndAlphabet;
    ArrayDeque<Character> digitsAndAlphabetStack;
    CommandParser commandParser;

    /**
     * näppäimistön kuuntelija
     *
     * @param parser commandparser-olio
     * @param panel commandpanel-olio
     * @param ip infopanel-olio
     */
    public KeyboardListener(CommandParser parser, CommandPanel panel, InfoPanel1 ip) {
        commandParser = parser;
        commandPanel = panel;
        infoPanel1 = ip;

        digitsAndAlphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        digitsAndAlphabetStack = new ArrayDeque<Character>();
        for (int i = 0; i < digitsAndAlphabet.length(); i++) {
            digitsAndAlphabetStack.add(digitsAndAlphabet.charAt(i));
        }

        KeyListener listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyboardInput = KeyEvent.getKeyText(e.getKeyCode());
                if (keyboardInput.equals("Shift")) {
                    sendShift();
                }
                if (keyboardInput.equals("Enter")) {
                    sendEnter();

                } else if (keyboardInput.equals("Space")) {
                    sendCharacter(' ');

                } else if (keyboardInput.equals("Backspace")) {
                    sendBackspace();

                } else if (keyboardInput.length() == 1) {
                    keyboardInputAsChar = keyboardInput.charAt(0);
                    for (char c : digitsAndAlphabetStack) {
                        if (keyboardInputAsChar == c) {
                            sendCharacter(c);
                            break;
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
        addKeyListener(listener);
        setFocusable(true);
    }

    private void sendShift() {
        infoPanel1.setMode(1);
    }

    private void sendEnter() {
        infoPanel1.setMode(0);
        commandParser.keybEnter();
        commandPanel.keybEnter();

    }

    private void sendBackspace() {
        commandParser.keybBackspace();
        commandPanel.keybBackspace();
    }

    private void sendCharacter(char c) {
        commandParser.keybCharacter(c);
        commandPanel.keybCharacter(c);
    }
}
