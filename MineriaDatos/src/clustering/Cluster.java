package clustering;

import java.util.HashMap;

import distanciaMinkowski.DistanciaEntreInstancias;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Cluster {

	public Instance antiguoCentroide;
	public Instance centroide;
	public HashMap<String, Double> dist;
	public int id;
	public Instances points;

	// Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
	}

	public Cluster(int id, Instances datos) {
		this.id = id;
		this.points = datos;
	}

	public void addCentroide(Instance ins) {
		this.centroide = ins;
	}

	public void addPoint(Instance ins) {
		this.points.add(ins);
	}

	public void añadirPuntos(Instances puntos) {
		this.points = puntos;
	}

	public void CalcularCentroide() {
		Instance nuevoCentroide = new DenseInstance(getPoints().numAttributes());
		nuevoCentroide.setDataset(getPoints());

		if (this.centroide == null) {
			double distancia = 0;
			for (int i = 0; i < getPoints().numAttributes(); i++) {
				if (points.attribute(i).isNumeric()) {
					distancia = 0;
					for (int j = 0; j < getPoints().numInstances(); j++) {
						distancia += getPoints().get(j).value(i);
					}
				}
				int puntos = this.points.numInstances();
				distancia = distancia / puntos;
				nuevoCentroide.setValue(i, distancia);
				addCentroide(nuevoCentroide);
			}
		}
		this.antiguoCentroide=nuevoCentroide;
		if(this)
	}

	public double CalcularDisimilitud() {
		DistanciaEntreInstancias mink = new DistanciaEntreInstancias();
		return mink.calcularDistancia(centroide, antiguoCentroide, 2);
	}

	public void clear() {
		this.points.delete();
	}

	public Instance getCentroide() {
		return centroide;
	}

	public int getId() {
		return id;
	}

	public Instances getPoints() {
		return points;
	}

	public void imprimirCluster() {
		System.out.println("[Cluster: " + id + "]");
		System.out.println("[Points: \n");
		for (Instance p : points) {
			System.out.println(p);
		}
		System.out.println("]");
	}

}