package preprocess;

import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instances;

public class InstantziaOperazioak {
	private Instances dev;
	private Instances train;
	private Instances test;

	public InstantziaOperazioak(Instances data) {
		dev = new Instances(data);
		dev.delete();
		train = new Instances(dev);
		test = new Instances(dev);
	}

	private int amaieraBilatu(int desplazamendua, ArrayList<String> osoa, int bueltakop, Instances instantziaOsoak) {
		final int lerroaKop = 6;
		for (int i = desplazamendua + ((bueltakop + 1) * lerroaKop); i < osoa.size(); i++) {
			if (osoa.get(i).contains("%BESTE FITXATEGI BAT")) {
				return i - lerroaKop * (bueltakop + 1);
			}
		}
		return instantziaOsoak.numInstances();
	}

	public Instances[] arraiaItzuli() {
		return new Instances[] { this.dev, this.train, this.test };
	}

	public Instances getDev() {
		return dev;
	}

	public Instances getTest() {
		return test;
	}

	public Instances getTrain() {
		return train;
	}

	public void instantziakBanatu(Instances osoa, String pathOsoa) throws IOException {
		FitxategiOperazioak f = new FitxategiOperazioak();
		ArrayList<String> fitxategiOsoa = f.lerroakIrakurri(new String[] { pathOsoa });
		String[] fitxategiak = new String[] { fitxategiOsoa.get(fitxategiOsoa.size() - 4),
				fitxategiOsoa.get(fitxategiOsoa.size() - 3), fitxategiOsoa.get(fitxategiOsoa.size() - 2) };
		int hasiera = 0;
		for (int i = 0; i < 3; i++) {
			int amaiera = this.amaieraBilatu(hasiera, fitxategiOsoa, i, osoa);
			this.instantziakKopiatu(fitxategiak[i], hasiera, amaiera, osoa);
			hasiera = amaiera;
		}
	}

	private void instantziakKopiatu(String path, int hasiera, int amaiera, Instances osoa) {
		for (; hasiera < amaiera; hasiera++) {
			if (path.contains("dev"))
				this.dev.add(osoa.get(hasiera));
			else if (path.contains("train"))
				this.train.add(osoa.get(hasiera));
			else if (path.contains("test_blind"))
				this.test.add(osoa.get(hasiera));
		}
	}

	public void setDev(Instances dev) {
		this.dev = dev;
	}

	public void setInstances(Instances[] lista) {
		this.dev = lista[0];
		this.train = lista[1];
		this.test = lista[2];
	}

	public void setTest(Instances test) {
		this.test = test;
	}

	public void setTrain(Instances train) {
		this.train = train;
	}
}
