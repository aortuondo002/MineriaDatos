package preprocess;

import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instances;

public class Nagusia {
	public static void main(String[] args) throws Exception {
		Nagusia nireNagusia = new Nagusia(args);
		if (args.length != 1)
			nireNagusia.erroreMezuaInprimatu();
		nireNagusia.hasieratu();
	}

	private FitxategiOperazioak o;
	private Preprozesatzailea pr;
	private InstantziaOperazioak i;
	private String[] argumentuak;
	private Instances data;

	public Nagusia(String[] args) {
		o = new FitxategiOperazioak();
		pr = new Preprozesatzailea();
		this.argumentuak = args;
	}

	private void erroreMezuaInprimatu() {
		System.err.println(
				"Programak funtzionatzeko ARFF fitxategiaren path-ak behar ditu (dev, test, train; edozein ordenetan)");
		System.exit(1);
	}

	private void hasieratu() throws Exception {
		System.out.println("Kargatutako fitxategiak:");
		for (int i = 0; i < argumentuak.length; i++)
			System.out.println(argumentuak[i]);
		System.out.println();
		ArrayList<String> osoa = o.lerroakIrakurri(argumentuak);
		String fitxategiOsoaPath = o.fitxategiOsoaGorde(osoa, argumentuak[0]);
		data = o.instantziakIrakurri(fitxategiOsoaPath);
		this.instantziaOsoakDeskribatu();
		int kop = pr.hitzEzberdinKopuruaLortu(data);
		System.out.println("Hitz ezberdin kopurua: " + kop);
		
	}

	private void instantziaOsoakDeskribatu() {
		System.out.println("\nInstantzien Laburpena:");
		System.out.println(data.toSummaryString());
		System.out.println("Instantzi kopurua: " + data.numInstances());
		System.out.println("Atributu kopurua: " + data.numAttributes());
	}

}
