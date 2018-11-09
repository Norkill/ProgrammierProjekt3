package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

public class Spam extends Paket {

	public Spam(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 2;

	}

	@Override
	public void tick() {
		move();
	}

	@Override
	public void remove() {
		die();
	}

	@Override
	protected void atEnd() {
		System.out.println("spam at end");
		die();
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);
	}

}
