package de.hshannover.inform.deinEigenerFirewall;

import java.awt.Dimension;

import javax.swing.JFrame;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;

public class Launcher {
	
	private GUIController guic ;
	
	/*
	 * erzeugt neues GUI anhand vorhandenes JFRAME
	 */
    public void lauchGUI(JFrame frame) {
    	
    	guic = new GUIController(frame);
    }
	
	
	/*public void startGame() {
		gc = new GameController("Board1.txt");
		guic = new GUIController();
		gc.start();
	}*/
    
    
    
}
