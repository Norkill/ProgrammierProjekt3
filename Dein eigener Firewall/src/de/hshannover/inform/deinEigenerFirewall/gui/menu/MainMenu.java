package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private GUIController guic;
	private JButton newGameButton;
	private JButton hiScoreButton;
	private JButton helpButton;
	private JButton closeButton;
	private JLabel layout;
	private JLabel speed;
	private JLabel title;
	private JPanel[] grids;

	
	private ArrayList<String> layoutNames;
	private ArrayList<String> layouts;
	private JSpinner layoutSpinner;
	private ArrayList<Double> speeds;
	private JSpinner speedSpinner;
	
	

	public MainMenu(GUIController guic) {
		this.guic = guic;
		
		initMenu();
	}
	
	
	/*LAYOUT
	 * 		 title
	 * gird0 grid0 grid0
	 * drid1 grid1 grid1
	 * ...
	 */
	
	
	
	private void initMenu() {
		setLayout(new GridLayout(7, 1, 20, 20));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		newGameButton = new JButton("Neues Spiel");		
		newGameButton.addActionListener(e -> guic.setGameState( (Double)speedSpinner.getValue(),(String) layoutSpinner.getValue()));	
		
		hiScoreButton = new JButton("HiScores");		
		hiScoreButton.addActionListener(e -> guic.setHiScoreMenuState());		

		helpButton = new JButton("Hilfe");		
		helpButton.addActionListener(e -> guic.setHelpMenuState());
	
		closeButton = new JButton("Beenden");		
		closeButton.addActionListener(e -> guic.setEndedState());
	
		layout = new JLabel("Spielbrett", SwingConstants.CENTER);
	
		speed = new JLabel("Geschwindigkeit", SwingConstants.CENTER);
		
		title = new JLabel("The Firewall", SwingConstants.CENTER);
		
		initSpinners();
		
		grids = new JPanel[6];
		grids[0] = new JPanel(new GridLayout(1,3));
		grids[0].add(newGameButton);
		grids[0].add(Box.createRigidArea(new Dimension(0,15)));
		grids[0].add(Box.createRigidArea(new Dimension(0,15)));
		
		grids[1] = new JPanel(new GridLayout(1,3));
		grids[1].add(speed);
		grids[1].add(speedSpinner);
		grids[1].add(Box.createRigidArea(new Dimension(0,15)));
		
		grids[2] = new JPanel(new GridLayout(1,3));
		grids[2].add(layout);
		grids[2].add(layoutSpinner);
		grids[2].add(Box.createRigidArea(new Dimension(0,15)));
		
		grids[3] = new JPanel(new GridLayout(1,3));
		grids[3].add(hiScoreButton);
		grids[3].add(Box.createRigidArea(new Dimension(0,15)));
		grids[3].add(Box.createRigidArea(new Dimension(0,15)));
		
		grids[4] = new JPanel(new GridLayout(1,3));
		grids[4].add(helpButton);
		grids[4].add(Box.createRigidArea(new Dimension(0,15)));
		grids[4].add(Box.createRigidArea(new Dimension(0,15)));
		
		grids[5] = new JPanel(new GridLayout(1,3));
		grids[5].add(closeButton);
		grids[5].add(Box.createRigidArea(new Dimension(0,15)));
		grids[5].add(Box.createRigidArea(new Dimension(0,15)));
		
		
		add(title);
		add(grids[0]);
		add(grids[1]);
		add(grids[2]);
		add(grids[3]);
		add(grids[4]);
		add(grids[5]);
		
		setFontToComponents();
		
		repaint();
	}	
	
	private void initSpinners() {
		speeds = new ArrayList<>();
		speeds.add(1.0);
		speeds.add(2.0);
		speeds.add(3.0);
		speedSpinner = new JSpinner(new SpinnerListModel(speeds));
		layoutSpinner = new JSpinner(new SpinnerListModel(getLayoutList()));
	}
	
	private ArrayList<String> getLayoutList(){
		ArrayList<String> results = new ArrayList<>();


		File[] files = new File("res/boards").listFiles();

		for (File file : files) {
		    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
		return results;
	}

	private void setFontToComponents() {
		for(JPanel p : grids) {
			for(Component c : p.getComponents()) {
				c.setFont(guic.getFont());
			}
		}
		for(Component c : getComponents()) {
			c.setFont(guic.getFont());
		}
	}
	
}
