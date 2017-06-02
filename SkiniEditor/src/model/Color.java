package model;

/**
 * A class to represent a color. r,g,b,a values are always between 0 and 255 inclusively.
 *
 * @author Zachary Chandler
 */
public class Color {

    /** The red color. */
    public final int r;

    /** The green color. */
    public final int g;
    
    /** The blue color. */
    public final int b;

    /** The alpha opacity. */
    public final int a;

    /**
     * Create a color with the given values.
     * @throws IllegalArgumentException if r, g, b, or a are negative or exceed 255.
     */
    public Color(int r, int g, int b, int a) {
        if (r < 0 || r > 255
                || g < 0 || g > 255
                || b < 0 || b > 255
                || a < 0 || a > 255) {
            throw new IllegalArgumentException("Cannot create a color with a value less than zero or more than 255.");
        }
        
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Create a color with the given values and an alpha of 255.
     * @throws IllegalArgumentException if r, g, b, or a are negative or exceed 255.
     */
    public Color(int r, int g, int b) {
        this(r, g, b, 255);
    }
    
    /**
     * Parse a color from the string of comma separated values in the form of "r, g, b" or "r, g, b, a" where whitespace
     * is ignored.
     * 
     * @param s the string to parse from.
     * @return a Color with the given values in s
     * @throws IllegalArgumentException if s isn't in the correct format.
     */
    public static Color parse(String s) {
        return null;
    }

    /**
     * A string with the values of this color in it, Such that it can be parsed by Color.parse
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append(r);
        result.append(", ");
        result.append(g);
        result.append(", ");
        result.append(b);
        
        if (a != 255) {
            result.append(", ");
            result.append(a);
        }
        
        return result.toString();
    }
    
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Color)) {
            return false;
        }
        
        Color otherColor = (Color) other;
        return this.r == otherColor.r &&
                this.g == otherColor.g &&
                this.b == otherColor.b &&
                this.a == otherColor.a;
    }
}
