package clustering;

import java.util.ArrayList;
import java.util.List;

import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
 
public class Cluster {
	
	public Instances points;
	public Instance centroide;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id,Instances grupo){
		this.points=grupo;
		this.centroide=CalcularCentroide();
	}
 
	public Instances getPoints() {
		return points;
	}
	
	public void addPoint(Instance ins) {
		points.add(ins);
	}
 
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		points.clear();
	}
	
	public DenseInstance CalcularCentroide() {
		Instances grupo= new Instances(points);
		grupo.delete();
		DenseInstance centroide = new DenseInstance(points.numAttributes());
		grupo.add(centroide);
		for (int i = 0; i < points.size(); i++) {
			if (points.attribute(i).isNumeric()) {
				double dist=0;
				for (int j = 0; j < points.size(); j++) {
					dist += points.get(j).value(i);
				}
				dist=dist/points.size();
				centroide.setValue(i, dist);
			}else{
				centroide.setValue(i, points.get(0).stringValue(i));;
			}
			
		}
		return centroide;
	}
	 

	public void imprimirCluster() {
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Points: \n");
		for(Instance p : points) {
			System.out.println(p);
		}
		System.out.println("]");
	}
 
}