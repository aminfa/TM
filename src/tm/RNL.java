package tm;

public enum RNL {
	R,N,L;

	public static RNL not(RNL way) {
		switch(way){
		case R: return L; 
		case L: return R; 
		case N: return N; 
		default: return N;
		}
	}
}
