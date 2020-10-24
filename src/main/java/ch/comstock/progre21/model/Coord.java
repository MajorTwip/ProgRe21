package ch.comstock.progre21.model;

public class Coord {
	private int gr;
	private int kl;
	private int h;
	
	public Coord(int grosseKoord, int kleineKoord, int hoehe) throws IllegalArgumentException{
		this.gr = normalizeGr(grosseKoord);
		this.kl = normalizeKl(kleineKoord);
		this.h = normalizeH(hoehe);
	}
	
	public int getGr() {
		return gr;
	}
	
	public int getKl() {
		return kl;
	}
	
	public int getH() {
		return h;
	}
	
	private int normalizeGr(int input) {
		if(input > 400000 && input <999999) {
			return input + 2000000;
		}; //LV03 -> LV95
		
		if(input > 2400000 && input <2999999) {
			return input;
		}; //LV95
		throw new IllegalArgumentException("Input must be between (2)400 000 and (2)999 999");
	}
	
	private int normalizeKl(int input) {
		if(input > 0 && input <399999) {
			return input + 1000000;
		}; //LV03 -> LV95
		
		if(input > 1000000 && input <1399999) {
			return input;
		}; //LV95
		throw new IllegalArgumentException("Input must be between (1)000 000 and (1)399 999");
	}
	private int normalizeH(int input) {
		if(input > 0 && input <8000) {
			return input;
		};
		throw new IllegalArgumentException("Input must be between 0 and 7999");
	}	

}
