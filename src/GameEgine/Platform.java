package GameEgine;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Platform {

	public int type;
	public int X;
	public int Y;
	public int width;
	public int height;
	public Color color;
	Rectangle platform;
	
	
	public Platform(int type ,int x, int y, int width, int height, double scaleWidth , double scaleHeight,  Color color) {
		super();
		this.type = type;
		this.X = x;
		this.Y = y;
		this.width = (int) (width * scaleWidth);
		this.height = (int) (height * scaleHeight);
		this.color = color;
		
		platform = this.createPlatform( x , y , width , height , color);
		
	}
	
	private Rectangle createPlatform(int x, int y, int height, int width, Color col){
		Rectangle wall = new Rectangle(width, height);
		wall.setTranslateX(y);
		wall.setTranslateY(x);
		wall.setFill(col);

		return wall;
	}	
	
	public Rectangle getPlatform() {
		return platform;
	}
	
	public Bounds getBounds() {
		return platform.getBoundsInParent();
	}

}
