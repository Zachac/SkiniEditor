package view;

import model.SkinVersion;

/**
 * A functional interface to translate one object into another type. An example
 * would be a string into an int.
 * 
 * @author Zachac
 */
public interface Translator {
	
	Object translate(Object o);
	

	/**
	 * Get a translator for the given type. Only types:
	 * 		Integer
	 * 		int
	 * 		String
	 * 		SkinVersion
	 * 		Boolean for values 0, 1, and 2 where 2 is false
	 * 		boolean for values 0, 1, and 2 where 2 is false
	 *  
	 * 
	 * Any other type will return a translator that only returns the given
	 * object.
	 */
	public static Translator getTranslator(Class<?> type) {
		Translator translator;
		if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
    		translator =  new Translator() {
    			@Override
    			public Object translate(Object o) {
    				return Integer.parseInt(o.toString());
    			}	
        	};
    	} else if (type.equals(SkinVersion.class)) {
    		translator =  new Translator() {
    			@Override
    			public Object translate(Object o) {
    				SkinVersion result = SkinVersion.parse(o.toString());
    				
    				if (result == null) {
    					throw new IllegalArgumentException("Invalid skin version");
    				}
    				
    				return result;
    			}	
        	};
    	} else if (type.equals(Boolean.class) || type.equals(Boolean.TYPE)) {
    		translator =  new Translator() {
    			@Override
    			public Object translate(Object o) {
    				switch(o.toString()) {
    				case "0":
    				case "2":
    					return false;
    					
    				case "1":
    					return true;
    					
					default:
						throw new IllegalArgumentException("Value not found");
    				}
    			}	
        	};
    	} else {
    		translator = new Translator() {
    			@Override
    			public Object translate(Object o) {
    				return o;
    			}
        	};
    	}
		return translator;
	}
}
