package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;

public class EntityManager extends Observable {

	private CopyOnWriteArrayList<Entity> entities;

	public EntityManager(GameControllerHandler gcHandler) {
		entities = new CopyOnWriteArrayList<>();
	}

	public void tick() {
		for (Entity e : entities) {
			e.tick();

		}
	}

	public void addEntity(Entity e) {
		entities.add(e);
		setChanged();
		notifyObservers(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	protected CopyOnWriteArrayList<Entity> getEntities() {
		return entities;
	}

	public Paket getPaketAtLoc(Point p) {

		for (Entity e : entities) {
			if (e instanceof Paket) {
				Paket paket = (Paket) e;
				if (paket.getBounds().contains(p)) {
					return paket;
				}
			}
		}
		return null;
	}

}
