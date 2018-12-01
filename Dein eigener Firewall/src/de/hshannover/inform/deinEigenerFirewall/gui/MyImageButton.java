package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;

import de.hshannover.inform.deinEigenerFirewall.gui.audio.SoundManager;

@SuppressWarnings("serial")
public class MyImageButton extends JButton{

	private BufferedImage img;
	public MyImageButton(BufferedImage img) {
		super();
		this.img = img;
		setFocusPainted(false);
		setMargin(new Insets(0, 0, 0, 0));
		setContentAreaFilled(false);
		setOpaque(false);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				SoundManager.playSound(SoundManager.MOUSE_ON_BUTTON);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				SoundManager.playSound(SoundManager.ON_CLICK);
			}

		});
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(img,0,0,null);
	}
	
	public void setImage(BufferedImage img) {
		this.img = img;
	}
}
