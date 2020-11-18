package ch.comstock.progre21.model.db;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import ch.comstock.progre21.model.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {



	public static ObservableList<Point> getPts() {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("points");
	    ObservableList<Point> pts = FXCollections.observableArrayList();
	    try {
			for(String key : prefs.keys()) {
				try {
					pts.add(new Point(prefs.get(key, "")));
				}catch (IllegalArgumentException e) {
					System.out.println("Invalid Point in DB: ");
					System.out.println("Invalid Point in DB: ");
				}
			}
		} catch (BackingStoreException e) {
			System.out.println("PointDB not disponible on this Platform");;
		}
		return pts;
	}
	
	public static void savePt(Point pt) {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("points");
	    prefs.put(String.valueOf(pt.getNr()), pt.stringify());
	}

	public static void delPt(int nr) {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("points");
	    prefs.remove(String.valueOf(nr));
	}	
	
}
