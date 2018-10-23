package de.hshannover.inform.deinEigenerFirewall.util;

public class Timer {

	private long timeStart;
	
	public Timer() {
		reset();
	}
	
	public void reset() {
		timeStart = System.currentTimeMillis();
	}
	
	public long getMillisFormStart() {
		return System.currentTimeMillis() - timeStart;
	}
	
	public void addMillisElapsed(long millis) {
		this.timeStart -= millis;
	}
}
