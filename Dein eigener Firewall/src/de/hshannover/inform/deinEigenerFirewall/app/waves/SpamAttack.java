package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class SpamAttack extends Wave {

	public SpamAttack(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] { 20, 10, 50, 10, 5, 5, 0 };

	}

}
