package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

import de.hshannover.inform.deinEigenerFirewall.gui.audio.SoundManager;

/**
 * Just JButton with look and sounds 
 * @author Norbert
 *
 */
@SuppressWarnings("serial")
public class MyJButton extends JButton {

	/**
	 * Creates a new MyJButton 
	 * @param label text to be displayed
	 */
	public MyJButton(String label) {
		super(label);
		setFocusPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setOpaque(false);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(Color.GREEN);
				SoundManager.playSound(SoundManager.MOUSE_ON_BUTTON);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				SoundManager.playSound(SoundManager.ON_CLICK);
				setForeground(Color.BLACK);
			}

		});
	}

	
}
