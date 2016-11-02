package processTFIDF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class FitxategiOperazioak {

	public FitxategiOperazioak() throws Exception {

	}

	private String fitxategiarenIzenaEraiki(String kargatzeko) {
		return kargatzeko.replace("datos.arff", "Train_BOW_FSS_TFIDF.arff");
	}

	public File fitxategiaSortu(String lekua) throws IOException {
		String lekuaARFF = fitxategiarenIzenaEraiki(lekua);
		File f = new File(lekuaARFF);
		if (!f.exists())
			f.createNewFile();
		else
			f.delete();
		return f;
	}

	public void guardarArchivos(Instances datos, String path) throws IOException {
		ArffSaver saver = new ArffSaver();
		File lekua = fitxategiaSortu(path);
		saver.setDestination(lekua);
		saver.setFile(lekua);
		saver.setInstances(datos);
		saver.writeBatch();
	}

	public Instances leerArchivos(String path) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		Instances data = new Instances(br);
		br.close();
		return data;
	}

}
