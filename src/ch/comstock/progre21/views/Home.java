package ch.comstock.progre21.views;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Home extends HBox {
	Scene scene;
	ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", Locale.getDefault());

	public Home()
	{
		super();
		this.scene=new Scene(this,800,600);
		
		VBox menu = new VBox();
		this.getChildren().add(menu);
		
			Button btn_db = new Button();
			btn_db.setText(messages.getString("window.title"));
			menu.getChildren().add(btn_db);
			
	
		ImageView imgv_progre98 = new ImageView("progre98.png");

		this.getChildren().add(imgv_progre98);
	

		
	}
	
}
