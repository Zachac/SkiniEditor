package model;

public enum ManiaKeyCount {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    TWELVE(12),
    FOURTEEN(14),
    SIXTEEN(16),
    EIGHTEEN(18);
    
    public final int value;

    ManiaKeyCount(int value) {
        this.value = value;
    }
    
    public static ManiaKeyCount valueOf(int value) {
        
        switch (value) {
            case 1: return ONE;
            case 2: return TWO;
            case 3: return THREE;
            case 4: return FOUR;
            case 5: return FIVE;
            case 6: return SIX;
            case 7: return SEVEN;
            case 8: return EIGHT;
            case 9: return NINE;
            case 10: return TEN;
            case 12: return TWELVE;
            case 14: return FOURTEEN;
            case 16: return SIXTEEN;
            case 18: return EIGHTEEN;
            default: return null;
        }
    }
}
