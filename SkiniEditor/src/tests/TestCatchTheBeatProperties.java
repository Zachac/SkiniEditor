package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.CatchTheBeatProperties;
import model.Color;

public class TestCatchTheBeatProperties {

    private static final Color GREEN = new Color(0, 255, 0); 
    
    CatchTheBeatProperties ctb;
    
    @Before
    public void setUp() throws Exception {
        ctb = new CatchTheBeatProperties();
    }

    @Test
    public void testCatchTheBeatProperties_IsInitializedWithDefaultValues() {
        assertEquals(ctb.getHyperDash(), new Color(255, 0, 0));
        assertEquals(ctb.getHyperDashAfterImage(), new Color(255, 0, 0));
        assertEquals(ctb.getHyperDashFruit(), new Color(255, 0, 0));
    }

    @Test(expected = NullPointerException.class)
    public void testSetHyperDash_Null_NullPointerException() {
        ctb.setHyperDash(null);
    }
    
    @Test
    public void testSetHyperDash_ValidColor_IsChanged() {
        assertNotEquals(GREEN, ctb.getHyperDash());
        ctb.setHyperDash(GREEN);
        assertEquals(GREEN, ctb.getHyperDash());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetHyperDashAfterImage_Null_NullPointerException() {
        ctb.setHyperDashAfterImage(null);
    }
    
    @Test
    public void testSetHyperDashAfterImage_ValidColor_IsChanged() {
        assertNotEquals(GREEN, ctb.getHyperDashAfterImage());
        ctb.setHyperDashAfterImage(GREEN);
        assertEquals(GREEN, ctb.getHyperDashAfterImage());
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetHyperDashFruit_Null_NullPointerException() {
        ctb.setHyperDashFruit(null);
    }
    
    @Test
    public void testSetHyperDashFruit_ValidColor_IsChanged() {
        assertNotEquals(GREEN, ctb.getHyperDashFruit());
        ctb.setHyperDashFruit(GREEN);
        assertEquals(GREEN, ctb.getHyperDashFruit());
    }
}
