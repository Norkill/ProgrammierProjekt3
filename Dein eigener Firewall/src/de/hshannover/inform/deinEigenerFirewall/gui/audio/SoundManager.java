package de.hshannover.inform.deinEigenerFirewall.gui.audio;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Sound Manager, saves all avaliable sounds and can play them
 * 
 * @author Norbert
 *
 */
public class SoundManager {

	public static final Sound ALARM = new Sound("alarm.wav");
	public static final Sound ERROR = new Sound("error.wav");
	public static final Sound LOST_EXP = new Sound("lost_exp.wav");
	public static final Sound LOST_VIRUS = new Sound("lost_virus.wav");
	public static final Sound MOUSE_ON_BUTTON = new Sound("mouse_on_button.wav");
	public static final Sound ON_CLICK = new Sound("on_click.wav");
	public static final Sound POINT = new Sound("point.wav");
	public static final Sound SOMETHING = new Sound("something.wav");
	public static final Sound WON = new Sound("won.wav");
	// public static final Sound = new Sound("");

	/**
	 * Plays a sound used as SoundManager.playSound(SoundManager.A_SOUND);
	 * 
	 * @param s Sound to be played
	 */
	public static void playSound(Sound s) {
		try {
			File f = new File("./res/sounds/" + s.getName());
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(f);
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
