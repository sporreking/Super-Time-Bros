package sk.STB;

import java.util.HashMap;

public final class STB {
	
	public static final String NAME = "Super Time Bros";
	
	private static final HashMap<String, Timer> timers = new HashMap<>();
	
	public static final void start(String name, double duration) {
		Timer t = getTimer(name);
		if(t == null) {
			timers.put(name, new Timer(duration));
		} else {
			t.duration = duration;
		}
	}
	
	public static final boolean done(String name) {
		Timer t = getTimer(name);
		if(t == null)
			throw new IllegalStateException("No timer with name: \"" + name + "\"");
		return t.done();
	}
	
	public static final void reset(String... names) {
		for(String name : names) {
			Timer t = getTimer(name);
			if(t == null)
				throw new IllegalStateException("No timer with name: \"" + name + "\"");
			t.reset();
		}
	}
	
	public static final void delete(String... names) {
		for(String name : names) {
			Timer t = getTimer(name);
			if(t == null)
				System.out.println("No timer with name: \"" + name + "\"");
			timers.remove(name);
		}
	}
	
	public static final void clear() {
		timers.clear();
	}
	
	public static final double getCount(String name) {
		Timer t = getTimer(name);
		if(t == null)
			throw new IllegalStateException("No timer with name: \"" + name + "\"");
		return t.counter;
	}
	
	public static final double getDuration(String name) {
		Timer t = getTimer(name);
		if(t == null)
			throw new IllegalStateException("No timer with name: \"" + name + "\"");
		return t.duration;
	}
	
	public static final void update(double tick, String... names) {
		for(String name : names) {
			Timer t = getTimer(name);
			if(t == null)
				throw new IllegalStateException("No timer with name: \"" + name + "\"");
			t.update(tick);
		}
	}
	
	public static final String[] getTimers() {
		
		String[] data = new String[]{};
		
		data = timers.keySet().toArray(data);
		
		return data;
	}
	
	private static final Timer getTimer(String name) {
		return timers.get(name);
	}
	
	private static class Timer {
		
		public double duration;
		
		public double counter;
		
		public Timer(double duration) {
			this.duration = duration;
			reset();
		}
		
		void update(double tick) {
			if(!done()) {
				counter += tick;
				if(done())
					counter = duration;
			}
		}
		
		boolean done() {
			return counter >= duration;
		}
		
		void reset() {
			counter = 0;
		}
	}
}