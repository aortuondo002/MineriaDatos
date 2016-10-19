package preprocess;

import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instances;

public class Nagusia {
	public static void main(String[] args) throws Exception {
		Nagusia nireNagusia = new Nagusia(args);
		if (args.length != 3)
			nireNagusia.erroreMezuaInprimatu();
		nireNagusia.hasieratu();
	}

	private FitxategiOperazioak o;
	private Preprozesatzailea pr;
	private Prozesatzailea p;
	private InstantziaOperazioak i;
	private String[] argumentuak;
	private Instances data;

	public Nagusia(String[] args) {
		o = new FitxategiOperazioak();
		pr = new Preprozesatzailea();
		p = new Prozesatzailea();
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
		System.out.println("\nStringToWordVector aplikatu.");
		data = p.stringToWordVectorAplikatu(data, kop, false);
		System.out.println("Matrize dispertsoa kendu.");
		data = p.sparseToNonSparzeAplikatu(data);
		System.out.println("Atributu kopurua StringToWordVector aplikatu eta gero: " + data.numAttributes());
		this.instantziakBanatu(fitxategiOsoaPath);
		i.setDev(p.atributuHautaketa(i.getDev()));
		i.setTrain(p.atributuHautaketa(i.getTrain()));
		System.out.println("\nDev multzoaren atributu kopurua atributu hautaketa aplikatu eta gero: "
				+ i.getDev().numAttributes());
		System.out.println("Train multzoaren atributu  kopurua atributu hautaketa aplikatu eta gero: "
				+ i.getTrain().numAttributes());
		Instances[] lista = i.arraiaItzuli();
		for (int i = 0; i < lista.length; i++) {
			lista[i] = p.stringToWordVectorAplikatu(lista[i], kop, true);
			lista[i] = p.sparseToNonSparzeAplikatu(lista[i]);
		}
		i.setInstances(lista);
		i.setTest(p.atributuakKendu(i.getTest()));
		System.out.println("Test_blind multzoaren atributu kopurua Remove aplikatu eta gero: " + i.getTest().numAttributes());
		o.fitxategiBerriakGorde(argumentuak, i, "TFIDF");
	}

	private void instantziakBanatu(String path) throws IOException {
		i = new InstantziaOperazioak(data);
		i.instantziakBanatu(data, path);
		System.out.println("Dev multzoaren instanzia kopurua: " + i.getDev().numInstances());
		System.out.println("Train multzoaren instanzia kopurua: " + i.getTrain().numInstances());
		System.out.println("Test multzoaren instanzia kopurua: " + i.getTest().numInstances());
		o.fitxategiBerriakGorde(argumentuak, i, "BOW");
	}

	private void instantziaOsoakDeskribatu() {
		System.out.println("\nInstantzien Laburpena:");
		System.out.println(data.toSummaryString());
		System.out.println("Instantzi kopurua: " + data.numInstances());
		System.out.println("Atributu kopurua: " + data.numAttributes());
	}

}
