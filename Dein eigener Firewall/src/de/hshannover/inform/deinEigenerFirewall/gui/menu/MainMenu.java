package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private GUIController guic;
	private JButton newGameButton;
	private JButton hiScoreButton;
	private JButton helpButton;
	private JButton closeButton;
	private JLabel difficulty;
	private JLabel speed;
	// TODO: Combobox or sth to implement
	private String layout;

	public MainMenu(GUIController guic) {
		this.guic = guic;
		
		initMenu();
	}
	
	private void initMenu() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
				
		newGameButton = new JButton("Neues Spiel");		
		newGameButton.addActionListener(e -> guic.setGameState("Board1.txt"));	
		
		hiScoreButton = new JButton("HiScores");		
		hiScoreButton.addActionListener(e -> guic.setHiScoreMenuState());		

		helpButton = new JButton("Hilfe");		
		helpButton.addActionListener(e -> guic.setHelpMenuState());
	
		closeButton = new JButton("Beenden");		
		closeButton.addActionListener(e -> guic.setEndedState());
	
		difficulty = new JLabel("Schwerigkeit");
	
		speed = new JLabel("Geschwindigkeit");
	
		add(newGameButton);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(speed);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(difficulty);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(hiScoreButton);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(helpButton);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(closeButton);
		
		for(Component c : getComponents()) {
			c.setFont(guic.getFont());
		}
		
		repaint();
	}	

}
