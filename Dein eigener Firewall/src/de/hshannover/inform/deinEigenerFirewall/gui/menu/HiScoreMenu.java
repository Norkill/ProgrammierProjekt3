package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.gui.MyJButton;

/**
 * HiScoreMenu class which shows HiScore list
 * 
 * @author bk3-4nz-u1
 *
 */
@SuppressWarnings("serial")
public class HiScoreMenu extends JPanel {
	private GUIController guic;
	private MyJButton backButton;
	private JLabel[][] listings = new JLabel[10][2];
	private JLabel label = new JLabel("Hall of Fame");

	/**
	 * creates and inits HiScoreMenu
	 * 
	 * @param guic GUIController
	 */
	public HiScoreMenu(GUIController guic) {
		this.guic = guic;
		initHiScore();
	}

	/**
	 * inits HiScore listen and draws it on this component
	 */
	private void initHiScore() {
		setLayout(new GridLayout(12, 2));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		backButton = new MyJButton("Zuruck");
		backButton.addActionListener(e -> guic.setMenuState());

		add(label);
		add(Box.createRigidArea(new Dimension(0, 15)));
		ArrayList<Integer> scores = guic.getGameFassade().getHiScorePointsList();
		ArrayList<String> names = guic.getGameFassade().getHiScoreNamesList();

		for (int i = 0; i < 10; i++) {
			listings[i][0] = new JLabel(names.get(i));
			listings[i][1] = new JLabel("" + scores.get(i));
			listings[i][0].setOpaque(true);
			listings[i][1].setOpaque(true);
			add(listings[i][0]);
			add(listings[i][1]);
		}
		add(backButton);

		for (Component c : getComponents()) {
			c.setFont(guic.getFont());
		}

		repaint();
	}

	/**
	 * reads all scores new (usefull when last game happened to go on the list
	 */
	public void resetScores() {
		for (int i = 0; i < 10; i++) {
			ArrayList<Integer> scores = guic.getGameFassade().getHiScorePointsList();
			ArrayList<String> names = guic.getGameFassade().getHiScoreNamesList();
			listings[i][0].setText(names.get(i));
			listings[i][1].setText("" + scores.get(i));
		}
	}

}