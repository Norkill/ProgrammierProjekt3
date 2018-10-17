package de.hshannover.inform.deinEigenerFirewall.app;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameBoardModel {

	public ArrayList<ArrayList<Point>> loadBoard(String fileName) {
		Scanner scanner = null;

		try {
			scanner = new Scanner(new File(("Boards/" + fileName)));
			
			
			ArrayList<ArrayList<Point>> ways = new ArrayList<ArrayList<Point>>();
			
			while(scanner.hasNextLine()) {
				Scanner tokenizer = new Scanner(scanner.nextLine());
				ArrayList<Point> way = new ArrayList<>();
				while(tokenizer.hasNextInt()) {
					way.add(new Point(tokenizer.nextInt(), tokenizer.nextInt()));
				}
				ways.add(way);
				tokenizer.close();
			}
			return ways;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error Loading the Board!!");
		} finally {
			scanner.close();
		}
		return null;

	}

}
