package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

import model.MColor;
import model.Properties;
import model.SkinProperties;
import model.SkinPropertiesIO;

/**
 * A class to display a color property and to set it.
 *
 * @author Zachary Chandler
 */
public class ColorProperty extends JPanel implements Observer {

    /** SVUID */
    private static final long serialVersionUID = 8251909035387261368L;
    
    private Field properties;
    private Method setterMethod;
    private Method getterMethod;
    private ColorPropertyDisplay colorShower;
    private SkinPropertiesIO currentSkinIO;
    private ColorSetter colorSet;

    /**
     * @param usr the SkinPropertiesIO to observe.
     * @param properties the field in SkinProperties with the property to set and observe.
     * @param identifier a string representation of the property.
     * @throws NullPointerException if any of the parameters are null.
     * @throws IllegalArgumentException if properties is not Observable or a Properties or if properties isn't a field 
     * of SkinProperties. 
     */
    public ColorProperty(UserContext usr, Field properties, String identifier) {
        Objects.requireNonNull(usr);
        Objects.requireNonNull(properties);
        Objects.requireNonNull(identifier);
        
        usr.addObserver(this);
        this.properties = properties;

        if (!Observable.class.isAssignableFrom(properties.getType()) 
                || !Properties.class.isAssignableFrom(properties.getType())
                || !properties.getDeclaringClass().equals(SkinProperties.class)) {
            throw new IllegalArgumentException("Invalid field.");
        }
        
        loadMethods(identifier);
        loadContent(identifier);
    }

    /**
     * Loads the getter and setter methods from the properties field.
     */
    private final void loadMethods(String identifier) {
        Class<?> c = properties.getType();
        
        for (Method m : c.getMethods()) {
            if (m.getName().equals("set" + identifier)) {
                setterMethod = m;
                
                if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(MColor.class)) {
                    throw new IllegalStateException("Invalid paramaters for " + identifier);
                }
                
                if (getterMethod != null) {
                    break;
                }
            } else if (m.getName().equals("get" + identifier)) {
                getterMethod = m;
                
                if (!m.getReturnType().equals(MColor.class)) {
                    throw new IllegalStateException("Invalid return type for " + identifier);
                }
                
                if (setterMethod != null) {
                    break;
                }
            }
        }
        
        if ((setterMethod == null || getterMethod == null)) {
            throw new IllegalStateException("Couldn't find " + (setterMethod == null ? "setter" : "getter") 
                    + " for " + identifier);
        }
    }
    
    /**
     * Load the content of the panel.
     */
    private final void loadContent(String identifier) {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setMaximumSize(ColorPropertyDisplay.MAX_SIZE);

        colorShower = new ColorPropertyDisplay(identifier);
        colorShower.addMouseListener(new ColorPicker(this));
        this.add(colorShower);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (arg0 instanceof UserContext) {
            if (((UserContext) arg0).getSkinIO() != this.currentSkinIO) {
                unloadSkinProperties();
                loadSkinProperties(((UserContext) arg0).getSkinIO().getSkinProperties());
                this.currentSkinIO = ((UserContext) arg0).getSkinIO();
            }
        } else if (arg0.getClass().equals(properties.getType())) {
            updateColor(arg0);
        } else {
            throw new IllegalStateException();
        }
    }
    
    /**
     * Update the color from thePropertyFiled
     */
    private void updateColor(Observable theProertyField) {
        try {
            MColor c = (MColor) getterMethod.invoke(theProertyField);
            colorShower.setColor(c.asAwtcolor());
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load the given skin.
     */
    private void loadSkinProperties(SkinProperties skin) {
        if (skin != null) {
            try {
                Observable newProps = (Observable) properties.get(skin);
                newProps.addObserver(this);
                colorShower.setColor(((MColor) getterMethod.invoke(newProps)).asAwtcolor());
                this.colorSet = (c) -> {
                    try {
                        setterMethod.invoke(newProps, c);
                    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }  
                };
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Unload the current skin.
     */
    private void unloadSkinProperties() {
        if (currentSkinIO != null) {
            try {
                Observable oldProps = (Observable) properties.get(currentSkinIO.getSkinProperties());
                oldProps.deleteObserver(this);
                this.colorSet = null;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            
            colorShower.showAsDisabled();
        }
    }
    
    /**
     * A mouse listener to choose a new color.
     *
     * @author Zachary Chandler
     */
    private static final class ColorPicker implements MouseListener {

        private ColorProperty p;
        
        public ColorPicker(ColorProperty colorProperty) {
            this.p = colorProperty;
        }

        @Override
        public void mouseClicked(MouseEvent arg0) {
             if (p.colorSet != null) {
                Color color = null;
                try {
                    color = ((MColor) p.getterMethod.invoke(
                            p.properties.get(p.currentSkinIO.getSkinProperties()))).asAwtcolor();
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                 
                color = JColorChooser.showDialog(arg0.getComponent(), p.colorShower.identifier, color);
                
                if (color != null) {
                    p.colorSet.set(MColor.parse(color));                        
                }
             }
        }
        
        @Override public void mouseEntered(MouseEvent arg0) {}
        @Override public void mouseExited(MouseEvent arg0) {}
        @Override public void mousePressed(MouseEvent arg0) {}
        @Override public void mouseReleased(MouseEvent arg0) {}
    }

    /**
     * A functional interface to set the color.
     *
     * @author Zachary Chandler
     */
    private interface ColorSetter {
        void set(MColor c);
    }
    
}
