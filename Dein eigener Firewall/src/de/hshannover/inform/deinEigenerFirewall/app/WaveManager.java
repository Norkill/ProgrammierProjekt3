package de.hshannover.inform.deinEigenerFirewall.app;

import de.hshannover.inform.deinEigenerFirewall.app.waves.*;
import de.hshannover.inform.deinEigenerFirewall.util.Timer;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/*
 * Wave Manager class which manages which pakets will be spawned and when
 */
public class WaveManager {

	private Wave[] waves;
	private int[] waveProbabilities;
	private final long MILLIS_BETWEEN_WAVES;
	private final long SPAWNS_IN_WAVE = 5;
	private Timer waveTimer;
	private Timer spawnTimer;
	private Wave actualWave;
	private int waveNumber;
	
	/**
	 * Creates a new WaveManager object and inits it
	 * 
	 * @param gcHandler
	 */
	public WaveManager(GameControllerHandler gcHandler) {
		// adds some time to timer to give player some time at the beginning of the game
		waveTimer = new Timer();
		waveTimer.addMillisElapsed(7000l);

		waves = new Wave[] { new NormalWave(gcHandler), new RandomWave(gcHandler), new SpamAttack(gcHandler),
				new VirusAttack(gcHandler), new WormsTrojans(gcHandler), new HackAttack(gcHandler) };
		waveProbabilities = new int[] {30, 20, 20, 10 , 10, 10};
		spawnTimer = new Timer();
		MILLIS_BETWEEN_WAVES = 8000 + gcHandler.getWays().size()*500;
	}

	/**
	 * Checks if current wave can spawn and if it is ended chooses a new one
	 * randomly
	 */
	public void tick() {
		if (waveTimer.getMillisFormStart() > (MILLIS_BETWEEN_WAVES * (50 + waveNumber) / 50d)) {
			setNextWave();
		}
		if (canSpawn()) {
			spawn();
		}
	}

	/**
	 * Sets new Wave
	 */
	private void setNextWave() {
		int i = Utils.getRandomNumber100();
		int waveNumber = 0;
		int prob = waveProbabilities[waveNumber];
		while (i > prob) {
			waveNumber++;
			prob += waveProbabilities[waveNumber];
		}
		actualWave = waves[waveNumber];
		waveNumber++;
		waveTimer.reset();
	}

	/**
	 * Checks if actual wave can spawn new Pakets
	 * 
	 * @return true if can
	 */
	private boolean canSpawn() {
		if (actualWave != null) {
			if (spawnTimer.getMillisFormStart() > (MILLIS_BETWEEN_WAVES * (100 - waveNumber) / 100) / SPAWNS_IN_WAVE) {
				return true;
			}
		}
		return false;
	}

	/**
	 * spawns Pakets of actual wave
	 */
	private void spawn() {
		spawnTimer.reset();
		actualWave.spawn();
	}
}
