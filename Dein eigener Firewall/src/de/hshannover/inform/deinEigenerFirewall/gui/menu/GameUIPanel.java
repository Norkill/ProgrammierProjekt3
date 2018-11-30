package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.gui.MyJButton;
import de.hshannover.inform.deinEigenerFirewall.gui.audio.SoundManager;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * GameUIPanel class to show all game parameters like Score and Viruses
 * 
 * @author bk3-4nz-u1
 *
 */
@SuppressWarnings("serial")
public class GameUIPanel extends JPanel implements Observer {

	private MyJButton backButton = new MyJButton(Utils.loadImage("res/images/exit.png"));
	private JLabel hscoreText = new JLabel("HiScore");
	private JLabel hscore;
	private JLabel scoretext = new JLabel("Score");
	private JLabel score = new JLabel("0");
	private JLabel userExpText = new JLabel("User Experience");
	private JLabel userExp = new JLabel("0 %");
	private JLabel virusText = new JLabel("Virusen");
	private JLabel virus = new JLabel("0 / 3");
	private boolean scoreHandled = false;

	private GUIController guic;
	private BufferedImage background;

	/**
	 * creates and inits GameUIPanel
	 * 
	 * @param guic GUIController
	 */
	public GameUIPanel(GUIController guic) {
		this.guic = guic;
		init();
	}

	/**
	 * Inits gameUIPanel
	 */
	private void init() {
		// initialize and set bounds for window(layout)
		setLayout(new GridLayout(9, 1));
		setBounds(guic.getGameWidth(), 0, guic.getWidth(), guic.getHeight());

		background = Utils.loadImage("res/images/statPanel.png");
		// add button to go back to menu
		
		backButton.addActionListener(e -> {
			guic.stopGame();
			guic.setMenuState();
		});
		
		add(backButton);
		
		hscore = new JLabel("" + guic.getGameFassade().getTopHiScore());

		

		add(hscoreText);
		add(hscore);
		add(scoretext);
		add(score);
		add(virusText);
		add(virus);
		add(userExpText);
		add(userExp);
		updateParameters();
		for (Component c : getComponents()) {
		    JComponent jc = (JComponent) c;
		    jc.setOpaque(false);
			c.setFont(guic.getFont());
		}
		guic.getGameFassade().addObserverToGameParameterManager(this);
		
		revalidate();
		repaint();
	}

	/**
	 * Paints this component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0, 0, null);
		revalidate();
		repaint();
	}

	/**
	 * Reads all parameters form game new
	 */
	private void updateParameters() {
		score.setText("" + guic.getGameFassade().getScore());
		int vir = (int) virus.getText().toCharArray()[0] - 48;
		virus.setText("" + guic.getGameFassade().getViruses() + " / 3");
		int vir2 = (int) virus.getText().toCharArray()[0] - 48;
		if (vir != vir2) {
			System.out.println(vir2);
			if (vir2 == 1) {
				SoundManager.playSound(SoundManager.ERROR);
			}
			if (vir2 == 2) {
				SoundManager.playSound(SoundManager.ALARM);
			}
		}
		userExp.setText("" + guic.getGameFassade().getUserExperience() + " %");
	}

	/**
	 * Shows input box to get name of the player to save it as a Highscore, used
	 * only if score high enough
	 * 
	 * @param pos
	 */
	private void showInputBox(Integer pos) {
		guic.stopGame();
		if (scoreHandled == false) {
			if (pos > 0) {
				SoundManager.playSound(SoundManager.WON);
				String name = JOptionPane.showInputDialog("Gluckwunsch, du hast neues HiScore Erreicht,\n du bist "
						+ pos + " mit " + guic.getGameFassade().getScore() + " Punkten!");

				guic.getGameFassade().setPlayerName(name);

			} else {
				SoundManager.playSound(SoundManager.LOST_VIRUS);
				JOptionPane.showMessageDialog(null, "Du hast verloren");
			}

			guic.setMenuState();
		}
		scoreHandled = true;
	}

	/**
	 * update method called by GameParameterManager when any of the parameters
	 * changed
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 != null) {
			showInputBox((Integer) arg1);
		}
		updateParameters();
		revalidate();
		repaint();
	}

}
