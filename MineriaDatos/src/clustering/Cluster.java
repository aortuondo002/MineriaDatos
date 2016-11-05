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
		Instances nuevo = new Instances(getPoints());
		nuevo.delete();
		DenseInstance centro = new DenseInstance(points.numAttributes());
		nuevo.add(centro);
		centro.setDataset(nuevo);
		
		if(points.numInstances()!=0){
		for (int i = 0; i < points.numAttributes(); i++) {
			if (points.attribute(i).isNumeric()) {
				double dist = 0;
				for (int j = 0; j < points.size(); j++) {
					dist += points.get(j).value(i);
				}
				dist = dist / points.size();
				centro.setValue(i, dist);
			} else {
				centro.setValue(i, points.get(0).stringValue(i));

			}

		}
		}
		if (!(this.centroide == null)) {
			this.antiguoCentroide = this.centroide;
		}

		this.centroide = centro;
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