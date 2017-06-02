package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.FontsProperties;

public class TestFontsProperties {

    private static final String PREFIX = "CUSTOM-PREFIX";
    
    FontsProperties fonts;
    
    @Before
    public void setUp() throws Exception {
        fonts = new FontsProperties();
    }

    @Test
    public void testInitialValues() {
        assertEquals(fonts.getComboOverlap(), -2);
        assertEquals(fonts.getScoreOverlap(), -2);
        assertEquals(fonts.getHitCircleOverlap(), -2);
        
        assertEquals(fonts.getHitCirclePrefix(), "default");
        assertEquals(fonts.getScorePrefix(), "score");
        assertEquals(fonts.getComboPrefix(), "score");
    }

    @Test(expected = NullPointerException.class)
    public void testSetHitCirclePrefix_NullString_NullPointerException() {
        fonts.setHitCirclePrefix(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetHitCirclePrefix_EmptyString_IllegalArgumentException() {
        fonts.setHitCirclePrefix("");
    }

    @Test
    public void testSetHitCirclePrefix_Prefix_IsSet() {
        assertNotEquals(fonts.getHitCirclePrefix(), PREFIX);
        fonts.setHitCirclePrefix(PREFIX);
        assertEquals(fonts.getHitCirclePrefix(), PREFIX);
    }

    @Test
    public void testSetHitCircleOverlap_NegativeValue_IsSet() {
        assertNotEquals(fonts.getHitCircleOverlap(), -3);
        fonts.setHitCircleOverlap(-3);
        assertEquals(fonts.getHitCircleOverlap(), -3);
    }

    @Test
    public void testSetHitCircleOverlap_ZeroValue_IsSet() {
        assertNotEquals(fonts.getHitCircleOverlap(), 0);
        fonts.setHitCircleOverlap(0);
        assertEquals(fonts.getHitCircleOverlap(), 0);
    }
    
    @Test
    public void testSetHitCircleOverlap_PositiveValue_IsSet() {
        assertNotEquals(fonts.getHitCircleOverlap(), 3);
        fonts.setHitCircleOverlap(3);
        assertEquals(fonts.getHitCircleOverlap(), 3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetScorePrefix_NullString_NullPointerException() {
        fonts.setHitCirclePrefix(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetScorePrefix_EmptyString_IllegalArgumentException() {
        fonts.setScorePrefix("");
    }

    @Test
    public void testSetScorePrefix_Prefix_IsSet() {
        assertNotEquals(fonts.getScorePrefix(), PREFIX);
        fonts.setScorePrefix(PREFIX);
        assertEquals(fonts.getScorePrefix(), PREFIX);
    }

    @Test
    public void testSetScoreOverlap_NegativeValue_IsSet() {
        assertNotEquals(fonts.getScoreOverlap(), -3);
        fonts.setScoreOverlap(-3);
        assertEquals(fonts.getScoreOverlap(), -3);
    }

    @Test
    public void testSetScoreOverlap_ZeroValue_IsSet() {
        assertNotEquals(fonts.getScoreOverlap(), 0);
        fonts.setScoreOverlap(0);
        assertEquals(fonts.getScoreOverlap(), 0);
    }
    
    @Test
    public void testSetScoreOverlap_PositiveValue_IsSet() {
        assertNotEquals(fonts.getScoreOverlap(), 3);
        fonts.setScoreOverlap(3);
        assertEquals(fonts.getScoreOverlap(), 3);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetComboPrefix_NullString_NullPointerException() {
        fonts.setHitCirclePrefix(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetComboPrefix_EmptyString_IllegalArgumentException() {
        fonts.setComboPrefix("");
    }

    @Test
    public void testSetComboPrefix_Prefix_IsSet() {
        assertNotEquals(fonts.getComboPrefix(), PREFIX);
        fonts.setComboPrefix(PREFIX);
        assertEquals(fonts.getComboPrefix(), PREFIX);
    }

    @Test
    public void testSetComboOverlap_NegativeValue_IsSet() {
        assertNotEquals(fonts.getComboOverlap(), -3);
        fonts.setComboOverlap(-3);
        assertEquals(fonts.getComboOverlap(), -3);
    }

    @Test
    public void testSetComboOverlap_ZeroValue_IsSet() {
        assertNotEquals(fonts.getComboOverlap(), 0);
        fonts.setComboOverlap(0);
        assertEquals(fonts.getComboOverlap(), 0);
    }
    
    @Test
    public void testSetComboOverlap_PositiveValue_IsSet() {
        assertNotEquals(fonts.getComboOverlap(), 3);
        fonts.setComboOverlap(3);
        assertEquals(fonts.getComboOverlap(), 3);
    }

}
