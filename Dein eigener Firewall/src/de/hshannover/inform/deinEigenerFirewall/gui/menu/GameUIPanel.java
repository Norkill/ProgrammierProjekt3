package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

@SuppressWarnings("serial")
public class GameUIPanel extends JPanel implements Observer{
	
	private JButton backButton;
	private JLabel hscoreText = new JLabel("HiScore");
	private JLabel hscore;
	private JLabel scoretext = new JLabel("Score");
	private JLabel score = new JLabel("0");
	private JLabel userExpText = new JLabel("User Experience");
	private JLabel userExp = new JLabel("0");
	private JLabel virusText = new JLabel("Virusen");
	private JLabel virus = new JLabel("0");
	

	private GUIController guic;

	public GameUIPanel(GUIController guic) {
		this.guic = guic;
		init();
	}

	private void init() {	
		// initialize and set bounds for window(layout)
		setLayout(new GridLayout(9,1));	
		setBounds(guic.getGameWidth(), 0, guic.getWidth(), guic.getHeight());
		// add button to go back to menu
		backButton = new JButton("Spiel beenden");
		backButton.addActionListener(e -> {
			guic.setMenuState();
			guic.getGameFassade().stopGame();
		});
		backButton.setBounds(guic.getGameWidth(), 0, guic.getWidth() - guic.getGameWidth(), 100);
		add(backButton);
		
		hscore= new JLabel(""+guic.getGameFassade().getTopHiScore());
		
		hscore.setOpaque(true);
		score.setOpaque(true);
		virus.setOpaque(true);
		userExp.setOpaque(true);
	
		add(hscoreText);
		add(hscore);
		add(scoretext);
		add(score);
		add(virusText);
		add(virus);
		add(userExpText);
		add(userExp);
		updateParameters();
		for(Component c : getComponents()) {
			c.setFont(guic.getFont());
		}
		
		guic.getGameFassade().addObserverToGameParameterManager(this);
	}
	@Override
	public void paintComponent(Graphics g) {
		revalidate();
		repaint();
	}

	private void updateParameters() {
		score.setText("" + guic.getGameFassade().getScore());
		virus.setText("" + guic.getGameFassade().getViruses());
		userExp.setText("" + guic.getGameFassade().getUserExperience());
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 != null) {
			guic.getGameFassade().setPlayerName("Someone");
		}
		updateParameters();
		revalidate();
		repaint();
	}
	
}
