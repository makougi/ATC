package atc.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import atc.logic.GameLogic;
import atc.gui.CommandPanel;
import java.util.ArrayDeque;

@SuppressWarnings("serial")
public class KeyboardListener extends JPanel {

    String keyboardInput;
    char keyboardInputAsChar;
    CommandPanel commandPanel;
    ArrayDeque<Character> digitsAndAlphabetStack;
    char[] digitsAndAlphabet;

    public KeyboardListener(GameLogic gl, CommandPanel cp) {
        commandPanel = cp;

        char[] digitsAndAlphabet = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        digitsAndAlphabetStack = new ArrayDeque<Character>();
        for (int i = 0; i < digitsAndAlphabet.length; i++) {
            digitsAndAlphabetStack.add(digitsAndAlphabet[i]);
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
                    
                } else if (keyboardInput.equals("Space")){
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
    private void sendEnter(){
        commandPanel.keybEnter();
    }
    private void sendBackspace(){
        commandPanel.keybBackspace();
    }
    private void sendCharacter(char c){
        commandPanel.keybCharacter(c);
    }
}
