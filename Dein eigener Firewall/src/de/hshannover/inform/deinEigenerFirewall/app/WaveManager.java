package de.hshannover.inform.deinEigenerFirewall.app;

import de.hshannover.inform.deinEigenerFirewall.app.waves.*;
import de.hshannover.inform.deinEigenerFirewall.util.Timer;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/*
 * Wave Manager klasse der wahlt welches wave gerade lauft
 */
public class WaveManager {

	private Wave[] waves;
	private int[] waveProbabilities;
	private final long MILLIS_BETWEEN_WAVES = 10000;
	private final long SPAWNS_IN_WAVE = 10;
	private Timer waveTimer;
	private Timer spawnTimer;
	private Wave actualWave;
	private int waveNumber;

	/**
	 * Erzeugt neuer WaveManager, wenn neue waves addiert ist es zu korrigieren
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
	 * Wenn zeit abgelaufen setze aktuelles wave zufallig neu
	 *  sonst prufe ob aktuelles wave kann pakete senden und mache das
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
	 * Wenn zeit abgelaufen setze aktuelles wave zufallig neu
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
	 * pruft nach der zeitlichen abstand zwischen einzigen spawns
	 * 
	 * @return true wenn moglich
	 */
	private boolean canSpawn() {
		if(actualWave != null) {
			if (spawnTimer.getMillisFormStart() > (MILLIS_BETWEEN_WAVES * (100 - waveNumber) / 100) / SPAWNS_IN_WAVE) {
				return true;
			}
		}
		return false;
	}

	/**
	 * spawns Pakete des aktuelles Waves
	 */
	private void spawn() {
		spawnTimer.reset();
		actualWave.spawn();
	}
}
