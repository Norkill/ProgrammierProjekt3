package de.hshannover.inform.deinEigenerFirewall.app;

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
	private GameParameterManager gpm;
	private GameFassade gf;
	private Ticker ticker;

	private boolean lost = false;

	public GameController() {
		init();
	}

	private void init() {
		gcHandler = new GameControllerHandler(this);
		entityManager = new EntityManager(gcHandler);
		ticker = new Ticker();
		gpm = new GameParameterManager(gcHandler);
		gf = new GameFassade(gcHandler);
	}

	public void resetGame() {
		entityManager = new EntityManager(gcHandler);
		gpm = new GameParameterManager(gcHandler);
	}

	public void initGameBoard(String layout, int gameWidth, int gameHeight) {
		gameBoardModel = new GameBoardModel(layout, gameWidth, gameHeight);
		gpm.setScoreMultiplayer();
		eventManager = new WaveManager(gcHandler);
	}

	public void run() {

		double timePerTick = 1000000000 / FPS_LIMIT; // nanosecs
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running && !lost) {

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
	}

	private void tick() {
		ticker.tick();
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

	protected void backToMenu() {
		running = false;
	}
	
	public GameParameterManager getGameParameterManager() {
		return gpm;
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

	public GameFassade getGameFassade() {
		return gf;
	}

	public Ticker getTicker() {
		return ticker;
	}
	
	protected void setLost() {
		this.lost = true;
	}

	public void setSpeed(Double speed) {
		gpm.setGameSpeed(speed);		
	}
}
