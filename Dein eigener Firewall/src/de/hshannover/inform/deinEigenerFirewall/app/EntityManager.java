package de.hshannover.inform.deinEigenerFirewall.app;

import java.util.ArrayList;

public class EntityManager {

	private ArrayList<Entity> entities;
	
	public void tick() {
		for(Entity e : entities) {
			e.tick();
			
		}
		
	}

}
