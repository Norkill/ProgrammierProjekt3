package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Abstract class Paket, extends the entity class, contains methods used by
 * pakets like move
 * 
 * @author Norbert
 *
 */
public abstract class Paket extends Entity {

	protected double speed;
	private ArrayList<Point> way;
	private Point target;
	private int targetNumber;
	private boolean atTarget = false;
	protected GameControllerHandler gcHandler;

	/**
	 * Creates a new Paket(should not be called, use exact paket instead)
	 * 
	 * @param gcHandler
	 * @param way
	 */
	public Paket(GameControllerHandler gcHandler, ArrayList<Point> way) {
		this.gcHandler = gcHandler;
		this.way = way;
		this.x = way.get(0).getX();
		this.y = way.get(0).getY();
		targetNumber = 0;
		getNextTarget();
	}

	/**
	 * set next target towards which paket will move next
	 */
	private void getNextTarget() {
		atTarget = false;
		targetNumber++;
		if (targetNumber >= way.size()) {
			atEnd();
			return;
		}
		target = way.get(targetNumber);
	}

	/**
	 * move towards next waypoint
	 */
	private void moveTowardsTarget() {
		// erstelle coordinaten differenz vektor
		double diffX = target.getX() - this.x;
		double diffY = target.getY() - this.y;
		double length = Math.sqrt(diffX * diffX + diffY * diffY);

		// prufe ob ziel erreicht wurde (wenn distanz kleiner als geschwindigkeit, gehe
		// zur terget und setze atTargetFlag = true
		if (length < speed) {
			this.x = target.getX();
			this.y = target.getY();
			atTarget = true;
			return;
		}
		// bewegung um den Vector differenz normaliziert zur lange Speed
		this.x += diffX / length * speed;
		this.y += diffY / length * speed;
	}

	/**
	 * called by GameController tells Paket to act
	 */
	public abstract void tick();

	/**
	 * Move towards target
	 * 
	 */
	protected void move() {
		if (atTarget) {
			getNextTarget();
		}
		moveTowardsTarget();
	}

	/**
	 * called if this entity gets clicked(removed by player)
	 */
	public abstract void remove();

	/**
	 * called if paket reaches end of its wave
	 */
	protected abstract void atEnd();

	/**
	 * removes this paket
	 */
	protected abstract void die();

}
