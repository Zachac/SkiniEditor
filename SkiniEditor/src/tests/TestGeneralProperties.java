package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.GeneralProperties;
import model.SkinVersion;

public class TestGeneralProperties {

    GeneralProperties general;
    
    @Before
    public void setUp() throws Exception {
        general = new GeneralProperties();
    }

    @Test
    public void testInitialValues() {
        //no default value, these just need to be valid
        assertNotEquals(general.getName(), null);
        assertNotEquals(general.getName(), "");
        assertNotEquals(general.getAuthor(), null);
        assertNotEquals(general.getAuthor(), "");
        assertNotEquals(general.getVersion(), null);
        
        assertEquals(true, general.isSliderBallFlip());
        assertEquals(general.getSliderBallFrames(),  0);

        assertEquals(false, general.isAllowSliderBallTint());
        assertEquals(true, general.isSliderStyle());
        assertEquals(true, general.isCursorRotate());
        assertEquals(true, general.isCursorExpand());
        assertEquals(true, general.isCursorCentre());
        assertEquals(true, general.isCursorTrailRotate());
        assertEquals(true, general.isHitCircleOverlayAboveNumber());
        assertEquals(true, general.isSpinnerFrequencyModulate());
        assertEquals(true, general.isLayeredHitSounds());
        assertEquals(true, general.isSpinnerFadePlayfield());
        assertEquals(false, general.isSpinnerNoBlink());
        assertEquals(false, general.isComboBurstRandom());
        assertEquals(0, general.getAnimationFramerate());
        
        // not sure what the default is on this, for default, I'm just not going include it.
        assertEquals(0, general.getCustomComboBurstSounds().size());
    }

    @Test(expected = NullPointerException.class)
    public void testSetName_Null_NullPointerException() {
        general.setName(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetName_EmptyString_IllegalArgumentException() {
        general.setName("");
    }

    @Test(expected = NullPointerException.class)
    public void testSetAuthor_Null_NullPointerException() {
        general.setAuthor(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetAuthor_EmptyString_IllegalArgumentException() {
        general.setAuthor("");
    }
    
    @Test(expected = NullPointerException.class)
    public void testSetVersion_Null_NullPointerException() {
        general.setVersion(null);
    }
    
    @Test
    public void testSetVersion_ValidVersion_IsSet() {
        assertNotEquals(general.getVersion(), SkinVersion.TWOPOINTTHREE);
        general.setVersion(SkinVersion.TWOPOINTTHREE);
        assertEquals(general.getVersion(), SkinVersion.TWOPOINTTHREE);
    }

    @Test
    public void testSetSliderBallFlip() {
        general.setSliderBallFlip(false);
        assertFalse(general.isSliderBallFlip());
        general.setSliderBallFlip(true);
        assertTrue(general.isSliderBallFlip());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetSliderBallFrames_NegativeValue_IllegalArgumentException() {
        general.setSliderBallFrames(-5);
    }
    
    @Test
    public void testSetSliderBallFrames_PositiveeValue_IsSet() {
        general.setSliderBallFrames(5);
        assertEquals(5, general.getSliderBallFrames());
    }
    
    @Test
    public void testSetSliderBallFrames_ZeroValue_IsSet() {
        general.setSliderBallFrames(0);
        assertEquals(0, general.getSliderBallFrames());
    }

    @Test
    public void testSetAllowSliderBallTint() {
        general.setAllowSliderBallTint(false);
        assertFalse(general.isAllowSliderBallTint());
        general.setAllowSliderBallTint(true);
        assertTrue(general.isAllowSliderBallTint());
    }

    @Test
    public void testSetSliderStyle() {
        general.setSliderStyle(false);
        assertFalse(general.isSliderStyle());
        general.setSliderStyle(true);
        assertTrue(general.isSliderStyle());
    }

    @Test
    public void testSetCursorRotate() {
        general.setCursorRotate(false);
        assertFalse(general.isCursorRotate());
        general.setCursorRotate(true);
        assertTrue(general.isCursorRotate());
    }

    @Test
    public void testSetCursorExpand() {
        general.setCursorExpand(false);
        assertFalse(general.isCursorExpand());
        general.setCursorExpand(true);
        assertTrue(general.isCursorExpand());
    }

    @Test
    public void testSetCursorCentre() {
        general.setCursorCentre(false);
        assertFalse(general.isCursorCentre());
        general.setCursorCentre(true);
        assertTrue(general.isCursorCentre());
    }

    @Test
    public void testSetCursorTrailRotate() {
        general.setCursorTrailRotate(false);
        assertFalse(general.isCursorTrailRotate());
        general.setCursorTrailRotate(true);
        assertTrue(general.isCursorTrailRotate());
    }

    @Test
    public void testSetHitCircleOverlayAboveNumber() {
        general.setHitCircleOverlayAboveNumber(false);
        assertFalse(general.isHitCircleOverlayAboveNumber());
        general.setHitCircleOverlayAboveNumber(true);
        assertTrue(general.isHitCircleOverlayAboveNumber());
    }

    @Test
    public void testSetSpinnerFrequencyModulate() {
        general.setSpinnerFrequencyModulate(false);
        assertFalse(general.isSpinnerFrequencyModulate());
        general.setSpinnerFrequencyModulate(true);
        assertTrue(general.isSpinnerFrequencyModulate());
    }

    @Test
    public void testSetLayeredHitSounds() {
        general.setLayeredHitSounds(false);
        assertFalse(general.isLayeredHitSounds());
        general.setLayeredHitSounds(true);
        assertTrue(general.isLayeredHitSounds());
    }

    @Test
    public void testSetSpinnerFadePlayfield() {
        general.setSpinnerFadePlayfield(false);
        assertFalse(general.isSpinnerFadePlayfield());
        general.setSpinnerFadePlayfield(true);
        assertTrue(general.isSpinnerFadePlayfield());
    }

    @Test
    public void testSetSpinnerNoBlink() {
        general.setSpinnerNoBlink(false);
        assertFalse(general.isSpinnerNoBlink());
        general.setSpinnerNoBlink(true);
        assertTrue(general.isSpinnerNoBlink());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAnimationFramerate_NegativeValue_IllegalArgumentException() {
        general.setAnimationFramerate(-5);
    }
    
    @Test
    public void testSetAnimationFramerate_PositiveeValue_IsSet() {
        general.setAnimationFramerate(5);
        assertEquals(5, general.getAnimationFramerate());
    }
    
    @Test
    public void testSetAnimationFramerate_ZeroValue_IsSet() {
        general.setAnimationFramerate(0);
        assertEquals(0, general.getAnimationFramerate());
    }

    @Test
    public void testSetComboBurstRandom() {
        general.setComboBurstRandom(false);
        assertFalse(general.isComboBurstRandom());
        general.setComboBurstRandom(true);
        assertTrue(general.isComboBurstRandom());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetCustomComboBurstSounds_AttemptModification_UnsupportedOperationException() {
        general.getCustomComboBurstSounds().add(22);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCustomComboBurstSound_Zero_IllegalArgumentException() {
        general.addCustomComboBurstSound(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCustomComboBurstSound_Negative_IllegalArgumentException() {
        general.addCustomComboBurstSound(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testAddCustomComboBurstSound_AComboThatAlreadyExists_IllegalArgumentException() {
        general.addCustomComboBurstSound(5);
        general.addCustomComboBurstSound(5);
    }
    
    @Test
    public void testAddCustomComboBurstSound_ANormalCombo_IsAdded() {
        general.addCustomComboBurstSound(5);
        assertEquals(general.getCustomComboBurstSounds().get(0), new Integer(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCustomComboBurstSound_NegativeValue_IllegalArgumentException() {
        general.removeCustomComboBurstSound(-1);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCustomComboBurstSound_Zero_IllegalArgumentException() {
        general.removeCustomComboBurstSound(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCustomComboBurstSound_NonExistentCombo_IllegalArgumentException() {
        general.removeCustomComboBurstSound(50);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveCustomComboBurstSound_ExistingCombo_IsRemoved() {
        general.removeCustomComboBurstSound(50);
        assertEquals(general.getCustomComboBurstSounds().get(0), new Integer(50));
        general.removeCustomComboBurstSound(50);
        assertEquals(general.getCustomComboBurstSounds().size(), 0);
    }

}
