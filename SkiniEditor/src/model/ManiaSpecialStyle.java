package model;

public enum ManiaSpecialStyle {
    MIDDLE(0), LEFT(1), RIGHT(2);
    
    private final String value;

    ManiaSpecialStyle(int value) {
        this.value = "" + value;
    }
    
    @Override
    public String toString() {
        return value;
    }
}
