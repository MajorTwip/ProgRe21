package ch.comstock.progre21.model;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class Point extends Coord{
	private int nr;
	private String name = "";
	
	public Point (int nr, Coord coord) {
		super(coord.getGr(),coord.getKl(),coord.getH());
		this.nr = nr;
	}
	
	public Point(String str) throws IllegalArgumentException{
		super(Integer.valueOf(str.split("/")[1]),Integer.valueOf(str.split("/")[2]),Integer.valueOf(str.split("/")[3]));
		try {
			this.nr = Integer.valueOf(str.split("/")[0]);
			if(str.split("/").length>4) {
			this.name = URLDecoder.decode(str.split("/")[4], Charset.defaultCharset());
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("illegal String: " + str);
		}
	}

	public String stringify() {
		return String.format("%s/%s/%s/%s/%s", String.valueOf(this.nr), String.valueOf(this.gr), String.valueOf(this.kl), String.valueOf(this.h), URLEncoder.encode(this.name,Charset.defaultCharset()));
	}
	
	public int getNr() {
		return nr;
	}
	public void setNr(int nr) {
		this.nr = nr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
