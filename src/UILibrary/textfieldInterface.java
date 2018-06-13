package UILibrary;

import javax.swing.border.Border;

public interface textfieldInterface {

	String getValue(String ID);
	void setValue(String ID , String value);
	void setborder(String ID, Border borderkind);
	void setFontFamliy(String ID , String afontFamliy);
	void setFontType(String ID , int afontType);
	void setFontSize(String ID ,int size);
	void updateFont(String ID);
	void setAllingment(String ID , int choice);
	int getPressedKey();
	void addKeypressEvent(String ID , Runnable callback);
	
}

