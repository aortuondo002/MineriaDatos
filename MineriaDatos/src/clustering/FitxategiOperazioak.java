package clustering;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.core.Instances;

public class FitxategiOperazioak {
	
	public Instances leerArchivos(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		Instances data = new Instances(br);
		br.close();
		return data;
}

}
