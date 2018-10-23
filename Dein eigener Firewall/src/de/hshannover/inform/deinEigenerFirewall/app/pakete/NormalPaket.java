package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class NormalPaket extends Paket {

	public NormalPaket(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 1;
	}

	@Override
	public void tick() {
		move();
	}

	

	@Override
	protected void remove() {
		gcHandler.setUserExperience(gcHandler.getUserExperience()-5);
		die();
	}

	@Override
	protected void atEnd() {
		gcHandler.setUserExperience(gcHandler.getUserExperience()+1);
		die();
	}

	
	// TODO: some animation things or sth, sounds etc
	@Override
	protected void die() {
		gcHandler.removeEntity(this);
		
	}

	

}
