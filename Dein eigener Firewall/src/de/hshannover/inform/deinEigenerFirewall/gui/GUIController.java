package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.app.GameController;
import de.hshannover.inform.deinEigenerFirewall.app.GameFassade;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.GameDrawer;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.HelpMenu;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.HiScoreMenu;
import de.hshannover.inform.deinEigenerFirewall.gui.menu.MainMenu;

/**
 * Kontroller-klasse zum kontrollieren welches menu gerade aktiv ist und als schnittstelle zum GameController
 * @author Norbert
 *
 */
public class GUIController {

	private MainMenu mm;
	private GameDrawer gd;
	private HiScoreMenu hsm;
	private HelpMenu hm;
	private GameController gc;
	private MouseManager mouseManager;
	protected static Assets assets;

	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

	private int currentState;
	private Map<Integer, JComponent> map;
	private JFrame frame;

	//private JPanel panel = new JPanel();
	public GUIController(JFrame frame) {
		this.frame = frame;
		Graphics2D g2d = (Graphics2D)frame.getGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		init();

		assets = new Assets();
		mouseManager = new MouseManager();
		
		frame.add(mm);
		frame.revalidate();
		frame.repaint();
		currentState = 0;
		
	}

	private void init() {
		mm = new MainMenu(this);
		hsm = new HiScoreMenu(this);
		hm = new HelpMenu(this);
		gd = null;
		gc = new GameController();
		
		map = new HashMap<>();
		map.put(0, mm);
		map.put(1, hsm);
		map.put(2, hm);
	}

	/**
	 * wenn diese methode benutzt wird, wird der GUI Controller ab jetzt das Menu
	 * auf dem JFrame zeigen
	 */
	
	//TODO: 1-2 siehe zeile!!
	public void setMenuState() {
		frame.remove(map.get(currentState));
		frame.add(mm);
		frame.revalidate();
		frame.repaint();
		currentState = 0;
	}

	public void setHiScoreMenuState() {
		frame.remove(map.get(currentState));
		frame.add(hsm);
		frame.revalidate();
		frame.repaint();
		currentState = 1;
	}

	public void setHelpMenuState() {
		frame.remove(map.get(currentState));
		frame.add(hm);
		frame.revalidate();
		frame.repaint();
		currentState = 2;
	}

	public void setGameState(String layout) {
		gc.initGameBoard(layout, getGameWidth(), getGameHeight());
		
		gc.start();
	
		gd = new GameDrawer(this);
		gd.addMouseListener(mouseManager);
		mouseManager.addObserver(gd);
		map.put(3, gd);
		frame.remove(map.get(currentState));
		frame.add(gd);
		frame.revalidate();
		frame.repaint();
		currentState = 3;
	}

	public void setEndedState() {
		currentState = -1;
		System.exit(0);
	}

	public int getCurrentState() {
		return currentState;
	}

	public Font getFont() {
		return font;
	}
	
	public GameFassade getGameFassade() {
		return gc.getGameFassade();
	}
	
	public int getWidth() {
		return frame.getWidth();
	}
	
	public int getHeight() {
		return frame.getHeight();
	}
	
	public int getGameWidth() {
		return getWidth()*8/10;
	}
	
	public int getGameHeight() {
		return getHeight();
	}

}
