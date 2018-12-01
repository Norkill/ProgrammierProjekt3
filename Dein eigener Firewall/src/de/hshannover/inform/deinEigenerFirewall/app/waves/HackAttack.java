package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class HackAttack extends Wave {

	public HackAttack(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] { 40, 0, 10, 10, 10, 30, 0 };
	}

}
