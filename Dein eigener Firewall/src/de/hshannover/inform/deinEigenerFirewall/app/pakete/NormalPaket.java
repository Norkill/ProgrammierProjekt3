package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;
/**
 * Just an ordinary paket, gives points when reaches end lowers userExperience when deleted too early
 * @author Norbert
 *
 */
public class NormalPaket extends Paket {

	public NormalPaket(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 1 * gcHandler.getGameSpeed();

	}

	@Override
	public void tick() {
		move();
	}
	
	@Override
	public void remove() {
		gcHandler.removeUserExperience(5);
		die();
	}

	@Override
	protected void atEnd() {
		gcHandler.addUserExperience(1);
		gcHandler.addScore(1);
		die();
	}

	// TODO: some animation things or sth, sounds etc
	@Override
	protected void die() {
		gcHandler.removeEntity(this);

	}

}
