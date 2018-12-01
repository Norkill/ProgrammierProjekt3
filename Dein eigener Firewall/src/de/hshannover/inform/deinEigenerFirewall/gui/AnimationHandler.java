package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * AnimationHandler performs animations of pakets during gameplay using Swing thread
 * @author Norbert
 *
 */
@SuppressWarnings("serial")
public class AnimationHandler extends JComponent{

	/**
	 * Creates a new AnimationHandler object
	 * @param guic GUIController instance
	 */
	public AnimationHandler(GUIController guic) {
		setLayout(null);
		setBounds(0, 0, guic.getGameWidth(), guic.getGameHeight());
	}
	
	public void addAnimation(Animation a) {
		add(a);
	}
	
	public void removeAnimation(Animation a) {
		remove(a);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		revalidate();
		repaint();
	}
	
}
