package de.hshannover.inform.deinEigenerFirewall.gui.menu;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.hshannover.inform.deinEigenerFirewall.gui.GUIController;
import de.hshannover.inform.deinEigenerFirewall.util.Utils;

/**
 * Klasse zum darstellen vom Hilfe menu
 * @author Norbert
 *
 */
@SuppressWarnings("serial")
public class HelpMenu extends JPanel {

	private GUIController guic;
	private JButton backButton;
	private JLabel imageLabel;

	private BufferedImage helpImage;

	public HelpMenu(GUIController guic) {
		this.guic = guic;

		initHelp();
	}

	private void initHelp() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		backButton = new JButton("Zuruck");
		backButton.addActionListener(e -> guic.setMenuState());

		helpImage = Utils.loadImage("images/helpImage.png");
		imageLabel = new JLabel(new ImageIcon(helpImage));

		add(backButton);
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(imageLabel);

		for (Component c : getComponents()) {
			c.setFont(guic.getFont());
		}

		repaint();
	}

}
