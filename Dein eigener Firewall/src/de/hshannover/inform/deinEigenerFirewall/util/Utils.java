package de.hshannover.inform.deinEigenerFirewall.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URISyntaxException;
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
	public static BufferedImage loadImage(String name) {
		BufferedImage img = null;
		
		try {
			//img = ImageIO.read(loadFile("/images/" + name));
			img = ImageIO.read(Utils.class.getResource("/images/" + name));
		} catch (Exception e) {
			System.out.println("Error loading an Image from /images/: " + name);	
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Loads text file
	 * @param path of this file
	 * @return File instance of this file
	 */
	public static File loadFile(String path) {
		File f = null;
		try {
			f = new File(Utils.class.getResource(path).toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return f;
	}
	
	
	
	/**
	 * Scales the orginal image to new width and hieight
	 * @param original
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static BufferedImage scaleImage(BufferedImage original, int newWidth, int newHeight) {
		BufferedImage resized = new BufferedImage(newWidth, newHeight, original.getType());
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(original, 0, 0, newWidth, newHeight, 0, 0, original.getWidth(),
		    original.getHeight(), null);
		g.dispose();
		return resized;
	}
}
