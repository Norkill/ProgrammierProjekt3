package de.hshannover.inform.deinEigenerFirewall.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

/**
 * GameParameterManager which keeps track of points, viruses, hiscores and other
 * game parameters
 * 
 * @author Norbert
 *
 */
public class GameParameterManager extends Observable {

	private int viruses = 0;
	private int userExperience = 100;
	private int score = 0;
	private String playerName = null;
	private boolean nameSetFlag = false;
	
	private GameControllerHandler gcHandler;
	private ArrayList<Integer> hiScoreList;
	private ArrayList<String> hiScoreNames;
	private double gameSpeed;
	private int scoreMultiplayer = 1;
	

	/**
	 * Creates and inits GameParameterManager
	 * 
	 * @param gcHandler
	 */
	public GameParameterManager(GameControllerHandler gcHandler) {
		this.gcHandler = gcHandler;
		initHiScoreList();

	}

	/**
	 * sets score multiplayer (if game is faster and have more ways user gets more
	 * points)
	 */
	protected void setScoreMultiplayer() {
		scoreMultiplayer = Math.max(1, (int) (gameSpeed / 2.0 * gcHandler.getWays().size() / 2.0));
	}

	/**
	 * 
	 * @return current number of viruses
	 */
	protected int getViruses() {
		return viruses;
	}

	/**
	 * removes a virus and notifies observers
	 */
	protected void removeVirus() {
		if (viruses > 0)
			viruses--;
		setChanged();
		notifyObservers();
	}

	/**
	 * adds a virus and notifies observers if 3 viruses are catched the game is set
	 * to Lost state
	 */
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

	/**
	 * 
	 * @return current amount of user experience
	 */
	protected int getUserExperience() {
		return userExperience;
	}

	/**
	 * Adds userExperience
	 * 
	 * @param amount
	 *            to be added
	 */
	protected void addUserExperience(int amount) {
		userExperience += amount;
		if (userExperience > 100)
			userExperience = 100;
		setChanged();
		notifyObservers();
	}

	/**
	 * Removes userExperience
	 * 
	 * @param amount
	 *            to be removed
	 */
	protected void removeUserExperience(int amount) {
		userExperience -= amount;
		if (userExperience < 1 && viruses <3) {
			gcHandler.setLost();
			setChanged();
			notifyObservers();
			handleHighScore();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Adds Points to the Score
	 * 
	 * @param amount
	 *            to be added
	 */
	protected void addScore(int amount) {
		score += (amount * scoreMultiplayer);
		setChanged();
		notifyObservers();
	}

	/**
	 * Removes points from score
	 * 
	 * @param amount
	 *            to be removed
	 */
	protected void removeScore(int amount) {
		score -= amount;
		setChanged();
		notifyObservers();
	}

	/**
	 * load hiscores from a file and saves them into arrayLists
	 */
	private void initHiScoreList() {
		Scanner scanner = null;
		hiScoreList = new ArrayList<>();
		hiScoreNames = new ArrayList<>();
		try {
			scanner = new Scanner(this.getClass().getResource("/res/hiscores.txt").openStream());
			while (scanner.hasNextLine()) {
				hiScoreList.add(scanner.nextInt());
				hiScoreNames.add(scanner.next());

			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("File hiscores.txt not found!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Sets player name if hiScore needs to be saved
	 * 
	 * @param name
	 */
	public void setPlayerName(String name) {
		playerName = name;
		nameSetFlag = true;
	}

	/**
	 * 
	 * @return current top score
	 */
	public int getTopHiScore() {
		return hiScoreList.get(0);
	}

	/**
	 * 
	 * @return current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return list of 10 best Hiscores sorted from best to last
	 */
	public ArrayList<Integer> getHiScorePointsList() {
		return hiScoreList;
	}

	/**
	 * 
	 * @return list of 10 best Person names sorted by Score from best to last
	 */
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
			while (!nameSetFlag) {
					//wait for inputBot to set name
			}
			nameSetFlag = false;
			if (playerName == null || playerName.isEmpty()) {
				playerName = "Anonymous";
			}
			checkName();
			hiScoreList.add(index, score);
			hiScoreNames.add(index, playerName);
			saveHiScores();			
		} else {
			setChanged();
			notifyObservers(new Integer(-1));
		}

	}

	/**
	 * checks if set name contains spaces so it could be used in hiscore
	 * if not does nothing
	 * else swaps all spaces with '_'
	 */
	private void checkName() {
		if(playerName.contains(" ")) {
			StringBuilder sb = new StringBuilder(playerName);
			for(int i=0; i<sb.length(); i++) {
				if(sb.charAt(i)==' ') {
					sb.setCharAt(i, '_');
				}
			}
			playerName = sb.toString();
		}
	}
	
	/**
	 * saves actual hiscore list to a file
	 */
	private void saveHiScores() {
		try {
			File f = new File("/res/hiscores.txt");
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
	}

	/**
	 * Sets GameSpeed parameter
	 * 
	 * @param speed
	 *            to be set (double)
	 */
	public void setGameSpeed(Double speed) {
		gameSpeed = speed;
	}

	/**
	 * 
	 * @return gameSpeed parameter
	 */
	public double getGameSpeed() {
		return gameSpeed;
	}

	
	public void forceSaveScore(int pos, int score, String name) {
		hiScoreList.remove(pos-1);
		hiScoreList.add(pos-1, score);
		hiScoreNames.remove(pos-1);
		hiScoreNames.add(pos-1, name);
		saveHiScores();
	}
}
