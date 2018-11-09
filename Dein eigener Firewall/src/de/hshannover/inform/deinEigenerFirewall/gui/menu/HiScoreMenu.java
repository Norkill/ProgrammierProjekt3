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
public class HiScoreMenu extends JPanel {
	private GUIController guic;
	private JButton backButton;
	// TODO: IMG HELP
	private JLabel platzhalter;
	
	public HiScoreMenu(GUIController guic) {
		this.guic = guic;
		
		initHelp();
	}
	
	private void initHelp() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
		backButton = new JButton("Zuruck");		
		backButton.addActionListener(e -> guic.setMenuState());
		
		platzhalter = new JLabel("HiScore Implementieren");
		
		add(platzhalter);
		add(Box.createRigidArea(new Dimension(0,15)));
		add(backButton);
		
		for(Component c : getComponents()) {
			c.setFont(guic.getFont());
		}
		
		repaint();
	}

}