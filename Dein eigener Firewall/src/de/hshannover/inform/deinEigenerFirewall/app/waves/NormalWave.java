package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class NormalWave extends Wave {

	public NormalWave(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] { 60, 10, 20, 10, 5, 5, 0 };

	}

}
