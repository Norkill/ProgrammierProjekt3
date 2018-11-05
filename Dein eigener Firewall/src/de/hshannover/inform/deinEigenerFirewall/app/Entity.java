package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.util.Observable;

/**
 * Reprasentiert alle darstellbare Objekte
 * @author Norbert
 *
 */
public abstract class Entity {

	protected double x, y;
	protected BufferedImage img = null;
	protected Shape bounds;
	
	public abstract void tick();
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public BufferedImage getImg() {
		return img;
	}

	public void setImg(BufferedImage img) {
		this.img = img;
	}

	public Shape getBounds() {
		return bounds;
	}

	public void setBounds(Shape bounds) {
		this.bounds = bounds;
	}
	
	

}
