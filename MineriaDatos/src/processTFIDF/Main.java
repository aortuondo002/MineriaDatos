package processTFIDF;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class Main {
	private static Lector lec;
	private static Instances allData;
	private static TFIDF tfidf;

	public static void main(String[] args) throws Exception {
		String a = args[0];
		String b = args[1];
		System.out.println("leyendo datos");
		Loader arfldr = new Loader(a, b);
		allData = arfldr.getData();
		
		allData = tfidf.convertirDatos(allData);
		
		ArffSaver saver = new ArffSaver();
		saver.setInstances(allData);
		saver.setStructure(allData);
		if (a.contains("dev")){
			a.replace("dev.arff", "trainBOW_FSS_TFIDF.arff");
		} else {
			a.replace("train.arff", "trainBOW_FSS_TFIDF.arff");
		}
		System.out.println(allData.numInstances());
		saver.setFile(new File(a));
		saver.writeBatch();//java.lang.IndexOutOfBoundsException -> no se puede guardar, seguir sin guardarel arff?
		
		
//		for (int i = 0; i < 2; i++) {
//			lec = new Lector(args[i]);
//		}
//		Instances datos = lec.getDatos();
//		datos = tfidf.convertirDatos(datos);
//		ArffSaver saver = new ArffSaver();
//		saver.setInstances(datos);
//		saver.setStructure(datos);
//		String nombre = args[1];
//
//		if (nombre.contains("dev")) {
//			nombre.replace("dev.arff", "trainBOW_FSS_TFIDF.arff");
//		} else {
//			nombre.replace("train.arff", "trainBOW_FSS_TFIDF.arff");
//		}
//		saver.setDestination(new File(nombre));
	}

	public Main() {

	}

}
