package GameEngine;

import LakerLibrary.$;
import LakerLibrary.console;

public class ScreenScale {
	
	private int Width_orginal = 1440;
	private int height_original = 900;
	
	public int screenWidth = $.computer.getScreenWidth();
	public int screenHeight = $.computer.getScreenHeight();
	
	public double scaleWidth =  (double)screenWidth / (double)Width_orginal;
	public double scaleHeight = (double)screenHeight / (double)height_original;

	public ScreenScale() {

		this.scaleWidth =  (double)screenWidth / (double)Width_orginal ;
		this.scaleHeight = (double)screenHeight / (double)height_original;
		
		console.logg("Screen width :" + screenWidth);
		console.logg("Screen height :" + screenHeight);
		console.logg("Scale  Width " + scaleWidth);
		console.logg("Scale  Height" + scaleHeight);
	}
	
	
	
}
