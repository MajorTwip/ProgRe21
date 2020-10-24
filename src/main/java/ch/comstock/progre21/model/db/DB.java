package ch.comstock.progre21.model.db;

import java.time.LocalTime;
import java.util.prefs.Preferences;

import ch.comstock.progre21.model.Coord;
import javafx.concurrent.Task;

public class DB {
	private Preferences prefs;
	
	public DB(){
	    prefs = Preferences.userRoot().node(this.getClass().getName());
	    
	    System.out.println(prefs.get("tst", "none"));
	    prefs.put("tst", LocalTime.now().toString());
	}
	
	
	
	
	protected class GetPoint extends Task<Coord>{
		
		private int nr = -1;
		
		public void setNr(int pointNr) {
			this.nr=pointNr;
		}
		
		@Override
		protected Coord call() throws Exception {
			if(nr<0) throw new IllegalStateException("setNr must be called befor running");
			updateMessage("looking Up");
			Coord res = new Coord(2600000, 1200000, 400);
			updateMessage("found");
			updateMessage("finished");
			return res;
		}
		
		
	}
	
	
	
	
}
