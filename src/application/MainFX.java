package application;

import java.io.IOException;

import LakerLibrary.$;
import LakerLibrary.console;
import consoleWindow.consoleFX;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class MainFX extends Application {

	public static Stage window;
	public static consoleFX printcon;

	@Override
	public void start(Stage primaryStage) {
		try {

			window = primaryStage;
			primaryStage.setTitle("Title");

			FXMLLoader loader = new FXMLLoader(getClass().getResource("first.fxml"));
			Parent root;

			try {
				root = loader.load();
			} catch (IOException ioe) {
				// log exception
				return;
			}

			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			console.log(e.getMessage());
		}

	}

	public static void startProgram(String[] args) {
		launch(args);
	}

}
