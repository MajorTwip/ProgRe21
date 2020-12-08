package ch.comstock.progre21;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class LoaderParent extends HBox {

	private ResourceBundle bundle;

	public LoaderParent(@SuppressWarnings("exports") Parent menu, ResourceBundle bundle) {
		super(menu, new Pane());
		this.bundle = bundle;
		this.setId("mainparent");
	}

	public void switchTo(SceneDirectory scene) {
		Parent content;
		try {
			content = FXMLLoader.load(getClass().getClassLoader().getResource(scene.getFXMLPath()), bundle);
			content.getStyleClass().add("content");
			this.getChildren().set(1, content);
			System.out.println("switched to " + scene.getFXMLPath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void setLocale(Locale loc) {
		bundle = ResourceBundle.getBundle("MessagesBundle",loc);
		Node newmenu = this.getChildren().get(0);
		try {
			newmenu = FXMLLoader.load(getClass().getClassLoader().getResource("views/Menu.fxml"),bundle);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.getChildren().set(0, newmenu);
	}

}
