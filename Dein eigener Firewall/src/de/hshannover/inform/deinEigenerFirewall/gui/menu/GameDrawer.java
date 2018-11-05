package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;

import de.hshannover.inform.deinEigenerFirewall.app.Entity;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.NormalPaket;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Spam;
import de.hshannover.inform.deinEigenerFirewall.app.pakete.Virus;
import de.hshannover.inform.deinEigenerFirewall.gui.Assets;
import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

public class GameDrawer extends JComponent implements Observer {

	private GUIController guic;
	private JButton backButton;
	private Rectangle gameBounds;
	private BufferedImage backgroundImage;
	private int frameNumber = 0;
	
	public GameDrawer(GUIController guic) {

		this.guic = guic;
		init();
	}

	private void init() {
		// initialize and set bounds for window(layout)
		setLayout(null);
		setBounds(0, 0, guic.getWidth(), guic.getHeight());
		gameBounds = new Rectangle(0, 0, guic.getWidth() * 8 / 10, guic.getHeight());

		// add background image and set it to size of the window
		backgroundImage = Utils.scaleImage(Utils.loadImage("images/gameBackground.png"), (int) gameBounds.getWidth(),
				(int) gameBounds.getHeight());
		drawWaysOnBackground(backgroundImage);

		// add button to go back to menu
		backButton = new JButton("Spiel beenden");
		backButton.addActionListener(e -> {
			guic.setMenuState();
			guic.getGameFassade().stopGame();
		});
		backButton.setBounds((int) gameBounds.getWidth(), 0, (int) (guic.getWidth() - gameBounds.getWidth()), 100);
		add(backButton);

		// observes entity manager for changes in Entities List
		guic.getGameFassade().getEntityManager().addObserver(this);
		// to get game refresh rate
		guic.getGameFassade().getTicker().addObserver(this);
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
				g2d.drawLine((int) (way.get(i - 1).getX() * gameBounds.getWidth() / 100),
						(int) (way.get(i - 1).getY() * gameBounds.getHeight() / 100),
						(int) (way.get(i).getX() * gameBounds.getWidth() / 100),
						(int) (way.get(i).getY() * gameBounds.getHeight() / 100));
				g2d.dispose();
			}
		}
	}

	/**
	 * Draws this component using background image, all entities
	 */
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, (int) gameBounds.getWidth(), (int) gameBounds.getHeight(), null);
		paintEntities(g);
		revalidate();
		repaint();
	}

	public void paintEntities(Graphics g) {

		CopyOnWriteArrayList<Entity> entities = guic.getGameFassade().getEntities();
		for (Entity e : entities) {
			g.drawImage(e.getImg(), (int) (e.getX() * gameBounds.getWidth() / 100)-16,
					(int) (e.getY() * gameBounds.getHeight() / 100)-16, 30, 30, null);

		}
	}

	/**
	 * 1. Listens to EntityManager changes, in new Entities added adds listener to
	 * them and gives them a graphical image
	 * 
	 * 2. Listens to Entity-movements and redraws them
	 */
	@Override
	public void update(Observable obs, Object arg) {

		// arg - newly added entity, if so give it a new image
		if (arg instanceof Entity) {
			setEntityImage((Entity) arg);
		} else {
			revalidate();
			repaint();
		}
	}

	/**
	 * gives an entity a new image and puts itself as its observer
	 */
	private void setEntityImage(Entity e) {
		e.setBounds(new Rectangle(0, 0, 64, 64));
		if(e instanceof NormalPaket) {
			e.setImg(Assets.normalPaketImgs[Utils.getRandomNumber100()%Assets.normalPaketImgs.length]);
		} else if(e instanceof Virus) {
			e.setImg(Assets.virusImgs[0]);
		} else if(e instanceof Spam) {
			e.setImg(Assets.spamImgs[0]);
		}
		
	}

}
