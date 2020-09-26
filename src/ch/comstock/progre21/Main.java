package ch.comstock.progre21;
	
import ch.comstock.progre21.views.Home;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Home home = new Home();
			home.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(home.getScene());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
