package view;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
    
    /** The size of the JPanel displaying the color. */
    private static final Dimension COLOR_SHOWER_MAX_SIZE = new Dimension(100, 15);
    
    private String identifier;
    private Field properties;
    private Method setterMethod;
    private Method getterMethod;
    private JPanel colorShower;
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
        this.identifier = identifier;

        if (!Observable.class.isAssignableFrom(properties.getType()) 
                || !Properties.class.isAssignableFrom(properties.getType())
                || !properties.getDeclaringClass().equals(SkinProperties.class)) {
            throw new IllegalArgumentException("Invalid field.");
        }
        
        loadMethods();
        loadContent();
    }

    /**
     * Loads the getter and setter methods from the properties field.
     */
    private final void loadMethods() {
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
    private final void loadContent() {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 0, 0));
        this.setMaximumSize(new Dimension(300, 25));
        
        colorShower = new JPanel();
        colorShower.setAlignmentY(TOP_ALIGNMENT);
        colorShower.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new LineBorder(Color.WHITE)));
        colorShower.setMaximumSize(COLOR_SHOWER_MAX_SIZE);
        colorShower.setPreferredSize(COLOR_SHOWER_MAX_SIZE);
        colorShower.addMouseListener(new ColorPicker(this));
        
        JLabel aLabel = new JLabel(identifier);
        aLabel.setAlignmentY(TOP_ALIGNMENT);
        
        this.add(aLabel);
        this.add(Box.createHorizontalGlue());
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
            colorShower.setBackground(c.asAwtcolor());
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
                colorShower.setBackground(((MColor) getterMethod.invoke(newProps)).asAwtcolor());
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
            
            colorShower.setBackground(getBackground());
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
                 
                color = JColorChooser.showDialog(arg0.getComponent(), p.identifier, color);
                
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
