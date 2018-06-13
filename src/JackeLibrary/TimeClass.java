package JackeLibrary;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimeClass {

	public TimeClass_clock clock = new TimeClass_clock();
	
	public long getUnixTime() {
		return System.currentTimeMillis() / 1000L;	
	}
	
	public String getDate() {
		return new SimpleDateFormat("yyyy/MM/dd - HH:mm:ss").format(Calendar.getInstance().getTime());
	}
	
	private static ArrayList<timerClass> intervalList =  new ArrayList<timerClass>();
	
	//: Calls a function at a specific rate
	public static void setInterval(String name, int delay, int interval , Runnable calledFunction ) {
		
		timerClass timer = new timerClass( name,  delay,  interval , calledFunction);
		intervalList.add(timer);
		timer.run();
				
	}
	
	public static void removeInterval(String name) {
		for (int i = 0 ; i < intervalList.size(); i++) {
			if(name.equals(intervalList.get(i).name)) {
				intervalList.get(i).stop();
				intervalList.remove(i);
			}
		}
	}
		
	//: Calls a function after a specific time
	public static void timer(int interval , Runnable calledFunction ) {
		
		TimerTask specifiedTask = new TimerTask() {
		   	@Override
			public void run() {
				calledFunction.run();
			}
	   };
	
	   Timer timer = new Timer();
	      
	   //: Adds the task to the schedule
	   timer.schedule(specifiedTask, interval);
		
	}
	
	
}
