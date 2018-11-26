package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * GameControllerHandler which is needed to make communication between different
 * objects of the Game possible
 * 
 * @author Norbert
 *
 */
public class GameControllerHandler {

	private GameController gc;

	/**
	 * Creates a new Handler object
	 * 
	 * @param gameController
	 */
	public GameControllerHandler(GameController gameController) {
		gc = gameController;
	}

	/**
	 * 
	 * @return list of all entities of EntityManager
	 */
	protected CopyOnWriteArrayList<Entity> getEntities() {
		return gc.getEntityManager().getEntities();
	}

	/**
	 * Tells EntityManager to remove Entity
	 * 
	 * @param e
	 *            Entity to remove
	 */
	public void removeEntity(Entity e) {
		gc.getEntityManager().removeEntity(e);
	}

	/**
	 * 
	 * @return list of ways on which Entities move themselves
	 */
	public ArrayList<ArrayList<Point>> getWays() {
		return gc.getGameBoardModel().getWays();
	}

	/**
	 * 
	 * @return GameParameterManager object which handles scores, lose conditions
	 *         etc.
	 */
	public GameParameterManager getGameParameterManager() {
		return gc.getGameParameterManager();
	}

	/**
	 * used if game was lost
	 */
	public void setLost() {
		gc.setLost();
	}

	/**
	 * 
	 * @return current number of viruses
	 */
	public int getViruses() {
		return gc.getGameParameterManager().getViruses();
	}

	/**
	 * removes one virus from gameParameterManager
	 */
	public void removeVirus() {
		gc.getGameParameterManager().removeVirus();
	}

	/**
	 * Adds one virus to GameParameterManager
	 */
	public void addVirus() {
		gc.getGameParameterManager().addVirus();
	}

	/**
	 * 
	 * @return UserExperience parameter form GameParameterManager
	 */
	public int getUserExperience() {
		return gc.getGameParameterManager().getUserExperience();
	}

	/**
	 * Adds userExperience parameter to GameParameterManager
	 * 
	 * @param amount
	 *            to be added
	 */
	public void addUserExperience(int amount) {
		gc.getGameParameterManager().addUserExperience(amount);
	}

	/**
	 * Removes userExperience parameter to GameParameterManager
	 * 
	 * @param amount
	 *            to be removed
	 */
	public void removeUserExperience(int amount) {
		gc.getGameParameterManager().removeUserExperience(amount);
	}

	/**
	 * 
	 * @return current hiScore
	 */
	public int getTopHiScore() {
		return gc.getGameParameterManager().getTopHiScore();
	}

	/**
	 * 
	 * @return current score
	 */
	public int getScore() {
		return gc.getGameParameterManager().getScore();
	}

	/**
	 * Adds points to score
	 * 
	 * @param amount
	 *            to be added
	 */
	public void addScore(int amount) {
		gc.getGameParameterManager().addScore(amount);
	}

	/**
	 * Removes points from score
	 * 
	 * @param amount
	 *            to be removed
	 */
	public void removeScore(int amount) {
		gc.getGameParameterManager().removeScore(amount);
	}

	/**
	 * 
	 * @return list of hiScores sorted form top to last score
	 */
	public ArrayList<Integer> getHiScorePointsList() {
		return gc.getGameParameterManager().getHiScorePointsList();
	}

	/**
	 * 
	 * @return List of Names of persons sorted from top to last score
	 */
	public ArrayList<String> getHiScoreNamesList() {
		return gc.getGameParameterManager().getHiScoreNamesList();
	}


	/**
	 * 
	 * @return EntityManager Object
	 */
	public EntityManager getEntityManager() {
		return gc.getEntityManager();
	}

	/**
	 * 
	 * @return Ticker object which synchronizes ticks with GameController
	 */
	public Ticker getTicker() {
		return gc.getTicker();
	}

	/**
	 * Sets player name when hiscore needs to be saved
	 * 
	 * @param name
	 */
	public void setPlayerName(String name) {
		gc.getGameParameterManager().setPlayerName(name);
	}

	/**
	 * 
	 * @return gameSpeed multiplayer value
	 */
	public double getGameSpeed() {
		return gc.getGameParameterManager().getGameSpeed();
	}
}
