package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.Timer;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * Worm Paket, has the ability to create new Worms while alive
 * 
 * @author Norbert
 *
 */
public class Worm extends Paket {

	private Timer t;

	public Worm(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 0.75 * gcHandler.getGameSpeed();
		t = new Timer((int)((10000 - Utils.getRandomNumber100() * 40)/gcHandler.getGameSpeed()), e -> {
			gcHandler.getEntityManager().addEntity(new Worm(gcHandler, way));
		});
		t.start();

	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public void remove() {
		gcHandler.addUserExperience(1);
		gcHandler.addScore(1);
		die();
	}

	@Override
	protected void atEnd() {

		gcHandler.removeScore(3);
		gcHandler.removeUserExperience(10);
		die();
	}

	@Override
	protected void die() {
		t.stop();
		gcHandler.removeEntity(this);
	}

}
