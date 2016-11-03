package clustering;

import java.util.HashMap;

import distanciaMinkowski.DistanciaEntreInstancias;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Cluster {

	public Instance centroide;
	public Instance antiguoCentroide;
	public int id;
	public Instances points;
	public HashMap<String, Double> dist;

	// Creates a new Cluster
	public Cluster(int id) {
		this.id=id;
		
	}
	public void addCentroide(Instance ins){
		this.centroide=ins;
	}
	
	public Instance getCentroide(){
		return centroide;
	}
	
	public void addPoint(Instance ins) {
		points.add(ins);
	}

	public void CalcularCentroide() {
		Instances grupo = new Instances(points);
		grupo.delete();
		DenseInstance centro = new DenseInstance(points.numAttributes());
		grupo.add(centro);
		for (int i = 0; i < points.size(); i++) {
			if (points.attribute(i).isNumeric()) {
				double dist = 0;
				for (int j = 0; j < points.size(); j++) {
					dist += points.get(j).value(i);
				}
				dist = dist / points.size();
				centro.setValue(i, dist);
			} else {
				centro.setValue(i, points.get(0).stringValue(i));
				;
			}

		}
		this.antiguoCentroide=this.centroide;
		this.centroide=centro;
	}
	
	
	public void clear() {
		this.points.delete();
	}

	public int getId() {
		return id;
	}

	public Instances getPoints() {
		return points;
	}
	public double CalcularDisimilitud(){
		DistanciaEntreInstancias mink=new DistanciaEntreInstancias();
		return mink.calcularDistancia(centroide, antiguoCentroide, 2);
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