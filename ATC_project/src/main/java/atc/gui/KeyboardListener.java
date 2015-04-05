package atc.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import java.util.ArrayDeque;
import atc.logic.CommandParser;

@SuppressWarnings("serial")
public class KeyboardListener extends JPanel {

    String keyboardInput;
    char keyboardInputAsChar;
    CommandPanel commandPanel;
    String digitsAndAlphabet;
    ArrayDeque<Character> digitsAndAlphabetStack;
    CommandParser commandParser;

    public KeyboardListener(CommandParser parser, CommandPanel panel) {
        commandParser = parser;
        commandPanel = panel;

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

    private void sendEnter() {
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
