package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Paket extends Entity {

	protected double speed;
	private ArrayList<Point> way;
	private Point target;
	private int targetNumber;
	private boolean atTarget = false;
	protected GameControllerHandler gcHandler;
	protected Ellipse2D bounds;
	protected BufferedImage image;

	public Paket(GameControllerHandler gcHandler, ArrayList<Point> way) {
		this.gcHandler = gcHandler;
		this.way = way;
		this.x = way.get(0).getX();
		this.y = way.get(0).getY();
		targetNumber = 0;
		getNextTarget();
	}

	private void getNextTarget() {
		targetNumber++;
		if (targetNumber >= way.size()) {
			atEnd();
			return;
		}
		target = way.get(targetNumber);
	}

	// TODO: check if one time check until target reached enough
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

	public abstract void tick();

	protected void move() {
		if (atTarget) {
			getNextTarget();
		}
		moveTowardsTarget();
	}

	protected abstract void remove();

	protected abstract void atEnd();

	protected abstract void die();
	
	public void setImage(BufferedImage img) {
		this.image = img;
	}
	
	public void setBounds(Ellipse2D bounds) {
		this.bounds = bounds;
	}
	
	public Ellipse2D getBounds() {
		return bounds;
	}
}
