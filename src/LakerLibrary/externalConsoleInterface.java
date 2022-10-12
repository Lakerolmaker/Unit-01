package LakerLibrary;

import console_external.consoleFX;
import console_external.consoleInterface;
import console_external.runnableConsole;

public interface externalConsoleInterface extends consoleInterface{

	public void openStandalone(runnableConsole caller);
	public void open();
	public void open(double x , double y);
	
}
