package tests;

import static org.junit.Assert.*;

import java.io.File;
import java.text.ParseException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.MColor;
import model.SkinProperties;
import model.SkinPropertiesIO;
import model.SkinVersion;

public class TestSkinPropertiesIO {

	SkinPropertiesIO newFile; 
	SkinPropertiesIO originalSkini;
	
	private final SkinProperties originalSkiniExpected = getOrignialSkini();
	
    @Before
    public void setUp() throws Exception {
        newFile = new SkinPropertiesIO(new File("skin.ini"));
        originalSkini = new SkinPropertiesIO(new File("New Skin/skin.ini"));
    }
    
    @After
    public void cleanUp() {
        new File("skin.ini").delete();
    }

    @Test(expected = NullPointerException.class)
    public void testSkinPropertiesIO_Null_NullPointerException() throws ParseException {
        new SkinPropertiesIO(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSkinPropertiesIO_Directory_IllegalArgumentException() throws ParseException {
        new SkinPropertiesIO(new File("New Skin\\"));
    }

    @Test
    public void testSkinPropertiesIO_DirectoryNotFile_LoadsDefault() {
        assertTrue(newFile.getSkinProperties().equals(new SkinProperties()));
    }
    
    @Test
    public void testSkinPropertiesIO_OriginalSkin_AllValuesSet() {
        assertTrue(originalSkini.getSkinProperties().colours.equals(originalSkiniExpected.colours));
        assertTrue(originalSkini.getSkinProperties().catchTheBeat.equals(originalSkiniExpected.catchTheBeat));
        assertTrue(originalSkini.getSkinProperties().fonts.equals(originalSkiniExpected.fonts));
        assertTrue(originalSkini.getSkinProperties().general.equals(originalSkiniExpected.general));
        assertTrue(originalSkini.getSkinProperties().equals(originalSkiniExpected));
    }

    @Test
    public void testSave_NonExistingFile_IsSaved() throws ParseException {
        assertFalse(newFile.getSaveLocation().exists());
        newFile.getSkinProperties().fonts.setComboOverlap(3);
        newFile.save();
        assertTrue(newFile.getSaveLocation().exists());
        
        SkinPropertiesIO loadedSkin = new SkinPropertiesIO(newFile.getSaveLocation());
        assertTrue(newFile.getSkinProperties().equals(loadedSkin.getSkinProperties()));
    }
    
    @Test
    public void testSave_ExistingFile_IsSaved() throws ParseException {
        assertTrue(originalSkini.getSaveLocation().exists());
        originalSkini.getSkinProperties().fonts.setComboOverlap(3);
        originalSkini.save();
        
        SkinPropertiesIO loadedSkin = new SkinPropertiesIO(originalSkini.getSaveLocation());
        assertTrue(originalSkini.getSkinProperties().equals(loadedSkin.getSkinProperties()));
        
        originalSkini.getSkinProperties().fonts.setComboOverlap(originalSkiniExpected.fonts.getComboOverlap());
        originalSkini.save();

        loadedSkin = new SkinPropertiesIO(originalSkini.getSaveLocation());
        assertTrue(originalSkiniExpected.equals(loadedSkin.getSkinProperties()));
    }

    @Test(expected = NullPointerException.class)
    public void testSetSaveLocation_Null_NullPointerException() {
        originalSkini.setSaveLocation(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSaveLocation_Directory_NullPointerException() {
        originalSkini.setSaveLocation(new File("New Skin\\"));
    }

    @Test
    public void testSetSaveLocation_NonExistingFile_IsSet() {
        File newLocation = new File("New Skin\\newfile.ini");
        
        assertNotEquals(newLocation, originalSkini.getSaveLocation());
        originalSkini.setSaveLocation(newLocation);
        assertEquals(newLocation, originalSkini.getSaveLocation());
    }
    
    @Test
    public void testSetSaveLocation_ExistingFile_IsSet() {
        File newLocation = new File("New Skin\\skin.ini");
        assertNotEquals(newLocation, newFile.getSaveLocation());
        newFile.setSaveLocation(newLocation);
        assertEquals(newLocation, newFile.getSaveLocation());
    }
    
    public static SkinProperties getOrignialSkini() {
		SkinProperties originalSkiniExpected = new SkinProperties();
		originalSkiniExpected.general.setName("New Skin");
		originalSkiniExpected.general.setAuthor("Parity + Keyasayo");
		originalSkiniExpected.general.setVersion(SkinVersion.TWOPOINTTWO);
		originalSkiniExpected.general.setSliderBallFlip(false);
		originalSkiniExpected.general.setSliderBallFrames(10);
		originalSkiniExpected.general.setAllowSliderBallTint(true);
		originalSkiniExpected.general.setSliderStyle(true);
		originalSkiniExpected.general.setCursorRotate(false);
		originalSkiniExpected.general.setCursorExpand(false);
		originalSkiniExpected.general.setCursorCentre(false);
		originalSkiniExpected.general.setCursorTrailRotate(false);
		originalSkiniExpected.general.setHitCircleOverlayAboveNumber(false);
		originalSkiniExpected.general.setSpinnerFrequencyModulate(false);
		originalSkiniExpected.general.setLayeredHitSounds(false);
		originalSkiniExpected.general.setSpinnerFadePlayfield(false);
		originalSkiniExpected.general.setSpinnerNoBlink(true);
		originalSkiniExpected.general.setAnimationFramerate(20);
		originalSkiniExpected.general.addCustomComboBurstSound(50);
		originalSkiniExpected.general.addCustomComboBurstSound(75);
		originalSkiniExpected.general.addCustomComboBurstSound(100);
		originalSkiniExpected.general.addCustomComboBurstSound(200);
		originalSkiniExpected.general.addCustomComboBurstSound(301);
		originalSkiniExpected.general.setComboBurstRandom(true);
		
		originalSkiniExpected.colours.assignComboColor(0, new MColor(1, 2, 3));
		originalSkiniExpected.colours.assignComboColor(1, new MColor(0, 202, 0, 1));
		originalSkiniExpected.colours.assignComboColor(2, new MColor(1, 2, 3, 5));
		originalSkiniExpected.colours.assignComboColor(3, new MColor(242,24,57,3));
		originalSkiniExpected.colours.addComboColor(new MColor(242, 16, 102));
		originalSkiniExpected.colours.addComboColor(new MColor(222, 23, 4, 254));
		originalSkiniExpected.colours.addComboColor(new MColor(1, 2, 3, 4));
		originalSkiniExpected.colours.addComboColor(new MColor(18,231,98));
		originalSkiniExpected.colours.setSliderBorder(new MColor(255, 103, 72));
		originalSkiniExpected.colours.setMenuGlow(new MColor(0, 52, 210));
		originalSkiniExpected.colours.setSliderBall(new MColor(170, 182, 18));
		originalSkiniExpected.colours.setSpinnerBackground(new MColor(152, 71, 81));
		originalSkiniExpected.colours.setSongSelectActiveText(new MColor(81, 215, 215));
		originalSkiniExpected.colours.setSongSelectInactiveText(new MColor(0, 2, 44));
		originalSkiniExpected.colours.setStarBreakAdditive(new MColor(11, 23, 45));
		originalSkiniExpected.colours.setSliderTrackOverride(new MColor(22, 33, 4));
		originalSkiniExpected.colours.setInputOverlayText(new MColor(2, 2, 4, 1));

		originalSkiniExpected.fonts.setHitCirclePrefix("defaults");
		originalSkiniExpected.fonts.setHitCircleOverlap(-23);
		originalSkiniExpected.fonts.setScorePrefix("scores");
		originalSkiniExpected.fonts.setScoreOverlap(-22);
		originalSkiniExpected.fonts.setComboPrefix("scoress");
		originalSkiniExpected.fonts.setComboOverlap(-21);
		
		originalSkiniExpected.catchTheBeat.setHyperDash(new MColor(255, 0, 0, 5));
		originalSkiniExpected.catchTheBeat.setHyperDashAfterImage(new MColor(254, 244, 234));
		originalSkiniExpected.catchTheBeat.setHyperDashFruit(new MColor(224, 214, 204));
		
		return originalSkiniExpected;
	}
}
