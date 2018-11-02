package de.hshannover.inform.deinEigenerFirewall.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Utils {

	public Random random = new Random();

	/**
	 * @return randomisierte zahl von 1 bis 100
	 */
	public static int getRandomNumber100() {
		Random random = new Random();
		return random.nextInt(100) + 1;
	}
	
	/**
	 * Ladet ein BufferedImage aus der datei aus
	 * @param path - pfad zur image
	 * @return image
	 */
	public static BufferedImage loadImage(String path) {
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.out.println("Error loading an Image from: " + path);	
			e.printStackTrace();
		}
		return img;
	}

}
