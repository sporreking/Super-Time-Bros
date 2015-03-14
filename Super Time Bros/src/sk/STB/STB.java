package sk.STB;

import java.util.HashMap;

public final class STB {
	
	public static final String NAME = "Super Time Bros";
	
	private static final HashMap<String, Timer> timers = new HashMap<>();
	
	public static final void start(String name, double duration) {
		timers.put(name, new Timer(duration));
	}
	
	public static final void updateTimer(String name) {
		timers.get(name);
	}
	
	private static class Timer {
		
		private double duration;
		
		private double counter;
		
		public Timer(double duration) {
			this.duration = duration;
			counter = 0;
		}
		
		void update(double tick) {
			duration += tick;
		}
		
		boolean done() {
			return counter > duration;
		}
	}
}