package de.hshannover.inform.deinEigenerFirewall.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Utils {

	public Random random = new Random();
	public static String hiScoresPath;

	/**
	 * @return random number in range 1 to 100
	 */
	public static int getRandomNumber100() {
		Random random = new Random();
		return random.nextInt(100) + 1;
	}

	/**
	 * Loads a BufferedImage from a file
	 * 
	 * @param path
	 * @return image
	 */
	public static BufferedImage loadImage(String name) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(Utils.class.getResource("/images/" + name));
		} catch (Exception e) {
			System.out.println("Error loading an Image from: " + name);
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Loads hiscores from local file system(if they are not here if creates them
	 * first)
	 * 
	 * @return
	 */
	public static File loadHiscores() {
		return new File(hiScoresPath);
	}

	/**
	 * 
	 * @return all avaliable boards as ArrayList of Strings
	 */
	public static ArrayList<String> getBoardNames() {
		ArrayList<String> results = new ArrayList<>();
		results.add("56k Dial-up 2 Adern");
		results.add("100Mbps 4 Adern");
		results.add("1Gbps 6 Adern");
		results.add("10 Gbps 8 Adern");
		results.add("Test 1 Adern");

		return results;
	}

	/**
	 * Creates a default hiScores file in system directory if it does not exist,
	 * sets its path into a variable
	 */
	public static void createHiScoresList() {
		File hiScores = null;
		//ClassLoader.getSystemClassLoader().getResource(".").getPath()+ "hiscores.txt"
		
		try {
			//System.out.println((Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + "hiscores.txt");
			//System.out.println(ClassLoader.getSystemClassLoader().getResource(".").getPath()+ "hiscores.txt");
			hiScores = new File(System.getProperty("user.home") + "\\hiscores.txt");
			
			if ( ! hiScores.exists()) {
				hiScores.createNewFile();
				fillHiscores(hiScores);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hiScoresPath = hiScores.getPath();
		
		

		//System.out.println((Utils.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getPath() + "hiscores.txt");
		if(hiScoresPath == null)
			hiScoresPath = System.getProperty("user.home") + "/hiscores.txt";

	}

	/**
	 * fills the hiscores file with default values
	 * 
	 * @param f
	 */
	private static void fillHiscores(File f) {

		try {
			OutputStream os = new FileOutputStream(f);
			InputStream is = Utils.class.getClassLoader().getResourceAsStream("res/defaulthiscores.txt");

			byte[] buffer = new byte[1024];
			int bytesRead;
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			is.close();
			os.close();
		} catch (IOException e) {
			System.out.println("failed to fill hiscores");
			e.printStackTrace();
		}
	}

	/**
	 * Scales the original image to new width and height
	 * 
	 * @param original
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static BufferedImage scaleImage(BufferedImage original, int newWidth, int newHeight) {
		BufferedImage resized = new BufferedImage(newWidth, newHeight, original.getType());
		Graphics2D g = resized.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(original, 0, 0, newWidth, newHeight, 0, 0, original.getWidth(), original.getHeight(), null);
		g.dispose();
		return resized;
	}
}
