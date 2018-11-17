package de.hshannover.inform.deinEigenerFirewall.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class GameParameterManager extends Observable {

	private int viruses = 0;
	private int userExperience = 100;
	private int score = 0;
	private String playerName = null;

	private GameControllerHandler gcHandler;
	private ArrayList<Integer> hiScoreList;
	private ArrayList<String> hiScoreNames;
	private double gameSpeed;
	private int scoreMultiplayer = 1;

	public GameParameterManager(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
		initHiScoreList();
		
	}

	protected void setScoreMultiplayer() {
		scoreMultiplayer = Math.max(1, (int)(gameSpeed/2.0 * gcHandler.getWays().size() / 2.0));
	}

	protected int getViruses() {
		return viruses;
	}

	protected void removeVirus() {
		if (viruses > 0)
			viruses--;
		setChanged();
		notifyObservers();
	}

	protected void addVirus() {
		viruses++;
		if (viruses > 2) {
			gcHandler.setLost();
			setChanged();
			notifyObservers();
			handleHighScore();
		}
		setChanged();
		notifyObservers();
	}

	protected int getUserExperience() {
		return userExperience;
	}

	protected void addUserExperience(int amount) {
		userExperience += amount;
		if (userExperience > 100)
			userExperience = 100;
		setChanged();
		notifyObservers();
	}

	protected void removeUserExperience(int amount) {
		userExperience -= amount;
		if (userExperience < 1) {
			gcHandler.setLost();
			setChanged();
			notifyObservers();
			handleHighScore();
		}
		setChanged();
		notifyObservers();
	}

	protected void addScore(int amount) {
		score += (amount * scoreMultiplayer);
		setChanged();
		notifyObservers();
	}

	protected void removeScore(int amount) {
		score -= amount;
		setChanged();
		notifyObservers();
	}

	private void initHiScoreList() {
		Scanner scanner = null;
		hiScoreList = new ArrayList<>();
		hiScoreNames = new ArrayList<>();
		try {
			scanner = new Scanner(new File(("res/hiscores.txt")));
			while (scanner.hasNextLine()) {
				hiScoreList.add(scanner.nextInt());
				hiScoreNames.add(scanner.next());

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File hiScores.txt not found!");
		}
	}

	public void setPlayerName(String name) {
		playerName = name;
	}

	public int getTopHiScore() {
		return hiScoreList.get(0);
	}

	public int getScore() {
		return score;
	}

	public ArrayList<Integer> getHiScorePointsList() {
		return hiScoreList;
	}

	public ArrayList<String> getHiScoreNamesList() {
		return hiScoreNames;
	}

	/**
	 * After player has lost, it will check if score was high enough to be on the
	 * HiScore list, if so waits till the mane gets written by Menu component and
	 * adds it to the hiscore list
	 */

	protected void handleHighScore() {
		int index = 10;
		for (int i = 0; i < 10; i++) {
			if (score > hiScoreList.get(i)) {
				index = i;
				break;
			}
		}
		// if hiscore better than any of current 10 scores insert on the list
		if (index < 10) {
			// Wait for input in menu
			setChanged();
			notifyObservers(new Integer(index + 1));
			while (playerName == null) {

				if (playerName != null)
					break;
			}
			if (playerName == "") {
				playerName = "Anonymous";
			}
			hiScoreList.add(index, score);
			hiScoreNames.add(index, playerName);

			try {
				File f = new File("res/hiscores.txt");
				f.delete();
				f.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				for (int i = 0; i < 10; i++) {
					bw.write(hiScoreList.get(i) + " " + hiScoreNames.get(i));
					if (i < 9) {
						bw.newLine();
					}
				}
				bw.close();
			} catch (IOException e) {
				System.out.println("Could not save the hiscore!");
			}
		} else {
			setChanged();
			notifyObservers(new Integer(-1));
		}
		
	}

	public void setGameSpeed(Double speed) {
		gameSpeed = speed;
	}
	
	public double getGameSpeed() {
		return gameSpeed;
	}

}
