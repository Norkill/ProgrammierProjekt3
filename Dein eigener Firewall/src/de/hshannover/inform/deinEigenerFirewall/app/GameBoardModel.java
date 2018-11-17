package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoardModel {

	private ArrayList<ArrayList<Point>> ways = null;
	private int gameWidth, gameHeight;
	
	public GameBoardModel(String boardpath, int width, int height) {
		gameWidth = width;
		gameHeight = height;
		loadBoard(boardpath);
	}
	
	private void loadBoard(String fileName) {

		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(("res/Boards/" + fileName)));

			ArrayList<ArrayList<Point>> waystemp = new ArrayList<ArrayList<Point>>();

			while (scanner.hasNextLine()) {
				Scanner tokenizer = new Scanner(scanner.nextLine());
				ArrayList<Point> way = new ArrayList<>();
				while (tokenizer.hasNextInt()) {
					way.add(new Point(tokenizer.nextInt()*gameWidth/100, tokenizer.nextInt()*gameHeight/100));
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

	public ArrayList<ArrayList<Point>> getWays() {
		return ways;
	}

}
