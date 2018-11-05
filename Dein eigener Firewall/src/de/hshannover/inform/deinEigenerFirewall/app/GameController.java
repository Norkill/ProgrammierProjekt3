package de.hshannover.inform.deinEigenerFirewall.app;

import java.util.Observable;

/**
 * Die Klasse ist fur den Spielverlauf verantwortlich, es beinhaltet die ganze
 * Spielschleife, initializiert alle Managers, pruft die LoseConditions und
 * wechselt die zustande wie Spiel, Pauze und Menu
 * 
 * @author Norbert
 *
 */
public class GameController implements Runnable {

	// false = menu, true = game
	private boolean running = false;
	private Thread thread;

	private static final int FPS_LIMIT = 60;
	private GameControllerHandler gcHandler;
	private EntityManager entityManager;
	private WaveManager eventManager;
	private GameBoardModel gameBoardModel;
	private GameFassade gf;
	private Ticker ticker;

	private int viruses = 0;
	private int userExperience = 100;
	private boolean lost = false;
	private boolean paused = false;

	// private String layout;

	public GameController() {
		// this.layout = layout;

		gcHandler = new GameControllerHandler(this);
		entityManager = new EntityManager(gcHandler);
		ticker = new Ticker();
		gf = new GameFassade(gcHandler);
	}

	public void initGameBoard(String layout) {
		gameBoardModel = new GameBoardModel(layout);
		eventManager = new WaveManager(gcHandler);
	}

	public void run() {

		
		double timePerTick = 1000000000 / FPS_LIMIT; // nanosecs
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
			if (paused) {
				handlePaused();
			}

			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;

			lastTime = now;
			if (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}

			if (timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
		handleHighScore();
	}

	private void tick() {
		entityManager.tick();
		eventManager.tick();
		ticker.tick();

	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		thread = new Thread((Runnable) this);
		thread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void checkLoseConditions() {
		if (viruses > 2)
			lost = true;
		if (userExperience < 1)
			lost = true;
	}

	private void handlePaused() {

	}

	protected void backToMenu() {
		running = false;
		handleHighScore();
	}

	private void handleHighScore() {

	}

	protected GameBoardModel getGameBoardModel() {
		return gameBoardModel;
	}

	protected WaveManager getEventManager() {
		return eventManager;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	protected int getViruses() {
		return viruses;
	}

	protected void setViruses(int viruses) {
		this.viruses = viruses;
	}

	protected int getUserExperience() {
		return userExperience;
	}

	protected void setUserExperience(int userExperience) {
		this.userExperience = userExperience;
	}

	

	public GameFassade getGameFassade() {
		return gf;
	}

	public Ticker getTicker() {
		return ticker;
	}
}
