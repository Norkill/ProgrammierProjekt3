package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

import de.hshannover.inform.deinEigenerFirewall.app.GameController;
import de.hshannover.inform.deinEigenerFirewall.app.GameFassade;
import de.hshannover.inform.deinEigenerFirewall.gui.audio.SoundManager;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.GameDrawer;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.HelpMenu;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.HiScoreMenu;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.MainMenu;

/**
 * GUIController class which maintains the correct usage of JFrame, setting
 * menus and communication between GameController
 * 
 * @author Norbert
 */
public class GUIController {

	private MainMenu mm;
	private GameDrawer gd;
	private HiScoreMenu hsm;
	private HelpMenu hm;
	private GameController gc;
	private MouseManager mouseManager;
	private SoundManager sm;
	protected static Assets assets;

	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

	private int currentState;
	private Map<Integer, JComponent> map;
	private JFrame frame;

	/**
	 * Creates a new GUIController object which maintains the correct usage of
	 * JFrame, setting menus and communication between GameController
	 * 
	 * @param frame
	 */
	public GUIController(JFrame frame) {
		this.frame = frame;

		Graphics2D g2d = (Graphics2D) frame.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		init();

		assets = new Assets();
		mouseManager = new MouseManager();
		frame.getContentPane().addMouseListener(mouseManager);
		
		frame.getContentPane().add(mm);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		currentState = 0;

	}

	/**
	 * inits the GUIController object with new menus
	 */
	private void init() {
		sm = new SoundManager();
		gc = new GameController();
		mm = new MainMenu(this);
		hsm = new HiScoreMenu(this);
		hm = new HelpMenu(this);
		gd = null;
		

		map = new HashMap<>();
		map.put(0, mm);
		map.put(1, hsm);
		map.put(2, hm);
	}

	
	public void stopGame() {
		gc.setRunning(false);
	}
	
	/**
	 * Shows main menu on the JFrame
	 */
	public void setMenuState() {
		frame.getContentPane().remove(map.get(currentState));
		frame.getContentPane().add(mm);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		currentState = 0;
	}

	/**
	 * shows HiScore menu on JFrame
	 */
	public void setHiScoreMenuState() {
		hsm.resetScores();
		frame.getContentPane().remove(map.get(currentState));
		frame.getContentPane().add(hsm);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		currentState = 1;
	}

	/**
	 * Shows help menu on the JFrame
	 */
	public void setHelpMenuState() {
		frame.getContentPane().remove(map.get(currentState));
		frame.getContentPane().add(hm);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		currentState = 2;
	}

	/**
	 * Shows game on the JFrame
	 * 
	 * @param speed  of the game
	 * @param layout of game board to play on
	 */
	public void setGameState(Double speed, String layout) {
		gc.initGameBoard(layout, getGameWidth(), getGameHeight());
		gc.resetGame();
		gc.setSpeed(speed);
		gc.start();
		
		gd = new GameDrawer(this);
		mouseManager.addObserver(gd);
		map.put(3, gd);
		frame.getContentPane().remove(map.get(currentState));
		frame.getContentPane().add(gd);
		frame.revalidate();
		frame.repaint();
		frame.pack();
		currentState = 3;
	}

	/**
	 * exits program
	 */
	public void setEndedState() {
		currentState = -1;
		gc.threadStop();
		System.exit(0);
	}

	/**
	 * shows actual state as int, which means: 0 - main menu, 1 - hiscore menu, 2 -
	 * help menu, 3 - game
	 */
	public int getCurrentState() {
		return currentState;
	}

	/**
	 * @return actual font to display most of the text in menus and game
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @return gamefassade object to get objects from game needed to display it
	 */
	public GameFassade getGameFassade() {
		return gc.getGameFassade();
	}

	/**
	 * @return width of the pane inside the JFrame
	 */
	public int getWidth() {
		return frame.getContentPane().getWidth();
	}

	/**
	 * @return height of the pane inside the JFrame
	 */
	public int getHeight() {
		return frame.getContentPane().getHeight();
	}

	/**
	 * @return width of the game itself - space for UI elements
	 */
	public int getGameWidth() {
		return getWidth() * 8 / 10;
	}

	/**
	 * @return height of the game itself - space for UI elements
	 */
	public int getGameHeight() {
		return getHeight();
	}
	
	public SoundManager getSoundmanager() {
		return sm;
	}
	

}
