package de.hshannover.inform.deinEigenerFirewall.app;

import java.util.Observable;

/**
 * Class Ticker to synchronize observers to GameController
 * 
 * @author Norbert
 *
 */
public class Ticker extends Observable {
	private int ticks = 0;

	/**
	 * Called be GameController, notifies observers when GameController ticks
	 */
	protected void tick() {
		if (ticks > 59) {
			ticks = 0;
		}
		ticks++;
		setChanged();
		notifyObservers();
	}

}
