/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atc.logic;

import atc.gui.GUIFrame;
import atc.gui.InfoPanel2;
import atc.gui.RadarPanel;
import java.awt.Color;
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
public class AircraftSchedulerTest {
    
    public AircraftSchedulerTest() {
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
    @Test
    public void AircraftSchedulerToimii(){
        GameLogic gl = new GameLogic();
        ArrayList<Aircraft> aircrafts = new ArrayList<Aircraft>();
        AircraftScheduler as = new AircraftScheduler(gl,aircrafts,1000);
        RadarPanel rp = new RadarPanel(gl,100);
        as.setRadarPanel(rp);
        as.scheduleClock = 0;
        as.timeOfNextScheduleEntry = 1;
        as.scheduleScope = -1;
        
        as.updateScheduleClock();
        
        assertEquals(1,as.getAircrafts().size());
    }
}
