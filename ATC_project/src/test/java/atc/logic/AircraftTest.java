/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

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
public class AircraftTest {

    Aircraft a;

    public AircraftTest() {
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
        a = new Aircraft(gl, "111111", 100, 100, 100, 100, 100);

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
    public void setAltitudeCommandToimii() {
        char[] c = {'9', '9', '9'};
        a.setAltitudeCommand(c);
        assertEquals(999, a.getZCommand());
    }

    @Test
    public void setHeadingCommandToimii() {
        char[] c = {'1', '0', '0'};
        a.setHeadingCommand(c);
        assertEquals(100, a.getHeadingCommand());
    }

    @Test
    public void setSpeedCommandToimii() {
        char[] c = {'0', '1', '0', '0'};
        a.setSpeedCommand(c);
        assertEquals(160, a.getSpeedCommand());
    }

    @Test
    public void updateToimii() {
        char[] alt = {'2', '0', '0'};
        char[] hdg = {'2', '0', '0'};
        char[] spd = {'0', '2', '0', '0'};
        a.setAltitudeCommand(alt);
        a.setHeadingCommand(hdg);
        a.setSpeedCommand(spd);
        a.update();
        assertEquals(101,a.getZ());
        assertEquals(103, a.getHeading());
        assertEquals(105, a.getSpeed());
    }
}
