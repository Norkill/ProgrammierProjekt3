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
	public static BufferedImage[] hackerImgs;
	public static BufferedImage[] russianHackerImgs;
	public static BufferedImage[] wormImgs;
	public static BufferedImage[] trojanImgs;

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
		normalPaketImgs[0] = Utils.loadImage("paket1.png");
		normalPaketImgs[1] = Utils.loadImage("paket2.png");
		normalPaketImgs[2] = Utils.loadImage("paket3.png");

		virusImgs = new BufferedImage[1];
		virusImgs[0] = Utils.loadImage("virus.png");

		spamImgs = new BufferedImage[1];
		spamImgs[0] = Utils.loadImage("spam1.png");
		
		hackerImgs = new BufferedImage[1];
		hackerImgs[0] = Utils.loadImage("hacker.png");
		
		russianHackerImgs = new BufferedImage[1];
		russianHackerImgs[0] = Utils.loadImage("rushacker.png");
		
		wormImgs = new BufferedImage[1];
		wormImgs[0] = Utils.loadImage("worm.png");
		
		trojanImgs = new BufferedImage[1];
		trojanImgs[0] = Utils.loadImage("trojan.png");

	}
}
