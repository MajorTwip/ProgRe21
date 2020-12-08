package ch.comstock.progre21.viewmodel;

import java.util.Locale;

import ch.comstock.progre21.LoaderParent;
import ch.comstock.progre21.SceneDirectory;
import ch.comstock.progre21.model.db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class Settings {
	@FXML
	private GridPane parent;

	@FXML
	private Button btn_cleardb;
	
	@FXML
	private ToggleGroup lang_group;
	
	@FXML
	private RadioButton rb_ger;
	
	@FXML
	private RadioButton rb_fra;
	
	
	
	@FXML
	private void initialize() {		
		
		
		if(Locale.getDefault()==Locale.FRENCH) {
			rb_fra.setSelected(true);
		}else {
			rb_ger.setSelected(true);
		}
		
		btn_cleardb.setOnAction((e)->{DB.clear();});
		
		
		lang_group.selectedToggleProperty().addListener((o,oldrb,newrb)->{
			if(newrb==rb_fra) {
				System.out.println("SetFra");
				Locale.setDefault(Locale.FRENCH);
			}else {
				Locale.setDefault(Locale.GERMAN);
			}
			((LoaderParent)parent.getScene().getRoot()).setLocale(Locale.getDefault());
			((LoaderParent)parent.getScene().getRoot()).switchTo(SceneDirectory.SETTINGS);			
		});
	}
	
}
