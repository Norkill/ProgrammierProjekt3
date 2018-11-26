package de.hshannover.inform.deinEigenerFirewall.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

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
