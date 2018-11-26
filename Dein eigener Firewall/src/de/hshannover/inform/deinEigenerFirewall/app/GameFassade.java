package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * GameFassade object which is used to communicate between shifts of GUI and
 * Game itself using getters and setters
 * 
 * @author Norbert
 *
 */
public class GameFassade {

	private GameControllerHandler gcHandler;

	/**
	 * Creates GameFassade object
	 * 
	 * @param gcHandler
	 *            GameController Handler object
	 */
	public GameFassade(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
	}

	/**
	 * 
	 * @return list of all entities in Game
	 */
	public CopyOnWriteArrayList<Entity> getEntities() {
		return gcHandler.getEntities();
	}



	/**
	 * 
	 * @return list of ways on which Entities move themselves
	 */
	public ArrayList<ArrayList<Point>> getWays() {
		return gcHandler.getWays();
	}

	/**
	 * 
	 * @return entityManager object which has list of all entities and can add new
	 *         ones and remove old ones
	 */
	public EntityManager getEntityManager() {
		return gcHandler.getEntityManager();
	}

	/**
	 * 
	 * @return ticker object which is used to synchronize GUI with Gameobject
	 */
	public Ticker getTicker() {
		return gcHandler.getTicker();
	}

	/**
	 * Adds observer to GameParameterManager
	 * 
	 * @param o
	 *            observer to be added
	 */
	public void addObserverToGameParameterManager(Observer o) {
		gcHandler.getGameParameterManager().addObserver(o);
	}

	/**
	 * @return Current number of viruses in GameParameterManager
	 */
	public int getViruses() {
		return gcHandler.getViruses();
	}

	/**
	 * 
	 * @return UserExperience parameter form GameParameterManager
	 */
	public int getUserExperience() {
		return gcHandler.getUserExperience();
	}

	/**
	 * 
	 * @return current Hiscore
	 */
	public int getTopHiScore() {
		return gcHandler.getTopHiScore();
	}

	/**
	 * 
	 * @return current score
	 */
	public int getScore() {
		return gcHandler.getScore();
	}

	/**
	 * Sets player name when hiscore needs to be saved
	 * 
	 * @param name
	 */
	public void setPlayerName(String name) {
		gcHandler.setPlayerName(name);
	}

	/**
	 * 
	 * @return list of hiScores sorted form top to last score
	 */
	public ArrayList<Integer> getHiScorePointsList() {
		return gcHandler.getHiScorePointsList();
	}

	/**
	 * 
	 * @return List of Names of persons sorted from top to last score
	 */
	public ArrayList<String> getHiScoreNamesList() {
		return gcHandler.getHiScoreNamesList();
	}
}
