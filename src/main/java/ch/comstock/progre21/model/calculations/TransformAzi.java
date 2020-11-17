package ch.comstock.progre21.model.calculations;

public class TransformAzi {
	//6400 Arty PerMille == 360° == 2PI
	public static double azi2rad(int azi) {
		return (double)azi/6400*2*Math.PI;
	}
	
	public static int rad2azi(float rad) {
		return (int) Math.round(rad/2/Math.PI*6400);
	}
}
