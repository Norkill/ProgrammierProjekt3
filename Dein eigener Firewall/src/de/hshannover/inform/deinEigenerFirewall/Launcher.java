package de.hshannover.inform.deinEigenerFirewall;

import javax.swing.JFrame;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

public class Launcher {
	
	/*
	 * erzeugt neues GUI anhand vorhandenes JFRAME
	 */
    public static void lauchGUI(JFrame frame) {
    	
    	new GUIController(frame);
    }
	
	
    // jetzt vom gui aufgerufen
	/*public void startGame() {
		gc = new GameController();
		gc.initGameBoard("Board1.txt");
		gc.start();
	}*/
    
    
    
}
