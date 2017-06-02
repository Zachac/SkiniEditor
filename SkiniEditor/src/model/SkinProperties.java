package model;

import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * A class to hold all of the properties of a skin.ini file.
 *
 * @author Zachary Chandler
 */
public class SkinProperties {
    
    /** The [General] properties of the file. */
    public final GeneralProperties general;
    
    /** The [Colours] properties of the file. */
    public final ColoursProperties colours;

    /** The [Fonts] properties of the file. */
    public final FontsProperties fonts;
    
    /** The [CatchTheBeat] properties of the file. */
    public final CatchTheBeatProperties catchTheBeat;
    
    /** The [Mania] properties of the file. */
    private final Map<ManiaKeyCount, ManiaProperties> mania;
    
    /**
     * Initialize a default skin.ini file.
     */
    public SkinProperties() {
        general = new GeneralProperties();
        colours = new ColoursProperties();
        fonts = new FontsProperties();
        catchTheBeat = new CatchTheBeatProperties();
        mania = new TreeMap<>();
    }
    
    /**
     * @param keys the number of keys for the [Mania] properties.
     * @return the ManiaProperties associated with the number of keys or null if it doesn't exist.
     * @throws NullPointerException if keys are null.
     */
    public ManiaProperties getMania(ManiaKeyCount keys) {
        Objects.requireNonNull(keys);
        return mania.get(keys);
    }
    
    /**
     * Creates a new ManiaProperties and associates it with this SkinProperties.
     * 
     * @param keys the number of keys for the [Mania] properties.
     * @return the ManiaProperties associated with the number of keys.
     * @throws NullPointerException if keys are null.
     * @throws IllegalArgumentException if there is already a ManiaProperties with that given number of keys.
     */
    public ManiaProperties createManiaProperties(ManiaKeyCount keys) {
        Objects.requireNonNull(keys);
        
        if (mania.containsKey(keys)) {
            throw new IllegalArgumentException("Attempt to create a mania with a key count that already exists.");
        }
        
        return mania.put(keys, new ManiaProperties(keys));
    }
    
    /**
     * Removes a ManiaProperties with the given number of keys.
     * @param keys the number of keys of the ManiaProperties to remove.
     * @throws NullPointerExcpetion if keys are null.
     * @throws IllegalArgumentException if there is not a ManiaProperties with the given number of keys.
     */
    public void removeManiaProperties(ManiaKeyCount keys) {
        Objects.requireNonNull(keys);
        
        if (!mania.containsKey(keys)) {
            throw new IllegalArgumentException("Attempt to remove a mania properties that doesn't exist.");
        }
        
        mania.remove(keys);
    }
    
    
}
