package inicializaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import clustering.Cluster;
import distanciaEntreClusters.CompleteLink;
import distanciaMinkowski.DistanciaEntreInstancias;
import weka.core.DenseInstance;
import weka.core.Instances;

public class Inicializaciones {
	CompleteLink cl = new CompleteLink();

	public List<Cluster> inicializacionA(Instances datos) {
		ArrayList<Cluster> aDevolver = new ArrayList<Cluster>();
		Instances nuevo = new Instances(datos);
		nuevo.delete();
		Random r = new Random();
		// int clusters=r.nextInt(nuevo.numInstances());
		for (int i = 0; i < 5; i++) {
			Cluster c = new Cluster(i, datos);
			DenseInstance centroide = new DenseInstance(nuevo.numAttributes());
			centroide.setDataset(nuevo);
			for (int j = 0; j < centroide.numAttributes(); j++) {

				if (centroide.attribute(j).isNumeric()) {
					centroide.setValue(j, r.nextDouble());
				}
			}
			nuevo.add(centroide);
			c.añadirPuntos(nuevo);
			aDevolver.add(c);

		}
		return aDevolver;

	}

	public List<Cluster> inicializacionC(Instances datos, int kClusters) {
		List<Cluster> clusters = new ArrayList<>();
		DistanciaEntreInstancias minkowski = new DistanciaEntreInstancias();
		DenseInstance instancia1 = new DenseInstance(datos.numAttributes());
		Instances grupo = datos;
		Random r = new Random();
		for (int j = 0; j < (2 * kClusters); j++) {

			for (int i = 0; i < instancia1.numAttributes(); i++) {
				if (instancia1.attribute(i).isNumeric()) {
					instancia1.setValue(i, r.nextDouble());
				}

			}
			Cluster c = new Cluster(j, grupo);
			c.clear();
			c.addCentroide(instancia1);
			clusters.add(c);
		}
		List<Cluster> entregar = new ArrayList<>();

		double dist = 0;
		double resultado = 0;
		for (int i = 0; i < clusters.size(); i++) {
			Cluster c1 = clusters.get(i);
			for (int j = 0; j < clusters.size(); j++) {
				Cluster c2 = clusters.get(j);
				dist = minkowski.calcularDistancia(c1.getCentroide(), c2.getCentroide(), 2);
				if (dist < resultado) {
					entregar.clear();
					entregar.add(c2);
					resultado = dist;
				}
			}
		}
		return entregar;
	}

}
