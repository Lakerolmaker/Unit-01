package LakerLibrary;

import java.util.Timer;
import java.util.TimerTask;

public class timerClass {

	public String name;
	private Timer timer;
	private int delay;
	private int interval;
	private Runnable calledFunction;
	private TimerTask specifiedTask;
	
	public timerClass(String name, int delay, int interval, Runnable calledFunction) {
		super();
		this.name = name;
		this.delay = delay;
		this.interval = interval;
		this.calledFunction = calledFunction;
		this.timer = new Timer();
		
		 specifiedTask = new TimerTask() {
			   	@Override
				public void run() {
					calledFunction.run();
				}
		   };
	}
	
	public void run() {
		 timer.scheduleAtFixedRate(specifiedTask, delay, interval);   
	}
	
	public void stop() {
		timer.cancel();
	}
	
}
