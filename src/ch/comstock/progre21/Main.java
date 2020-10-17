package ch.comstock.progre21;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle");
			
			//System.out.println(getClass().getResource("resources/views/Home.fxml"));
			Parent menu = FXMLLoader.load(getClass().getClassLoader().getResource("views/Menu.fxml"),bundle);
			Parent content = FXMLLoader.load(getClass().getClassLoader().getResource("views/Home.fxml"),bundle);
			
			HBox root = new HBox(menu,content);
			Scene scene = new Scene (root,800,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle(bundle.getString("window.title"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
