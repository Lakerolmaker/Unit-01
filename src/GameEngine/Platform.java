package GameEngine;

import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
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
	ImageView image;
	private static GameEngine gameObject;
	
	
	public Platform(int type ,int x, int y, int width, int height, Color color) {
		super();
		this.type = type;
		this.X = x;
		this.Y = y;
		this.width = (int) (width * gameObject.scale.scaleWidth);
		this.height = (int) (height * gameObject.scale.scaleHeight);
		this.color = color;
		
		
		platform = this.createPlatform( x , y , width , height , color);
		
	
	}
	
	public Platform(GameEngine gameObject) {
		this.gameObject = gameObject;
	}
	

	public void createPicture() {
		
		switch (this.type) {
		case 1:
			
			break;

		default:
			break;
		}
		
		
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
