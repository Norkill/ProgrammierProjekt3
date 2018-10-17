package de.hshannover.inform.deinEigenerFirewall;

import javax.swing.JFrame;

import de.hshannover.inform.deinEigenerFirewall.app.GameController;
import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

public class Launcher {
	GameController gc;
	GUIController guic;

	

	public void startGame() {
		gc = new GameController();
		guic = new GUIController();
	}
}
