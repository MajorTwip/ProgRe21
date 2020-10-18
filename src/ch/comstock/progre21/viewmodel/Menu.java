package ch.comstock.progre21.viewmodel;

import ch.comstock.progre21.LoaderParent;
import ch.comstock.progre21.SceneDirectory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class Menu {
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
	private Button btn_menu_main_artyobs;
	@FXML
	private Button btn_menu_main_calculator;
	@FXML
	private Button btn_menu_main_settings;

	@FXML
	public void initialize() {
		
		hamburger.setOnAction((e)->{
			if(mainmenu.getStyleClass().contains("mainmenu")) {
				hideMenu();
			}else {
				showMenu();
			}	
		});
		
		
		btn_menu_main_ptdb_new.setOnAction((e)->{callSwitchTo(SceneDirectory.PTDB_NEW);});
		btn_menu_main_ptdb_list.setOnAction((e)->{callSwitchTo(SceneDirectory.PTDB_LIST);});
		btn_menu_main_measurements_vf.setOnAction((e)->{callSwitchTo(SceneDirectory.MS_VF);});
		btn_menu_main_measurements_vr.setOnAction((e)->{callSwitchTo(SceneDirectory.MS_VR);});
		btn_menu_main_measurements_arc.setOnAction((e)->{callSwitchTo(SceneDirectory.MS_ARC);});
		btn_menu_main_measurements_arcr.setOnAction((e)->{callSwitchTo(SceneDirectory.MS_ARCR);});
		btn_menu_main_artyobs.setOnAction((e)->{callSwitchTo(SceneDirectory.ARTYOBS);});
		btn_menu_main_calculator.setOnAction((e)->{callSwitchTo(SceneDirectory.CALC_UPN);});
		btn_menu_main_settings.setOnAction((e)->{callSwitchTo(SceneDirectory.SETTINGS);});
		
		
	}
	
	private void callSwitchTo(SceneDirectory scene) {
		((LoaderParent)mainmenu.getScene().getRoot()).switchTo(scene);
		hideMenu();
	}
	
	public void showMenu() {
		mainmenu.getStyleClass().remove("mainmenu_hidden");
		mainmenu.getStyleClass().add("mainmenu");
	}
	
	public void hideMenu() {
		mainmenu.getStyleClass().remove("mainmenu");
		mainmenu.getStyleClass().add("mainmenu_hidden");
	}

}
