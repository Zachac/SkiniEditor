package view;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.FontsProperties;

/**
 * A class to display and update font properties.
 * 
 * @author Zachac
 */
public class FontsPanel extends JPanel implements Observer {

    /** SVUID */
    private static final long serialVersionUID = -3521128583953110853L;

    private final List<TextBoxProperty> properties;
	private UserContext usr;

    public FontsPanel(UserContext usr) {
        super();
        
        properties = new LinkedList<>();
        this.usr = Objects.requireNonNull(usr);
        
        usr.addObserver(this);
        loadContent();
    }

    /**
     * Load the content of this panel.
     */
    private final void loadContent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        TextBoxProperty prop;

        prop = getFontsTextBoxProperty("HitCirclePrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getFontsTextBoxProperty("HitCircleOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);

        prop = getFontsTextBoxProperty("ScorePrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getFontsTextBoxProperty("ScoreOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);

        prop = getFontsTextBoxProperty("ComboPrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getFontsTextBoxProperty("ComboOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);
        
    }

    /**
     * Get a TextBoxProperty for the given identifier that will set and update
     * a property in FontsProperties.
     * 
     * @param identifier the exact case sensitive name of the property.
     * @param type the type of identifier, only String or int are allowed
     */
    private TextBoxProperty getFontsTextBoxProperty(String identifier,
    		Class<?> type) {

    	Translator translator = getTranslator(type);
    	Method setter = getSetterMethod(identifier, type);
    	Method getter = getGetterMethod(identifier);
    	
    	Getter get = getGetter(getter);
    	Setter set = getSetter(setter, translator);
    	
		return new TextBoxProperty(identifier, set, get);
	}

    /**
     * Get a setter for the given setter method and translator.
     */
	private Setter getSetter(Method setter, Translator translator) {
		return new Setter() {

			@Override
			public String set(Object o) {
				if (usr.getSkinIO() == null) {
					return "Error: no current skin.";
				}
				
				try {
					setter.invoke(usr.getSkinIO().getSkinProperties().fonts,
							translator.translate(o));
				} catch (NumberFormatException e) {
					return "Invalid number format.";
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
				
				return null;
			}			
    	};
	}

	/**
	 * Create a getter for the given getter method.
	 */
	private Getter getGetter(Method getter) {
		return new Getter() {
			@Override
			public Object get() {
				if (usr.getSkinIO() == null) {
					return "";
				}
				
				try {
					return getter.invoke(usr.getSkinIO().getSkinProperties().fonts);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					e.printStackTrace();
					throw new IllegalArgumentException();
				}
			}
    	};
	}

	/**
	 * Get a getter method for the given identifier.
	 */
	private Method getGetterMethod(String identifier) {
		try {
			return FontsProperties.class.getMethod("get" + identifier);
		} catch (NoSuchMethodException | SecurityException e1) {
			e1.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Get a setter method for the given identifier and type of setter.
	 */
	private Method getSetterMethod(String identifier, Class<?> type) {
		try {
			return FontsProperties.class.getMethod("set" + identifier, type);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Get a translator for the given type. Only types int is supported. Any
	 * other type will return a translator that only returns the given object.
	 */
	private Translator getTranslator(Class<?> type) {
		Translator translator;
		if (type.equals(Integer.TYPE)) {
    		translator =  new Translator() {
    			@Override
    			public Object translate(Object o) {
    				return Integer.parseInt(o.toString());
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

	@Override
    public void update(Observable arg0, Object arg1) {
		
		if (arg0 instanceof UserContext) {
			properties.forEach((e) -> {
				e.setEnabled(((UserContext) arg0).getSkinIO() != null);
				e.update();
			});
		}
    }
}
