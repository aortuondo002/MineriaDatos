package preprocess;

import java.io.IOException;
import java.util.ArrayList;

public class Nagusia {
	private static final String IZENBURUA = "SpamConvert - 1.0.0";

	private static void laguntzaInprimatu() {
		System.err.println("Programak funtzionatzeko argumentuetan txt fitxategiaren lekua jaso behar du!");
		System.out.println("Adibidez: java -jar /path/to/spam.jar /path/test.txt");
		System.out.println(
				"Programari /path/test.txt fitxategia ematean, arff fitxategia /path/test.arff izenarekin gordeko du.");
		System.exit(1);
	}

	public static void main(String[] args) throws IOException {
		System.out.println(IZENBURUA);
		if (args.length < 1)
			Nagusia.laguntzaInprimatu();
		for (int i = 0; i < args.length; i++) {
			fitxategiaGorde(args[i]);
		}
	}

	private static void fitxategiaGorde(String fitxategia) throws IOException {
		FitxategiOperazioak f = new FitxategiOperazioak();
		ArrayList<String[]> array = f.datuakIrakurri(fitxategia);
		System.out.println("Emandako fitxategia " + fitxategia + " da, eta honek " + array.size() + " tupla ditu");
		f.arffIdatzi(f.fitxategiaSortu(fitxategia), array);
	}

}