package de.hshannover.inform.deinEigenerFirewall.app;

import java.util.Observable;

public class Ticker extends Observable{
	private int ticks = 0;
	
	protected void tick() {
		if(ticks>59) {
			ticks = 0;
		}
		ticks++;
		setChanged();
		notifyObservers();
	}

}
