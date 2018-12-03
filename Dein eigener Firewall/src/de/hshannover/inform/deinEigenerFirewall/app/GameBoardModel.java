package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
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
	 * Creates GameBoard object, inits it with bord at path and sets coordinates to
	 * list of points on which entities move
	 * 
	 * @param boardpath
	 *            name of board in boards source folder
	 * @param width
	 *            game width
	 * @param height
	 *            game height
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

		try {
			scanner = new Scanner(new File(("/res/boards/" + fileName)));

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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error Loading the Board!!");
		} finally {
			scanner.close();
		}

	}

	/**
	 * 
	 * @return list of ways that have been readen from a file
	 */
	public ArrayList<ArrayList<Point>> getWays() {
		return ways;
	}

}
