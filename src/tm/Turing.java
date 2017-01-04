package tm;

import tm.ZustandsFunction.QGrnl;

public class Turing {
	public final ZustandsFunction zF;
	public final String Q0;
	public final String[] QFs;
	public static char BLANK = 'B';
	public Turing(ZustandsFunction zF, String Q0, String[] QFs){
		this.zF = zF;
		this.Q0 = Q0;
		this.QFs = QFs;
	}
	public static boolean IS(String a, String b){
		return a.equals(b);
	}
	public boolean eingabe(String s){
		return eingabe(new Band(s));
	}
	public boolean eingabe(Bandle b){
		return eingabe(b,null);
	}
	public boolean eingabeAusgabe(Bandle b){
		return eingabe(b,(ba,q)->{System.out.println(ba.konfig(q));});
	}
	public boolean eingabeAusgabe(String b){
		return eingabeAusgabe(new Band(b));
	}
	public boolean eingabe(Bandle b,Visitor v){
		String cQ = Q0;
		while(true){
			QGrnl out = zF.funtion(cQ, b.get());
			if(v!=null)
				v.visit(b, cQ);
			if(out==null){
				return false;
			}
			cQ =out.Q;
			b.write(out.G);
			b.move(out.way);
			for(String q : QFs){
				if(q.equals(cQ)){
					if(v!=null)
						v.visit(b, cQ);
					return true;
				}
			}
		}
	}
}
