package tm.bs;

import static org.junit.Assert.*;

import org.junit.Test;

import tm.Band;
import tm.RNL;
import tm.Turing;
import tm.ZustandsFunction;
public class einMnullM {

	@Test
	public void test() {
		ZustandsFunction zF = new ZustandsFunction(){
			@Override
			public QGrnl funtion(String q, char g) {
				QGrnl out = new QGrnl();
				if(g=='0'){
					out.G = '0';
					out.way = RNL.R;
					if(is(q,"q0")){
						out.Q = q;
					}
					else if(is(q,"q3")){
						out.Q = q;
						out.way = RNL.L;
					}
					else if(is(q,"q4")){
						out.G = Turing.BLANK;
						out.Q ="q5";
					}
					else if(is(q,"q5")){
						out.Q = "q6";
					}
					else if(is(q,"q6")){
						out.Q = q;
					}
					else{
						return null;
					}
				}
				if(g=='1'){
					out.G = '1';
					out.way = RNL.R;
					if(is(q,"q0")){
						out.Q = "q1";
					}
					else if(is(q,"q1")){
						out.Q = "q1";
					}
					else if(is(q,"q2")){
						out.G = Turing.BLANK;
						out.way = RNL.L;
						out.Q = "q3";
					}
					else if(is(q,"q3")){
						out.way = RNL.L;
						out.Q = "q3";
					}
					else if(is(q,"q5")){
						out.Q = "q6";
					}
					else if(is(q,"q6")){
						out.Q = "q6";
					}
					else{
						return null;
					}
				}
				if(g==Turing.BLANK){
					out.G = Turing.BLANK;
					if(is(q,"q1")){
						out.Q = "q2";
						out.way = RNL.L;
					}
					else if(is(q,"q3")){
						out.Q = "q4";
						out.way = RNL.R;
					}
					else if(is(q,"q5")){
						out.Q = "q7";
						out.way = RNL.N;
					}
					else if(is(q,"q6")){
						out.Q = "q2";
						out.way = RNL.L;
					}
					else{
						return null;
					}
				}
				return out;
			}
		};
		String[] qfs = {"q7"};
		Turing t = new Turing(zF,"q0",qfs);
		if(!t.eingabe(new Band("0011"))){
			fail();
		}
		//t.eingabeAusgabe(new Band("000000000000000000011111111111111111111"));
	}
	public static boolean is(String a, String b){
		return a.equals(b);
	}
}
