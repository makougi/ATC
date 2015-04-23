/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

import atc.gui.GUIFrame;
import atc.main.Timer;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kimmo
 */
public class GameLogicTest {
Timer timer;
GameLogic gl;
GUIFrame guiMain;
ArrayList<Aircraft> as;
Aircraft a1;
Aircraft a2;

    public GameLogicTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        System.out.println("testsart");
//        GameLogic gl = new GameLogic();
//        GUIFrame guiMain = new GUIFrame(gl);
//        System.out.println("teston");
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
    public void GameLogicToimii() {
        GameLogic gl = new GameLogic();
        GUIFrame guiMain = new GUIFrame(gl);
        
        Aircraft a1 = new Aircraft(gl,"111111",100,100,100,100,100);
        Aircraft a2 = new Aircraft(gl,"222222",100,100,100,100,100);
        
        gl.aircrafts.add(a1);
        gl.aircrafts.add(a2);
                
        gl.update();

        assertEquals(true, gl.gameOver);

    }
}
