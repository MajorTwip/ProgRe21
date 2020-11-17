package ch.comstock.progre21.model.drawings;

import javafx.scene.Group;
import javafx.scene.shape.Line;

public class Vectors extends Group {
	public Vectors(boolean vr) {
		Line north = new Line(0,-100,0,100);
		Line east = new Line(-100,0,100,0);
		this.getChildren().addAll(north,east);
	}
}
