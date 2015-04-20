/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

import atc.gui.GUIFrame;
import atc.main.Timer;
import java.lang.reflect.Method;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author makougi
 */
public class GameLogicTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public void GameLogicToimii(){
        GameLogic gameLogic = new GameLogic();
        GUIFrame guiMain = new GUIFrame(gameLogic);
        Timer timer = new Timer(guiMain, gameLogic);
        
        while(gameLogic.getAircrafts().size()==0) {
            //wait
        }
        assertEquals(2, gameLogic.getAircrafts().size());
        //ei onnistu. miten voi testata, kun pelin pitäisi olla pyörimässä, jotta voisi testata. en osaa.
    }
}
