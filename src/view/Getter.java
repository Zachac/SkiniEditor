package view;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

import model.SkinProperties;

/**
 * A functional interface to wrap a getter.
 * 
 * @author Zachac
 */
public interface Getter {
	Object get();
	

	/**
	 * Create a getter for the given getter method.
	 * @throws NullPointerException if any parameter is null.
	 */
	public static Getter getGetter(Method getter, UserContext usr) {
		Objects.requireNonNull(getter);
		Objects.requireNonNull(usr);
		
		Field result = null;
		
		for (Field f : SkinProperties.class.getFields()) {
			if (f.getType().equals(getter.getDeclaringClass())) {
				result = f;
				break;
			}
		}
		
		if (result == null) {
			throw new IllegalArgumentException();
		}
		
		Field properties = result;
		
		return new Getter() {
			@Override
			public Object get() {
				if (usr.getSkinIO() == null) {
					return "";
				}
				
				try {
					return getter.invoke(
							properties.get(usr.getSkinIO().getSkinProperties()));
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
			}
    	};
	}
}
