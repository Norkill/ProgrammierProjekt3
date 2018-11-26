package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.Timer;

import de.hshannover.inform.deinEigenerFirewall.app.Paket;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

@SuppressWarnings("serial")
public class Animation extends JComponent {

	private BufferedImage img;
	private int x, y;
	private Point rotationPoint;
	private int orientation = 1;
	private Timer t;
	private double angle = 0;

	
	public Animation(GUIController guic, Paket p, AnimationHandler ah) {
		setLayout(null);
		setBounds(0, 0, guic.getGameWidth(), guic.getGameHeight());
		ah.addAnimation(this);
		img = p.getImg();
		x = (int) p.getX();
		y = (int) p.getY();

		if (Utils.getRandomNumber100() > 50)
			orientation = -1;
	
		rotationPoint = new Point((int) (guic.getGameWidth() / 2
				+ (guic.getGameHeight() / 3 * ((Utils.getRandomNumber100() / 100))) * orientation ) + (guic.getGameWidth()/3*orientation), y);
		
		t = new Timer(1000 / 60, e -> {
			angle += Math.toRadians(10) * orientation;
			if (angle > Math.PI || angle < -Math.PI) {
				ah.removeAnimation(this);
				t.stop();
			}
		});
		t.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.rotate(angle, rotationPoint.x, rotationPoint.y);
		g.drawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2, null);
		g2d.rotate(-angle, rotationPoint.x, rotationPoint.y);
	}

}
