package tm.bs;

import static org.junit.Assert.*;

import org.junit.Test;
import tm.*;

public class Palindrom {

	@Test
	public void test() {
		char [] alph = {'0','1', 'a','b','c','d',Turing.BLANK };
		ZFTable zft =  new ZFTable(alph);
		zft.createTable("q", 3);
		zft.createTable("[]", alph.length-1);
		zft.createTable("<>", alph.length-1);
		//zft.fill(0);
		for(int i = 0; i < alph.length-1; i++){
			zft.push(0, 1, i, Turing.BLANK, RNL.R);
		}
		zft.push(0, 0, 2, Turing.BLANK, RNL.R);

		for(int i = 0; i < alph.length-1; i++){
			for(int j = 0; j < alph.length-1; j++){
				zft.push(1, 1, i, alph[j], RNL.R);
			}
			zft.push(1, 2, i, Turing.BLANK, RNL.L);
		}

		for(int i = 0; i < alph.length-1; i++){
			for(int j = 0; j < alph.length-1; j++){
				if(i==j)
					zft.push(2, 0, 1, Turing.BLANK, RNL.L);
				else zft.push(2);
			}
			zft.push(2);
		}
		for(int i = 0; i < alph.length-1; i++){
			zft.push(0,0,1,alph[i],RNL.L);
		}
		zft.push(0,0,0,Turing.BLANK,RNL.R);
		System.out.println(zft);
		ZustandsFunction zf = zft.create();
		String[] qfs = {"q2"};
		Turing tm = new Turing(zf,"q0",qfs);
		tm.eingabeAusgabe("101");
		System.out.println();
		tm.eingabeAusgabe("0110");
		if(!tm.eingabe("abcdaadcba")) fail();
		if(tm.eingabe("abbdaadcba")) fail();
	}

}
