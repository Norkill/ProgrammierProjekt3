package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Das ist ein Handler der ermoglicht und vereinfacht die kommunikation zwischen aller Klassen des Spielkerns
 * @author Norbert
 *
 */
public class GameControllerHandler {

	private GameController gc;
	
	
	
	public GameControllerHandler(GameController gameController) {
		gc = gameController;
	}
	
	protected CopyOnWriteArrayList<Entity> getEntities(){
		return gc.getEntityManager().getEntities();
	}
	
	public void removeEntity(Entity e) {
		gc.getEntityManager().removeEntity(e);
	}
	
	public ArrayList<ArrayList<Point>> getWays() {
		return gc.getGameBoardModel().getWays();
	}
	
	public GameParameterManager getGameParameterManager() {
		return gc.getGameParameterManager();
	}
	
	public void setLost() {
		gc.setLost();
	}

	public int getViruses() {
		return gc.getGameParameterManager().getViruses();
	}
	
	public void removeVirus() {
		gc.getGameParameterManager().removeVirus();
	}
	
	public void addVirus() {
		gc.getGameParameterManager().addVirus();
	}

	public int getUserExperience() {
		return gc.getGameParameterManager().getUserExperience();
	}

	public void addUserExperience(int amount) {
		gc.getGameParameterManager().addUserExperience(amount);
	}
	
	public void removeUserExperience(int amount) {
		gc.getGameParameterManager().removeUserExperience(amount);
	}
	
	public int getTopHiScore() {
		return gc.getGameParameterManager().getTopHiScore();
	}
	
	public int getScore() {
		return gc.getGameParameterManager().getScore();
	}
	
	public void addScore(int amount) {
		gc.getGameParameterManager().addScore(amount);
	}
	
	public void removeScore(int amount) {
		gc.getGameParameterManager().removeScore(amount);
	}
	
	public ArrayList<Integer> getHiScorePointsList() {
		return gc.getGameParameterManager().getHiScorePointsList();
	}
	
	public ArrayList<String> getHiScoreNamesList() {
		return gc.getGameParameterManager().getHiScoreNamesList();
	}
	
	public void stopGame() {
		gc.backToMenu();
	}
	
	public EntityManager getEntityManager() {
		return gc.getEntityManager();
	}
	
	public Ticker getTicker() {
		return gc.getTicker();
	}
	
	public void setPlayerName(String name) {
		gc.getGameParameterManager().setPlayerName(name);
	}
}
