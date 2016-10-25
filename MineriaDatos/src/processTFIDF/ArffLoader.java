package processTFIDF;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class ArffLoader {
	
	private String path1;
	private String path2;
	private Instances allData;
	
	public ArffLoader(String path1, String path2){
		this.path1 = path1;
		this.path2 = path2;
		allData = null;
		start(path1, path2);
	}

	private void start(String path1, String path2) {
		Instances a = load(path1);
		Instances b = load(path2);
		a.addAll(b);
		allData = a;
	}

	private Instances load(String path) {
		Instances data = null;
		FileReader fi = null;
		try {
			fi = new FileReader(path);
		} catch (FileNotFoundException e) {
			System.out.println("Error: revisar path del fichero de datos: " + path);
		}
		try {
			data = new Instances(fi);
		} catch (IOException e) {
			System.out.println("Error: revisar contenido del fichero de datos: "+path);
		}
		try {
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public Instances getData(){
		return this.allData;
	}

}
