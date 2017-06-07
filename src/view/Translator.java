package view;

/**
 * A functional interface to translate one object into another type. An example
 * would be a string into an int.
 * 
 * @author Zachac
 */
public interface Translator {
	
	Object translate(Object o);
}
