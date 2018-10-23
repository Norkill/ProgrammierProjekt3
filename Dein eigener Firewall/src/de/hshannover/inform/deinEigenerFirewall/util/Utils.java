package de.hshannover.inform.deinEigenerFirewall.util;

import java.util.Random;

public class Utils {

	public Random random = new Random();

	/**
	 * @return randomisierte zahl von 1 bis 100
	 */
	public static int getRandomNumber100() {
		Random random = new Random();
		return random.nextInt(100) + 1;

	}

}
