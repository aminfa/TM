package tm.bs;

import static org.junit.Assert.*;

import org.junit.Test;

import konj.StandardTM;
import konj.UniTM;
import tm.Band;
import tm.RNL;
import tm.Turing;
import tm.kb.Bild;
import tm.kb.KTuring;
import tm.kb.KZF;
import tm.kb.Kopfable;
import tm.kb.Zustand;

public class Kopierer {

	@Test
	public void test() {
		char [] alph = {'0','1',Turing.BLANK };
		
		KZF kopier = StandardTM.kopierer(0,RNL.L, 1, RNL.R,Turing.BLANK);
		
		KTuring kt = new KTuring(
				UniTM.union(StandardTM.forward(0, Turing.BLANK, RNL.R), 
							kopier), 
				 "q0", "qf");
		
		//if(!kt.eingabe(new Band("hallooo"), 2)) fail();
		kt.eingabe(new Band("hallooo"), 2,(k,Q)->{
			if(Q!= null && ( Q.toString().equals("qf") )){
				System.out.println(k.printBand(0));
				System.out.println(k.printBand(1));
			}
				//System.out.println(k.printBand(0, Q));
				//System.out.println(k.printBand(1,Q));
			
		});
	}

}
