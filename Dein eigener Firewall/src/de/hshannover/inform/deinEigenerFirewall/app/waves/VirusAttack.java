package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class VirusAttack extends Wave{

	public VirusAttack(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] {40, 50 ,10};
		
	}

}
