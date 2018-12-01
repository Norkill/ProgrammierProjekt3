package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.app.Entity;
import de.hshannover.inform.deinEigenerFirewall.app.Paket;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Hacker;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.NormalPaket;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.RussianSuperHacker;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Spam;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Trojan;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Virus;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Worm;
import de.hshannover.inform.deinEigenerFirewall.gui.Animation;
import de.hshannover.inform.deinEigenerFirewall.gui.AnimationHandler;
import de.hshannover.inform.deinEigenerFirewall.gui.Assets;
import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * GameDrawer class draws the game, which means all entities and Gameboard, it
 * also have UI panel which shows all game parameters
 * 
 * @author bk3-4nz-u1
 *
 */
@SuppressWarnings("serial")
public class GameDrawer extends JPanel implements Observer {

	private GUIController guic;
	private BufferedImage backgroundImage;
	private GameUIPanel gameUIPanel;
	private AnimationHandler animationHandler;

	/**
	 * Creates and inits new GameDrawer object
	 * 
	 * @param guic
	 */
	public GameDrawer(GUIController guic) {
		this.guic = guic;
		init();
	}

	/**
	 * inits GameDrawer object
	 */
	private void init() {
		// initialize and set bounds for window(layout)
		setLayout(null);
		setBounds(0, 0, guic.getWidth(), guic.getHeight());

		// inits a new UIPanel for game parameters on the side
		gameUIPanel = new GameUIPanel(guic);
		gameUIPanel.setLocation(guic.getGameWidth(), 0);
		add(gameUIPanel);
		revalidate();
		repaint();
		// add background image and set it to size of the window
		backgroundImage = Utils.scaleImage(Utils.loadImage("res/images/gameBackground.png"), guic.getGameWidth(),
				guic.getGameHeight());
		drawWaysOnBackground(backgroundImage);

		// observes entity manager for changes in Entities List
		guic.getGameFassade().getEntityManager().addObserver(this);
		// to get game refresh rate
		guic.getGameFassade().getTicker().addObserver(this);
		animationHandler = new AnimationHandler(guic);
		add(animationHandler);
	}

	/**
	 * Draws ways on which packages will be moving on to the background
	 * 
	 * @param background
	 */
	private void drawWaysOnBackground(BufferedImage background) {
		ArrayList<ArrayList<Point>> ways = guic.getGameFassade().getWays();
		for (ArrayList<Point> way : ways) {
			for (int i = 1; i < way.size(); i++) {
				Graphics2D g2d = background.createGraphics();
				g2d.setColor(new Color(200, 200, 0));

				g2d.setStroke((Stroke) new BasicStroke(4f, BasicStroke.CAP_BUTT, BasicStroke.CAP_ROUND));
				g2d.drawLine((int) (way.get(i - 1).getX()), (int) (way.get(i - 1).getY()), (int) (way.get(i).getX()),
						(int) (way.get(i).getY()));
				g2d.dispose();
			}
		}
	}

	/**
	 * Draws this component using background image, all entities
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, guic.getGameWidth(), guic.getGameHeight(), null);
		paintEntities(g);
		revalidate();
		repaint();
	}

	/**
	 * Draws all Entities on the screen
	 * 
	 * @param g Graphics object
	 */
	public void paintEntities(Graphics g) {

		CopyOnWriteArrayList<Entity> entities = guic.getGameFassade().getEntities();
		for (Entity e : entities) {
			Rectangle r = e.getCollisionBox();
			g.drawImage(e.getImg(), (int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight(), null);

		}
	}

	/**
	 * 1. Listens to EntityManager changes, in new Entities added adds listener to
	 * them and gives them a graphical image
	 * 
	 * 2. MouseListener click
	 * 
	 * 3. GameController tick(render needed)
	 */
	@Override
	public void update(Observable obs, Object arg) {
		// arg - newly added entity, if so give it a new image
		if (arg instanceof Entity) {
			setEntityImage((Entity) arg);
			// arg - mouseListener click
		} else if (arg instanceof MouseEvent) {
			handleClick(arg);
			// arg - tick (new Frame must be rendered)
		} else {
			revalidate();
		}
	}

	/**
	 * gives an entity a new image and puts itself as its observer
	 */
	private void setEntityImage(Entity e) {
		if (e instanceof NormalPaket) {
			e.setImg(Assets.normalPaketImgs[Utils.getRandomNumber100() % Assets.normalPaketImgs.length]);
		} else if (e instanceof Virus) {
			e.setImg(Assets.virusImgs[0]);
		} else if (e instanceof Spam) {
			e.setImg(Assets.spamImgs[0]);
		} else if (e instanceof Trojan) {
			((Trojan) e).setImage2(Assets.trojanImgs[0]);
			e.setImg(Assets.normalPaketImgs[Utils.getRandomNumber100() % Assets.normalPaketImgs.length]);
		} else if (e instanceof Worm) {
			e.setImg(Assets.wormImgs[0]);
		} else if (e instanceof RussianSuperHacker) {
			e.setImg(Assets.russianHackerImgs[0]);
		} else if (e instanceof Hacker) {
			e.setImg(Assets.hackerImgs[0]);
		}
	}

	/**
	 * Handles click from observable mouseManager, asks entityManager if Paket was
	 * clicked and removes it if so
	 * 
	 * @param arg MouseEvent
	 */
	private void handleClick(Object arg) {
		MouseEvent e = (MouseEvent) arg;
		Paket p = guic.getGameFassade().getEntityManager().getPaketAtLoc(e.getPoint());
		if (p != null) {
			animationHandler.addAnimation(new Animation(guic, p, animationHandler));
			p.remove();
		}
	}
}
