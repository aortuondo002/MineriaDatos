package processTFIDF;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Main {
	private static Lector lec;
	private static TFIDF tfidf;

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 2; i++) {
			lec = new Lector(args[i]);
		}
		Instances datos = lec.getDatos();
		datos = tfidf.convertirDatos(datos);
		ArffSaver saver = new ArffSaver();
		saver.setInstances(datos);
		saver.setStructure(datos);
		String nombre = args[1];

		if (nombre.contains("dev")) {
			nombre.replace("dev.arff", "trainBOW_FSS_TFIDF.arff");
		} else {
			nombre.replace("train.arff", "trainBOW_FSS_TFIDF.arff");
		}
		saver.setDestination(new File(nombre));
	}

	public Main() {

	}

}
