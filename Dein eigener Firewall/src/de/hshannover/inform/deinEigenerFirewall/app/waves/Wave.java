package de.hshannover.inform.deinEigenerFirewall.app.waves;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.Entity;
import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.NormalPaket;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Spam;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Virus;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * Abstrakte klasse Wave, es generiert die Pakete nach wahrscheinlichkeiten die
 * in child klassen definiert sind (zB. NormalWave, VirusAttack..)
 * 
 * @author Norbert
 *
 */
public abstract class Wave {

	// Fur besondere Waves zu implementieren, liste aller paket namen und
	// wahrscheinlichkeit dass dieser paket in diesem Wave erzeugt wird
	// wahrscheinlichkeit IMMER in % (0-100)
	protected int[] probabilities;

	// Liste aller Pakate und ihre classen fur constructoren aufrufe
	@SuppressWarnings("rawtypes")
	protected Class[] paketClasses = { NormalPaket.class, Virus.class, Spam.class };

	GameControllerHandler gcHandler;
	private ArrayList<ArrayList<Point>> ways;

	public Wave(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
		ways = gcHandler.getWays();

	}

	/**
	 * Liest aller namen der Paketen und speichert die klassen in eine Map fur
	 * konstruktoren zugriff
	 */

	/**
	 * Spawns eine einzige "Welle" in den Wave(der normalerweise aus mehreren wellen
	 * besteht)
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
