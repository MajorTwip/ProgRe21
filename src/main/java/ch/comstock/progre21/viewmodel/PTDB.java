package ch.comstock.progre21.viewmodel;

import ch.comstock.progre21.LoaderParent;
import ch.comstock.progre21.SceneDirectory;
import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Point;
import ch.comstock.progre21.model.db.DB;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class PTDB {

	@FXML
	private GridPane parent;
	
	@FXML
	private TextField txt_input_nr;
	@FXML
	private TextField txt_input_gr;
	@FXML
	private TextField txt_input_kl;
	@FXML
	private TextField txt_input_h;
	@FXML
	private Button btn_save;
	@FXML
	private Button btn_del;
	@FXML
	private Button btn_set;

	@FXML
	private ListView<Point> lv_points;

	private ObservableList<Point> pts = FXCollections.observableArrayList();

	@FXML
	private void initialize() {
		
		Coord pt = DB.getTransferCoord();
		if(pt!=null) {
			txt_input_gr.setText(String.valueOf(pt.getGr()));
			txt_input_kl.setText(String.valueOf(pt.getKl()));
			txt_input_h.setText(String.valueOf(pt.getH()));
			DB.resetTransfer();
		}
		
		SceneDirectory scene = DB.getTransferDestination();
		if(scene!=null) {
			btn_set.setVisible(true);
			btn_set.setOnAction(e->{
	        	if(!txt_input_nr.textProperty().isEmpty().get()) {
					DB.setTransferCoord(new Coord(
							Integer.valueOf(txt_input_gr.getText()), 
							Integer.valueOf(txt_input_kl.getText()),
							Integer.valueOf(txt_input_h.getText()))); 
					((LoaderParent)parent.getScene().getRoot()).switchTo(scene);
	        	}
			});
		}else {
			btn_set.setVisible(false);
		}
		
		pts = DB.getPts();
		
		txt_input_nr.textProperty().addListener(getChangeListener(txt_input_nr)); 
		txt_input_gr.textProperty().addListener(getChangeListener(txt_input_gr)); 
		txt_input_kl.textProperty().addListener(getChangeListener(txt_input_kl)); 
		txt_input_h.textProperty().addListener(getChangeListener(txt_input_h));

		lv_points.setItems(pts);
		lv_points.setCellFactory(pointListView -> new PointListViewCell());
		lv_points.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		lv_points.getSelectionModel().selectedItemProperty()
				.addListener((ChangeListener<Point>) (val, deselected, selected) -> {
					if(selected != null) {
						txt_input_nr.setText(String.valueOf(selected.getNr()));
						txt_input_gr.setText(String.valueOf(selected.getGr()));
						txt_input_kl.setText(String.valueOf(selected.getKl()));
						txt_input_h.setText(String.valueOf(selected.getH()));
					}
				});

		btn_save.setOnAction((e) -> {
        	boolean allFilled = true;
        	if(txt_input_nr.textProperty().isEmpty().get()) allFilled=false;
        	if(txt_input_gr.textProperty().length().lessThan(6).get()) allFilled=false;
        	if(txt_input_kl.textProperty().length().lessThan(6).get()) allFilled=false;
        	if(txt_input_h.textProperty().isEmpty().get()) allFilled=false;

        	if(!allFilled) {
        		return;
        	}
			DB.savePt(new Point(Integer.valueOf(txt_input_nr.getText()),
					new Coord(Integer.valueOf(txt_input_gr.getText()), Integer.valueOf(txt_input_kl.getText()),
							Integer.valueOf(txt_input_h.getText()))));
		pts.clear();pts.addAll(DB.getPts());
		});
		
		btn_del.setOnAction((e) -> {
        	if(!txt_input_nr.textProperty().isEmpty().get()) {
        		DB.delPt(Integer.valueOf(txt_input_nr.getText()));
        		pts.clear();pts.addAll(DB.getPts());
        	}
		});
	}

	//Check for non-digits and remove them
	ChangeListener<String> getChangeListener(TextField textField){
		return new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//replace non-digits
				if (!newValue.matches("\\d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		        }
			}
		};
	}
}
