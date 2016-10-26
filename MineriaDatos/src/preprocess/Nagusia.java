package preprocess;

import java.io.IOException;
import java.util.ArrayList;

public class Nagusia {

	private static void fitxategiaGorde(String[] fitxategia) throws IOException {
		FitxategiOperazioak f = new FitxategiOperazioak();
		ArrayList<String[]> array= new ArrayList<String[]>();
			for(int i=0;i<2;i++){
				array.addAll(f.datuakIrakurri(fitxategia[i]));
		}
		f.arffIdatzi(f.fitxategiaSortu(fitxategia[1]), array);
		System.out.println("Emandako fitxategiak " + array.size() + " tupla ditu");
	}

	private static void laguntzaInprimatu() {
		System.err.println("Programak funtzionatzeko argumentuetan txt fitxategiaren lekua jaso behar du!");
		System.out.println("Adibidez: java -jar /path/to/spam.jar /path/test.txt");
		System.out.println(
				"Programari /path/test.txt fitxategia ematean, arff fitxategia /path/test.arff izenarekin gordeko du.");
		System.exit(1);
	}

	public static void main(String[] args) throws IOException {

		if (args.length < 1)
			Nagusia.laguntzaInprimatu();
			fitxategiaGorde(args);
	}

}