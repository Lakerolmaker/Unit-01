package JackeLibrary;

import consoleWindow.consoleFX;
import javafx.scene.input.KeyCode;

public interface externalConsoleInterface {
	
	public void openStandalone(Runnable code) ;
	public void open();
	public void open(double x , double y);
	
	public void move(double x , double y)  throws Exception;
	public void print(String text);
	public void printLine();
	public void error(String text);
	void addKeydownEvent(String name, KeyCode trigger, Runnable code);
	void addKeydownEvent(String name, char trigger, Runnable code);
	public void removeEnterEvent(String name);
	public void RemoveConsoleSymbol();
	public String getInput();
	public String getText();	
	public void saveText();
	public void close() ;

}
