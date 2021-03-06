package ch.comstock.progre21.model;

import java.net.URLDecoder;
import java.nio.charset.Charset;

import ch.comstock.progre21.exceptions.IllegalValueException;

public class Coord {
	protected int gr;
	protected int kl;
	protected int h;
	
	public Coord(int grosseKoord, int kleineKoord, int hoehe) throws IllegalArgumentException{
		this.gr = normalizeGr(grosseKoord);
		this.kl = normalizeKl(kleineKoord);
		this.h = normalizeH(hoehe);
	}
	
	public Coord(String str) throws IllegalArgumentException{
		try {
			String[] elems = str.split("/");
			int grosseKoord = Integer.valueOf(elems[0]);
			int kleineKoord = Integer.valueOf(elems[1]);
			int hoehe = Integer.valueOf(elems[2]);
			
			this.gr = normalizeGr(grosseKoord);
			this.kl = normalizeKl(kleineKoord);
			this.h = normalizeH(hoehe);
		}catch(Exception e) {
			System.out.println("illegal String: " + str);
		}
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
	
	public String stringify() {
		return String.format("%s/%s/%s",this.gr,this.kl,this.h);
	}
	
	private int normalizeGr(int input) {
		if(input > 400000 && input <999999) {
			return input + 2000000;
		}; //LV03 -> LV95
		
		if(input > 2400000 && input <2999999) {
			return input;
		}; //LV95
		throw new IllegalValueException("Big Coord must be between (2)400 000 and (2)999 999", "e.coord.gr.oob");
	}
	
	private int normalizeKl(int input) {
		if(input > 0 && input <399999) {
			return input + 1000000;
		}; //LV03 -> LV95
		
		if(input > 1000000 && input <1399999) {
			return input;
		}; //LV95
		throw new IllegalArgumentException("Small Coord must be between (1)000 000 and (1)399 999");
	}
	private int normalizeH(int input) {
		if(input > 0 && input <8000) {
			return input;
		};
		throw new IllegalArgumentException("Altitude must be between 0 and 7999");
	}
	
	

}
