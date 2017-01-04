package tm.kb;

public interface KZF {
	public int K();
	public Zustand funtion(Zustand q, Kopfable k);
	public default String print(char[] Gs, Zustand q0){
			String s = "\t";
			for(char c : Gs){
				s+= c + "\t\t";
			}
			java.util.List<Zustand> qs = new java.util.ArrayList<Zustand>();
			qs.add(q0);
			for(int i = 0; i<qs.size();i++){
				for(char g : Gs){
					
				}
			}
		return null;
		// TODO zu viele moegliche lese buchstaben muessen durchiteriert werden.
	}
}
