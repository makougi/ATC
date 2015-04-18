package atc.main;

import atc.logic.GameLogic;
import atc.gui.*;

/**
 * 
 * @author Kimmo
 * <p>
 * pelin käynnistävä luokka
 */
public class Main {

    /**
     * Ensin luodaan GameLogic-olio, joka huolehtii pelin logiikasta.
     * <p>
     * Tämän jälkeen GUIFrame-olio, joka huolehtii pelin piirtämisestä ruudulle.
     * <p>
     * Tämän jälkeen Timer-olio, joka tarjoaa pelilogiikalle kellon
     *
     * @param args args
     */
    public static void main(String[] args) {
        GameLogic gameLogic = new GameLogic();
        GUIFrame guiMain = new GUIFrame(gameLogic);
        Timer timer = new Timer(guiMain, gameLogic);
    }
}
