package de.hshannover.inform.deinEigenerFirewall.app;

public class GameController {

	
	//false = menu, true = game
	private boolean running = false;
	private Thread thread;
	
	private EntityManager entityManager;
	private EventManager eventManager;
	private GameBoardModel gameBoardModel;
	
	private int viruses = 0;
	private int userExperience = 100;
	
	public GameController() {

	}

	public void run() {

		// TODO: params to load different Maps
		gameBoardModel.loadBoard();

		int fps = 60;
		double timePerTick = 1000000000 / fps; // nanosecs
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while (running) {
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
}
