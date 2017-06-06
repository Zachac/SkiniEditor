package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ColorPropertyDisplay extends JPanel {

    /** SVUID */
    private static final long serialVersionUID = 1172955127282124750L;

    /** The size of the JPanel displaying the color. */
    private static final Dimension COLOR_SHOWER_MAX_SIZE = new Dimension(100, 15);
    
    /** The size of the JPanel displaying the color. */
    public static final Dimension MAX_SIZE = new Dimension(300, 25);
    
    private JPanel colorShower;
    
    public final String identifier;
    
    public ColorPropertyDisplay(String identifier) {
        this.identifier = Objects.requireNonNull(identifier);
        loadContent();
    }
    
    private void loadContent() {
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new EmptyBorder(10, 10, 0, 0));
        this.setMaximumSize(MAX_SIZE);
        
        colorShower = new JPanel();
        colorShower.setAlignmentY(CENTER_ALIGNMENT);
        colorShower.setBorder(new CompoundBorder(new LineBorder(Color.BLACK), new LineBorder(Color.WHITE)));
        colorShower.setMaximumSize(COLOR_SHOWER_MAX_SIZE);
        colorShower.setPreferredSize(COLOR_SHOWER_MAX_SIZE);
        
        JLabel aLabel = new JLabel(identifier);
        aLabel.setAlignmentY(CENTER_ALIGNMENT);
        
        Component glue = Box.createHorizontalGlue();
        glue.setMaximumSize(new Dimension(MAX_SIZE.width, 0));

        this.add(aLabel);
        this.add(glue);
        this.add(colorShower);
        this.showAsDisabled();
    }

    public void showAsDisabled() {
        colorShower.setBackground(this.getBackground());
        colorShower.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
    public void showAsEnabled() {
        colorShower.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public void setColor(Color c) {
        showAsEnabled();
        colorShower.setBackground(c);
    }
}
