package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.ColoursProperties;
import model.MColor;
import model.SkinPropertiesIO;

public class ComboColors extends JPanel implements Observer {
    
    /** SVUID */
    private static final long serialVersionUID = -5785841427084200171L;
    
    private SkinPropertiesIO skinIO;
    private final List<Combo> combos;
    
    /**
     * Create a combo colors panel that observes a given usr.
     */
    public ComboColors(UserContext usr) {
        combos = new ArrayList<>(8);
        
        usr.addObserver(this);
        loadContent();
    }

    /**
     * Loads the content of the panel.
     */
    private final void loadContent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(new EmptyBorder(0, 0, 10, 0));

        for (int i = 0; i < 8; i++) {
            Combo c = new Combo(i + 1);
            this.add(c);
            combos.add(c);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof UserContext) {
            
            // if the skin properties changed
            if (((UserContext) o).getSkinIO() != this.skinIO) {
                if (skinIO != null) {
                    skinIO.getSkinProperties().colours.deleteObserver(this);
                }

                this.skinIO = ((UserContext) o).getSkinIO();
                
                if (skinIO != null) {
                    skinIO.getSkinProperties().colours.addObserver(this);
                    
                    if (skinIO.getSkinProperties() != null) {
                        updateCombos(skinIO.getSkinProperties().colours);
                    } else {
                        updateCombos(null);
                    }
                }
            }
            
        } else if (o instanceof ColoursProperties) {
            updateCombos((ColoursProperties) o);
        }
    }

    /**
     * Update the combos for the given colors
     */
    private void updateCombos(ColoursProperties colors) {        
        int i = 0;
        
        if (colors != null) {
            List<MColor> newCombos = colors.getComboColors();
            
            for (i = 0; i < newCombos.size(); i++) {
                combos.get(i).setColor(newCombos.get(i).asAwtcolor());
            }
        }
        
        if (i < 8) {
            combos.get(i).setVisible(true);
            combos.get(i).showAsDisabled();
            
            for (i++; i < 8; i++) {
                combos.get(i).setVisible(false);;
            }
        }
    }
    
    /**
     * A class to display, remove and add combo colors.
     *
     * @author Zachary Chandler
     */
    private class Combo extends ColorPropertyDisplay {
        
        /** SVUID */
        private static final long serialVersionUID = -5352847096539055697L;
        private final int combo;
        private JLabel removeButton;
        private final Dimension MAX_SIZE_W_REMOVE = new Dimension(ColorPropertyDisplay.MAX_SIZE.width
                + 51 + 10, ColorPropertyDisplay.MAX_SIZE.height);

        /**
         * Create a combo for the given combo i.
         */
        public Combo(int i) {
            super("Combo" + i);
            this.combo = i;
            
            removeButton = new JLabel("[remove]");
            removeButton.setAlignmentY(CENTER_ALIGNMENT);
            removeButton.addMouseListener(new RemoveMouseEvent());
            removeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            
            this.setMaximumSize(MAX_SIZE_W_REMOVE);
            this.add(Box.createRigidArea(new Dimension(10, 0)));
            this.add(removeButton);
//            this.showAsEnabled();
            this.showAsDisabled();
            this.addMouseListener(new ColorPickerMouseEvent());
        }

        @Override
        public void showAsDisabled() {
            super.showAsDisabled();
            if (removeButton != null) {
                this.setMaximumSize(new Dimension(MAX_SIZE.width + 10, MAX_SIZE.height));
                removeButton.setVisible(false);          
            }
        }
        
        public void showAsEnabled() {
            super.showAsEnabled();
            if (removeButton != null) {
                this.setMaximumSize(MAX_SIZE_W_REMOVE);
                removeButton.setVisible(true);          
            }
        }
        
        /**
         * A class to pick colors for a Combo.
         *
         * @author Zachary Chandler
         */
        private final class ColorPickerMouseEvent implements MouseListener {
            @Override public void mouseEntered(MouseEvent arg0) {}
            @Override public void mouseExited(MouseEvent arg0) {}
            @Override public void mousePressed(MouseEvent arg0) {}
            @Override public void mouseReleased(MouseEvent arg0) {}

            @Override
            public void mouseClicked(MouseEvent arg0) {
                if (skinIO == null) {
                    return;
                }
                
                List<MColor> nCombos = skinIO.getSkinProperties().colours.getComboColors();
                
                Color color = null;
                if (nCombos.size() >= combo) {
                    color = nCombos.get(combo - 1).asAwtcolor();
                }
                
                color = JColorChooser.showDialog(arg0.getComponent(), "Combo" + combo, color);
                
                if (color != null) {
                    if (removeButton.isVisible()) {
                        skinIO.getSkinProperties().colours.assignComboColor(combo - 1, MColor.parse(color));
                    } else {
                        skinIO.getSkinProperties().colours.addComboColor(MColor.parse(color));
                    }
                }
            }
        }

        /**
         * A class to handle mouse events for the Combo.
         *
         * @author Zachary Chandler
         */
        private final class RemoveMouseEvent implements MouseListener {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                skinIO.getSkinProperties().colours.removeComboColor(combo - 1);
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
                removeButton.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                removeButton.setForeground(Color.BLACK);
            }

            @Override public void mousePressed(MouseEvent arg0) {}
            @Override public void mouseReleased(MouseEvent arg0) {}
        }
    }
}
