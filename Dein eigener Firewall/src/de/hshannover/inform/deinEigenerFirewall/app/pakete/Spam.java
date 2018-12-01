package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

/**
 * Spam paket, removes userExperience when reaches end
 * 
 * @author Norbert
 *
 */
public class Spam extends Paket {

	public Spam(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 2 * gcHandler.getGameSpeed();

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
		gcHandler.removeUserExperience(5);
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);
	}

}
