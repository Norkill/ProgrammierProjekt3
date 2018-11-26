package de.hshannover.inform.deinEigenerFirewall.util;

/**
 * Class timer to track wave timings
 * 
 * @author bk3-4nz-u1
 *
 */
public class Timer {

	private long timeStart;

	/**
	 * creates a new Timer object
	 */
	public Timer() {
		reset();
	}

	/**
	 * resets the timer with actuall time
	 */
	public void reset() {
		timeStart = System.currentTimeMillis();
	}

	/**
	 * 
	 * @return time in millis from last reset of timer
	 */
	public long getMillisFormStart() {
		return System.currentTimeMillis() - timeStart;
	}

	/**
	 * adds time to this timer so it has more time elapsed
	 * 
	 * @param millis time to be added in millis
	 */
	public void addMillisElapsed(long millis) {
		this.timeStart -= millis;
	}
}
