package LakerLibrary;

public class TimeClass_clock {

	private long startTime;
	public void start() {
		startTime = System.nanoTime();
	}
	
	public void stop() {
		long endTime = System.nanoTime();
		long duration =  (endTime - startTime);
		long miliseconds = duration / 1000000;
		
		console.logg("Time : " + duration + " nanoseconds | " +  miliseconds  + " miliseconds");
	}
	
	public void reset() {
		startTime = System.nanoTime();
	}
	
	public long elapsedTime() {
		long endTime = System.nanoTime();
		long duration =  (endTime - startTime);
		long miliseconds = duration / 1000000;
		return miliseconds;
	}

}
