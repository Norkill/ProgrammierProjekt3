package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.image.BufferedImage;

import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * Class Assets loads and saves the images of entities
 * 
 * @author bk3-4nz-u1
 *
 */
public class Assets {

	public static BufferedImage[] normalPaketImgs;
	public static BufferedImage[] virusImgs;
	public static BufferedImage[] spamImgs;

	/**
	 * creates and inits Object Asets, after you can just use Assets.nameOfImg to
	 * get required image
	 */
	public Assets() {
		init();
	}

	/**
	 * inits all Assets
	 */
	private void init() {
		normalPaketImgs = new BufferedImage[3];
		normalPaketImgs[0] = Utils.loadImage("res/images/paket1.png");
		normalPaketImgs[1] = Utils.loadImage("res/images/paket2.png");
		normalPaketImgs[2] = Utils.loadImage("res/images/paket3.png");

		virusImgs = new BufferedImage[1];
		virusImgs[0] = Utils.loadImage("res/images/virus1.png");

		spamImgs = new BufferedImage[1];
		spamImgs[0] = Utils.loadImage("res/images/spam1.png");

	}
}
