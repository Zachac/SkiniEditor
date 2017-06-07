package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MultipleChoiceProperty extends JPanel implements Updatable {

	/** SVUID */
	private static final long serialVersionUID = 3292368098856803671L;
	
	private final String identifier;
	private final Setter set;
	private final Getter get;

	private JComboBox<String> choices;

	private final List<String> choicesList;

	public MultipleChoiceProperty(String identifier, Setter set, Getter get, 
			String choice, String... choices) {
		
		this.choicesList = new LinkedList<String>();
		this.identifier = Objects.requireNonNull(identifier);
		this.set = Objects.requireNonNull(set);
		this.get = Objects.requireNonNull(get);
		
		choicesList.add(Objects.requireNonNull(choice));
		
		for (String s : choices) {
			choicesList.add(s);
		}
		
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
		
		choices = new JComboBox<String>();
		choices.setPreferredSize(new Dimension(100, 15));
		choices.setMaximumSize(new Dimension(100, 15));
		choices.setEnabled(false);
		choices.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Handle Choice");
			}
		});
		
		for (String s : choicesList) {
			choices.addItem(s);
		}
		
		this.add(new JLabel(identifier));
		this.add(Box.createHorizontalGlue());
		this.add(choices);	
	}

	@Override
	public void update() {
		String currentValue = get.get().toString();
		Iterator<String> iter = choicesList.iterator();
		
		int i = 0;
		
		String s = null;
		while (iter.hasNext()) {
			s = iter.next();
			
			if (s.equals(currentValue)) {
				break;
			} else {
				s = null;
				i++;
			}
		}
		
		if (s == null) {
			throw new IllegalStateException();
		}
		
		choices.setSelectedIndex(i);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		choices.setEnabled(enabled);
	}

    public static MultipleChoiceProperty getMultipleChoiceroperty(UserContext usr, 
    		Class<?> pHolder, String prop, Class<?> type, String choice, String... choices) {

    	Translator translator = Translator.getTranslator(type);
    	Method setter = Main.getMethod(pHolder, "set" + prop, Void.TYPE, type);
    	Method getter = Main.getMethod(pHolder, "get" + prop, type);
    	
    	Getter get = Getter.getGetter(getter, usr);
    	Setter set = Setter.getSetter(setter, translator, usr);
    	
		return new MultipleChoiceProperty(prop, set, get, choice, choices);
	}
}
