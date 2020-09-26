package ch.comstock.progre21;

import ch.comstock.progre21.views.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//System.out.println(getClass().getResource("resources/views/Home.fxml"));
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("views/Home.fxml"));
			Scene scene = new Scene (root,400,600);
			primaryStage.setScene(scene);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Home home = new Home();
			home.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(home.getScene());
		}
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
