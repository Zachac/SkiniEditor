package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Color;

public class TestColor {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testColorIntIntIntInt_AllJustUnderMax_IsCreated() {
        assertTrue(new Color(255, 255, 255, 255) != null);
    }
    
    @Test
    public void testColorIntIntIntInt_AllZero_IsCreated() {
        assertTrue(new Color(0, 0, 0, 0) != null);
    }

    @Test
    public void testColorIntIntInt_AllJustUnderMax_IsCreated() {
        assertTrue(new Color(255, 255, 255) != null);
    }
    
    @Test
    public void testColorIntIntInt_AllZero_IsCreated() {
        assertTrue(new Color(0, 0, 0) != null);
    }

//    @Test
//    public void testParse() {
//        fail("Not yet implemented");
//    }
//
//    @Test
//    public void testToString() {
//        fail("Not yet implemented");
//    }

}
