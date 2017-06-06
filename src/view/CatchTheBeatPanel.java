package view;

import java.lang.reflect.Field;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.SkinProperties;

public class CatchTheBeatPanel extends JPanel {

    /** SVUID */
    private static final long serialVersionUID = 2410120447965252325L;

    public CatchTheBeatPanel(UserContext usr) {
        Field f = null;
        try {
            f = SkinProperties.class.getField("catchTheBeat");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new ColorProperty(usr, f, "HyperDash"));
        this.add(new ColorProperty(usr, f, "HyperDashAfterImage"));
        this.add(new ColorProperty(usr, f, "HyperDashFruit"));
    }
}
