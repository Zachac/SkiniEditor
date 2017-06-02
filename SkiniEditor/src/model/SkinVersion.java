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
}
