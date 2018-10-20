package de.hshannover.inform.deinEigenerFirewall.app;

import java.util.ArrayList;

/**
 * Das ist die Game Fassade, es ermoglicht den zugriff von Aussen fur die GUI Controller auf einige Objekte des GameControllers die notig sind um das Spiel zu Zeichnen
 * @author Norbert
 *
 */
public class GameFassadeClass implements GameFassade{

	private GameControllerHandler gcHandler;
	
	public GameFassadeClass(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
	}
	
	public ArrayList<Entity> getEntities(){
		return gcHandler.getEntities();
	}
}
