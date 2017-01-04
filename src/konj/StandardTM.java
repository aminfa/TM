package konj;

import tm.RNL;
import tm.Turing;
import tm.kb.KZF;
import tm.kb.Kopfable;
import tm.kb.Zustand;

public class StandardTM {
	public static KZF kopierer(
			int sourceBandIndex, RNL sourceWay,
			int targetBandIndex, RNL targetWay,
			char endSign){
		int k = sourceBandIndex > targetBandIndex? sourceBandIndex: targetBandIndex;
		return new KZF(){

			@Override
			public int K() {
				return k;
			}

			@Override
			public Zustand funtion(Zustand q, Kopfable k) {
				if(q.equals("q0")){
					if(k.r(sourceBandIndex) == endSign){
						return Zustand.string("qf");
					}
					k.w(targetBandIndex, k.r(sourceBandIndex));
					k.m(targetBandIndex, targetWay);
					k.m(sourceBandIndex, sourceWay);
					return q;
				}
				return null;
			}

			
		};
	}
	public static KZF forward(int band, char endSign, RNL way){
		return new KZF(){

			@Override
			public int K() {
				return band;
			}

			@Override   
			public Zustand funtion(Zustand q, Kopfable k) {
				  if(q.equals("q0"))
				  {
					  if(k.r(band) == endSign){
						  	k.m(band, RNL.not(way));
							return Zustand.string("qf");
						}
					  k.m(band, way);
					  return q;
				  }
				  return null;
			}

			
		};
	}
	
}
