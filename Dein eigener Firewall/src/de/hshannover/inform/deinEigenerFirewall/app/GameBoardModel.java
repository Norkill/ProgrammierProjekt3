package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GameBoardModel class, loads gameBoard from File, needs to be initialized
 * first(loadBoard method) saves Ways on which entities move
 * 
 * @author Norbert
 *
 */
public class GameBoardModel {

	private ArrayList<ArrayList<Point>> ways = null;
	private int gameWidth, gameHeight;

	/**
	 * Creates GameBoard object, inits it with board at path and sets coordinates to
	 * list of points on which entities move
	 * 
	 * @param boardpath name of board in boards source folder
	 * @param width     game width
	 * @param height    game height
	 */
	public GameBoardModel(String boardpath, int width, int height) {
		gameWidth = width;
		gameHeight = height;
		loadBoard(boardpath);
	}

	/**
	 * Loads board
	 * 
	 * @param fileName
	 */
	private void loadBoard(String fileName) {

		Scanner scanner = null;
	
		scanner = new Scanner(this.getClass().getResourceAsStream(fileName));
		ArrayList<ArrayList<Point>> waystemp = new ArrayList<ArrayList<Point>>();

		while (scanner.hasNextLine()) {
			Scanner tokenizer = new Scanner(scanner.nextLine());
			ArrayList<Point> way = new ArrayList<>();
			while (tokenizer.hasNextInt()) {
				way.add(new Point(tokenizer.nextInt() * gameWidth / 100, tokenizer.nextInt() * gameHeight / 100));
			}
			waystemp.add(way);
			tokenizer.close();
		}
		this.ways = waystemp;
		scanner.close();

	}

	/**
	 * 
	 * @return list of ways that have been read from a file
	 */
	public ArrayList<ArrayList<Point>> getWays() {
		return ways;
	}

}
