package de.hshannover.inform.deinEigenerFirewall.app.waves;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import de.hshannover.inform.deinEigenerFirewall.app.GameControllerHandler;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;


/**
 * Abstrakte klasse Wave, es generiert die Pakete nach wahrscheinlichkeiten die in child klassen definiert sind (zB. NormalWave, VirusAttack..)
 * @author Norbert
 *
 */
public abstract class Wave {

	// Fur besondere Waves zu implementieren, liste aller paket namen und
	// wahrscheinlichkeit dass dieser paket in diesem Wave erzeugt wird
	// wahrscheinlichkeit IMMER in % (0-100)
	protected int[] probabilities;

	// Liste aller Pakate und ihre classen fur constructoren aufrufe
	protected Class[] paketClasses;

	// Liste aller zu erzeugenden verfugbaren pakete
	protected String[] paketNames = { "NormalPaket", "Virus", "Spam" };

	GameControllerHandler gcHandler;
	private ArrayList<ArrayList<Point>> ways;

	public Wave(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
		ways = gcHandler.getWays();
		mapPakets();
	}

	/**
	 * Liest aller namen der Paketen und speichert die klassen in eine Map fur
	 * konstruktoren zugriff
	 */
	private void mapPakets() {
		try {
			for (int i = 0; i < paketNames.length; i++) {

				Class<?> clazz = Class.forName("de.hshannover.inform.deinEigenerFirewall.app.pakete." + paketNames[i]);
				paketClasses[i] = clazz;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Spawns eine einzige "Welle" in den Wave(der normalerweise aus mehreren wellen
	 * besteht)
	 */
	public void spawn() {
		for (ArrayList<Point> way : ways) {
			int i = Utils.getRandomNumber100();
			int paketNumber = 0;
			int prob = probabilities[paketNumber];
			while (i > prob) {
				prob += probabilities[paketNumber];
			}

			// GameControllerHandler gcHandler, ArrayList<Point> way
			try {
				paketClasses[paketNumber].getConstructor(GameControllerHandler.class, ArrayList.class)
						.newInstance(new Object[] { gcHandler, way });
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				System.out.println("Error while creating Paket in Wave Class!");
				e.printStackTrace();
			}
		}
	}
}
