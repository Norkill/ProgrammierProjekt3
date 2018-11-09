package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

public class MouseManager extends Observable implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		
		setChanged();
		notifyObservers(e);
		System.out.println("Click!");
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
