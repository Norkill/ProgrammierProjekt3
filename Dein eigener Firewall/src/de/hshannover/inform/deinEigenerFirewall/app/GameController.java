package de.hshannover.inform.deinEigenerFirewall.app;

/**
 * GameController class, it starts the game, creates all needed objects and
 * guarantees all needed getters and setters in GameFassade object To start one
 * must initGameBoard object with a board filename
 * 
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
	private WaveManager waveManager;
	private GameBoardModel gameBoardModel;
	private GameParameterManager gpm;
	private GameFassade gf;
	private Ticker ticker;

	private boolean lost = false;

	/**
	 * Creates a new GameController object and inits it, still gameBoardinit must be
	 * called afterwards
	 */
	public GameController() {
		init();
	}

	/**
	 * inits GameController
	 */
	private void init() {
		gcHandler = new GameControllerHandler(this);
		entityManager = new EntityManager(gcHandler);
		ticker = new Ticker();
		gpm = new GameParameterManager(gcHandler);
		gf = new GameFassade(gcHandler);
	}

	/**
	 * Resets all parameters, entities and waves of this Game, called when new Game
	 * starts
	 */
	public void resetGame() {
		entityManager = new EntityManager(gcHandler);
		gpm.deleteObservers();
		gpm = new GameParameterManager(gcHandler);
		waveManager = new WaveManager(gcHandler);
		lost = false;
		
	}

	/**
	 * inits gameBoard
	 * 
	 * @param layout
	 *            gameboard filename
	 * @param gameWidth
	 * @param gameHeight
	 */
	public void initGameBoard(String layout, int gameWidth, int gameHeight) {
		gameBoardModel = new GameBoardModel(layout, gameWidth, gameHeight);
		gpm.setScoreMultiplayer();
		waveManager = new WaveManager(gcHandler);
	}

	/**
	 * Runnable, starts game, aking it tick at FPS rate
	 */
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
	
	}

	/**
	 * tick method which is used in run method, it tells for example entities when
	 * to move
	 */
	private void tick() {
		ticker.tick();
		entityManager.tick();
		waveManager.tick();
	}

	/**
	 * Runnable start method
	 */
	public synchronized void start() {
		if (running)
			return;
		running = true;
		System.out.println("running");
		thread = new Thread((Runnable) this);
		System.out.println("new thread");
		thread.start();
		System.out.println("start");
	}

	/**
	 * Runnable stop method
	 */
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


	/**
	 * 
	 * @return GameParameterManager object
	 */
	public GameParameterManager getGameParameterManager() {
		return gpm;
	}

	/**
	 * 
	 * @return GameBoardModel object
	 */
	protected GameBoardModel getGameBoardModel() {
		return gameBoardModel;
	}

	/**
	 * 
	 * @return WaveManagerObject
	 */
	protected WaveManager getWaveManager() {
		return waveManager;
	}

	/**
	 * 
	 * @return EntityManager object which manages all Entities in the game
	 */
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	/**
	 * 
	 * @return GameFassade object which contains all needed getters and seters to
	 *         communicate with the Game and draw it
	 */
	public GameFassade getGameFassade() {
		return gf;
	}

	/**
	 * 
	 * @return Ticker Object to synchronize other object which wants to run parallel
	 *         to the game
	 */
	public Ticker getTicker() {
		return ticker;
	}

	/**
	 * Called when game is lost, used to stop the run method
	 */
	protected void setLost() {
		this.lost = true;
	}

	/**
	 * Sets speed of the game (double value)
	 * 
	 * @param speed
	 */
	public void setSpeed(Double speed) {
		gpm.setGameSpeed(speed);
	}
	
	/**
	 * Stops thread completely from running, called if you want to exit a program
	 */
	public void threadStop() {
		stop();
	}
	
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	
}
