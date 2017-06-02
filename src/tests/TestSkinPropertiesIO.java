package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import model.Color;
import model.SkinProperties;
import model.SkinPropertiesIO;
import model.SkinVersion;

public class TestSkinPropertiesIO {

	SkinPropertiesIO newFile; 
	SkinPropertiesIO originalSkini;
	
	private static final SkinProperties originalSkiniExpected = getOrignialSkini();
			
	
	
    @Before
    public void setUp() throws Exception {
        newFile = new SkinPropertiesIO(new File("skin.ini"));
        originalSkini = new SkinPropertiesIO(new File("New Skin/skin.ini"));
    }

    @Test(expected = NullPointerException.class)
    public void testSkinPropertiesIO_Null_NullPointerException() {
        new SkinPropertiesIO(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSkinPropertiesIO_Directory_IllegalArgumentException() {
        new SkinPropertiesIO(new File("New Skin"));
    }

    @Test
    public void testSkinPropertiesIO_DirectoryNotFile_LoadsDefault() {
        assertTrue(newFile.getSkinProperties().equals(new SkinProperties()));
    }
    
    @Test
    public void testSkinPropertiesIO_OriginalSkin_AllValuesSet() {
        assertTrue(originalSkini.getSkinProperties().equals(originalSkiniExpected));
    }

    @Test
    public void testSave() {
        fail("Not yet implemented");
    }

    @Test
    public void testLoad() {
        fail("Not yet implemented");
    }
    
    public static SkinProperties getOrignialSkini() {
		SkinProperties originalSkiniExpected = new SkinProperties();
		originalSkiniExpected.general.setName("New Skin");
		originalSkiniExpected.general.setAuthor("Parity + Keyasayo");
		originalSkiniExpected.general.setVersion(SkinVersion.TWOPOINTTWO);
		originalSkiniExpected.general.setSliderBallFlip(false);
		originalSkiniExpected.general.setSliderBallFrames(10);
		originalSkiniExpected.general.setAllowSliderBallTint(true);
		originalSkiniExpected.general.setSliderStyle(false);
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
		
		originalSkiniExpected.colours.setComboColor(0, new Color(1, 2, 3));
		originalSkiniExpected.colours.setComboColor(1, new Color(0, 202, 0, 1));
		originalSkiniExpected.colours.setComboColor(2, new Color(1, 2, 3, 5));
		originalSkiniExpected.colours.setComboColor(3, new Color(242,24,57,3));
		originalSkiniExpected.colours.addComboColor(new Color(242, 16, 102));
		originalSkiniExpected.colours.addComboColor(new Color(222, 23, 4, 254));
		originalSkiniExpected.colours.addComboColor(new Color(1, 2, 3, 4));
		originalSkiniExpected.colours.addComboColor(new Color(18,274,98));
		originalSkiniExpected.colours.setSliderBorder(new Color(255, 103, 72));
		originalSkiniExpected.colours.setMenuGlow(new Color(0, 52, 210));
		originalSkiniExpected.colours.setSliderBall(new Color(170, 182, 18));
		originalSkiniExpected.colours.setSpinnerBackground(new Color(152, 71, 81));
		originalSkiniExpected.colours.setSongSelectActiveText(new Color(81, 215, 215));
		originalSkiniExpected.colours.setSongSelectInactiveText(new Color(0, 2, 44));
		originalSkiniExpected.colours.setStarBreakAdditive(new Color(11, 23, 45));
		originalSkiniExpected.colours.setSliderTrackOverride(new Color(22, 33, 4));
		originalSkiniExpected.colours.setInputOverlayText(new Color(2, 2, 4, 1));

		originalSkiniExpected.fonts.setHitCirclePrefix("defaults");
		originalSkiniExpected.fonts.setHitCircleOverlap(-23);
		originalSkiniExpected.fonts.setScorePrefix("scores");
		originalSkiniExpected.fonts.setScoreOverlap(-22);
		originalSkiniExpected.fonts.setComboPrefix("soress");
		originalSkiniExpected.fonts.setComboOverlap(-21);
		
		originalSkiniExpected.catchTheBeat.setHyperDash(new Color(255, 0, 0, 5));
		originalSkiniExpected.catchTheBeat.setHyperDashAfterImage(new Color(255, 244, 234));
		originalSkiniExpected.catchTheBeat.setHyperDashFruit(new Color(224, 214, 204));
		
		return originalSkiniExpected;
	}
}
