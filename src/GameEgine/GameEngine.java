package GameEgine;
import java.util.ArrayList;

import JackeLibrary.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameEngine extends Application {
		
	int speed = 9;
	int fps;
	Text fpsText;
	Player player;
	Pane GameRoot = new Pane();
	Pane ApplicationRoot = new Pane();
	LevelData levelData = new LevelData();
	ScreenScale scale = new ScreenScale();
	
	public ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	public static void startProgram(String[] args)  {
		launch(args);
	}
	
	public void start(Stage window) 
	{
				
		GameLayout();
		
		$.time.clock.start();
		
		player = new Player( 20 , 20 , 20 , 20 , Color.WHITE , this);

		GameRoot.getChildren().add(player.getPlayer());

		Scene scene = new Scene(ApplicationRoot , scale.screenWidth , scale.screenHeight );

		window.setTitle("Game");
		window.setScene(scene);
		window.show();

		Timeline tl = new Timeline(new KeyFrame(Duration.millis(speed), e->{

			player.update();
			updateFPS();
			
		}));
		
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.play();

		scene.setOnKeyPressed(e ->{
			KeyCode code = e.getCode();
			keyDown(code);
		});

		scene.setOnKeyReleased(e ->{
			KeyCode code = e.getCode();
			keyUP(code);
		});

	}
	
	//: clear all platforms
	public void clearStage() {
		ApplicationRoot.getChildren().clear();
		platforms.clear();
	}
	
	public void generateBackground() {
		
		Rectangle background = new Rectangle( scale.screenWidth , scale.screenHeight, Color.CORNFLOWERBLUE);

		ApplicationRoot.getChildren().addAll(background, GameRoot);

	}
	
	public void resetGame() {
		levelData.X = 0;
		levelData.Y = 0;
		GameLayout();
	}
	
	public void GameLayout(){
		
		clearStage();
		
		generateBackground();
		
		generatePlatform();
		
		createFPScounter();
		
	}
	
	//: Set's the layout of the level
	public void generatePlatform(){
		
		int levelX = levelData.X;
		int levelY = levelData.Y;
		Integer[][] level = levelData.worlMap[levelY][levelX].map;
	
		
		for(int x = 0; x < level.length; x++) {
			for(int y = 0; y < level[x].length; y++) {
				
				int value = level[x][y];
				
				switch(value)
				{
				case 0:
					break;
				case 1:
					addPlatform(new Platform( 1 , (x * 60) - 60 , (y * 60) - 60, 60 , 60 , scale.scaleWidth , scale.scaleHeight , Color.BLACK));
					break;
				case 6:
					addPlatform(new Platform( 6 , (x * 60) - 60 , (y * 60) - 60, 60 , 60 , scale.scaleWidth , scale.scaleHeight , Color.RED));
					break;
				case 7:
					addPlatform(new Platform( 7 , (x * 60) - 60 , (y * 60) - 60, 60 , 60 , scale.scaleWidth , scale.scaleHeight , Color.TRANSPARENT));
					break;
				case 8:
					addPlatform(new Platform( 8 , (x * 60) - 60 , (y * 60) - 60, 60 , 60 , scale.scaleWidth , scale.scaleHeight , Color.TRANSPARENT));
					break;
				case 9:
					addPlatform(new Platform( 9 , (x * 60) - 60 , (y * 60) - 60, 60 , 60 , scale.scaleWidth , scale.scaleHeight , Color.TRANSPARENT));
					break;
				}
				
				
			}
		}	
			
	}
	
	private void addPlatform(Platform platform) {
		platforms.add(platform);	
		ApplicationRoot.getChildren().add(platform.getPlatform());
	}
		
	public void createFPScounter() {
		
		fpsText = new Text();
				
		fpsText.setX(10);
		fpsText.setY(20);

			
		ApplicationRoot.getChildren().add(fpsText);
	}
	
	private void updateFPS() {
		
		long lastFrame_Time = $.time.clock.elapsedTime();
		
		if(lastFrame_Time < 1000) {
			fps++;
		}else {
			displayFPS();
			fps = 0;
			$.time.clock.reset();
		}
		
	}
	
	private void displayFPS() {
		String text = "Fps : " + String.valueOf(fps);
		fpsText.setText(text);
	}
	
	private void keyDown(KeyCode code) {
		
		if((code == KeyCode.D) || (code == KeyCode.RIGHT)){
			player.moveRight();
		}
		
		if((code == KeyCode.A) || (code == KeyCode.LEFT)){
			player.moveLeft();
		}

		if((code == KeyCode.SPACE) || (code == KeyCode.UP) || (code == KeyCode.Z)){
			player.jump();
		}
		
		if((code == KeyCode.X) || (code == KeyCode.M)){
			player.shoot();
		}
				
	}
	
	private void keyUP(KeyCode code) {
		
		if((code == KeyCode.D) || (code == KeyCode.RIGHT)){
			player.stopHorizontalMovement();
		}
		
		if((code == KeyCode.A) || (code == KeyCode.LEFT)){
			player.stopHorizontalMovement();
		}

		if((code == KeyCode.SPACE) || (code == KeyCode.UP) || (code == KeyCode.Z)){
			
		}
		
		if((code == KeyCode.X) || (code == KeyCode.M)){
			
		}
				
	}

	
}