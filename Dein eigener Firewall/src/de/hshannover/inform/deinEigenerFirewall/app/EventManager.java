package de.hshannover.inform.deinEigenerFirewall.app;

import de.hshannover.inform.deinEigenerFirewall.app.waves.*;
import de.hshannover.inform.deinEigenerFirewall.util.Timer;

public class EventManager {
	
	private final long MILLIS_BETWEEN_WAVES = 10000;
	private final long SPAWNS_IN_WAVE = 10;
	private final long MILLIS_BETWEEN_SPAWNS = MILLIS_BETWEEN_WAVES / SPAWNS_IN_WAVE;
	
	private int actualWaveSpawns;
	private Timer waveTimer;
	private Timer spawnTimer;
	
	private Wave actualWave;
	

	public EventManager(GameControllerHandler gcHandler) {
		// initializiert neues Timer und addiert 7 sekunden um 3 sekunden am anfang des spiels zu verzogern
		waveTimer = new Timer();
		waveTimer.addMillisElapsed(7000l);
		
	}

	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	
	// TODO: Wave-objekten erzeugen und wahrscheinlichkeiten zuweisen, checks machen und in tick() einbinden
	private void checkForNewWave() {
		
	}

	
	
	
	/**
	 * pruft nach der zeitlichen abstand zwischen einzigen spawns
	 * @return true wenn moglich
	 */
	private boolean canSpawn() {
		if(spawnTimer.getMillisFormStart() > MILLIS_BETWEEN_SPAWNS) {
			return true;
		}
		return false;
	}
}
