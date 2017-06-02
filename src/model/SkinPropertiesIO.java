package model;

import java.io.File;

public class SkinPropertiesIO {
    
    /**
     * Instantiates the IO for a given file. If the file is readable the associated profile for this object will be'
     * loaded from associatedFile.
     * 
     * @param associatedFile
     */
    public SkinPropertiesIO(File associatedFile) {
        
    }
    
    /**
     * @return the skin properties of this file.
     */
    public SkinProperties getSkinProperties() {
        return null;
    }
    
    /**
     * Save the SkinProperties to the file.
     */
    public void save() {
        
    }
    
    /**
     * @return a SkinProperties for the associatedFile.
     */
    public static final SkinProperties load(File associatedFile) {
        return null;
    }
}
