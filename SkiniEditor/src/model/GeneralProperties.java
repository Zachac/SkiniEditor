package model;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

public class GeneralProperties extends Observable {
    
    private static final Comparator<Integer> INT_COMPARATOR = new Comparator<Integer>() {

        @Override
        public int compare(Integer arg0, Integer arg1) {
            return arg0.compareTo(arg1);
        }
    };
    
    private String name;
    private String author;
    
    private SkinVersion version;
    
    private int sliderBallFrames;
    
    private boolean sliderBallFlip;
    private boolean allowSliderBallTint;
    private boolean sliderStyle;
    
    private boolean cursorRotate;
    private boolean cursorExpand;
    private boolean cursorCentre;
    private boolean cursorTrailRotate;
    
    private boolean hitCircleOverlayAboveNumber;
    private boolean spinnerFrequencyModulate;
    private boolean layeredHitSounds;
    private boolean spinnerFadePlayfield;
    private boolean spinnerNoBlink;
    
    private int animationFramerate;
    
    private final List<Integer> customComboBurstSounds;
    
    private boolean comboBurstRandom;
    
    public GeneralProperties() {
        customComboBurstSounds = new LinkedList<>();
        
        this.setName("Default Skini Editor Name");
        this.setAuthor("Unkown");
        this.setVersion(SkinVersion.LATEST);

        this.setSliderBallFlip(true);
        this.setAllowSliderBallTint(false);
        this.setSliderStyle(true);
        this.setCursorRotate(true);
        this.setCursorExpand(true);
        this.setCursorCentre(true);
        this.setCursorTrailRotate(true);
        this.setHitCircleOverlayAboveNumber(true);
        this.setSpinnerFrequencyModulate(true);
        this.setLayeredHitSounds(true);
        this.setSpinnerFadePlayfield(true);
        this.setSpinnerNoBlink(false);
        this.setComboBurstRandom(false);
        
        this.setAnimationFramerate(0);
        this.setSliderBallFrames(0);
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     * @throws NullPointerException if name is null.
     * @throws IllegalArgumentException if name is the empty string.
     */
    public void setName(String name) {
        Objects.requireNonNull(name);
        
        if (name.equals("")) {
            throw new IllegalArgumentException("Cannot set empty string.");
        }
        
        this.name = name;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }
    
    /**
     * @param author the author to set
     * @throws NullPointerException if author is null.
     * @throws IllegalArgumentException if author is the empty string.
     */
    public void setAuthor(String author) {
        Objects.requireNonNull(author);
        
        if (author.equals("")) {
            throw new IllegalArgumentException("Cannot set empty string.");
        }
        this.author = author;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the version
     */
    public SkinVersion getVersion() {
        return version;
    }
    
    /**
     * @param version the version to set
     * @throws NullPointerException if version is null.
     */
    public void setVersion(SkinVersion version) {
        Objects.requireNonNull(version);
        
        this.version = version;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the sliderBallFlip
     */
    public boolean isSliderBallFlip() {
        return sliderBallFlip;
    }
    
    /**
     * @param sliderBallFlip the sliderBallFlip to set
     */
    public void setSliderBallFlip(boolean sliderBallFlip) {
        this.sliderBallFlip = sliderBallFlip;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the sliderBallFrames
     */
    public int getSliderBallFrames() {
        return sliderBallFrames;
    }
    
    /**
     * @param sliderBallFrames the sliderBallFrames to set
     * @throws IllegalArgumentException if sliderBallFrames is negative
     */
    public void setSliderBallFrames(int sliderBallFrames) {
        if (sliderBallFrames < 0) {
            throw new IllegalArgumentException("Cannot set negative framerates.");
        }
        
        this.sliderBallFrames = sliderBallFrames;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the allowSliderBallTint
     */
    public boolean isAllowSliderBallTint() {
        return allowSliderBallTint;
    }
    
    /**
     * @param allowSliderBallTint the allowSliderBallTint to set
     */
    public void setAllowSliderBallTint(boolean allowSliderBallTint) {
        this.allowSliderBallTint = allowSliderBallTint;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the sliderStyle
     */
    public boolean isSliderStyle() {
        return sliderStyle;
    }
    
    /**
     * @param sliderStyle the sliderStyle to set
     */
    public void setSliderStyle(boolean sliderStyle) {
        this.sliderStyle = sliderStyle;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the cursorRotate
     */
    public boolean isCursorRotate() {
        return cursorRotate;
    }
    
    /**
     * @param cursorRotate the cursorRotate to set
     */
    public void setCursorRotate(boolean cursorRotate) {
        this.cursorRotate = cursorRotate;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the cursorExpand
     */
    public boolean isCursorExpand() {
        return cursorExpand;
    }
    
    /**
     * @param cursorExpand the cursorExpand to set
     */
    public void setCursorExpand(boolean cursorExpand) {
        this.cursorExpand = cursorExpand;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the cursorCentre
     */
    public boolean isCursorCentre() {
        return cursorCentre;
    }
    
    /**
     * @param cursorCentre the cursorCentre to set
     */
    public void setCursorCentre(boolean cursorCentre) {
        this.cursorCentre = cursorCentre;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the cursorTrailRotate
     */
    public boolean isCursorTrailRotate() {
        return cursorTrailRotate;
    }
    
    /**
     * @param cursorTrailRotate the cursorTrailRotate to set
     */
    public void setCursorTrailRotate(boolean cursorTrailRotate) {
        this.cursorTrailRotate = cursorTrailRotate;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the hitCircleOverlayAboveNumber
     */
    public boolean isHitCircleOverlayAboveNumber() {
        return hitCircleOverlayAboveNumber;
    }
    
    /**
     * @param hitCircleOverlayAboveNumber the hitCircleOverlayAboveNumber to set
     */
    public void setHitCircleOverlayAboveNumber(boolean hitCircleOverlayAboveNumber) {
        this.hitCircleOverlayAboveNumber = hitCircleOverlayAboveNumber;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the spinnerFrequencyModulate
     */
    public boolean isSpinnerFrequencyModulate() {
        return spinnerFrequencyModulate;
    }
    
    /**
     * @param spinnerFrequencyModulate the spinnerFrequencyModulate to set
     */
    public void setSpinnerFrequencyModulate(boolean spinnerFrequencyModulate) {
        this.spinnerFrequencyModulate = spinnerFrequencyModulate;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the layeredHitSounds
     */
    public boolean isLayeredHitSounds() {
        return layeredHitSounds;
    }
    
    /**
     * @param layeredHitSounds the layeredHitSounds to set
     */
    public void setLayeredHitSounds(boolean layeredHitSounds) {
        this.layeredHitSounds = layeredHitSounds;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the spinnerFadePlayfield
     */
    public boolean isSpinnerFadePlayfield() {
        return spinnerFadePlayfield;
    }
    
    /**
     * @param spinnerFadePlayfield the spinnerFadePlayfield to set
     */
    public void setSpinnerFadePlayfield(boolean spinnerFadePlayfield) {
        this.spinnerFadePlayfield = spinnerFadePlayfield;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the spinnerNoBlink
     */
    public boolean isSpinnerNoBlink() {
        return spinnerNoBlink;
    }
    
    /**
     * @param spinnerNoBlink the spinnerNoBlink to set
     */
    public void setSpinnerNoBlink(boolean spinnerNoBlink) {
        this.spinnerNoBlink = spinnerNoBlink;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the animationFramerate
     */
    public int getAnimationFramerate() {
        return animationFramerate;
    }
    
    /**
     * @param animationFramerate the animationFramerate to set
     * @throws IllegalArgumentException if animationFramerate is negative
     */
    public void setAnimationFramerate(int animationFramerate) {
        if (animationFramerate < 0) {
            throw new IllegalArgumentException("Cannot set negative framerates.");
        }
        
        this.animationFramerate = animationFramerate;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the comboBurstRandom
     */
    public boolean isComboBurstRandom() {
        return comboBurstRandom;
    }
    
    /**
     * @param comboBurstRandom the comboBurstRandom to set
     */
    public void setComboBurstRandom(boolean comboBurstRandom) {
        this.comboBurstRandom = comboBurstRandom;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the customComboBurstSounds
     */
    public List<Integer> getCustomComboBurstSounds() {
        return Collections.unmodifiableList(customComboBurstSounds);
    }

    /**
     * @param customComboBurstSound the customComboBurstSound to add
     * @throws IllegalArgumentException if customComboBurstSound is negative or zero or has been added without being
     * removed.
     */
    public void addCustomComboBurstSound(int customComboBurstSound) {
        if (customComboBurstSound <= 0) {
            throw new IllegalArgumentException("Cannot have zero or negative combo.");
        } else if (customComboBurstSounds.contains(new Integer(customComboBurstSound))) {
            throw new IllegalArgumentException("Cannot add duplicate combo.");
        }
        
        customComboBurstSounds.add(customComboBurstSound);
        customComboBurstSounds.sort(INT_COMPARATOR);
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @param customComboBurstSound the customComboBurstSound to remove
     * @throws IllegalArgumentException if customComboBurstSound has not been added or has already been removed.
     */
    public void removeCustomComboBurstSound(int customComboBurstSound) {
        if (!customComboBurstSounds.contains(customComboBurstSound)) {
            throw new IllegalArgumentException("Cannot remove combo that isn't added.");
        }
        
        customComboBurstSounds.remove(customComboBurstSound);
        this.setChanged();
        this.notifyObservers();
    }
}
