package clustering;

import java.util.HashMap;

import distanciaMinkowski.DistanciaEntreInstancias;
import weka.classifiers.rules.JRip.NumericAntd;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Cluster {

	public Instance antiguoCentroide;
	public Instance centroide;
	public int id;
	public Instances points;
	public DistanciaEntreInstancias d;

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

	public void aņadirPuntos(Instances puntos) {
		this.points = puntos;
	}

	public void CalcularCentroide() {
		
			Instance nuevoCentroide = new DenseInstance(this.points.numAttributes());
			nuevoCentroide.setDataset(this.points);
			double distancia = 0;
			for (int i = 0; i < this.points.numAttributes(); i++) {
				if (points.attribute(i).isNumeric()) {
					distancia = 0;
					for (int j = 0; j < this.points.numInstances(); j++) {
						distancia += this.points.get(j).value(i);
					}
				}
				int puntos = this.points.numInstances();
				distancia = distancia / puntos;
				nuevoCentroide.setValue(i, distancia);
			}
			addCentroide(nuevoCentroide);
	}
	
	public void reCalcularCentroide(){
		this.antiguoCentroide=this.centroide;
		if(this.points.numInstances()>1){
		Instance nuevoCentroide = new DenseInstance(this.points.numAttributes());
		nuevoCentroide.setDataset(this.points);
		double distancia = 0;
		for (int i = 0; i < this.points.numAttributes(); i++) {
			if (points.attribute(i).isNumeric()) {
				distancia = 0;
				for (int j = 0; j < this.points.numInstances(); j++) {
					distancia += this.points.get(j).value(i);
				}
			}
			int puntos = this.points.numInstances();
			distancia = distancia / puntos;
			nuevoCentroide.setValue(i, distancia);
		}
		addCentroide(nuevoCentroide);
		}
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