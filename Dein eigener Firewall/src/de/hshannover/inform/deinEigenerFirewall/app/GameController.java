package de.hshannover.inform.deinEigenerFirewall.app;

/**
 * Die Klasse ist fur den Spielverlauf verantwortlich, es beinhaltet die ganze
 * Spielschleife, initializiert alle Managers, pruft die LoseConditions und
 * wechselt die zustande wie Spiel, Pauze und Menu
 * 
 * @author Norbert
 *
 */
public class GameController implements Runnable{

	// false = menu, true = game
	private boolean running = false;
	private Thread thread;

	private GameControllerHandler gcHandler;
	private EntityManager entityManager;
	private WaveManager eventManager;
	private GameBoardModel gameBoardModel;

	private int viruses = 0;
	private int userExperience = 100;
	private boolean lost = false;
	private boolean paused = false;

	private String layout;

	public GameController(String layout) {
		this.layout = layout;
		
		gameBoardModel = new GameBoardModel(layout);
		gcHandler = new GameControllerHandler(this);
		entityManager = new EntityManager(gcHandler);
		eventManager = new WaveManager(gcHandler);
	}

	public void run() {

		int fps = 60;
		double timePerTick = 1000000000 / fps; // nanosecs
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

	private void backToMenu() {
		running = false;
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
	
	
}
