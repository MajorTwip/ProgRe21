package ch.comstock.progre21.model.calculations;

import ch.comstock.progre21.exceptions.IllegalValueException;
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
		double D = (Math.sqrt(Math.pow((l.getGr()-r.getGr()),2)+Math.pow((l.getKl()-r.getKl()),2)));
		if((dl+dr)<D)throw new IllegalValueException("the circles do not intersect", "e.measur.arc.nointersec");
		if(Math.abs(dl-dr)>D)throw new IllegalValueException("the circles do not intersect(overlay)", "e.measur.arc.nointersecover");
		int switcher = -1; //1 would give the other intersection point
		
		//formula from http://www.ambrsoft.com/TrigoCalc/Circles2/circle2intersection/CircleCircleIntersection.htm
		double rho = Math.sqrt((D+dl+dr)*(D+dl-dr)*(D-dl+dr)*(-D+dl+dr))/4;
		System.out.println(rho);
		int a = l.getGr();
		int b = l.getKl();
		int c = r.getGr();
		int d = r.getKl();
		System.out.println((((c-a)*((dl*dl)-(dr*dr)))/2*D*D));
		double x1 = (((a+c)/2) + (((c-a)*((dl*dl)-(dr*dr)))/(2*D*D))+(switcher*2*(b-d)/(D*D)*rho));
		double y1 = (((b+d)/2) + (((d-b)*((dl*dl)-(dr*dr)))/(2*D*D))+(-switcher*2*(a-c)/(D*D)*rho));
		int h1 = l.getH()+(r.getH()-l.getH())*dr/(dl+dr);
		System.out.println("x1: " + x1 + " y1: " + y1 + "h1: " +h1  );
		return new Coord((int)x1,(int)y1,h1);
	}

	public static Coord arcr(Coord l, int dl, Coord r, int dr) {
		if(dl==dr || (dl+3200)%6400==dr || (dl-3200)%6400==dr)throw new IllegalValueException("the directions are (anti)parallel", "e.measur.arcr.nointersec");
		
		//formula from http://www.ambrsoft.com/TrigoCalc/Circles2/circle2intersection/CircleCircleIntersection.htm
		
		double dlr = TransformAzi.azi2rad(dl);
		double drr = TransformAzi.azi2rad(dr);
		
		double m1;
		double b1;
		
		double gr=0;
		double kl=0;

		boolean invalidatel = false;
		
		if(dlr%Math.PI==0) {
			m1=Integer.MAX_VALUE;
			b1=0;
			gr = l.getGr();
			invalidatel=true;
		}else {
			m1=Math.cos(dlr)/Math.sin(dlr);
			if(Math.abs(m1)<1.0E-8)m1=0;
			if(m1==0) {
				kl=l.getKl();
				b1=l.getKl();
			}else{
				b1=l.getKl()-(l.getGr()*m1);
			}
		}
		
		double m2; 
		double b2; 
		
		if(drr%Math.PI==0) {
			m2=Integer.MAX_VALUE;
			b2=0;
			gr = r.getGr();
		}else {
			m2=Math.cos(drr)/Math.sin(drr);
			if(Math.abs(m2)<1.0E-8)m2=0;
			if(m2==0) {
				kl=r.getKl();
				b2=r.getKl();
			}else{
				b2=r.getKl()-(r.getGr()*m2);
			}
		}
		
		System.out.println("m1: " + m1 +" b1: " + b1);
		System.out.println("m2: " + m2 +" b2: " + b2);

		double x;
		double y;
		
		if(kl!=0&&gr!=0) {
			x=gr;
			y=kl;
		}else if(!invalidatel) {
			x = (b2 - b1) / (m1 - m2);
			y = m1 * x + b1;
		}else{
			x = gr;
			y = m2 * x + b2;
		}
		
		return new Coord((int)x,(int)y,(l.getH()+r.getH())/2);
	}
}
