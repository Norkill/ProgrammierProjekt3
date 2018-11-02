package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;

public class Virus extends Paket{

	public Virus(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 1;
		System.out.println("Virus paket created");
	}

	@Override
	public void tick() {

		move();
	}

	

	@Override
	protected void remove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void atEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void die() {
		gcHandler.removeEntity(this);		
	}

}
