package model;

public enum ManiaNoteBodyStyle {
    STRECHED(0), REPEAT_TOP(1), REPEAT_BOTTOM(2);
    
    private final String value;

    ManiaNoteBodyStyle(int value) {
        this.value = "" + value;
    }
    
    @Override
    public String toString() {
        return value;
    }
}
