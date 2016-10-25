package processTFIDF;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;

public class Loader {
	
	private String path1;
	private String path2;
	private Instances allData;
	
	public Loader(String path1, String path2){
		this.path1 = path1;
		this.path2 = path2;
		allData = null;
		start(path1, path2);
	}

	private void start(String path1, String path2) {
		System.out.println("Leyendo a");
		Instances a = load(path1);
		System.out.println("Leyendo b");
		Instances b = load(path2);
		System.out.println("juntando instancias");
		allData = new Instances(a);
		allData.addAll(b);
		System.out.println("Instancias juntadas");
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
