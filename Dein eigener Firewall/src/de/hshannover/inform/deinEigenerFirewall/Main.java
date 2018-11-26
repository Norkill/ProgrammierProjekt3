package de.hshannover.inform.deinEigenerFirewall;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	private final static int WIDTH = 1280; 
	private final static int HEIGHT = 720;
	private static JFrame frame;
	

	public static void main(String[] args) {
		initFrame();
		Launcher.lauchGUI(frame);
	}
	
	
	private static void initFrame() {
		frame = new JFrame("Firewall game");
		frame.setSize(WIDTH, HEIGHT);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
}
