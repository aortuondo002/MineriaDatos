package processTFIDF;

import java.util.HashSet;
import java.util.Iterator;

import weka.core.Instance;
import weka.core.Instances;

public class Preprozesatzailea {
	private HashSet<String> daudenHitzEzberdinak;

	public Preprozesatzailea() {
		daudenHitzEzberdinak = new HashSet<>();
	}

	private void hitzakSartu(String esaldia) {
		String[] esaldiaBanatuta = null;
		String[] banatzekoLista = new String[] { " ", "\r", "\n", "\t", ",", ";", ":", "'", "\"", "(", ")", "?", "!",
				"\\", "." };
		for (int i = 1; i < banatzekoLista.length; i++) {
			esaldia = esaldia.replace(banatzekoLista[i], " ");
		}
		esaldiaBanatuta = esaldia.split(" ");
		for (int i = 0; i < esaldiaBanatuta.length; i++) {
			daudenHitzEzberdinak.add(esaldiaBanatuta[i]);
		}
	}

	public int hitzEzberdinKopuruaLortu(Instances data) {
		for (Iterator<Instance> iterator = data.iterator(); iterator.hasNext();)
			this.instantziaEraldatu(iterator.next());
		return daudenHitzEzberdinak.size();
	}

	private void instantziaEraldatu(Instance i) {
		String esaldia = i.toString().toLowerCase();
		if (esaldia.contains("spam"))
			esaldia = esaldia.substring(0, esaldia.length() - 5);
		else if (esaldia.contains("ham"))
			esaldia = esaldia.substring(0, esaldia.length() - 4);
		else
			esaldia = esaldia.substring(0, esaldia.length() - 2);
		this.hitzakSartu(esaldia);
	}

}
