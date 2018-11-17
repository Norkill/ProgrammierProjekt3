package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Das ist die Game Fassade, es ermoglicht den zugriff von Aussen fur die GUI Controller auf einige Objekte des GameControllers die notig sind um das Spiel zu Zeichnen
 * @author Norbert
 *
 */
public class GameFassade {

	private GameControllerHandler gcHandler;
	
	public GameFassade(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
	}
	
	public CopyOnWriteArrayList<Entity> getEntities(){
		return gcHandler.getEntities();
	}
	
	public void stopGame() {
		gcHandler.stopGame();
	}
	
	public ArrayList<ArrayList<Point>> getWays() {
		return gcHandler.getWays();
	}
	
	public EntityManager getEntityManager() {
		return gcHandler.getEntityManager();
	}
	
	public Ticker getTicker() {
		return gcHandler.getTicker();
	}
	
	public void addObserverToGameParameterManager(Observer o) {
		gcHandler.getGameParameterManager().addObserver(o);
	}
	
	public int getViruses() {
		return gcHandler.getViruses();
	}

	public int getUserExperience() {
		return gcHandler.getUserExperience();
	}
	
	public int getTopHiScore() {
		return gcHandler.getTopHiScore();
	}
	
	public int getScore() {
		return gcHandler.getScore();
	}
	
	public void setPlayerName(String name) {
		gcHandler.setPlayerName(name);
	}
	
	public ArrayList<Integer> getHiScorePointsList() {
		return gcHandler.getHiScorePointsList();
	}
	
	public ArrayList<String> getHiScoreNamesList() {
		return gcHandler.getHiScoreNamesList();
	}
}
