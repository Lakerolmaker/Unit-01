package LakerLibrary;

import consoleWindow.consoleFX;
import consoleWindow.consoleInterface;
import consoleWindow.runnableConsole;

public interface externalConsoleInterface extends consoleInterface{

	public void openStandalone(runnableConsole caller);
	public void open();
	public void open(double x , double y);
	
}
