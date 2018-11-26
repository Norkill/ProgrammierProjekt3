package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

/**
 * MouseManager class, implements MouseListener to catch mouse actions it
 * extends Observable too
 * 
 * @author bk3-4nz-u1
 *
 */
public class MouseManager extends Observable implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * Notifies all registered observers when mouse is pressed
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		setChanged();
		notifyObservers(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
