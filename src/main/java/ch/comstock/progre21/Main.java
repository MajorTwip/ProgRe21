package ch.comstock.progre21;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) {
		try {
			
			//Parent menu1 = FXMLLoader.load(getClass().getClassLoader().getResource("views/Menu.fxml"));
			//System.out.println(menu1.toString());
			ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle");
			
			Parent menu = FXMLLoader.load(getClass().getClassLoader().getResource("views/Menu.fxml"),bundle);
			
			LoaderParent root = new LoaderParent(menu,bundle);
			root.switchTo(SceneDirectory.HOME);
			Scene scene = new Scene (root,800,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle(bundle.getString("window.title"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}