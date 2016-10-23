package processTFIDF;

import java_cup.runtime.lr_parser;

public class Main {
	private static Lector lec;
	
	
	public Main(){
		
	}
	
	public static void main (String[] args) throws Exception{
		for(int i=1;i<2;i++){
			lec=new Lector(args[i]);
		}
	}
	
}
