package clustering;

import java.util.List;

import distanciaMinkowski.DistanciaEntreInstancias;
import weka.core.Instance;
import weka.core.Instances;

public class ClusterOperazioak {

	public void calcularCentroides(List<Cluster> clusters) {
		for (int i = 0; i < clusters.size(); i++) {
			clusters.get(i).CalcularCentroide();
		}
	}

	public double calcularDisimilitudMedia(List<Cluster> clusters) {
		double resultado = 0;
		for (int i = 0; i < clusters.size(); i++) {
			resultado += clusters.get(i).CalcularDisimilitud();
		}
		resultado = resultado / clusters.size();
		return resultado;
	}

	public void imprimirClusters(List<Cluster> clusters) {
		for (int i = 0; i < clusters.size(); i++) {
			System.out.println(clusters.get(i).getId() + " = " + clusters.get(i).getPoints().numInstances());
		}
	}

	public void meterInstanciasEnClusters(List<Cluster> clusters, Instances datos, int minkowski) {
		DistanciaEntreInstancias d = new DistanciaEntreInstancias();
		double distanciaCentroideMinima = Double.MAX_VALUE;
		Cluster c = new Cluster(0, datos);

		for (int i = 0; i < datos.numInstances(); i++) {
			Instance actual = datos.get(i);

			for (int j = 0; j < clusters.size(); j++) {
			
				double distanciaCentroideActual = d.calcularDistancia(actual, clusters.get(j).centroide, minkowski);

				if (distanciaCentroideActual < distanciaCentroideMinima) {
					distanciaCentroideMinima = distanciaCentroideActual;
					c = clusters.get(j);
				}
			}

			c.addPoint(actual);
		}

	}

	public void vaciarTodo(List<Cluster> clusters) {
		for (int i = 0; i < clusters.size(); i++) {
			clusters.get(i).clear();
		}
	}

}
