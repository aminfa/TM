package tm;

public class Band_Old implements Bandle{
	private String wort;
	private int index = 0;
	public Band_Old(String input){
		this.wort = input;
	}
	@Override
	public char get() {
		if(index < 0){
			return Turing.BLANK;
		}
		if(index >= wort.length()){
			return Turing.BLANK;
		}
		return wort.charAt(index);
	}

	@Override
	public void move(RNL rnl) {
		switch(rnl){
		case R: index++;
		break;
		case L: index--;
		break;
		default:
			break;
		}
	}

	@Override
	public void write(char c) {
		if(index < 0){
			while(index != -1){
				wort = Turing.BLANK+wort;
				index++;
			}
			wort = c+wort;
			index++;
		}
		else if(index >= wort.length()){
			wort = wort+c;
		}
		else {
			wort = wort.substring(0, index) + c + wort.substring(index+1);
		}
	}
	public String getWord(){
		return wort;
	}
	@Override
	public String konfig(String q) {
		String s = "";
		int ibackup = index;
		if(index > 0){
			index = 0;
		}
		while(index < wort.length() || index <= ibackup){
			if(index == ibackup){
				s +=" " + q + " ";
			}
			s+= get();
			index++;
		}
		index = ibackup;
		return   s;
	}
	@Override
	public String konfig() {
		return wort;
	}
	

}
