package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * A class to display a text box for a given property and handle input events.
 * 
 * @author Zachac
 */
public class TextBoxProperty extends JPanel {

	/** SVUID */
	private static final long serialVersionUID = 3292368098856803671L;
	
	private final String identifier;
	private final Setter set;
	private final Getter get;

	private JTextField textField;

	/**
	 * Create a textbox property with the given identifier, setter, and getter.
	 * @throws NullPointerException if identifier, set, or get are null.
	 */
	public TextBoxProperty(String identifier, Setter set, Getter get) {
		this.identifier = Objects.requireNonNull(identifier);
		this.set = Objects.requireNonNull(set);
		this.get = Objects.requireNonNull(get);
		
		loadContent();
	}

	/**
	 * Load the content of this panel and setup event listeners.
	 */
	private final void loadContent() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.setAlignmentX(LEFT_ALIGNMENT);
		this.setAlignmentY(CENTER_ALIGNMENT);
		this.setBorder(new EmptyBorder(10, 10, 0, 0));
		this.setMaximumSize(ColorPropertyDisplay.MAX_SIZE);
		
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(100, 15));
		textField.setMaximumSize(new Dimension(100, 15));
		textField.setEnabled(false);
		textField.addFocusListener(new UpdateWithSetterOnFocusLoss());
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.getParent().requestFocus();
			}
		});
		
		this.add(new JLabel(identifier));
		this.add(Box.createHorizontalGlue());
		this.add(textField);		
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		if (!enabled) {
			textField.setText("");
		}
		
		textField.setEnabled(enabled);
	}

	/**
	 * Update the panel.
	 */
	public void update() {
		textField.setText(get.get().toString());
	}
	
	/**
	 * A listener to update the setter on focus loss.
	 * 
	 * @author Zachac
	 */
	private final class UpdateWithSetterOnFocusLoss implements FocusListener {
		@Override public void focusGained(FocusEvent arg0) { }

		@Override
		public void focusLost(FocusEvent arg0) {
			String result = set.set(textField.getText());
			
			if (result != null) {
				JOptionPane.showMessageDialog(arg0.getComponent(), result);
				textField.setText(get.get().toString());
			}
		}
	}
}
