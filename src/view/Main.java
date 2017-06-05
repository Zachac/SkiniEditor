package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import model.SkinPropertiesIO;

public class Main {

    public static void main(String[] args) {
        
        UserContext usr = new UserContext();
        
        JFrame window = new JFrame("Skini Editor");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem openDefault = new JMenuItem("open default");
        openDefault.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    usr.setSkinIO(new SkinPropertiesIO(new File("New Skin/skin.ini")));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

        JMenuItem save = new JMenuItem("save");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                usr.getSkinIO().save();
            }
        });
        
        fileMenu.addMenuListener(new MenuListener() {
            @Override public void menuCanceled(MenuEvent arg0) {}
            @Override public void menuDeselected(MenuEvent arg0) {}

            @Override
            public void menuSelected(MenuEvent arg0) {
                save.setEnabled(usr.getSkinIO() != null);
            }
            
        });

        fileMenu.add(openDefault);
        fileMenu.add(save);
        menuBar.add(fileMenu);
        window.setJMenuBar(menuBar);
        
        
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("General", new GeneralPanel(usr));
        tabs.add("Colours", new ColoursPanel(usr));
        tabs.add("Fonts", new FontsPanel(usr));
        tabs.add("CatchTheBeat", new CatchTheBeatPanel(usr));
        tabs.add("Mania", new ManiaPanel(usr));
        
        window.add(tabs);
        
        window.setSize(new Dimension(480, 640));
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

}
