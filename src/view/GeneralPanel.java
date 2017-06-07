package view;

import static view.TextBoxProperty.getTextBoxProperty;

import java.awt.Component;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.GeneralProperties;
import model.SkinVersion;

public class GeneralPanel extends JPanel implements Observer {

    /** SVUID */
    private static final long serialVersionUID = -7345437371067550354L;
	private final UserContext usr;
	private final List<Updatable> properties;

    public GeneralPanel(UserContext usr) {
        super();
        
        this.usr = Objects.requireNonNull(usr);
        this.properties = new LinkedList<>();
        
        usr.addObserver(this);
        loadContent();
    }

    private final void loadContent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        Class<?> g = GeneralProperties.class;
        
        TextBoxProperty p = getTextBoxProperty(usr, g, "Version", SkinVersion.class);
        properties.add(p);
        this.add(p);
        
        
        MultipleChoiceProperty p1 = 
        		MultipleChoiceProperty.getMultipleChoiceroperty(usr, 
        				GeneralProperties.class, "SliderBallFlip", Boolean.TYPE,
        				"1", "0");
        properties.add(p1);
        this.add(p1);
        
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
