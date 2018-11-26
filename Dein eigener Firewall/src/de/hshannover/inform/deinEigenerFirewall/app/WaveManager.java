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
	private final long MILLIS_BETWEEN_WAVES = 10000;
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
		// initializiert neues Timer und addiert 7 sekunden um 3 sekunden am anfang des
		// spiels zu verzogern
		waveTimer = new Timer();
		waveTimer.addMillisElapsed(7000l);

		waves = new Wave[] { new NormalWave(gcHandler), new RandomWave(gcHandler), new SpamAttack(gcHandler),
				new VirusAttack(gcHandler) };
		waveProbabilities = new int[] { 40, 30, 20, 10 };
		spawnTimer = new Timer();
	}

	/**
	 * Checks if current wave can spawn and if it is ended chooses a new one
	 * randomly
	 */
	public void tick() {
		if (waveTimer.getMillisFormStart() > (MILLIS_BETWEEN_WAVES * (100 - waveNumber) / 100d)) {
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
