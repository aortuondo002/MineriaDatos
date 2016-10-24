package processTFIDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import weka.core.Instances;

public class Lector {
	private Instances train;
	private Instances dev;
	private Instances data;
	
	public Lector(String path) throws Exception{
		leerArchivos(path);
	}

	public void leerArchivos(String path) throws Exception{
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			if(path.contains("dev"))
				dev = new Instances(br);
			else{
				train=new Instances(br);
			}
			br.close();
			juntar(dev, train);
	}
	
	public void juntar(Instances dev,Instances train){
		this.data=new Instances(train);
		this.data.addAll(dev);	
	}
	
	public Instances getDatos(){
		return this.data;
	}
	
	
	
	
}
