package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * Reprasentiert alle darstellbare Objekte
 * 
 * @author Norbert
 *
 */
public abstract class Entity {

	protected double x, y;
	protected BufferedImage img = null;
	protected Rectangle bounds;
	protected int width, height;

	/**
	 * tickt object as GameController ticks
	 */
	public abstract void tick();

	/**
	 * 
	 * @return x coordinate of that entity
	 */
	public double getX() {
		return x;
	}

	/**
	 * 
	 * @return y coordinate of that entity
	 */
	public double getY() {
		return y;
	}

	/**
	 * 
	 * @return image of that entity
	 */
	public BufferedImage getImg() {
		return img;
	}

	/**
	 * Sets img to that entity
	 * 
	 * @param img
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
		this.width = img.getWidth();
		this.height = img.getHeight();
	}

	/**
	 * 
	 * @return width of that entity
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * 
	 * @return height of that entity
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * Used to collision detection, it takes current entity location into account,
	 * used also for drawImg as it contains x and y coordinates of the point at
	 * which image must be drawn for a correct location
	 */
	// TODO: WAS IST DAS!
	public Rectangle getCollisionBox() {
		return new Rectangle((int) (x - width / 2) - 1, (int) (y - height / 2) - 1, width, height);
	}

	// TODO: ?????
	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}

	// TODO: ???? siehe oben
	public boolean isColliding(Point p) {
		if (getCollisionBox().contains(p))
			return true;
		return false;
	}

}
