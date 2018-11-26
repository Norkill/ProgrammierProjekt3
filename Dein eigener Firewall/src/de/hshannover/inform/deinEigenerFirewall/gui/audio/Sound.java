package de.hshannover.inform.deinEigenerFirewall.gui.audio;

public class Sound {

	String name;
	
	Sound(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return name of file which this sound bound to
	 */
	String getName(){
		return name;
	}
}
