package Da_vinci_sleep;
	
import java.io.IOException;

import JackeLibrary.$;
import JackeLibrary.console;
import consoleWindow.consoleFX;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;


public class sleepCalulator extends Application {
	
	public static Stage window;
	
	public TextField normalhours;
	public TextField daysUsingDavinci;
	public TextField davinciHoursAwake;
	public TextField davinciMinutesAleep;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			window = primaryStage;
			window.setTitle("Sleep Calculator 1.0");
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("sleep.fxml"));

			Scene scene = new Scene(loader.load());
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			window.setScene(scene);
			window.show();
			
					
		} catch(Exception e) {
			console.log(e.getMessage());
		}	
		
		window.xProperty().addListener((obs, oldVal, newVal) ->{
			x = newVal.doubleValue() + 230;
			move();
		});
		window.yProperty().addListener((obs, oldVal, newVal) -> {
			y = newVal.doubleValue();
			move();
		});

		window.setOnCloseRequest(e ->{
			e.consume();
			console.external.close();
			window.close();
		});		
	}
	
	public static void start() {
		launch(APP.Main.ARGS);
	}
	
	public void calculate() {
				
		int timeAwake = 0;
		int timesAsleap = 0;
		int days = 0;
		int normalAmoutPerDay = 0;
		boolean error = true;
		
		try {
			 timeAwake = Integer.valueOf(davinciHoursAwake.getText());
			 timesAsleap = Integer.valueOf(davinciMinutesAleep.getText());
			 days = Integer.valueOf(daysUsingDavinci.getText());
			 normalAmoutPerDay = Integer.valueOf(normalhours.getText());
			 error = false;
		} catch (Exception e) {}
		
		if(error == false) {
			
		int stop = days * 24 * 60;
		for(int i = 0; i < stop ; i++) {
			
			if(i == timeAwake * 60) {
				timesAsleap ++;
				stop -= timeAwake * 60;
				i = 0;
			}
				
		}
		
		int minutesAsleep =  timesAsleap * 20;
		int hoursAsleep =  minutesAsleep / 60;
		
		double x = window.getX() + 230;
		double y = window.getY();
		
		console.external.open(x, y);
		console.external.print("Stats for using davince sleep for " + days + " days, sleeping " + normalAmoutPerDay  +" hours per night : ");
		console.external.print("Number of naps : " + timesAsleap);
		console.external.print("Totall time aleep : " + minutesAsleep + " min" + " | " + hoursAsleep+ " hours");
	
		console.external.print("Time saved : " + (hoursAsleep - normalAmoutPerDay) * 60 + " mintes" +
		" | " + (hoursAsleep - normalAmoutPerDay) + " hours" +
		" | " + (hoursAsleep - normalAmoutPerDay) / 24 + " days");
		console.external.print("--------------------------------------------------------");
		
		}else {	
			Alert alert = new Alert(AlertType.ERROR, "All fields most be filled in", ButtonType.OK);
			alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
			alert.show();
		}
		
		
		
		
	}

	double x;
	double y;
	
	public void move() {
		try {
			console.external.move(x, y);
		} catch (Exception e) {}
	}
	
}
