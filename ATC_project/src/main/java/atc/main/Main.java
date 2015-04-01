package atc.main;

import atc.logic.GameLogic;
import atc.gui.*;

public class Main {

    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        GUIFrame guiMain = new GUIFrame(gameLogic);
        Timer timer = new Timer(guiMain, gameLogic);
    }
}
