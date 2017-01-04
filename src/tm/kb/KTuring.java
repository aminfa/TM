package tm.kb;

import tm.Band;
import tm.Bandle;
import tm.RNL;

public class KTuring {
	public final KZF zF;
	public final Zustand Q0;
	public final Zustand QF;
	public static char BLANK = 'B';
	public KTuring(KZF zF, Zustand Q0, Zustand QF){
		this.zF = zF;
		this.Q0 = Q0;
		this.QF = QF;
	}
	public KTuring(KZF zF, String Q0, String QF){
		this(zF, Zustand.string(Q0), Zustand.string(QF));
	}
	public boolean eingabe(Kopfable kopf){
		return eingabe(kopf, null);
	}
	
	public boolean eingabe(Kopfable kopf, KVisitor v){
		Zustand cQ = Q0;
		while(true){
			cQ = zF.funtion(cQ, kopf);
			if(v!=null)
				v.visit(kopf, cQ);
			if(cQ==null){
				return false;
			}
			if(QF.equals(cQ)){
				return true;
			}
			
		}
	}
	public boolean eingabe(Bandle b, int k){
		return eingabe(b,k,null);
	}
	public boolean eingabe(Bandle b, int k, KVisitor v){
		if(k<1) return false;
		Bandle[] kband = new Bandle[k];
		kband[0] = b;
		for(int i = 1;i < k; i++){
			kband[i] = new Band("");
		}
		Kopfable kopf = new Kopf(kband);
		
		return eingabe(kopf,v);
	}
	
}
