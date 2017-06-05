package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * A class to load and save SkinProperties.
 *
 * @author Zachary Chandler
 */
public class SkinPropertiesIO {
    
    private File file;
    private SkinProperties skin;
    
    @SuppressWarnings("unchecked") // all classes must implement properties
    private static final Map<String, PropertySetter> propertySetters = 
        getProperySetterMap((Class<? extends Properties>[]) new Class<?>[]
            {GeneralProperties.class, ColoursProperties.class, FontsProperties.class, CatchTheBeatProperties.class});

    /**
     * Instantiates the IO for a given file. If the file is readable the associated profile for this object will be'
     * loaded from associatedFile.
     * 
     * @param associatedFile the file to load from.
     * @throws NullPointerException if associatedFile is null.
     * @throws IllegalArgumentException if associatedFile is a directory.
     * @throws ParseException if associatedFile cannot be parsed.
     */
    public SkinPropertiesIO(File associatedFile) throws ParseException {
        Objects.requireNonNull(associatedFile);
        
        if (associatedFile.isDirectory()) {
            throw new IllegalArgumentException();
        }
        
        this.file = associatedFile;
        
        if (this.file.exists()) {
            this.skin = load(this.file);            
        } else {
            this.skin = new SkinProperties();
        }
    }

    /**
     * @return the skin properties of this file.
     */
    public SkinProperties getSkinProperties() {
        return skin;
    }
    
    /**
     * Save the SkinProperties to the file.
     */
    public void save() {
        try {
            PrintStream output = new PrintStream(file);
            output.print(skin.toString());
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Set the location to save the file.
     * @param f the file location to save.
     * @throws NullPointerException if f is null.
     * @throws IllegalArgumentException if f is a directory.
     */
    public void setSaveLocation(File f) {
        Objects.requireNonNull(f);
        
        if (f.isDirectory()) {
            throw new IllegalArgumentException();
        }
        
        this.file = f;
    }
    
    /**
     * @return the location to save the file
     */
    public File getSaveLocation() {
        return this.file;
    }
    
    /**
     * Load a given file and return a SkinProperties with the values form the file.
     * 
     * @return a SkinProperties for the associatedFile.
     * @throws NullPointerException if associatedFile is null.
     * @throws IllegalArgumentException if associatedFile is a directory or doesn't exist.
     * @throws UnsuportedOperationException if the associatedFile contains a [Mania] section.
     * @throws ParseException if the file cannot be parsed.
     */
    public static final SkinProperties load(File associatedFile) throws ParseException {
        Objects.requireNonNull(associatedFile);
        
        if (!associatedFile.exists() || associatedFile.isDirectory()) {
            throw new IllegalArgumentException("Cannot load file. Either it doesn't exist or it is a directory.");
        }
        
        SkinProperties skin = new SkinProperties();
        Scanner input;
        Properties currentProperties = null;
        int lineNumber = 0;
        
        try {
            input = new Scanner(associatedFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
        while(input.hasNextLine()) {
            String line = input.nextLine().trim();
            lineNumber++;
            
            if (line.equals("[General]")) {
               currentProperties = skin.general;
            } else if (line.equals("[Colours]")) {
                currentProperties = skin.colours;
            } else if (line.equals("[Fonts]")) {
                currentProperties = skin.fonts;
            } else if (line.equals("[CatchTheBeat]")) {
                currentProperties = skin.catchTheBeat;
            } else if (line.equals("[Mania]")) {
                input.close();
                throw new UnsupportedOperationException("Mania is not supported.");
            } else if (line.startsWith("//") || line.equals("")) {
                //  ignore
            } else {
                try {
                    LoadLine(currentProperties, line);
                } catch (IllegalArgumentException e) {
                    System.err.printf("%s %d: %s\n", e.getMessage(), lineNumber, line);
                    input.close();
                    throw new ParseException(line, lineNumber);
                }
            }
        }
        
        input.close();
        return skin;
    }
    
    /**
     * Load a single line into the given properties.
     */
    private static void LoadLine(Properties p, String line) {
        String[] parts = line.split(": ");
        
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid format ");
        }
        
        String identifier = parts[0];
        String value = parts[1];
        
        if (identifier.startsWith("Combo") && identifier.length() == 6) {
            loadComboColor(p, identifier.charAt(5), value);
        } else if (identifier.equals("CustomComboBurstSounds")) {
            loadComboBurstSounds(p, value);
        } else if (propertySetters.containsKey(identifier)){
            propertySetters.get(identifier).set(p, value);
        } else {
            throw new IllegalArgumentException("Could not find property ");
        }
    }

    /**
     * Load the combo bursts described in value into the properties p.
     * @throws IllegalArgumentException if value is not able to be parsed or p isn't an instance of GeneralProperties.
     */
    private static void loadComboBurstSounds(Properties p, String value) {
        if (!(p instanceof GeneralProperties)) {
            throw new IllegalArgumentException("Cannot set CustomComboBurstSounds for " + p.getClass().getName());
        }
        
        String[] svars = value.split("[\\s]*,[\\s]*");
        GeneralProperties general = (GeneralProperties) p;
        
        for (String s : svars) {
            
            try {
                general.addCustomComboBurstSound(Integer.parseInt(s));                       
            } catch (NumberFormatException e) {
                System.out.println("non integer value error catching for: " + s);
            }
        }
    }
    
    /**
     * Load a combo color into the combo comboNumberAsChar as the parsed Color value.
     * @throws IllegalArgumentException if the given p isn't an instance of ColoursProperties.
     * @throws IllegalArgumentException if the given combo cannot be set.
     * @throws IllegalArgumentException if color isn't able to be parsed.
     */
    private static void loadComboColor(Properties p, char comboNumberAsChar, String value) {
        if (comboNumberAsChar < '1' || comboNumberAsChar > '8') {
            throw new IllegalArgumentException("Combo value out of bounds");
        }
        
        if (!(p instanceof ColoursProperties)) {
            throw new IllegalArgumentException("Cannot set " + comboNumberAsChar + " for " + p.getClass().getName());
        }
        
        int comboNumber = Integer.parseInt(String.valueOf(comboNumberAsChar)) - 1;

        ColoursProperties colours = (ColoursProperties) p;
        
        List<MColor> combos = colours.getComboColors();
        
        try { 
            if (combos.size() == comboNumber) {                       
                colours.addComboColor(MColor.parse(value));
            } else if (combos.size() > comboNumber) {
                colours.assignComboColor(comboNumber, MColor.parse(value));
            } else {
                throw new IllegalArgumentException("Error catching for invalid combo order.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Combo error catching for " + e.getMessage());
        }
    }

    /**
     * @param propertyClasses the classes of properties that will be taken from.
     * @return a map of properties from a given identifier of a property to a PropertySetter for that identifier.
     * @throws IllegalStateException if there is an invalid setter in a class in property classes.
     * @throws IllegalStateException if a setter does not have the value Color, String, boolean, int, or SkinVersion.
     */
    private static Map<String, PropertySetter> getProperySetterMap(Class<? extends Properties>[] propertyClasses) {
        TreeMap<String, PropertySetter> result = new TreeMap<>();
        
        for (Class<?> c : propertyClasses) {
            if (!Properties.class.isAssignableFrom(c)) {
                throw new IllegalStateException("Heap pollution, expected Properties, got " + c);
            }
            
            for (Method m : c.getMethods()) {
                if (m.getName().startsWith("set")) {
                    String identifier = m.getName().substring(3);

                    if (m.getName().equals("set" + identifier)) {
                        if (m.getParameterCount() != 1) {
                            throw new IllegalStateException("Invalid setter in Properties class. " + m);
                        }
                        
                        Class<?> param = m.getParameterTypes()[0];
                        
                        if (param.equals(MColor.class)) {
                            result.put(identifier, new ColorSetter(m));
                        } else if (param.equals(String.class)) {
                            result.put(identifier, new StringSetter(m));
                        } else if (param.equals(Integer.TYPE)) {
                            result.put(identifier, new IntSetter(m));
                        } else if (param.equals(Boolean.TYPE)) {
                            result.put(identifier, new BooleanSetter(m));
                        } else if (param.isAssignableFrom(SkinVersion.class)) {
                            result.put(identifier, new SkinVersionSetter(m));
                        } else {
                            throw new IllegalStateException("Unrecognized paramater type: " + param);
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * A setter to set a SkinVersion on a given method.
     *
     * @author Zachary Chandler
     */
    private static final class SkinVersionSetter implements PropertySetter {
        private Method m;

        /**
         * @param m the method to set.
         * @throws IllegalStateException if m does not have only one parameter or if the parameter isn't the right type.
         */
        public SkinVersionSetter(Method m) {
            if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(SkinVersion.class)) {
                throw new IllegalStateException();
            }
            
            this.m = m;
        }

        @Override
        public void set(Properties p, String value) {
            try {
                m.invoke(p, SkinVersion.parse(value));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("error catching for " + e.getMessage());
            }
        }
    }

    /**
     * A setter to set a boolean on a given method.
     *
     * @author Zachary Chandler
     */
    private static final class BooleanSetter implements PropertySetter {
        private Method m;

        /**
         * @param m the method to set.
         * @throws IllegalStateException if m does not have only one parameter or if the parameter isn't the right type.
         */
        public BooleanSetter(Method m) {
            if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(Boolean.TYPE)) {
                throw new IllegalStateException();
            }
            
            this.m = m;
        }

        @Override
        public void set(Properties p, String value) {
            try {
                m.invoke(p, value.equals("1"));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("boolean error catching for " + e.getMessage());
            }
        }
    }

    /**
     * A setter to set a int on a given method.
     *
     * @author Zachary Chandler
     */
    private static final class IntSetter implements PropertySetter {
        private Method m;

        /**
         * @param m the method to set.
         * @throws IllegalStateException if m does not have only one parameter or if the parameter isn't the right type.
         */
        public IntSetter(Method m) {
            if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(Integer.TYPE)) {
                throw new IllegalStateException();
            }
            
            this.m = m;
        }

        @Override
        public void set(Properties p, String value) {
            try {
                m.invoke(p, Integer.parseInt(value));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("integer error catching for " + e.getMessage());
            }
        }
    }

    /**
     * A setter to set a String on a given method.
     *
     * @author Zachary Chandler
     */
    private static final class StringSetter implements PropertySetter {
        private Method m;

        /**
         * @param m the method to set.
         * @throws IllegalStateException if m does not have only one parameter or if the parameter isn't the right type.
         */
        public StringSetter(Method m) {
            if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(String.class)) {
                throw new IllegalStateException();
            }
            
            this.m = m;
        }

        @Override
        public void set(Properties p, String value) {
            try {
                m.invoke(p, value);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("string error catching for " + e.getMessage());
            }
        }
    }

    /**
     * A setter to set a Color on a given method.
     *
     * @author Zachary Chandler
     */
    private static final class ColorSetter implements PropertySetter {
        private Method m;

        /**
         * @param m the method to set.
         * @throws IllegalStateException if m does not have only one parameter or if the parameter isn't the right type.
         */
        public ColorSetter(Method m) {
            if (m.getParameterCount() != 1 || !m.getParameterTypes()[0].equals(MColor.class)) {
                throw new IllegalStateException();
            }
            
            this.m = m;
        }

        @Override
        public void set(Properties p, String value) {
            try {
                m.invoke(p, MColor.parse(value));
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new IllegalStateException(e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("color error catching for " + e.getMessage());
            }
        }
    }

    /**
     * An interface to set a property on a Properties instance.
     *
     * @author Zachary Chandler
     */
    private interface PropertySetter {
        
        /**
         * Sets a certain property on a given Properties object to the given value.
         */
        void set(Properties p, String value);
    }
}
