package LakerLibrary;

public class systemClass {

	public static void close() {
		Runtime.getRuntime().exit(0);	
	}
	
	public static void forceExit() {
		Runtime.getRuntime().halt(0);	
	}
	
	public static void deleteGarbage() {
		Runtime.getRuntime().gc();		
	}
	
	public systemInfo info = new systemInfo();
}
