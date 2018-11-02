package de.hshannover.inform.deinEigenerFirewall.gui;

import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

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

	private Font font = new Font(Font.SANS_SERIF, Font.BOLD, 20);

	private int currentState;
	private Map<Integer, JComponent> map;
	private JFrame frame;

	public GUIController(JFrame frame) {
		this.frame = frame;
		init();

		frame.add(mm);
		frame.revalidate();
		frame.repaint();

		currentState = 0;
	}

	private void init() {
		mm = new MainMenu(this);
		hsm = new HiScoreMenu(this);
		hm = new HelpMenu(this);
		gd = new GameDrawer(this);

		map = new HashMap<>();
		map.put(0, mm);
		map.put(1, hsm);
		map.put(2, hm);
		map.put(3, gd);
	}

	/**
	 * wenn diese methode benutzt wird, wird der GUI Controller ab jetzt das Menu
	 * auf dem JFrame zeigen
	 */
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

	public void setGameState() {
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

}
