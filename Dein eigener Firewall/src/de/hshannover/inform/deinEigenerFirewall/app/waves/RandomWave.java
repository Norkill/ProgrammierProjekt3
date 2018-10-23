package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class RandomWave extends Wave{

	public RandomWave(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] {33, 33 ,34};
		
	}

}
