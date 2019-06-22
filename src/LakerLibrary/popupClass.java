package LakerLibrary;

import javax.swing.JOptionPane;

public class popupClass {

	public void popup(String title , String message) { 
		
		JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
		
	}

	public String popupInput(String message , String inputValue) {
		
		return JOptionPane.showInputDialog(null, message, inputValue ,JOptionPane.PLAIN_MESSAGE);

	}

	public String popupDrowdown(String title , String message) {
		
		Object[] possibilities = {"ham", "spam", "yam"};
		
		return (String)JOptionPane.showInputDialog( null, message , title, JOptionPane.PLAIN_MESSAGE,  null, possibilities, "ham");
			
	}

	public String popupConfirm(String Opt1 , String Opt2 , String title , String message) {
		
			Object[] options = {Opt1, Opt2};
		
			int answer = JOptionPane.showOptionDialog(null,
			message,
			title,
			JOptionPane.YES_NO_OPTION,
			JOptionPane.PLAIN_MESSAGE,
			null,     //do not use a custom Icon
			options,  //the titles of buttons
			options[0]); //default button title
		
		return Integer.toString(answer);
		
	}
	
	
}
