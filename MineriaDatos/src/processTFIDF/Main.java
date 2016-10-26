package processTFIDF;


import weka.core.Instances;

public class Main {
	private static FitxategiOperazioak f;
	private static TFIDF tfidf;

	public static void main(String[] args) throws Exception {
		f=new FitxategiOperazioak();
		tfidf=new TFIDF();
		Instances datos=f.leerArchivos(args[0]);
		
		Instances stwv = tfidf.datosTFIDF(datos);
		System.out.println(stwv.toSummaryString());
		f.guardarArchivos(stwv, args[0]);
	}

	public Main() {

	}

}
