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

	public int getViruses() {
		return gc.getViruses();
	}

	public void setViruses(int viruses) {
		gc.setViruses(viruses);
	}

	public int getUserExperience() {
		return gc.getUserExperience();
	}

	public void setUserExperience(int userExperience) {
		gc.setUserExperience(userExperience);
	}
	
	public void stopGame() {
		gc.backToMenu();
	}
	
	public EntityManager getEntityManager() {
		return gc.getEntityManager();
	}
	
	public int getTicks() {
		return gc.getTicks();
	}
}
