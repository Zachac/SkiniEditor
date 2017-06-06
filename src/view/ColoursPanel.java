package view;

import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.SkinProperties;

public class ColoursPanel extends JPanel {

    /** SVUID */
    private static final long serialVersionUID = -5926084121943458727L;

    public ColoursPanel(UserContext usr) {
        Field f = null;
        try {
            f = SkinProperties.class.getField("colours");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new ComboColors(usr));
        this.add(new ColorProperty(usr, f, "SliderBorder"));
        this.add(new ColorProperty(usr, f, "MenuGlow"));
        this.add(new ColorProperty(usr, f, "SliderBall"));
        this.add(new ColorProperty(usr, f, "SpinnerBackground"));
        this.add(new ColorProperty(usr, f, "SongSelectActiveText"));
        this.add(new ColorProperty(usr, f, "SongSelectInactiveText"));
        this.add(new ColorProperty(usr, f, "StarBreakAdditive"));
        this.add(new ColorProperty(usr, f, "SliderTrackOverride"));
        this.add(new ColorProperty(usr, f, "InputOverlayText"));
    }

}
