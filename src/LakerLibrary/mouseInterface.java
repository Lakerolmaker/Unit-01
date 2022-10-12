package LakerLibrary;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;

public class mouseInterface {

	public Point getPosition() {
		
		PointerInfo a = MouseInfo.getPointerInfo();
        return a.getLocation();
        
	}
	
	
}
