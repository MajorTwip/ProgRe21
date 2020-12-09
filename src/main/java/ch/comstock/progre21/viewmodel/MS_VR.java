package ch.comstock.progre21.viewmodel;

import ch.comstock.progre21.LoaderParent;
import ch.comstock.progre21.SceneDirectory;
import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Dir;
import ch.comstock.progre21.model.calculations.Measurement;
import ch.comstock.progre21.model.db.DB;
import ch.comstock.progre21.model.drawings.Vectors;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MS_VR {
	@FXML
	private HBox hbox;
	
	@FXML
	private TextField txt_input_gr;
	@FXML
	private TextField txt_input_kl;
	@FXML
	private TextField txt_input_h;
	
	@FXML
	private TextField txt_input_azi;
	@FXML
	private TextField txt_input_gelwi;
	@FXML
	private TextField txt_input_dist;
	
	@FXML
	private TextField txt_result_gr;
	@FXML
	private TextField txt_result_kl;
	@FXML
	private TextField txt_result_h;
	
	
	@FXML
	private Button btn_fromdb;
	@FXML
	private Button btn_todb;
	
	@FXML
	private Label lbl_alert;
	
	String dirkeyname = "vr_dir";
	
	protected Coord a = null;
	protected Coord z = null;
	protected Dir dir = null;
	protected StringProperty result_gr = new SimpleStringProperty();
	protected StringProperty result_kl = new SimpleStringProperty();
	protected StringProperty result_h = new SimpleStringProperty();

	private Vectors graph = new Vectors(false);
	  
	ChangeListener<String> getChangeListener(TextField textField){
		return new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//replace non-digits
				if (!newValue.matches("\\d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		            
		        //check if everything is filled out
		        }else {
		        	hideAlert();
		        	
		        	boolean coordFilled = true;
		        	if(txt_input_gr.textProperty().length().lessThan(6).get()) coordFilled=false;
		        	if(txt_input_kl.textProperty().length().lessThan(6).get()) coordFilled=false;
		        	if(txt_input_h.textProperty().isEmpty().get()) coordFilled=false;
		        	
		        	
		        	if(coordFilled) {
		        		try {
			        		int gr = Integer.valueOf(txt_input_gr.textProperty().get());
			        		int kl = Integer.valueOf(txt_input_kl.textProperty().get());
			        		int h = Integer.valueOf(txt_input_h.textProperty().get());
			        		z = new Coord(gr,kl,h);
			        		System.out.println(z.stringify());
		        	  	}catch(Exception e) {
			        		showAlert(e.getLocalizedMessage());
			        		z=null;
			        	}	
		        	}else {
		        		z=null;
		        	}
		        	
		        	boolean dirFilled = true;
		        	if(txt_input_azi.textProperty().length().lessThan(4).get()) dirFilled=false;
		        	if(txt_input_gelwi.textProperty().length().lessThan(4).get()) dirFilled=false;
		        	if(txt_input_dist.textProperty().isEmpty().get()) dirFilled=false;
		        	
		        	if(dirFilled) {
			        	//create a Coord and a Dir
			        	try {
			        		int azi = Integer.valueOf(txt_input_azi.textProperty().get());
			        		int gelwi = Integer.valueOf(txt_input_gelwi.textProperty().get());
			        		int dist = Integer.valueOf(txt_input_dist.textProperty().get());
			        		dir = new Dir(azi,gelwi,dist);
			        		System.out.println(dir.stringify());
			        	}catch(Exception e) {
			        		showAlert(e.getLocalizedMessage());
			        		dir=null;
			        	}	
		        	}else {
		        		dir=null;
		        	}
			        
		        	if(z!=null&&dir!=null) {
		        		a = Measurement.vr(z, dir);
		        		result_gr.set(String.valueOf(a.getGr()));
		        		result_kl.set(String.valueOf(a.getKl()));
		        		result_h.set(String.valueOf(a.getH()));
		        		btn_todb.setVisible(true);
		        	}else {
		        		result_gr.set("");
		        		result_kl.set("");
		        		result_h.set("");
		        		btn_todb.setVisible(false);		        		
		        	}	
		        }
			}
		};
	}	
	
	private void update() {
		
	}
	
	
	
	@FXML
	private void initialize() {		
		btn_todb.setVisible(false);
		
		//get the point a from the PTDB if one was put to memory
		Coord pt = DB.getTransferCoord();
		if(pt!=null) {
			txt_input_gr.setText(String.valueOf(pt.getGr()));
			txt_input_kl.setText(String.valueOf(pt.getKl()));
			txt_input_h.setText(String.valueOf(pt.getH()));
			DB.resetTransfer();
		}
		
		//get the dir from the temp if one was put to memory
		String tmpdir = DB.getKey(dirkeyname);
		if(!tmpdir.isBlank()) {
			this.dir=new Dir(tmpdir);
			txt_input_azi.setText(String.format("%04d" , dir.getAzi()));
			txt_input_gelwi.setText(String.format("%04d" , dir.getGelwi()));
			txt_input_dist.setText(String.valueOf(dir.getDist()));
			DB.setKey(dirkeyname, "");
		}
		
		//artificially kick off changelistener
		getChangeListener(txt_input_gr).changed(result_gr, "", "");
		
		hbox.getChildren().add(graph);
		
		txt_input_gr.textProperty().addListener(getChangeListener(txt_input_gr)); 
		txt_input_kl.textProperty().addListener(getChangeListener(txt_input_kl)); 
		txt_input_h.textProperty().addListener(getChangeListener(txt_input_h));
		
		txt_input_azi.textProperty().addListener(getChangeListener(txt_input_azi)); 
		txt_input_gelwi.textProperty().addListener(getChangeListener(txt_input_gelwi)); 
		txt_input_dist.textProperty().addListener(getChangeListener(txt_input_dist));

		txt_result_gr.textProperty().bind(result_gr);
		txt_result_kl.textProperty().bind(result_kl);
		txt_result_h.textProperty().bind(result_h);
		
		btn_fromdb.setOnAction((e)->{
			if(dir!=null)DB.setKey(dirkeyname,dir.stringify());
			DB.setTransferDestination(SceneDirectory.MS_VR);
			((LoaderParent)hbox.getScene().getRoot()).switchTo(SceneDirectory.PTDB);
		});
		
		btn_todb.setOnAction((e)->{
			DB.setTransferCoord(a);
			((LoaderParent)hbox.getScene().getRoot()).switchTo(SceneDirectory.PTDB);
		});
	}
	
	private void showAlert(String msg) {
		System.out.println("Alert: " + msg);
		lbl_alert.setText(msg);
		lbl_alert.setVisible(true);
	}
	
	private void hideAlert() {
		lbl_alert.setVisible(false);
	}
}
