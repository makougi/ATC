package atc.main;

import atc.logic.GameLogic;
import atc.gui.*;

public class Main {

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        GUIMain guiMain = new GUIMain(gameLogic);
        guiMain.setVisible(true);
        Timer timer = new Timer(guiMain, gameLogic);
    }
}
