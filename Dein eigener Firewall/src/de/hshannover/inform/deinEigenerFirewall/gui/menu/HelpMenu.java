package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.gui.MyImageButton;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * This class shows help menu
 * 
 * @author Norbert
 *
 */
@SuppressWarnings("serial")
public class HelpMenu extends JPanel {

	private GUIController guic;
	private MyImageButton backButton;
	

	private MyImageButton arrowlButton;
	private MyImageButton arrowrButton;

	private BufferedImage[] helpImages;

	private int currentImg = 0;

	/**
	 * Creates new HelpMenu object and inits it
	 * 
	 * @param guic GUIController
	 */
	public HelpMenu(GUIController guic) {
		this.guic = guic;

		initHelp();
	}

	/**
	 * inits helpMenu object
	 */
	private void initHelp() {
		setLayout(null);
		setBounds(0, 0, guic.getGameWidth(), guic.getGameHeight());

		

		arrowlButton = new MyImageButton(Utils.loadImage("arrowl.png"));
		arrowlButton.setBounds(0, 0, 128, 720);
		arrowlButton.addActionListener(e -> prevImg());

		arrowrButton = new MyImageButton(Utils.loadImage("arrowr.png"));
		arrowrButton.setBounds(guic.getWidth() - 128, 0, 128, 720);
		arrowrButton.addActionListener(e -> nextImg());
		
		backButton = new MyImageButton(Utils.scaleImage(Utils.loadImage("exit.png"), 250, 80));
		backButton.setBounds(arrowlButton.getWidth()-1, 0, backButton.getImage().getWidth(), backButton.getImage().getHeight());
		backButton.addActionListener(e -> guic.setMenuState());

		helpImages = new BufferedImage[] { Utils.loadImage("help1.png"),
				Utils.loadImage("help2.png"), Utils.loadImage("help3.png") };

		add(backButton);
		add(arrowlButton);
		add(arrowrButton);
		
		repaint();
	}

	
	/**
	 * Sets next image of help menu
	 */
	private void nextImg() {
		if (currentImg == 2) {
			currentImg = 0;
		} else {
			currentImg++;
		}
		repaint();
	}

	/**
	 * Sets previous image of help menu
	 */
	private void prevImg() {
		if (currentImg == 0) {
			currentImg = 2;
		} else {
			currentImg--;
		}
		repaint();
	}

	/**
	 * Draws this component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.drawImage(helpImages[currentImg], arrowlButton.getWidth(), 0,
				guic.getWidth() - arrowlButton.getWidth() - arrowrButton.getWidth(), guic.getGameHeight(), null);
	}

}
