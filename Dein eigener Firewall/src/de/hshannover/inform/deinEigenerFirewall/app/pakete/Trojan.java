package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

/**
 * Trojan horse paket, remains hidden until detected by the Player (will look
 * like an ordinary paket)
 * 
 * @author Norbert
 *
 */
public class Trojan extends Paket {

	private BufferedImage image2;
	private int timer = 10;
	private boolean detected = false;

	public Trojan(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 0.5 * gcHandler.getGameSpeed();
	}

	@Override
	public void tick() {
		move();
		if (detected) {
			timer--;
		}
	}

	@Override
	public void remove() {
		if (!detected) {
			detect();
		} else if (detected && timer < 0) {

			gcHandler.addUserExperience(3);
			gcHandler.addScore(4);
			die();
		}
	}

	@Override
	protected void atEnd() {
		gcHandler.removeScore(2);
		gcHandler.addVirus();
		gcHandler.removeUserExperience(7);
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);

	}

	public void detect() {
		detected = true;
		setImg(image2);
	}

	public void setImage2(BufferedImage img) {
		image2 = img;
	}

	public boolean isDetected() {
		return detected;
	}
}
