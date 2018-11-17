package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

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
		gcHandler.addScore(1);
		die();
	}

	@Override
	protected void atEnd() {
		gcHandler.addVirus();
		gcHandler.removeUserExperience(10);
		gcHandler.removeScore(5);
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);
	}

}
