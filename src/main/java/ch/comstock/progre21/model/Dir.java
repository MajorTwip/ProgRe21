package ch.comstock.progre21.model;

public class Dir {
	private int azi;
	private int gelwi;
	private int dist;
	
	public Dir(int azi,int gelwi,int dist) throws IllegalArgumentException{
		this.azi=normalizeAzi(azi);
		this.gelwi=normalizeGelwi(gelwi);
		this.dist=normalizeDist(dist);
	}
	
	
	public Dir(String str) throws IllegalArgumentException{
		try {
			String[] elems = str.split("/");
			int azi = Integer.valueOf(elems[0]);
			int gelwi = Integer.valueOf(elems[1]);
			int dist = Integer.valueOf(elems[2]);
			
			this.azi = normalizeAzi(azi);
			this.gelwi = normalizeGelwi(gelwi);
			this.dist = normalizeDist(dist);
		}catch(Exception e) {
			System.out.println("illegal String: " + str);
		}
	}
	
	private int normalizeDist(int dist) {
		if(dist > 0) {
			return dist;
		};
		throw new IllegalArgumentException("Distance must be positive");
	}

	private int normalizeAzi(int azi) {
		return Math.floorMod(azi, 6400);
	}
	
	private int normalizeGelwi(int gelwi) {
		return Math.floorMod(gelwi, 6400);
	}

	public int getAzi() {
		return azi;
	}

	public int getGelwi() {
		return gelwi;
	}

	public int getDist() {
		return dist;
	}
	
	public String stringify() {
		return String.format("%s/%s/%s",this.azi,this.gelwi,this.dist);
	}
}
