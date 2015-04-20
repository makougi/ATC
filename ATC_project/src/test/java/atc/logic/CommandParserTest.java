/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

import atc.gui.CommandPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import atc.logic.CommandParser;
import java.awt.Color;
import java.util.Random;

/**
 *
 * @author makougi
 */
public class CommandParserTest {

    CommandParser cp;

    public CommandParserTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        GameLogic gl = new GameLogic();
        cp = new CommandParser(gl);
        CommandPanel comPan = new CommandPanel(gl, Color.BLACK, 10);
        cp.setCommandPanel(comPan);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void commandParserToimiiKunEiYhtaanMerkkia() {
        cp.keybEnter();
    }

    @Test
    public void commandParserToimiiSpaceMerkilla() {
        cp.keybCharacter(' ');
        cp.keybEnter();
    }

    @Test
    public void commandParserToimiiYhdellaMerkilla() {
        cp.keybCharacter('a');
        cp.keybEnter();
    }

    @Test
    public void commandParserToimiiYhdellaNumerolla() {
        cp.keybCharacter('0');
        cp.keybEnter();
    }

    @Test
    public void commandParserToimiiTosiMonellaMerkilla() {
        int a = 0;
        while (a < 100000) {
            cp.keybCharacter('a');
            a++;
        }
        cp.keybEnter();
    }
}
