package konj;

import tm.kb.KTuring;
import tm.kb.KVisitor;
import tm.kb.KZF;
import tm.kb.Kopfable;
import tm.kb.Zustand;

public class UniTM {
	public static int UNION_COUNT = 0;
	public static boolean workOff(Kopfable k, KVisitor v, KTuring... tms){
		for(KTuring ktm : tms){
			if(!ktm.eingabe(k, v)) return false;
		}
		return true;
	}
	public static KZF union(KZF zf1, KZF zf2){
		return union(zf1,zf2, Zustand.string("q0"),  Zustand.string("q0"), Zustand.string("qf"));
	}
	public static KZF union(KZF zf1, KZF zf2, Zustand q0Zf1 ,Zustand q0Zf2, Zustand qfZf1){
		int k = zf1.K()>zf2.K()?zf1.K():zf2.K();
		int index1 = ++UNION_COUNT;
		int index2 = ++UNION_COUNT;
		return new KZF(){
			@Override
			public int K() {
				return k;
			}

			@Override
			public Zustand funtion(Zustand q, Kopfable k) {
				UnionZustand currentQ = null;
				UnionZustand nextQ = null;
				if(q.equals(q0Zf1)){
					currentQ = new UnionZustand(index1, q);
				}
				else{
					if(q instanceof UnionZustand){
						currentQ = (UnionZustand) q;
					}
					else return null;
				}
				int pre = currentQ.getI();
				if(pre == index1){
					Zustand qNext = zf1.funtion(currentQ.getQ(),k);
					if(qNext == null) return null;
					if(qNext.equals(qfZf1)){

						nextQ = new UnionZustand(index2, q0Zf2);
					}
					else{
						nextQ = new UnionZustand(index1, qNext);
					}
				}
				else if(pre == index2){
					Zustand qNext = zf2.funtion(currentQ.getQ(),k);
					if(qNext == null)return null;
					nextQ = new UnionZustand(index2, qNext);
				}
				return nextQ;
			}

		};
	}
}
class UnionZustand implements Zustand{
	private Zustand q;
	private int index;
	public int getI(){
		return index;
	}
	public Zustand getQ(){
		return q;
	}
	UnionZustand(int index, Zustand z){
		this.index=index;
		q =z;
	}
	@Override
	public String s() {
		return index + "_" + q.s();
	}

	public String toString(){
		return getQ().s();
	}
	
}
