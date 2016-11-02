package inicializaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import clustering.Cluster;
import distanciaEntreClusters.CompleteLink;
import weka.core.DenseInstance;
import weka.core.Instances;

public class Inicializaciones {
	CompleteLink cl = new CompleteLink();

	public List<Cluster> inicializacionA(Instances datos) {
		List<Cluster> clusters = new ArrayList<>();
		Instances grupo = new Instances(datos);
		grupo.delete();
		DenseInstance instancia1 = new DenseInstance(datos.numAttributes());
		grupo.add(instancia1);
		Random r = new Random();
		int numeroClusters = r.nextInt(datos.numInstances());
		for (int j = 0; j < numeroClusters; j++) {

			for (int i = 0; i < instancia1.numAttributes(); i++) {
				if (instancia1.attribute(i).isNumeric()) {
					instancia1.setValue(i, r.nextDouble());
				}

			}
			Cluster c = new Cluster(j, grupo);
			clusters.add(c);
		}
		return clusters;

	}

	public List<Cluster> inicializacionC(Instances datos, int kClusters) {
		List<Cluster> clusters = new ArrayList<>();
		Instances grupo = new Instances(datos);
		grupo.delete();
		DenseInstance instancia1 = new DenseInstance(datos.numAttributes());
		grupo.add(instancia1);
		Random r = new Random();
		for (int j = 0; j < (2 * kClusters); j++) {
			
			for (int i = 0; i < instancia1.numAttributes(); i++) {
				if (instancia1.attribute(i).isNumeric()) {
					instancia1.setValue(i, r.nextDouble());
				}

			}
			Cluster c = new Cluster(j, grupo);
			clusters.add(c);
		}
		List<Cluster> entregar = new ArrayList<>();

		double dist = 0;
		double resultado = 0;
		for (int i = 0; i < clusters.size(); i++) {
			Cluster c1 = clusters.get(i);
			for (int j = 0; j < clusters.size(); j++) {
				Cluster c2 = clusters.get(j);
				dist = cl.calcularCompleteLink(c1, c2, 2);
				if (dist < resultado) {
					entregar.clear();
					entregar.add(c1);
					entregar.add(c2);
					resultado = dist;
				}
			}
		}
		return entregar;
	}

}
