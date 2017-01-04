
package tm.kb;

import tm.RNL;

public interface Kopfable {
	public char r(int i);
	public void w(int i, char c);
	public void m(int i, RNL way);
	public int k();
	public String printBand(int i);
	public String printBand(int i, Zustand z);
}
