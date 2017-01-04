package tm;

import java.util.ArrayList;
import java.util.Iterator;

import tm.ZustandsFunction.QGrnl;

public class ZFTable {
	private int Q ,G;
	private char Gs[];
	private ArrayList<Tableable> tables;
	public ZFTable(char[] Gs){
		this.G = Gs.length;
		this.Gs = Gs;
		
		tables = new ArrayList<Tableable>();
	}
	public void push(int sourceTableNr, int targetTableNr, int outQ, int outC, RNL way){
		push(sourceTableNr, targetTableNr,outQ, (char)(outC+48), way);
	}
	public void push(int sourceTableNr, int targetTableNr, int outQ, char outC, RNL way){
		if(targetTableNr < 0 || targetTableNr >= tables.size()) return ;
		if(sourceTableNr < 0 || sourceTableNr >= tables.size()) return ;
		String outS = (tables.get(targetTableNr).getIndex(outQ));
		tables.get(sourceTableNr).push(outS, outC, way);
	}
	public void push(int sourceTableNr){
		tables.get(sourceTableNr).pushNull();
	}
	public String toString(){
		String s= "";
		for(Tableable t : tables){
			s += t.toString();
			s += "\n";
		}
		return s;
	}
	public ZustandsFunction create(){
		return new ZustandsFunction(){

			@Override
			public QGrnl funtion(String q, char g) {
				for(int i = 0 ; i < Gs.length; i++){
					if(Gs[i]==g){
						for(Tableable t : tables){
							if(t.contains(q)){
								try{
									return t.getQG(q, i);
								}catch(Exception ex){
									System.out.println(ex.getMessage());
									System.out.println(q);
								}
							}
						}
						return null;
					}
				}
				return null;
			}
			
		};
	}
	public void createTable(String... names){
		CustomTable cT = new CustomTable(names);
		for(Tableable t: tables){
			if(t.equals(cT)) {
				cT = null;
				return;
			}
		}
		tables.add(cT);
		Q += names.length;
	}
	public void createTable(String name, int size){
		Table cT = new Table(size,name);
		for(Tableable t: tables){
			if(t.equals(cT)) {
				cT = null;
				return;
			}
		}
		tables.add(cT);
		Q += size;
	}
	public void fill(int tableNr){
		if(tableNr < 0 || tableNr >= tables.size()) return ;
		while(!tables.get(tableNr).pushNull()){
			
		}
	}
	interface Tableable extends Iterable<String>{
		public boolean pushNull();
		public boolean contains(String q);
		public boolean push(String q, char out, RNL way);
		public boolean equals(Tableable t);
		public String toString();
		public String getIndex(int i);
		public QGrnl getQG(int Q, int G);
		public QGrnl getQG(String Q, int G);
	}
	class Table implements Tableable{
		private String qName;
		private int qNr;
		private QGrnl[][] t;
		private int cQ,cG;
		Table(int size, String name){
			qName = name;
			qNr = size;
			t = new QGrnl[qNr][G];
			cQ = 0;
			cG = 0;
		}
		public boolean pushNull() { 
			 if(cG>=G) {
				 cG = 0;
				 cQ++;
			 }
			 if(cQ>=qNr) {
				 return true;
			 }
			 t[cQ][cG]=null;
			 cG++;
			 return false;
		}
		public boolean push(String q, char out, RNL way){
			 if(cQ>=qNr) {
				 return true;
			 }
			 if(cG>=G) {
				 cG = 0;
				 cQ++;
			 }
			 QGrnl Qg = new QGrnl();
			 Qg.Q = q;
			 Qg.G = out;
			 Qg.way = way;
			 t[cQ][cG]=Qg;
			 cG++;
			 return false;
		}
		
		public String toString(){
			String s = "\t";
			for(char c : Gs){
				s+= c + "\t\t";
			}
			s+=  "\n";
			for(int x = 0 ; x < t.length; x++){
				s += qName + x + "\t";
				for(int y = 0 ; y < t[0].length ; y++){
					if(t[x][y]==null) s += " - \t";
					else s += t[x][y].toString();
					s+= "\t";
				}
				s+=  "\n";
			}
			return s;
		}
		@Override
		public boolean equals(Tableable t) {
			for(String s : this){
				for(String s1 : t){
					if(s.equals(s1)) return true;
				}
			}
			return false;
		}
		@Override
		public Iterator<String> iterator() {
			return new LinearIt();
		}
		class LinearIt implements Iterator<String>{
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < qNr;
			}

			@Override
			public String next() {
				return qName + (i++);
			}
			
		}
		@Override
		public String getIndex(int i) {
			return qName + i;
		}
		@Override
		public QGrnl getQG(int x, int y) {
			return t[x][y];
		}
		@Override
		public boolean contains(String q) {
			for(String s : this){
				if(q.equals(s))
						return true;
			}
			return false;
		}
		@Override
		public QGrnl getQG(String Q, int G) {
			for(int i = 0;i < qNr; i++){
				if(Q.equals(qName + i))
					return getQG(i,G);
			}
			return null;
		}
		
	}
	class CustomTable implements Tableable{
		private String[] qNames;
		private QGrnl[][] t;
		private int cQ,cG;
		public CustomTable(String...ss){
			qNames = ss;
			t = new QGrnl[qNames.length][G];
			cQ = 0;
			cG = 0;
		}
		@Override
		public Iterator<String> iterator() {
			return new It();
		}
		class It implements Iterator<String>{
			int i = 0;
			@Override
			public boolean hasNext() {
				return i < qNames.length;
			}

			@Override
			public String next() {
				return qNames[i++];
			}
			
		}
		@Override
		public boolean pushNull() {
			 if(cG>=G) {
				 cG = 0;
				 cQ++;
			 }
			 if(cQ>=qNames.length) {
				 return true;
			 }
			 t[cQ][cG]=null;
			 cG++;
			 return false;
		}

		@Override
		public boolean contains(String q) {
			for(String s : this){
				if(q.equals(s))
						return true;
			}
			return false; 	
		}

		@Override
		public boolean push(String q, char out, RNL way) {
			if(cQ>=qNames.length) {
				 return true;
			 }
			 if(cG>=G) {
				 cG = 0;
				 cQ++;
			 }
			 QGrnl Qg = new QGrnl();
			 Qg.Q = q;
			 Qg.G = out;
			 Qg.way = way;
			 t[cQ][cG]=Qg;
			 cG++;
			 return false;
		}

		@Override
		public boolean equals(Tableable t) {
			for(String s : this){
				for(String s1 : t){
					if(s.equals(s1)) return true;
				}
			}
			return false;
		}

		@Override
		public String getIndex(int i) {
			return qNames[i];
		}

		@Override
		public QGrnl getQG(int Q, int G) {
			return t[Q][G];
		}

		@Override
		public QGrnl getQG(String Q, int G) {
			for(int i = 0;i < qNames.length; i++){
				if(Q.equals(qNames[i]))
					return getQG(i,G);
			}
			return null;
		}

		public String toString(){
			String s = "\t";
			for(char c : Gs){
				s+= c + "\t\t";
			}
			s+=  "\n";
			for(int x = 0 ; x < t.length; x++){
				s += qNames[x] + "\t";
				for(int y = 0 ; y < t[0].length ; y++){
					if(t[x][y]==null) s += " - \t";
					else s += t[x][y].toString();
					s+= "\t";
				}
				s+=  "\n";
			}
			return s;
		}
	}
}
