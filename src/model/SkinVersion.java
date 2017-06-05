package model;

public enum SkinVersion {
    ONE("1.0"), 
    TWOPOINTZERO("2.0"),
    TWOPOINTONE("2.1"),
    TWOPOINTTWO("2.2"),
    TWOPOINTTHREE("2.3"),
    TWOPOINTFOUR("2.4"),
    TWOPOINTFIVE("2.5"),
    LATEST("latest");
    
    
    public final String value;

    SkinVersion(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value;
    }

    public static SkinVersion parse(String string) {
        
        switch(string.toUpperCase()) {
        case "LATEST":  return LATEST;
        case "1.0":     return ONE;
        case "2.5":     return TWOPOINTFIVE;
        case "2.4":     return TWOPOINTFOUR;
        case "2.1":     return TWOPOINTONE;
        case "2.3":     return TWOPOINTTHREE;
        case "2.2":     return TWOPOINTTWO;
        case "2.0":     return TWOPOINTZERO;
        default:
            throw new IllegalArgumentException("Could not find skin version: " + string);
        }
    }
}
