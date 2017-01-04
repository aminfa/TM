package tm.bs;

import static org.junit.Assert.fail;

import org.junit.Test;

import tm.Band;
import tm.RNL;
import tm.Turing;

public class BandTest {

	@Test
	public void test() {
		Band b = new Band("hallo");
		Assert(b.get(), 'h');
		b.move(RNL.L);
		Assert(b.get(), Turing.BLANK);
		b.move(RNL.L);
		Assert(b.get(), Turing.BLANK);
		b.move(RNL.L);
		b.write('s');
		Assert(b.get(), 's');
		Assert(b.getWord(), "sBBhallo");
		b.move(RNL.R);
		b.move(RNL.R);
		b.move(RNL.R);
		b.move(RNL.R);
		b.move(RNL.R);
		b.move(RNL.R);
		b.move(RNL.R);
		Assert(b.get(), 'o');
		b.move(RNL.R);
		Assert(b.get(), Turing.BLANK);
	}
	private static <T>void Assert(T a, T b){
		if(!a.equals(b)){
			fail();
		}
	}
}
