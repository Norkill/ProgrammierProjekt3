package de.hshannover.inform.deinEigenerFirewall.app.waves;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

public class NormalWave extends Wave{

	public NormalWave(GameControllerHandler gcHandler) {
		super(gcHandler);
		probabilities = new int[] {70, 10 ,20};
		
	}

	
}
