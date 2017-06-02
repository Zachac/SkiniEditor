package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.ColoursProperties;

public class TestColoursProperties {

    private static final Color UNIQUE_COLOR = new Color(23, 42, 100, 27);
    
    private ColoursProperties colors;
    private ColoursProperties maxComboColors;

    @Before
    public void setUp() throws Exception {
        colors = new ColoursProperties();
        
        maxComboColors = new ColoursProperties();
        maxComboColors.addComboColor(UNIQUE_COLOR);
        maxComboColors.addComboColor(UNIQUE_COLOR);
        maxComboColors.addComboColor(UNIQUE_COLOR);
        maxComboColors.addComboColor(UNIQUE_COLOR);
    }

    @Test
    public void testColoursProperties_StartsWithDefaultValues() {
        assertEquals(colors.getComboColors().size(), 4);
        assertEquals(colors.getComboColors().get(0), new Color(255,192,0));
        assertEquals(colors.getComboColors().get(1), new Color(0,202,0 ));
        assertEquals(colors.getComboColors().get(2), new Color(18,124,255));
        assertEquals(colors.getComboColors().get(3), new Color(242,24,57));

        assertEquals(colors.getSliderBorder(), new Color(255,255,255));
        assertEquals(colors.getMenuGlow(), new Color(0,78,155));
        assertEquals(colors.getSliderBall(), new Color(2,170,255 ));
        assertEquals(colors.getSpinnerBackground(), new Color(100, 100, 100));
        assertEquals(colors.getSongSelectActiveText(), new Color(0,0,0));
        assertEquals(colors.getSongSelectInactiveText(), new Color(255, 255, 255));
        assertEquals(colors.getStarBreakAdditive(), new Color(255,182,193));
        assertEquals(colors.getSliderTrackOverride(), null);
        assertEquals(colors.getInputOverlayText(), new Color(124,108,246));
    }

    @Test
    public void testGetComboColors_IsNotNull() {
        assertNotEquals(colors.getComboColors(), null);
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void testGetComboColors_IsUnModifiable() {
        colors.getComboColors().remove(0);
    }

    @Test(expected = NullPointerException.class)
    public void testSetComboColor_Null_NullPointerException() {
        colors.setComboColor(0, null);
    }
    
    @Test
    public void testSetComboColor_UniqueColor_ColorIsChanged() {
        assertNotEquals(colors.getComboColors().get(0), UNIQUE_COLOR);
        colors.setComboColor(0, UNIQUE_COLOR);
        assertEquals(colors.getComboColors().get(0), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testAddComboColor_Null_NullPointerException() {
        colors.addComboColor(null);
    }

    @Test
    public void testAddComboColor_UniqueColor_IsAddedToTheEnd() {
        int size = colors.getComboColors().size();
        colors.addComboColor(UNIQUE_COLOR);
        assertEquals(colors.getComboColors().get(size), UNIQUE_COLOR);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddComboColor_AddedPastEightColors_IllegalArgumentException() {
        maxComboColors.addComboColor(UNIQUE_COLOR);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveComboColor_NegativeCombo_IllegalArgumentExcpetion() {
        colors.removeComboColor(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveComboColor_OutSideBounds_IllegalArgumentExcpetion() {
        colors.removeComboColor(4);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSliderBorder_Null_NullPointerException() {
        colors.setSliderBorder(null);
    }
    
    @Test
    public void testSetSliderBorder_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSliderBorder(), UNIQUE_COLOR);
        colors.setSliderBorder(UNIQUE_COLOR);
        assertEquals(colors.getSliderBorder(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetMenuGlow_Null_NullPointerException() {
        colors.setMenuGlow(null);
    }
    
    @Test
    public void testSetMenuGlow_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getMenuGlow(), UNIQUE_COLOR);
        colors.setMenuGlow(UNIQUE_COLOR);
        assertEquals(colors.getMenuGlow(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSliderBall_Null_NullPointerException() {
        colors.setSliderBall(null);
    }
    
    @Test
    public void testSetSliderBall_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSliderBall(), UNIQUE_COLOR);
        colors.setSliderBall(UNIQUE_COLOR);
        assertEquals(colors.getSliderBall(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSpinnerBackground_Null_NullPointerException() {
        colors.setSpinnerBackground(null);
    }
    
    @Test
    public void testSetSpinnerBackground_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSpinnerBackground(), UNIQUE_COLOR);
        colors.setSpinnerBackground(UNIQUE_COLOR);
        assertEquals(colors.getSpinnerBackground(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSongSelectActiveText_Null_NullPointerException() {
        colors.setSongSelectActiveText(null);
    }
    
    @Test
    public void testSetSongSelectActiveText_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSongSelectActiveText(), UNIQUE_COLOR);
        colors.setSongSelectActiveText(UNIQUE_COLOR);
        assertEquals(colors.getSongSelectActiveText(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetSongSelectInactiveText_Null_NullPointerException() {
        colors.setSongSelectInactiveText(null);
    }
    
    @Test
    public void testSetSongSelectInactiveText_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSongSelectInactiveText(), UNIQUE_COLOR);
        colors.setSongSelectInactiveText(UNIQUE_COLOR);
        assertEquals(colors.getSongSelectInactiveText(), UNIQUE_COLOR);
    }

    @Test(expected = NullPointerException.class)
    public void testSetStarBreakAdditive_Null_NullPointerException() {
        colors.setStarBreakAdditive(null);
    }
    
    @Test
    public void testStarBreakAdditive_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getStarBreakAdditive(), UNIQUE_COLOR);
        colors.setStarBreakAdditive(UNIQUE_COLOR);
        assertEquals(colors.getStarBreakAdditive(), UNIQUE_COLOR);
    }
    
    @Test
    public void testSetSliderTrackOverride_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getSliderTrackOverride(), UNIQUE_COLOR);
        colors.setSliderTrackOverride(UNIQUE_COLOR);
        assertEquals(colors.getSliderTrackOverride(), UNIQUE_COLOR);
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetInputOverlayText_Null_NullPointerException() {
        colors.setInputOverlayText(null);
    }
    
    @Test
    public void testSetInputOverlayText_UniqueColor_ColorIsChangedToUniqueColor() {
        assertNotEquals(colors.getInputOverlayText(), UNIQUE_COLOR);
        colors.setInputOverlayText(UNIQUE_COLOR);
        assertEquals(colors.getInputOverlayText(), UNIQUE_COLOR);
    }
    
    @Test
    public void testEqualsObject_Object_False() {
    	assertFalse(colors.equals(new Object()));
    }

    @Test
    public void testEqualsObject_Null_False() {
    	assertFalse(colors.equals(null));
    }

    @Test
    public void testEqualsObject_Itself_True() {
    	assertTrue(colors.equals(colors));
    }

    @Test
    public void testEqualsObject_OtherWithSameValues_True() {
    	assertTrue(colors.equals(new ColoursProperties()));
    }
    
    @Test
    public void testEqualsObject_OtherWithDifferentValues_False() {
    	colors.setInputOverlayText(new Color(0, 0, 2, 254));
    	assertFalse(colors.equals(new ColoursProperties()));
    }
}
