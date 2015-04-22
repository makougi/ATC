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
public class ValuesTest {

    GameLogic gl;

    public ValuesTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        gl = new GameLogic();
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    public void valuesGetHeadingXYValuesToimii() {
        assertEquals(360, Values.getHeadingXYValues().length);
        assertEquals(0, Values.getHeadingXYValues()[0][0]);
        assertEquals(90, Values.getHeadingXYValues()[90][0]);
        assertEquals(0, Values.getHeadingXYValues()[180][0]);
        assertEquals(-90, Values.getHeadingXYValues()[270][0]);
        assertEquals(-90, Values.getHeadingXYValues()[0][1]);
        assertEquals(0, Values.getHeadingXYValues()[90][1]);
        assertEquals(90, Values.getHeadingXYValues()[180][1]);
        assertEquals(0, Values.getHeadingXYValues()[270][1]);
    }

    public void valuesGetIdentifierToimii() {
        assertEquals(6, Values.createIdentifier(gl).length());
    }
}
