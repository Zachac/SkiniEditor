package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GeneralPanel extends JPanel implements Observer {

    /** SVUID */
    private static final long serialVersionUID = -7345437371067550354L;

    public GeneralPanel(UserContext usr) {
        super();
        usr.addObserver(this);
        loadContent();
    }

    private final void loadContent() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel notImplementedLabel = new JLabel("Not Implemented");
        notImplementedLabel.setAlignmentX(CENTER_ALIGNMENT);
        
        this.add(notImplementedLabel);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
    }

}
