package model;

import java.util.Objects;
import java.util.Observable;

/**
 * A class to hold the properties of [CatchTheBeat]
 *
 * @author Zachary Chandler
 */
public class CatchTheBeatProperties extends Observable {
    
    private Color hyperDash;
    private Color hyperDashAfterImage;
    private Color hyperDashFruit;
    
    /**
     * Instantiate a new [CatchTheBeat] properties with default values.
     */
    public CatchTheBeatProperties() {
        this.setHyperDash(new Color(255, 0, 0));
        this.setHyperDashFruit(new Color(255, 0, 0));
        this.setHyperDashAfterImage(new Color(255, 0, 0));
    }
    
    /**
     * @return the hyperDash
     */
    public Color getHyperDash() {
        return hyperDash;
    }
    
    /**
     * @param hyperDash the hyperDash to set
     */
    public void setHyperDash(Color hyperDash) {
        Objects.requireNonNull(hyperDash);
        this.hyperDash = hyperDash;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the hyperDashAfterImage
     */
    public Color getHyperDashAfterImage() {
        return hyperDashAfterImage;
    }
    
    /**
     * @param hyperDashAfterImage the hyperDashAfterImage to set
     */
    public void setHyperDashAfterImage(Color hyperDashAfterImage) {
        Objects.requireNonNull(hyperDashAfterImage);
        this.hyperDashAfterImage = hyperDashAfterImage;
        this.setChanged();
        this.notifyObservers();
    }
    
    /**
     * @return the hyperDashFruit
     */
    public Color getHyperDashFruit() {
        return hyperDashFruit;
    }
    
    /**
     * @param hyperDashFruit the hyperDashFruit to set
     */
    public void setHyperDashFruit(Color hyperDashFruit) {
        Objects.requireNonNull(hyperDashFruit);
        this.hyperDashFruit = hyperDashFruit;
        this.setChanged();
        this.notifyObservers();
    }
}