package de.hshannover.inform.deinEigenerFirewall.app;

/**
 * Reprasentiert alle darstellbare Objekte
 * @author Norbert
 *
 */
public abstract class Entity {

	protected double x, y;
	
	public abstract void tick();
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

}
