package view;

/**
 * A functional interface to wrap a setter.
 * 
 * @author Zachac
 */
public interface Setter {
	
	/**
	 * Attempts to set the value o. If the setter fails, returns a non null
	 * string with an error message.
	 */
	String set(Object o);
}
