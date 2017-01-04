package tm.bs;

import static org.junit.Assert.*;

import org.junit.Test;

import tm.RNL;
import tm.Turing;
import tm.ZFTable;

public class HA1A2 {

	@Test
	public void test() {
		char [] alph = {'0','1', '2','3', 'C',Turing.BLANK };
		ZFTable zft =  new ZFTable(alph);
		zft.createTable("q0");
		zft.createTable("q>", alph.length-4);
		zft.createTable("[q", alph.length-4);
		zft.createTable("qz");
		zft.createTable("E", 2);
		for(int i = 0 ; i < alph.length-4;i++){
			zft.push(0, 1, i, Turing.BLANK, RNL.R);
		}
		zft.push(0, 4, 0, 2, RNL.R);
		zft.push(0, 4, 0, 3, RNL.R);
		for(int counter = 0 ; counter < alph.length-4; counter++){
			for(int i = 0 ; i < alph.length-4;i++){
				zft.push(1, 1, counter, alph[i], RNL.R);
			}
			zft.push(1, 2, counter, 2, RNL.R);
			zft.push(1, 2, counter, 3, RNL.R);
			zft.push(1);
			zft.push(1);
		}

		for(int counter = 0 ; counter < alph.length-4; counter++){
			for(int i = 0 ; i < alph.length-4;i++){
				if(counter==i){
					zft.push(2, 3, 0, 'C', RNL.L);
				}
				else{
					zft.push(2);
				}
			}
			zft.push(2);
			zft.push(2);
			zft.push(2, 2, counter, 'C', RNL.R);
			zft.push(2);
			
		}
		for(int i = 0 ; i < alph.length-1;i++){
			zft.push(3, 3, 0, alph[i], RNL.L);
		}
		zft.push(3,0,0,Turing.BLANK,RNL.R);
		for(int i = 0 ; i < alph.length-2;i++){
			zft.push(4);
		}
		zft.push(4, 4, 0, 'C', RNL.R);
		zft.push(4, 4, 1, 'B', RNL.N);
		System.out.println(zft);
		String[] efs = {"E1"};
		Turing tm = new Turing(zft.create(),"q0", efs);
		//System.out.println(tm.eingabeAusgabe("0413041"));
		//System.out.println(tm.eingabeAusgabe("0101"));
		//System.out.println(tm.eingabeAusgabe("2"));
		if(tm.eingabe("1102011"))fail();
		if(tm.eingabe("110011"))fail();
		if(!tm.eingabe("2"))fail();
		if(tm.eingabe(""))fail();
		if(!tm.eingabe("110001121100011"))fail();
	}

}
