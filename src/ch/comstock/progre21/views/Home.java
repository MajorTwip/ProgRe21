package ch.comstock.progre21.views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Home extends HBox {
	Scene scene;

	public Home()
	{
		super();
		this.scene=new Scene(this,800,600);
		
		VBox menu = new VBox();
		this.getChildren().add(menu);
		
		Button btn_db = new Button();
		
		btn_db.setText("Punkte");
		menu.getChildren().add(btn_db);
	}
	
}
