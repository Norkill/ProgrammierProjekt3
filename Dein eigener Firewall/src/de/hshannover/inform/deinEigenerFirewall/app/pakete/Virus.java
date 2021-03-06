package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

/**
 * Virus paket, adds virus when reaches end should be removed
 * 
 * @author Norbert
 *
 */
public class Virus extends Paket {

	public Virus(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 1 * gcHandler.getGameSpeed();

	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public void remove() {
		gcHandler.addUserExperience(1);
		gcHandler.addScore(2);
		die();
	}

	@Override
	protected void atEnd() {
		gcHandler.removeScore(5);
		gcHandler.addVirus();
		gcHandler.removeUserExperience(10);
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);
	}

}
