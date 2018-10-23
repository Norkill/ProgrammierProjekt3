package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;

public class EntityManager {

	private ArrayList<Entity> entities;

	public EntityManager(GameControllerHandler gcHandler) {
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		for (Entity e : entities) {
			e.tick();

		}

	}

	public void addEntity(Entity e) {
		entities.add(e);
	}

	public void removeEntity(Entity e) {
		entities.remove(e);
	}

	protected ArrayList<Entity> getEntities() {
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
