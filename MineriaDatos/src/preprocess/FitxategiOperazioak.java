package preprocess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;

import weka.core.Instances;

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
		Charset utf8= Charset.forName("UTF-8");
		String s;
		while ((s = br.readLine()) != null) {
			s=utf8.decode(utf8.encode(s)).toString();
			String[] sa = s.split("\t");
			s=s.replaceAll("[^A-Za-z0-9 ]", " ");
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
		if (kargatzeko.contains("dev")){
			return kargatzeko.replace("dev.txt", "datos.arff");
		}
		else {
			return kargatzeko.replace("train.txt", "datos.arff");
		}
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
