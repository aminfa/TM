package tm.kb;

import tm.RNL;

public class Bild {
	public Zustand Q;
	public char[] chars;
	public RNL[] way;
	public Bild(int k, Zustand q, char... cs){
		way = new RNL[k];
		this.chars = cs;
		Q = q;
	}
}
