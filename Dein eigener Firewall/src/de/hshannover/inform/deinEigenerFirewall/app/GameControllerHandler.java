package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Das ist ein Handler der ermoglicht und vereinfacht die kommunikation zwischen aller Klassen des Spielkerns
 * @author Norbert
 *
 */
public class GameControllerHandler {

	private GameController gc;
	private GameBoardModel gbm;
	private EntityManager em;
	private WaveManager evm;
	
	
	public GameControllerHandler(GameController gameController) {
		gc = gameController;
		gbm = gc.getGameBoardModel();
		em = gc.getEntityManager();
		evm = gc.getEventManager();
	}
	
	protected ArrayList<Entity> getEntities(){
		return em.getEntities();
	}
	
	public void removeEntity(Entity e) {
		em.removeEntity(e);
	}
	
	public ArrayList<ArrayList<Point>> getWays() {
		return gbm.getWays();
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
	
	
	
	
}
