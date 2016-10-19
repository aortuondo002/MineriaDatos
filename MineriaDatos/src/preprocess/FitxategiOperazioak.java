package preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class FitxategiOperazioak {

	public void arffIdatzi(String lekua, ArrayList<String[]> array) throws IOException {
		FileWriter fw = new FileWriter(lekua);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("@RELATION spam\n\n");
		bw.write("@ATTRIBUTE text STRING\n");
		bw.write("@ATTRIBUTE klasea {ham,spam}\n\n");
		bw.write("@DATA\n");
		Iterator<String[]> i = array.iterator();
		while (i.hasNext()) {
			String[] arrayberria = i.next();
			bw.write("'" + arrayberria[1].replace('\'', '-') + "'" + "," + arrayberria[0] + "\n");
		}
		bw.flush();
		bw.close();
		System.out.println("Arff-a " + lekua + " fitxategian gorde da");
	}

	public ArrayList<String[]> datuakIrakurri(String fitxategia) throws IOException {
		FileReader fr = new FileReader(fitxategia);
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String[]> array = new ArrayList<>();
		String s;
		while ((s = br.readLine()) != null) {
			String[] sa = s.split("\t");
			if (sa[0].equalsIgnoreCase("ham")) {
				array.add(new String[] { sa[0], s.substring(4, s.length()) });
			} else if (sa[0].equalsIgnoreCase("spam")) {
				array.add(new String[] { sa[0], s.substring(5, s.length()) });
			} else {
				array.add(new String[] { "?", s });
			}
		}
		br.close();
		return array;
	}

	private String fitxategiarenIzenaEraiki(String kargatzeko) {
		return kargatzeko.substring(0, kargatzeko.length() - 4) + ".arff";
	}

	public String fitxategiaSortu(String lekua) throws IOException {
		String lekuaARFF = fitxategiarenIzenaEraiki(lekua);
		File f = new File(lekuaARFF);
		if (!f.exists())
			f.createNewFile();
		else
			f.delete();
		return lekuaARFF;
	}
}
