package tm.kb;

public interface Zustand {
	public String s();
	public default boolean equals(Zustand z){
		return s().equals(z.s());
	}
	public default boolean equals(String s){
		return s().equals(s);
	}
	public static Zustand string(String s){
		return new StringZ(s);
	}
	public static Zustand[] string(String[] s){
		Zustand[] zs = new Zustand[s.length];
		for(int i = 0 ;i < s.length ; i++){
			int index = i;
			zs[i] = new Zustand(){
				@Override
				public String s() {
					return s[index];
				}

				
			};
		}
		 return zs;
	}
}
class StringZ implements Zustand{
	String s;
	StringZ(String s){
		this.s= s;
	}
	@Override
	public String s() {
		return s;
	}

	public String toString(){
		return s();
	}
	
}
