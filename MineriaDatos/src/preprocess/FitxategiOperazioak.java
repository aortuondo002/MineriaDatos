package preprocess;
//----------------------------------------------------------
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

public class FitxategiOperazioak {

	private ArrayList<String> burukoakKendu(ArrayList<String> osoa, String[] pathLista) {
		for (int i = 6; i < osoa.size(); i++) {
			if (osoa.get(i).startsWith("@")) {
				osoa.set(i, "%BESTE FITXATEGI BAT");
			}
		}
		for (int i = 0; i < pathLista.length; i++) {
			osoa.add("%" + pathLista[i]);
		}
		return osoa;
	}

	public void fitxategiBerriakGorde(String[] path, InstantziaOperazioak iOp, String ipintzeko) throws IOException {
		System.out.println();
		for (int i = 0; i < path.length; i++) {
			if (path[i].contains("test")) {
				this.oraingoFitxategiaGorde(iOp.getTest(), this.pathEraldatu(path[i], "test_blind_" + ipintzeko));
			} else if (path[i].contains("dev")) {
				this.oraingoFitxategiaGorde(iOp.getDev(), this.pathEraldatu(path[i], "dev_" + ipintzeko));
			} else {
				this.oraingoFitxategiaGorde(iOp.getTrain(), this.pathEraldatu(path[i], "train_" + ipintzeko));
			}
		}

	}

	public String fitxategiOsoaGorde(ArrayList<String> osoa, String path) throws IOException {
		String pathBerria = this.pathEraldatu(path, "osoa");
		System.out.println("Fitxategi osoa gordeko den path:\n" + pathBerria);
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(pathBerria)));
		for (Iterator<String> iterator = osoa.iterator(); iterator.hasNext();) {
			bw.write(iterator.next());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		return pathBerria;
	}

	public Instances instantziakIrakurri(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		Instances data = new Instances(br);
		br.close();
		return data;
	}

	public ArrayList<String> lerroakIrakurri(String[] pathLista) throws IOException {
		ArrayList<String> osoa = new ArrayList<>();
		BufferedReader br = null;
		String lerroa = "";
		for (int i = 0; i < pathLista.length; i++) {
			br = new BufferedReader(new FileReader(new File(pathLista[i])));
			while ((lerroa = br.readLine()) != null)
				osoa.add(lerroa);
		}
		br.close();
		return this.burukoakKendu(osoa, pathLista);
	}

	private void oraingoFitxategiaGorde(Instances oraingoa, String path) throws IOException {
		ArffSaver gorde = new ArffSaver();
		File f = new File(path);
		System.out.println("Fitxategia gordeko den path-a: " + path);
		gorde.setDestination(f);
		gorde.setFile(f);
		gorde.setInstances(oraingoa);
		gorde.writeBatch();
	}

	private String pathEraldatu(String path, String ipintzeko) {
		if (path.contains("test"))
			path = path.replace("test_blind", ipintzeko);
		else if (path.contains("dev"))
			path = path.replace("dev", ipintzeko);
		else
			path = path.replace("train", ipintzeko);
		return path;
	}
}
