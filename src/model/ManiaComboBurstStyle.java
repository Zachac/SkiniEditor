package model;


public enum ManiaComboBurstStyle {
    BOTH(0), RIGHT(1), LEFT(2);
    
    private final String value;

    ManiaComboBurstStyle(int value) {
        this.value = "" + value;
    }
    
    @Override
    public String toString() {
        return value;
    }
}
