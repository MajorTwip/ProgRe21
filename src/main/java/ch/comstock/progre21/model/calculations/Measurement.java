package ch.comstock.progre21.model.calculations;

import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Dir;

public class Measurement {
	public static Coord vf(Coord a, Dir dir) throws IllegalArgumentException{
		int gr = a.getGr();
		int kl = a.getKl();
		int h = a.getH();
		try {
			return new Coord(gr,kl,h);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("Calculation leads to an illegal result: %d/%d/%d",gr,kl,h));
		}
	}
}
