package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComponent;

import de.hshannover.inform.deinEigenerFirewall.app.Entity;
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

		//add background image and set it to size of the window
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
		
		
	}

	/**
	 * Draws ways on which packages will be moving on to the background
	 * @param background
	 */
	private void drawWaysOnBackground(BufferedImage background) {
		ArrayList<ArrayList<Point>> ways = guic.getGameFassade().getWays();
		for (ArrayList<Point> way : ways) {
			for (int i = 1; i < way.size(); i++) {
				Graphics2D g2d = background.createGraphics();
				g2d.setColor(Color.BLACK);
				float dash1[] = { 10.0f };
				g2d.setStroke((Stroke) new BasicStroke(3.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1,
						0.0f));
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

	private void paintEntities(Graphics g) {
		if(guic.getGameFassade().getEntities() != null) {
			for (Entity e : guic.getGameFassade().getEntities()) {
				g.drawImage(e.getImg(), (int) e.getX(), (int) e.getY(), null);
			}
		}	
	}


	/**
	 * 1. Listens to EntityManager changes, in new Entities added adds listener to them and gives them a graphical image
	 * 
	 * 2. Listens to Entity-movements and redraws them
	 */
	@Override
	public void update(Observable obs, Object arg) {
	
		System.out.println("notified with: " + arg!=null);
		// arg - newly added entity, if so give it a new image
		if(arg != null) {
			setEntityImage((Entity) arg);
		}
		revalidate();
		repaint();

	}
	
	/**
	 * gives an entity a new image and puts itself as its observer
	 */
	private void setEntityImage(Entity e) {
		BufferedImage eimg = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = eimg.createGraphics();
		g2d.setColor(Color.RED);
		g2d.fillOval(0, 0, 64, 64);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(0, 0, 64, 64);
		e.addObserver(this);
		
	}

}
