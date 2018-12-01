package de.hshannover.inform.deinEigenerFirewall.app.pakete;

import java.awt.Point;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;

/**
 * THIS CLASS IS SUPER BUT DO NOT EXIST. I DON'T KNOW WHAT ARE YOU TALKING ABOUT
 * 
 * @author Norbert
 *
 */
public class RussianSuperHacker extends Hacker {

	/**
	 * This constructor do not create a russian hacker, because everybody knows that
	 * there are no hackers in Russia
	 * 
	 * @param gcHandler
	 * @param way
	 */
	public RussianSuperHacker(GameControllerHandler gcHandler, ArrayList<Point> way) {
		super(gcHandler, way);
		speed = 2 * gcHandler.getGameSpeed();
	}

	@Override
	protected void atEnd() {
		gcHandler.removeScore(gcHandler.getScore());
		gcHandler.getGameParameterManager().forceSaveScore(10, 0, "Ivan");
		gcHandler.addVirus();
		gcHandler.addVirus();
		gcHandler.addVirus();
		gcHandler.removeUserExperience(100);
		die();

	}

}
