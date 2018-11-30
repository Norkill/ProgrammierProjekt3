package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.gui.MyJButton;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * MainMenu class, shows on the start, has all buttons and parameter setter for
 * start
 * 
 * @author bk3-4nz-u1
 *
 */
@SuppressWarnings("serial")
public class MainMenu extends JPanel {

	private GUIController guic;
	private MyJButton newGameButton;
	private MyJButton hiScoreButton;
	private MyJButton helpButton;
	private MyJButton closeButton;
	private JLabel layout;
	private JLabel speed;
	private JLabel title;
	private JPanel[] grids;

	private JSpinner layoutSpinner;
	private ArrayList<Double> speeds;
	private JSpinner speedSpinner;
	
	private BufferedImage background;
	private Timer t;
	private int imgY = 0;
	private boolean up = true;

	/**
	 * Creates and inits MainMenu
	 * 
	 * @param guic
	 */
	public MainMenu(GUIController guic) {
		this.guic = guic;
		setAnimatedBackground();
		initMenu();
	}

	/*
	 * LAYOUT title gird0 grid0 grid0 drid1 grid1 grid1 ...
	 */

	/**
	 * Sets new Background image and a Timer to move it up and down to look better
	 */
	private void setAnimatedBackground() {
		background = Utils.loadImage("res/images/menu.png");
		t = new Timer(1000 / 60, e -> { 
			
			repaint();
			if(imgY>guic.getGameHeight())
				up = false;
			if(imgY<0)
				up = true;
			
			if(up)
				imgY++;
			else
				imgY--;
		});
		t.start();
	}
	
	/**
	 * inits MainMenu with all the labels and buttons
	 */
	private void initMenu() {
		setLayout(new GridLayout(7, 1, 20, 20));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		
		newGameButton = new MyJButton("Neues Spiel");
		
		newGameButton.addActionListener(
				e -> guic.setGameState((Double) speedSpinner.getValue(), (String) layoutSpinner.getValue()));

		hiScoreButton = new MyJButton("HiScores");
		hiScoreButton.addActionListener(e -> guic.setHiScoreMenuState());

		helpButton = new MyJButton("Hilfe");
		helpButton.addActionListener(e -> guic.setHelpMenuState());

		closeButton = new MyJButton("Beenden");
		closeButton.addActionListener(e -> guic.setEndedState());

		layout = new JLabel("Spielbrett", SwingConstants.CENTER);
		layout.setToolTipText("Wahle dein Spielbrett \n(Mehr adern macht es schweriger \naber gibt auch mehr punkte)");

		speed = new JLabel("Geschwindigkeit", SwingConstants.CENTER);
		speed.setToolTipText("Wahle die Geschwindigkeit des Spiels\n (Je schneller desto mehr Punkte) ");
		title = new JLabel("The Firewall", SwingConstants.CENTER);

		initSpinners();

		grids = new JPanel[6];
		grids[0] = new JPanel(new GridLayout(1, 3));
		grids[0].add(newGameButton);
		grids[0].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[0].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[0].setOpaque(false);

		grids[1] = new JPanel(new GridLayout(1, 3));
		grids[1].add(speed);
		grids[1].add(speedSpinner);
		grids[1].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[1].setOpaque(false);
		
		grids[2] = new JPanel(new GridLayout(1, 3));
		grids[2].add(layout);
		grids[2].add(layoutSpinner);
		grids[2].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[2].setOpaque(false);
		
		grids[3] = new JPanel(new GridLayout(1, 3));
		grids[3].add(hiScoreButton);
		grids[3].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[3].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[3].setOpaque(false);
		
		grids[4] = new JPanel(new GridLayout(1, 3));
		grids[4].add(helpButton);
		grids[4].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[4].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[4].setOpaque(false);
		
		grids[5] = new JPanel(new GridLayout(1, 3));
		grids[5].add(closeButton);
		grids[5].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[5].add(Box.createRigidArea(new Dimension(0, 15)));
		grids[5].setOpaque(false);
		add(title);
		add(grids[0]);
		add(grids[1]);
		add(grids[2]);
		add(grids[3]);
		add(grids[4]);
		add(grids[5]);

		setFontToComponents();

		repaint();
	}
	
	/**
	 * Paints this component
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(background, 0,0-imgY,null);
	}
	

	/**
	 * inits speed and layout spinners
	 */
	private void initSpinners() {
		speeds = new ArrayList<>();
		speeds.add(1.0);
		speeds.add(2.0);
		speeds.add(3.0);
		speedSpinner = new JSpinner(new SpinnerListModel(speeds));
		speedSpinner.setOpaque(false);
		speedSpinner.getEditor().setOpaque(false);
		((JSpinner.DefaultEditor)speedSpinner.getEditor()).getTextField().setOpaque(false);
	
		layoutSpinner = new JSpinner(new SpinnerListModel(getLayoutList()));
		layoutSpinner.setOpaque(false);
		layoutSpinner.getEditor().setOpaque(false);
		((JSpinner.DefaultEditor)layoutSpinner.getEditor()).getTextField().setOpaque(false);
		
	}
	/**
	 * loads all the filenames from boards directory
	 * 
	 * @return list of filenames
	 */
	private ArrayList<String> getLayoutList() {
		ArrayList<String> results = new ArrayList<>();

		File[] files = new File("res/boards").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				results.add(file.getName());
			}
		}
		return results;
	}

	/**
	 * sets font to all its components
	 */
	private void setFontToComponents() {
		for (JPanel p : grids) {
			for (Component c : p.getComponents()) {
				c.setFont(guic.getFont());
			}
		}
		for (Component c : getComponents()) {
			c.setFont(guic.getFont());
		}
	}

}
