package de.hshannover.inform.deinEigenerFirewall.app;

public abstract class Paket extends Entity{

	public abstract void tick() ;
	
	protected abstract void move() ;
	
	protected abstract void remove() ;
	
	protected abstract void atEnd() ;
}
