package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.image.BufferedImage;

import de.hshannover.inform.deinEigenerFirewall.util.Utils;

public class Assets {

	public static BufferedImage[] normalPaketImgs;
	public static BufferedImage[] virusImgs;
	public static BufferedImage[] spamImgs;
	
	public Assets() {
		init();
	}
	
	private void init() {
		normalPaketImgs = new BufferedImage[3];
		normalPaketImgs[0] = Utils.loadImage("images/paket1.png");
		normalPaketImgs[1] = Utils.loadImage("images/paket2.png");
		normalPaketImgs[2] = Utils.loadImage("images/paket3.png");
		
		virusImgs = new BufferedImage[1];
		virusImgs[0] = Utils.loadImage("images/virus1.png");
		
		spamImgs = new BufferedImage[1];
		spamImgs[0] = Utils.loadImage("images/spam1.png");
		
		
	}
}
