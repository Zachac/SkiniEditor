package view;

import static view.TextBoxProperty.getTextBoxProperty;

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
        
        Class<FontsProperties> fonts = FontsProperties.class;
        TextBoxProperty prop;

        prop = getTextBoxProperty(usr, fonts, "HitCirclePrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getTextBoxProperty(usr, fonts, "HitCircleOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);

        prop = getTextBoxProperty(usr, fonts, "ScorePrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getTextBoxProperty(usr, fonts, "ScoreOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);

        prop = getTextBoxProperty(usr, fonts, "ComboPrefix", String.class);
        properties.add(prop);
        this.add(prop);

        prop = getTextBoxProperty(usr, fonts, "ComboOverlap", Integer.TYPE);
        properties.add(prop);
        this.add(prop);
        
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
