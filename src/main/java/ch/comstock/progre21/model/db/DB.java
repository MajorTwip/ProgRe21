package ch.comstock.progre21.model.db;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import ch.comstock.progre21.SceneDirectory;
import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Point;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DB {

	static String transferCoord = "transferCoord";
	static String transferDest = "transferDest";
	


	public static ObservableList<Point> getPts() {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("points");
	    ObservableList<Point> pts = FXCollections.observableArrayList();
	    try {
			for(String key : prefs.keys()) {
				try {
					pts.add(new Point(prefs.get(key, "")));
				}catch (IllegalArgumentException e) {
					System.out.println("Invalid Point in DB: ");
					System.out.println(prefs.get(key, "none"));
				}
			}
		} catch (BackingStoreException e) {
			System.out.println("PointDB not disponible on this Platform");;
		}
	    pts.sort((Point l, Point r)->Integer.compare(l.getNr(), r.getNr()));
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
	
	public static void setTransferCoord(Coord coord) {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("nav");
	    prefs.put(transferCoord, coord.stringify());
	}	
	
	public static void setTransferDestination(SceneDirectory scene) {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("nav");
	    prefs.putInt(transferDest, scene.ordinal());
	}	
	
	public static SceneDirectory getTransferDestination() {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("nav");
	    int scene = prefs.getInt(transferDest, -1);
	    if(scene<0)return null;
	    
	    return SceneDirectory.values()[scene];
	}	
	
	public static Coord getTransferCoord() {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("nav");
		Coord coord = null;
	    try {
	    	String result = prefs.get(transferCoord, "");
	    	
	    	if(result == "") return null;
			coord = new Coord(result);
		}catch (IllegalArgumentException e) {
			System.out.println("Invalid Coord in DB");
			System.out.println(prefs.get(transferCoord, "none"));
		}
		return coord;
	}

	public static void resetTransfer() {
	    Preferences prefs = Preferences.userRoot().node("ch.ffhs.gui.vky").node("nav");
	    prefs.remove(transferCoord);
	    prefs.remove(transferDest);
	}	
	
}
