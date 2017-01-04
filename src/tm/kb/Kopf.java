package tm.kb;

import tm.Bandle;
import tm.RNL;

public class Kopf implements Kopfable{
	private Bandle[] kband;
	public Kopf(Bandle[] bs){
		this.kband = bs;
	}
	@Override
	public char r(int i) {
		return kband[i].get();
	}
	@Override
	public void w(int i, char c) {
		kband[i].write(c);
	}
	@Override
	public void m(int i, RNL way) {
		kband[i].move(way);				
	}
	@Override
	public int k() {
		return kband.length;
	}
	@Override
	public String printBand(int i) {
		return kband[i].konfig();
	}
	@Override
	public String printBand(int i, Zustand z) {
		if(z==null) return printBand(i, Zustand.string("null"));
		return kband[i].konfig(z.s());
	}
}
