package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Entity Manager class, for managing entity list
 * 
 * @author Norbert
 *
 */
public class EntityManager extends Observable {

	private CopyOnWriteArrayList<Entity> entities;

	/**
	 * Creates new EntityManager object and inits it with new empty list
	 * 
	 * @param gcHandler
	 */
	public EntityManager(GameControllerHandler gcHandler) {
		entities = new CopyOnWriteArrayList<>();
	}

	/**
	 * Ticks all entities when GameController ticks
	 */
	public void tick() {
		for (Entity e : entities) {
			e.tick();
		}
	}

	/**
	 * Adds entity to list, notifies observers with new entity as parameter
	 * 
	 * @param e
	 *            added Entity
	 */
	public void addEntity(Entity e) {
		entities.add(e);
		setChanged();
		notifyObservers(e);
	}

	/**
	 * removes entity from a list
	 * 
	 * @param e
	 *            removed Entity
	 */
	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	/**
	 * 
	 * @return list of Entities
	 */
	protected CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * Returns Paket Entity ad this Location
	 * 
	 * @param p
	 *            location to look for Entities at
	 * @return Entity if found else null
	 */
	public Paket getPaketAtLoc(Point p) {
		for (Entity e : entities) {
			if (e.isColliding(p)) {
				return (Paket) e;
			}

		}
		return null;
	}

}
