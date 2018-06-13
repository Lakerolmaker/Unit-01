package GameEgine;


import com.sun.media.jfxmediaimpl.platform.Platform;

import JackeLibrary.console;
import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Player {

	//: Physics
	private final int movementSpeed = 3;
	private final int gravityForce = 2;
	private final double jumpTime = 0.05;
	private final double jumpStrenth = 9;
	
	//: Data for the player
	public double deltaX = 0;
	public double deltaY = 0;
	double X;
	double Y;
	double startX;
	double startY;
	double height;
	double width;
	
	//: Jumping stats
	int jumpCounter = 0;
	boolean jumping = false;
	
	//: Object
	CollisionData collision = new CollisionData();
	private Color color;
	private Rectangle p;
	GameEngine gameobject;
	
	public Player(int x, int y, int width, int height, Color color , GameEngine gameobject)
	{
		this.X = x;
		this.Y = y;
		this.startX = X;
		this.startY = y;
		this.width = (int) (width * gameobject.scale.scaleWidth);
		this.height = (int) (height * gameobject.scale.scaleHeight);
		this.color = color;
		this.gameobject = gameobject;

		p = 	new Rectangle(width, height, color);
		this.resetPosition();
		
	}
	
	//: Main function that updates the player
	public void update() {
		
		getCollisionData();
		goingToLand();
		
		collisionDetection();
		
		gameLogic();
		
		applyPhysics();
				
		//: Updates the position of the player
		updatePosition();
		
	}

	//: Logic of game mechanics
	private void gameLogic() {
		
		if(jumping) {
			jumpUpdate();
		}
			
	}
	
	//: set's the players position
	public void setPosition(double x, double y)
	{
		X = x;
		Y = y;

		p.setX(X);
		p.setY(Y);
	}
	
	//: Resets the charcters postion
	private void resetPosition() {
		this.setPosition(this.startX, this.startY);
		this.deltaY = 3;
	}
	
	private void updatePosition() {	
		p.setX(X);
		p.setY(Y);
	}
	
	private void applyPhysics() {
		
		applyForces();
		
		applyGravity();
		
	}
	
	private void applyForces() {
		
		//:  Movement left and right
		if(( (!collision.right) && (deltaX > 0)) || ((!collision.left) && (deltaX < 0)) ){
			this.X += this.deltaX;
		}
		
		//: Movement upwards
		this.Y += this.deltaY;
		
	}
	
	public void collisionDetection() {

		if(collision.up){
			this.deltaY = 0;
		}
		
		if(((collision.down))  && (jumping == false)){
			this.deltaY = 0;
		}
		
	}
	
	public void applyGravity()
	{
		
		if(collision.down == false) {
			Y += gravityForce;	
		}
		
		//: Decreases the jumping force
		if(jumping) {
			deltaY += jumpTime;	
		}
		
	}

	private double getGravityForce() {
		return  deltaY + gravityForce  ;
	}
	
	private void goingToLand() {
		
		for(GameEgine.Platform platformObj: gameobject.platforms){
			
			Rectangle platform =  platformObj.getPlatform();
			
			if(platform.getBoundsInParent().contains( this.X , this.Y + this.getGravityForce())) {
				this.collision.down = true;
			}
			
		}
		
	}
	
	public void jump()
	{
		if(jumpCounter < 2) {
			deltaY = (gravityForce * 2) * -1;
			jumping = true;
			jumpCounter++;
		}
		
	}

	private void jumpUpdate() {
	
		if((this.getGravityForce() > 0)) {
		
			if(collision.down == true) {
				jumping = false;
				jumpCounter = 0;
				deltaY = 0;
			}
		
		}
		
	}

	public void shoot() {
		
	}
	
	private void getCollisionData() {
	    
		CollisionData updatedCollision = new CollisionData();
		
		for(GameEgine.Platform platformObj: gameobject.platforms){
			
			Rectangle platform =  platformObj.getPlatform();
			
			double minX = this.getBounds().getMinX();
			double maxX = this.getBounds().getMaxX();
			double minY = this.getBounds().getMinY();
			double maxY = this.getBounds().getMaxY();
				
			
			//: middle hitbox
			double middleTopX = maxX - (width / 2);
			double middleTopY = minY;
			
			double middleDownX = maxX - (width / 2);
			double middleDownY = maxY;
			
			double middleLeftX = minX;
			double middleLeftY = maxY - (height / 2);
			
			double middleRightX = maxX;
			double middleRightY = maxY - (height / 2);
			
			int offset = 1;
			
			//: corner hitbox
			double TopLeftX = minX;
			double TopLeftY = minY;
			
			double TopRightX = maxX - offset;
			double TopRightY = minY + offset;
			
			double BottomLeftX = minX;
			double BottomLeftY = maxY;
			
			double BottomRightX = maxX - offset;
			double BottomRightY = maxY - offset;
	
			if(platform.getBoundsInParent().intersects(this.getBounds())){
				
				int type = platformObj.type;
				
				if(type == 1) {

					
					if((platform.getBoundsInParent().contains(TopLeftX, TopLeftY)) && (platform.getBoundsInParent().contains(TopRightX, TopRightY))) {
						updatedCollision.up = true;
					}
					
					if(platform.getBoundsInParent().contains(middleDownX, middleDownY)){
						updatedCollision.down = true;
					}
					
					if(platform.getBoundsInParent().contains(middleRightX, middleRightY)){
						updatedCollision.right = true;
					}
					
					if(platform.getBoundsInParent().contains(middleLeftX, middleLeftY)){
						updatedCollision.left = true;
					}
					
					/*	
					if((platform.getBoundsInParent().contains(TopLeftX, TopLeftY)) && (platform.getBoundsInParent().contains(TopRightX, TopRightY))) {
						updatedCollision.up = true;
					}
					
					if(platform.getBoundsInParent().contains(middleDownX, middleDownY)){
						updatedCollision.down = true;
					}
					
					boolean topright  = platform.getBoundsInParent().contains(TopRightX, TopRightY);
					boolean bottomright = platform.getBoundsInLocal().contains( BottomRightX , BottomRightX);
					
					
					
					if((platform.getBoundsInParent().contains(TopRightX, TopRightY)) && (platform.getBoundsInParent().contains( BottomRightX , BottomRightX))){
						updatedCollision.right = true;
					}
					
					if(platform.getBoundsInParent().contains(middleLeftX, middleLeftY)){
						updatedCollision.left = true;
					}
				*/
				}else if(type == 6) {
					
					playerDeath();
					
				}else if(type == 7) {
					gameobject.levelData.levelDown();
					this.setPosition( X , this.startY);
					gameobject.GameLayout();
				}else if(type == 8) {
					gameobject.levelData.levelLeft();
					this.setPosition( gameobject.scale.screenWidth - this.width, Y);
					gameobject.GameLayout();
				}else if(type == 9) {
					gameobject.levelData.levelRight();
					this.setPosition( this.startX , Y);
					gameobject.GameLayout();
				}
	
			}
		}
		
		collision = updatedCollision;
	}

	private void playerDeath() {
			resetPosition();
			gameobject.resetGame();
	}
	
	private void nextLevel() {
		this.setPosition( 0 , Y );
	}
	
	private void previousLevel() {
		this.setPosition( gameobject.scale.screenWidth , Y );
	}

	public double getX()
	{
		return X;
	}

	public double getY()
	{
		return Y;
	}

	public Rectangle getPlayer()
	{
		return p;
	}

	public Bounds getBounds()
	{
		return p.getBoundsInParent();
	}

	public void moveRight(){
		deltaX = movementSpeed;
	}

	public void moveLeft() 
	{
		deltaX = movementSpeed * -1;
	}
	
	public void stopHorizontalMovement() {
		deltaX = 0;
	}

	public void moveUp()
	{
		deltaY = gravityForce * -1;	
	}

	
}



