package ch.comstock.progre21;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class LoaderParent extends StackPane {

	private ResourceBundle bundle;

	public LoaderParent(Parent menu, Parent content, ResourceBundle bundle) {
		super(content, menu);
		this.alignmentProperty().set(Pos.TOP_LEFT);
		this.bundle = bundle;
	}

	public void switchTo(SceneDirectory scene) {
		Parent content;
		try {
			content = FXMLLoader.load(getClass().getClassLoader().getResource(scene.getFXMLPath()), bundle);
			this.getChildren().set(0, content);
			System.out.println("switched to " + scene.getFXMLPath());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
