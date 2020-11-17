package ch.comstock.progre21.viewmodel;


import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Dir;
import ch.comstock.progre21.model.calculations.Measurement;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MS_VF {
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
	
	protected Coord a = null;
	protected Dir dir = null;
	protected StringProperty result_gr = new SimpleStringProperty();
	protected StringProperty result_kl = new SimpleStringProperty();
	protected StringProperty result_h = new SimpleStringProperty();

	
	  
	ChangeListener<String> getChangeListener(TextField textField){
		return new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				//replace non-digits
				if (!newValue.matches("\\d*")) {
		            textField.setText(newValue.replaceAll("[^\\d]", ""));
		            
		        //check if everything is filled out
		        }else {
		        	boolean allFilled = true;
		        	if(txt_input_gr.textProperty().length().lessThan(6).get()) allFilled=false;
		        	if(txt_input_kl.textProperty().length().lessThan(6).get()) allFilled=false;
		        	if(txt_input_h.textProperty().isEmpty().get()) allFilled=false;
		        	if(txt_input_azi.textProperty().length().lessThan(4).get()) allFilled=false;
		        	if(txt_input_gelwi.textProperty().length().lessThan(4).get()) allFilled=false;
		        	if(txt_input_dist.textProperty().isEmpty().get()) allFilled=false;
		        	
		        	if(!allFilled) {
		        		result_gr.set("");
		        		result_kl.set("");
		        		result_h.set("");
		        		return;
		        	}
		        	
		        	//create a Coord and a Dir
		        	try {
		        		int gr = Integer.valueOf(txt_input_gr.textProperty().get());
		        		int kl = Integer.valueOf(txt_input_kl.textProperty().get());
		        		int h = Integer.valueOf(txt_input_h.textProperty().get());
		        		System.out.printf("Gr: %d Kl: %d H: %d",gr,kl,h);
		        		a = new Coord(gr,kl,h);
		        		
		        		int azi = Integer.valueOf(txt_input_azi.textProperty().get());
		        		int gelwi = Integer.valueOf(txt_input_gelwi.textProperty().get());
		        		int dist = Integer.valueOf(txt_input_dist.textProperty().get());

		        		System.out.printf("Azi: %d Gelwi: %d Dist: %s\n",azi,gelwi,dist);

		        		dir = new Dir(azi,gelwi,dist);
		        		
		        		Coord result = Measurement.vf(a, dir);
		        		result_gr.set(String.valueOf(result.getGr()));
		        		result_kl.set(String.valueOf(result.getKl()));
		        		result_h.set(String.valueOf(result.getH()));

		        		
		        	}catch(Exception e) {
		        		System.out.println(e.getMessage());
		        		result_gr.set("");
		        		result_kl.set("");
		        		result_h.set("");
		        		return;
		        		
		        	}	
		        }
			}
		};
	}	
	
	
	
	@FXML
	private void initialize() {
		txt_input_gr.textProperty().addListener(getChangeListener(txt_input_gr)); 
		txt_input_kl.textProperty().addListener(getChangeListener(txt_input_kl)); 
		txt_input_h.textProperty().addListener(getChangeListener(txt_input_h));
		
		txt_input_azi.textProperty().addListener(getChangeListener(txt_input_azi)); 
		txt_input_gelwi.textProperty().addListener(getChangeListener(txt_input_gelwi)); 
		txt_input_dist.textProperty().addListener(getChangeListener(txt_input_dist));

		txt_result_gr.textProperty().bind(result_gr);
		txt_result_kl.textProperty().bind(result_kl);
		txt_result_h.textProperty().bind(result_h);
	}
}
