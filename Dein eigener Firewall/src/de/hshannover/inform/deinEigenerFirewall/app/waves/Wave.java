package de.hshannover.inform.deinEigenerFirewall.app.waves;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.Entity;
import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Hacker;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.NormalPaket;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.RussianSuperHacker;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Spam;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Trojan;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Virus;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Worm;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * Abstract class Wave, generates pakets based on probabilities from its child classes like NormalWave, VirusAttack
 * @author Norbert
 *
 */
public abstract class Wave {


	//to implement by every Wave type
	//every int of probabilities  is the chance of spawning a paket listed on same index in paketClasses array
	protected int[] probabilities;

	// list of all classes to construct new pakets
	@SuppressWarnings("rawtypes")
	protected Class[] paketClasses = { NormalPaket.class, Virus.class, Spam.class, Trojan.class, Worm.class,
			Hacker.class, RussianSuperHacker.class };

	GameControllerHandler gcHandler;
	private ArrayList<ArrayList<Point>> ways;

	public Wave(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
		ways = gcHandler.getWays();

	}



	/**
	 * one spawn in current wave(one wave is a few spawns)
	 */
	@SuppressWarnings("unchecked")
	public void spawn() {
		for (ArrayList<Point> way : ways) {
			int i = Utils.getRandomNumber100();
			int paketNumber = 0;
			int prob = probabilities[paketNumber];
			while (i > prob) {
				paketNumber++;
				prob += probabilities[paketNumber];
			}
			try {
				gcHandler.getEntityManager().addEntity((Entity) paketClasses[paketNumber]
						.getConstructor(GameControllerHandler.class, ArrayList.class).newInstance(gcHandler, way));

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Error while creating Paket in Wave Class!");
				e.printStackTrace();
			}
		}
	}
}
