package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class WormsTrojans extends Wave {

	public WormsTrojans(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] { 20, 10, 10, 25, 25, 10, 0 };
	}

}
