package tm;

public class Band implements Bandle{
	private String preW, postW;
	public Band(String input){
		preW = "";
		this.postW = input;
	}
	@Override
	public char get() {
		if(postW.length()==0){
			return Turing.BLANK;
		}
		else return postW.charAt(0);
	}

	@Override
	public void move(RNL rnl) {
		switch(rnl){
		case R: 
			if(postW.length()==0){
				preW = preW + Turing.BLANK;
			}
			else{
				preW = preW + postW.charAt(0);
				postW = postW.substring(1);
			}
			break;
		case L: 
			if(preW.length()==0){
				postW = Turing.BLANK + postW ;
			}
			else{
				postW =  preW.charAt(preW.length()-1) + postW;
				preW = preW.substring(0,preW.length()-1);
			}
		default: return;
		}
	}

	@Override
	public void write(char c) {
		if(postW.length()==0){
			postW = ""+c;
		}
		else{
			postW = c + postW.substring(1); 
		}
	}
	public String getWord(){
		return preW + postW;
	}
	@Override
	public String konfig(String q) {
		String s = "";
		s += preW + " " + q + " " + postW;
			
		return   s;
	}
	@Override
	public String konfig() {
		return getWord();
	}
	public String toString(){
		String s = "";
		s += preW + " " + postW;
	
		return   s;
	}

}