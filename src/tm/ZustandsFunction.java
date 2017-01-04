package tm;

public interface ZustandsFunction {
	public QGrnl funtion(String q, char g);
	class QGrnl {
		public String Q;
		public char G;
		public RNL way;
		public String toString(){
			String s = "";
			s = "(" + Q + ", " + G + ", " + way + ")";
			return s;
		}
	}
}

