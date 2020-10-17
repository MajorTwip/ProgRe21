package ch.comstock.progre21;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MenuController {
	@FXML
	private VBox mainmenu;
	@FXML
	private Button hamburger;
	@FXML
	private MenuButton btn_menu_main_ptdb;
	@FXML
	private MenuItem btn_menu_main_ptdb_new;
	@FXML
	private MenuItem btn_menu_main_ptdb_list;
	@FXML
	private MenuButton btn_menu_main_measurements;
	@FXML
	private MenuItem btn_menu_main_measurements_vf;
	@FXML
	private MenuItem btn_menu_main_measurements_vr;
	@FXML
	private MenuItem btn_menu_main_measurements_arc;
	@FXML
	private MenuItem btn_menu_main_measurements_arcr;
	@FXML
	private MenuButton btn_menu_main_artyobs;
	@FXML
	private MenuButton btn_menu_main_calculator;
	@FXML
	private MenuButton btn_menu_main_settings;

	@FXML
	public void initialize() {
		
		hamburger.setOnAction((e)->{
			if(mainmenu.getStyleClass().contains("mainmenu")) {
				mainmenu.getStyleClass().remove("mainmenu");
				mainmenu.getStyleClass().add("mainmenu_hidden");
			}else {
				mainmenu.getStyleClass().remove("mainmenu_hidden");
				mainmenu.getStyleClass().add("mainmenu");
			}	
			btn_menu_main_artyobs.hide();
		});
		btn_menu_main_ptdb_new.setOnAction((e)->{((LoaderParent)mainmenu.getScene().getRoot()).switchTo(SceneDirectory.PTDB_NEW);});
	}

}
