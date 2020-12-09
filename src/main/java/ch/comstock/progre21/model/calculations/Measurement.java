package ch.comstock.progre21.model.calculations;

import ch.comstock.progre21.model.Coord;
import ch.comstock.progre21.model.Dir;

public class Measurement {
	
	
	public static Coord vf(Coord a, Dir dir) throws IllegalArgumentException{
		
		//Calculate new Height
		//Transform to Radiant
		double gelwirad = TransformAzi.azi2rad(dir.getGelwi());
		//Calculate Heightdiff
		double deltaH = dir.getDist()*Math.sin(gelwirad);
		//Set new height
		int h = (int) (a.getH() + deltaH);
		//Calculate new coordinates
		//Transform Azi to Rad
		double azirad = TransformAzi.azi2rad(dir.getAzi());
		//Get distance on plaine
		double topoDist = dir.getDist()*Math.cos(gelwirad);
		//get DeltaGr / DeltaKl
		//(In Topography the "X" is south to north, not like normaly in maths)
		double deltaGr = topoDist * Math.sin(azirad);
		double deltaKl = topoDist * Math.cos(azirad);
		
		int gr = (int)(a.getGr() + deltaGr);
		int kl = (int)(a.getKl() + deltaKl);
		
		try {
			return new Coord(gr,kl,h);
		}catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(String.format("Calculation leads to an illegal result: %d/%d/%d",gr,kl,h));
		}
	}

	public static Coord vr(Coord z, Dir dir) throws IllegalArgumentException{
		// do vf but inversed
		Dir dirinv = new Dir(3200+dir.getAzi(),-dir.getGelwi(),dir.getDist());
		return vf(z,dirinv);
	}

	public static Coord arc(Coord l, int dl, Coord r, int dr) throws IllegalArgumentException{
		// TODO Auto-generated method stub
		return null;
	}
}
