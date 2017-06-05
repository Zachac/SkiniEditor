package model;

import java.util.Objects;
import java.util.Observable;

/**
 * A class to hold the properties of [Fonts]
 *
 * @author Zachary Chandler
 */
public class FontsProperties extends Observable implements Properties {

    private String hitCirclePrefix;
    private int hitCircleOverlap;
    private String scorePrefix;
    private int scoreOverlap;
    private String comboPrefix;
    private int comboOverlap;
    
    /**
     * Instantiate a new [Fonts] properties with default values.
     */
    public FontsProperties() {
        setHitCircleOverlap(-2);
        setScoreOverlap(-2);
        setComboOverlap(-2);

        setHitCirclePrefix("default");
        setScorePrefix("score");
        setComboPrefix("score");
    }
    
    /**
     * @return the hitCirclePrefix
     */
    public String getHitCirclePrefix() {
        return hitCirclePrefix;
    }
    
    /**
     * @param hitCirclePrefix the hitCirclePrefix to set
     * @throws NullPointerException if an argument is null.
     * @throws IllegalArgumentException if an argument is the empty string.
     */
    public void setHitCirclePrefix(String hitCirclePrefix) {
        Objects.requireNonNull(hitCirclePrefix);
        
        if (hitCirclePrefix.equals("")) {
            throw new IllegalArgumentException("Cannot set empty string.");
        }
        
        this.hitCirclePrefix = hitCirclePrefix;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the hitCircleOverlap
     */
    public int getHitCircleOverlap() {
        return hitCircleOverlap;
    }
    
    /**
     * @param hitCircleOverlap the hitCircleOverlap to set
     */
    public void setHitCircleOverlap(int hitCircleOverlap) {
        this.hitCircleOverlap = hitCircleOverlap;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the scorePrefix
     */
    public String getScorePrefix() {
        return scorePrefix;
    }
    
    /**
     * @param scorePrefix the scorePrefix to set
     * @throws NullPointerException if an argument is null.
     * @throws IllegalArgumentException if an argument is the empty string.
     */
    public void setScorePrefix(String scorePrefix) {
        Objects.requireNonNull(scorePrefix);
        
        if (scorePrefix.equals("")) {
            throw new IllegalArgumentException("Cannot set empty string.");
        }
        
        this.scorePrefix = scorePrefix;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the scoreOverlap
     */
    public int getScoreOverlap() {
        return scoreOverlap;
    }
    
    /**
     * @param scoreOverlap the scoreOverlap to set
     */
    public void setScoreOverlap(int scoreOverlap) {
        this.scoreOverlap = scoreOverlap;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the comboPrefix
     */
    public String getComboPrefix() {
        return comboPrefix;
    }
    
    /**
     * @param comboPrefix the comboPrefix to set
     * @throws NullPointerException if an argument is null.
     * @throws IllegalArgumentException if an argument is the empty string.
     */
    public void setComboPrefix(String comboPrefix) {
        Objects.requireNonNull(comboPrefix);
        
        if (comboPrefix.equals("")) {
            throw new IllegalArgumentException("Cannot set empty string.");
        }
        
        this.comboPrefix = comboPrefix;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the comboOverlap
     */
    public int getComboOverlap() {
        return comboOverlap;
    }
    
    /**
     * @param comboOverlap the comboOverlap to set
     */
    public void setComboOverlap(int comboOverlap) {
        this.comboOverlap = comboOverlap;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public boolean equals(Object other) {
    	if (!(other instanceof FontsProperties)) {
    		return false;
    	}
    	
    	FontsProperties o = (FontsProperties) other;
    	return o.comboOverlap == comboOverlap &&
    			o.hitCircleOverlap == hitCircleOverlap &&
    			o.scoreOverlap == scoreOverlap &&
    			o.comboPrefix.equals(comboPrefix) &&
    			o.hitCirclePrefix.equals(hitCirclePrefix) &&
    			o.scorePrefix.equals(scorePrefix);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("[Fonts]");
        result.append('\n');

        result.append("HitCirclePrefix: ");
        result.append(hitCirclePrefix.toString());
        result.append('\n');

        result.append("HitCircleOverlap: ");
        result.append(hitCircleOverlap);
        result.append('\n');
        
        result.append("ScorePrefix: ");
        result.append(scorePrefix.toString());
        result.append('\n');

        result.append("ScoreOverlap: ");
        result.append(scoreOverlap);
        result.append('\n');
        
        result.append("ComboPrefix: ");
        result.append(comboPrefix.toString());
        result.append('\n');

        result.append("ComboOverlap: ");
        result.append(comboOverlap);
        result.append('\n');
        
        return result.toString();
    }
}
