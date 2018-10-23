package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class SpamAttack extends Wave{

	public SpamAttack(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] {10, 10 ,80};
		
	}

}
