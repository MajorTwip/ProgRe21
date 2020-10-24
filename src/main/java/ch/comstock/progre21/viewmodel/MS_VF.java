package ch.comstock.progre21.viewmodel;


import ch.comstock.progre21.model.db.DB;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class MS_VF {
	@FXML
	private ProgressBar pb_progress;
	
	@FXML
	private void initialize() {
		

		DB db = new DB();
	}
}
