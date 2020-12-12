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

public class MS_ARC {
	@FXML
	private HBox hbox;
	
	@FXML
	private TextField txt_input_lgr;
	@FXML
	private TextField txt_input_lkl;
	@FXML
	private TextField txt_input_lh;
	@FXML
	private TextField txt_input_ld;
	
	@FXML
	private TextField txt_input_rgr;
	@FXML
	private TextField txt_input_rkl;
	@FXML
	private TextField txt_input_rh;
	@FXML
	private TextField txt_input_rd;
	
	@FXML
	private TextField txt_result_gr;
	@FXML
	private TextField txt_result_kl;
	@FXML
	private TextField txt_result_h;
	
	
	@FXML
	private Button btn_lfromdb;
	@FXML
	private Button btn_rfromdb;
	@FXML
	private Button btn_todb;
	
	@FXML
	private Label lbl_alert;
	
	String dirkeynameptl = "arc_ptl";
	String dirkeynameptr = "arc_ptr";
	String dirkeynamedl = "arc_dl";
	String dirkeynamedr = "arc_dr";
	String dirkeynameptdb = "arc_target";

	
	
	protected Coord l = null;
	protected Coord r = null;
	protected int dl = 0;
	protected int dr = 0;
	
	protected Coord z = null;
	protected StringProperty result_gr = new SimpleStringProperty();
	protected StringProperty result_kl = new SimpleStringProperty();
	protected StringProperty result_h = new SimpleStringProperty();

	//private Vectors graph = new Vectors(false);
	  
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
		        	
		        	boolean coordlFilled = true;
		        	if(txt_input_lgr.textProperty().length().lessThan(6).get()) coordlFilled=false;
		        	if(txt_input_lkl.textProperty().length().lessThan(6).get()) coordlFilled=false;
		        	if(txt_input_lh.textProperty().isEmpty().get()) coordlFilled=false;
		        	
		        	
		        	if(coordlFilled) {
		        		try {
			        		int gr = Integer.valueOf(txt_input_lgr.textProperty().get());
			        		int kl = Integer.valueOf(txt_input_lkl.textProperty().get());
			        		int h = Integer.valueOf(txt_input_lh.textProperty().get());
			        		l = new Coord(gr,kl,h);
		        	  	}catch(Exception e) {
			        		showAlert(e.getLocalizedMessage());
			        		l=null;
			        	}	
		        	}else {
		        		l=null;
		        	}
		        	
		        	boolean coordrFilled = true;
		        	if(txt_input_rgr.textProperty().length().lessThan(6).get()) coordrFilled=false;
		        	if(txt_input_rkl.textProperty().length().lessThan(6).get()) coordrFilled=false;
		        	if(txt_input_rh.textProperty().isEmpty().get()) coordrFilled=false;
		        	
		        	
		        	if(coordrFilled) {
		        		try {
			        		int gr = Integer.valueOf(txt_input_rgr.textProperty().get());
			        		int kl = Integer.valueOf(txt_input_rkl.textProperty().get());
			        		int h = Integer.valueOf(txt_input_rh.textProperty().get());
			        		r = new Coord(gr,kl,h);
		        	  	}catch(Exception e) {
			        		showAlert(e.getLocalizedMessage());
			        		r=null;
			        	}	
		        	}else {
		        		r=null;
		        	}
		        	
		        	if(txt_input_ld.textProperty().isNotEmpty().get()) {
		        		dl = Integer.valueOf(txt_input_ld.textProperty().get());
		        	}else{
		        		dl=0;
		        	};
		        	
		        	if(txt_input_rd.textProperty().isNotEmpty().get()) {
		        		dr = Integer.valueOf(txt_input_rd.textProperty().get());
		        	}else{
		        		dr=0;
		        	};
		        	
			        
		        	if(l!=null&&r!=null&&dl>0&&dr>0) {
		        		try {
		        		z = Measurement.arc(l,dl,r,dr);
		        		result_gr.set(String.valueOf(z.getGr()));
		        		result_kl.set(String.valueOf(z.getKl()));
		        		result_h.set(String.valueOf(z.getH()));
		        		btn_todb.setVisible(true);
		        		hideAlert();
		        		}catch(Exception e){
		        			result_gr.set("");
			        		result_kl.set("");
			        		result_h.set("");
			        		btn_todb.setVisible(false);	
			        		showAlert(e.getLocalizedMessage());
		        		}
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
	
	
	@FXML
	private void initialize() {		
		btn_todb.setVisible(false);
		
		//get the point a from the PTDB if one was put to memory
		Coord pt = DB.getTransferCoord();
		if(pt!=null) {
			String target = DB.getKey(dirkeynameptdb);
			if(target.equals("l")) {
				txt_input_lgr.setText(String.valueOf(pt.getGr()));
				txt_input_lkl.setText(String.valueOf(pt.getKl()));
				txt_input_lh.setText(String.valueOf(pt.getH()));
				DB.setKey(dirkeynameptl, "");
			}

			if(target.equals("r")) {
				txt_input_rgr.setText(String.valueOf(pt.getGr()));
				txt_input_rkl.setText(String.valueOf(pt.getKl()));
				txt_input_rh.setText(String.valueOf(pt.getH()));
				DB.setKey(dirkeynameptr, "");
			}
			DB.resetTransfer();
		}
		
		//get the pt left from the temp if one was put to memory
		String tmp = DB.getKey(dirkeynameptl);
		if(!tmp.isBlank()) {
			this.l=new Coord(tmp);
			txt_input_lgr.setText(String.valueOf(l.getGr()));
			txt_input_lkl.setText(String.valueOf(l.getKl()));
			txt_input_lh.setText(String.valueOf(l.getH()));
			DB.setKey(dirkeynameptl, "");
		}
		
		//get the pt right from the temp if one was put to memory
		tmp = DB.getKey(dirkeynameptr);
		if(!tmp.isBlank()) {
			this.r=new Coord(tmp);
			txt_input_rgr.setText(String.valueOf(r.getGr()));
			txt_input_rkl.setText(String.valueOf(r.getKl()));
			txt_input_rh.setText(String.valueOf(r.getH()));
			DB.setKey(dirkeynameptr, "");
		}
		
		//get the distances from the temp if one was put to memory
		tmp = DB.getKey(dirkeynamedl);
		if(!tmp.isBlank()) {
			this.dl=Integer.valueOf(tmp);
			txt_input_ld.setText(String.valueOf(dl));
			DB.setKey(dirkeynamedl, "");
		}

		tmp = DB.getKey(dirkeynamedr);
		if(!tmp.isBlank()) {
			this.dr=Integer.valueOf(tmp);
			txt_input_rd.setText(String.valueOf(dr));
			DB.setKey(dirkeynamedr, "");
		}
		
		//artificially kick off changelistener
		getChangeListener(txt_input_lgr).changed(result_gr, "", "");
		
		//hbox.getChildren().add(graph);
		
		txt_input_lgr.textProperty().addListener(getChangeListener(txt_input_lgr)); 
		txt_input_lkl.textProperty().addListener(getChangeListener(txt_input_lkl)); 
		txt_input_lh.textProperty().addListener(getChangeListener(txt_input_lh));
		txt_input_ld.textProperty().addListener(getChangeListener(txt_input_ld));

		txt_input_rgr.textProperty().addListener(getChangeListener(txt_input_rgr)); 
		txt_input_rkl.textProperty().addListener(getChangeListener(txt_input_rkl)); 
		txt_input_rh.textProperty().addListener(getChangeListener(txt_input_rh));
		txt_input_rd.textProperty().addListener(getChangeListener(txt_input_rd));

		txt_result_gr.textProperty().bind(result_gr);
		txt_result_kl.textProperty().bind(result_kl);
		txt_result_h.textProperty().bind(result_h);
		
		btn_lfromdb.setOnAction((e)->{
			if(l!=null)DB.setKey(dirkeynameptl,l.stringify());
			if(r!=null)DB.setKey(dirkeynameptr,r.stringify());
			if(dl>0)DB.setKey(dirkeynamedl,String.valueOf(dl));
			if(dr>0)DB.setKey(dirkeynamedr,String.valueOf(dr));
			DB.setKey(dirkeynameptdb, "l");
			DB.setTransferDestination(SceneDirectory.MS_ARC);
			((LoaderParent)hbox.getScene().getRoot()).switchTo(SceneDirectory.PTDB);
		});
		
		btn_rfromdb.setOnAction((e)->{
			if(l!=null)DB.setKey(dirkeynameptl,l.stringify());
			if(r!=null)DB.setKey(dirkeynameptr,r.stringify());
			if(dl>0)DB.setKey(dirkeynamedl,String.valueOf(dl));
			if(dr>0)DB.setKey(dirkeynamedr,String.valueOf(dr));
			DB.setKey(dirkeynameptdb, "r");
			DB.setTransferDestination(SceneDirectory.MS_ARC);
			((LoaderParent)hbox.getScene().getRoot()).switchTo(SceneDirectory.PTDB);
		});
		
		btn_todb.setOnAction((e)->{
			DB.setTransferCoord(z);
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
