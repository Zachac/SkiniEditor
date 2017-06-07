package view;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import model.SkinProperties;

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
	
	/**
     * Get a setter for the given setter method and translator.
     * @throws NullPointerException if any paramamter is null.
     */
	public static Setter getSetter(Method setter, Translator translator, UserContext usr) {
		Objects.requireNonNull(usr);
		Objects.requireNonNull(translator);
		Objects.requireNonNull(setter);

		Field result = null;
		
		for (Field f : SkinProperties.class.getFields()) {
			if (f.getType().equals(setter.getDeclaringClass())) {
				result = f;
				break;
			}
		}
		
		if (result == null) {
			throw new IllegalArgumentException();
		}
		
		Field properties = result;
		
		return new Setter() {
			
			@Override
			public String set(Object o) {
				if (usr.getSkinIO() == null) {
					return "Error: no current skin.";
				}
				
				try {
					setter.invoke(properties.get(usr.getSkinIO().getSkinProperties()),
							translator.translate(o));
				} catch (NumberFormatException e) {
					return "Invalid number format.";
				} catch (IllegalArgumentException e) {
					return e.getMessage();
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
				
				return null;
			}			
    	};
	}
}
