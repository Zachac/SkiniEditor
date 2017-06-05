package model;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;

/**
 * A class to hold the properties of [Colours]
 *
 * @author Zachary Chandler
 */
public class ColoursProperties extends Observable implements Properties {
    
    public static final int MAX_COMBO_COLORS = 8;
    
    private final List<Color> comboColors;
    private Color sliderBorder;
    private Color menuGlow;
    private Color sliderBall;
    private Color spinnerBackground;
    private Color songSelectActiveText;
    private Color songSelectInactiveText;
    private Color starBreakAdditive;
    private Color sliderTrackOverride;
    private Color inputOverlayText;
    
    /**
     * Instantiate a new [Colours] properties with default values.
     */
    public ColoursProperties() {
        comboColors = new LinkedList<>();
        
        addComboColor(new Color(255,192,0));
        addComboColor(new Color(0,202,0 ));
        addComboColor(new Color(18,124,255));
        addComboColor(new Color(242,24,57));
        
        setSliderBorder(new Color(255,255,255));
        setMenuGlow(new Color(0,78,155));
        setSliderBall(new Color(2,170,255 ));
        setSpinnerBackground(new Color(100, 100, 100));
        setSongSelectActiveText(new Color(0,0,0));
        setSongSelectInactiveText(new Color(255, 255, 255));
        setStarBreakAdditive(new Color(255,182,193));
        setSliderTrackOverride(null);
        setInputOverlayText(new Color(124,108,246));
    }
    
    /**
     * @return an unmodifiable list of comboColors.
     */
    public List<Color> getComboColors() {
        return Collections.unmodifiableList(comboColors);
    }

    /**
     * @param combo the combo to set.
     * @param c the color of the combo to set.
     * @throws NullPointerException if c is null.
     * @throws IllegalArgumentException if combo is negative or if it exceeds the maximum combo.
     */
    public void assignComboColor(int combo, Color c) {
        Objects.requireNonNull(c);
        
        if (combo < 0 || combo >= comboColors.size()) {
            throw new IllegalArgumentException("Cannot set negative or non-existing combo color.");
        }
        
        comboColors.set(combo, c);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Add the color c to the next available combo.
     * @param c the color to add.
     * @throws NullPointerException if c is null.
     * @throws IllegalArgumentException if the combo colors are already full.
     */
    public void addComboColor(Color c) {
        Objects.requireNonNull(c);
        
        if (comboColors.size() >= MAX_COMBO_COLORS) {
            throw new IllegalArgumentException("Cannot add past max combo color.");
        }
        
        comboColors.add(c);
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * Remove the given combo from the combo colors.
     * @throws IllegalArgumentException if combo is negative or greater than the current number of combo colors.
     */
    public void removeComboColor(int combo) {
        if (combo < 0 || combo >= comboColors.size()) {
            throw new IllegalArgumentException("Cannot remove non-existent combo.");
        }
        
        comboColors.remove(combo);
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the sliderBorder
     */
    public Color getSliderBorder() {
        return sliderBorder;
    }

    /**
     * @param sliderBorder the sliderBorder to set
     * @throws NullPointerException if sliderBorder is null.
     */
    public void setSliderBorder(Color sliderBorder) {
        Objects.requireNonNull(sliderBorder);
        this.sliderBorder = sliderBorder;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the menuGlow
     */
    public Color getMenuGlow() {
        return menuGlow;
    }

    /**
     * @param menuGlow the menuGlow to set
     * @throws NullPointerException if menuGlow is null.
     */
    public void setMenuGlow(Color menuGlow) {
        Objects.requireNonNull(menuGlow);
        this.menuGlow = menuGlow;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the sliderBall
     */
    public Color getSliderBall() {
        return sliderBall;
    }

    /**
     * @param sliderBall the sliderBall to set
     * @throws NullPointerException if sliderBall is null.
     */
    public void setSliderBall(Color sliderBall) {
        Objects.requireNonNull(sliderBall);
        this.sliderBall = sliderBall;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the spinnerBackground
     */
    public Color getSpinnerBackground() {
        return spinnerBackground;
    }

    /**
     * @param spinnerBackground the spinnerBackground to set
     * @throws NullPointerException if spinnerBackground is null.
     */
    public void setSpinnerBackground(Color spinnerBackground) {
        Objects.requireNonNull(spinnerBackground);
        this.spinnerBackground = spinnerBackground;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the songSelectActiveText
     */
    public Color getSongSelectActiveText() {
        return songSelectActiveText;
    }

    /**
     * @param songSelectActiveText the songSelectActiveText to set
     * @throws NullPointerException if songSelectActiveText is null.
     */
    public void setSongSelectActiveText(Color songSelectActiveText) {
        Objects.requireNonNull(songSelectActiveText);
        this.songSelectActiveText = songSelectActiveText;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the songSelectInactiveText
     */
    public Color getSongSelectInactiveText() {
        return songSelectInactiveText;
    }

    /**
     * @param songSelectInactiveText the songSelectInactiveText to set
     * @throws NullPointerException if songSelectInactiveText is null.
     */
    public void setSongSelectInactiveText(Color songSelectInactiveText) {
        Objects.requireNonNull(songSelectInactiveText);
        this.songSelectInactiveText = songSelectInactiveText;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the starBreakAdditive
     */
    public Color getStarBreakAdditive() {
        return starBreakAdditive;
    }

    /**
     * @param starBreakAdditive the starBreakAdditive to set
     * @throws NullPointerException if starBreakAdditive is null.
     */
    public void setStarBreakAdditive(Color starBreakAdditive) {
        Objects.requireNonNull(starBreakAdditive);
        this.starBreakAdditive = starBreakAdditive;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the sliderTrackOverride
     */
    public Color getSliderTrackOverride() {
        return sliderTrackOverride;
    }

    /**
     * @param sliderTrackOverride the sliderTrackOverride to set
     */
    public void setSliderTrackOverride(Color sliderTrackOverride) {
        this.sliderTrackOverride = sliderTrackOverride;
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * @return the inputOverlayText
     */
    public Color getInputOverlayText() {
        return inputOverlayText;
    }

    /**
     * @param inputOverlayText the inputOverlayText to set
     * @throws NullPointerException if inputOverlayText is null.
     */
    public void setInputOverlayText(Color inputOverlayText) {
        Objects.requireNonNull(inputOverlayText);
        this.inputOverlayText = inputOverlayText;
        this.setChanged();
        this.notifyObservers();
    }


    @Override
    public boolean equals(Object other) {
    	if (!(other instanceof ColoursProperties)) {
    		return false;
    	}
    	
    	ColoursProperties o = (ColoursProperties) other;

    	Iterator<Color> oiter = o.comboColors.iterator();
    	Iterator<Color> iter = comboColors.iterator();
    	
    	while (oiter.hasNext() && iter.hasNext()) {
    		if (!oiter.next().equals(iter.next())) {
    			return false;
    		}
    	}
    	
    	if (oiter.hasNext() || iter.hasNext()) {
    		return false;
    	}
    	
    	return o.inputOverlayText.equals(inputOverlayText) &&
    			o.menuGlow.equals(menuGlow) &&
    			o.sliderBall.equals(sliderBall) &&
    			o.sliderBorder.equals(sliderBorder) &&
    			(o.sliderTrackOverride == sliderTrackOverride || o.sliderTrackOverride.equals(sliderTrackOverride)) &&
    			o.songSelectActiveText.equals(songSelectActiveText) &&
    			o.songSelectInactiveText.equals(songSelectInactiveText) &&
    			o.spinnerBackground.equals(spinnerBackground) &&
    			o.starBreakAdditive.equals(starBreakAdditive);
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("[Colours]");
        result.append('\n');

        Iterator<Color> iter = comboColors.iterator();
        
        int i = 0;
        while (iter.hasNext()) {
            i++;
            result.append("Combo");
            result.append(i);
            result.append(": ");
            result.append(iter.next());
            result.append('\n');
        }

        result.append("SliderBorder: ");
        result.append(sliderBorder);
        result.append('\n');
        
        result.append("MenuGlow: ");
        result.append(menuGlow);
        result.append('\n');
        
        result.append("SliderBall: ");
        result.append(sliderBall);
        result.append('\n');

        result.append("SpinnerBackground: ");
        result.append(spinnerBackground);
        result.append('\n');
        
        result.append("SongSelectActiveText: ");
        result.append(songSelectActiveText);
        result.append('\n');
        
        result.append("SongSelectInactiveText: ");
        result.append(songSelectInactiveText);
        result.append('\n');
        
        result.append("StarBreakAdditive: ");
        result.append(starBreakAdditive);
        result.append('\n');
        
        if (sliderTrackOverride != null) {
            result.append("SliderTrackOverride: ");
            result.append(sliderTrackOverride);
            result.append('\n');            
        }
        
        result.append("InputOverlayText: ");
        result.append(inputOverlayText);
        result.append('\n');
        
        
        
        return result.toString();
    }
}
