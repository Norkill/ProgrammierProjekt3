package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

/**
 * Hacker will slowly try to reach to the end, if not catched you loose, has
 * many lives
 * 
 * @author Norbert
 *
 */
public class Hacker extends Paket {

	private int lives = 3;

	public Hacker(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 0.2 * gcHandler.getGameSpeed();
	}

	@Override
	public void tick() {
		move();

	}

	@Override
	public void remove() {
		if (lives > 0)
			lives--;
		else {
			gcHandler.addUserExperience(30);
			gcHandler.addScore(30);
			die();
		}
	}

	@Override
	protected void atEnd() {
		gcHandler.removeUserExperience(100);
		gcHandler.removeScore(50);
		gcHandler.addVirus();
		gcHandler.addVirus();
		gcHandler.addVirus();
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);

	}
}
