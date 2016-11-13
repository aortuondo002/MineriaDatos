package clustering;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import distanciaEntreClusters.CompleteLink;
import distanciaEntreClusters.SingleLink;
import distanciaMinkowski.DistanciaEntreInstancias;
import inicializaciones.Inicializaciones;
import weka.core.DenseInstance;
import weka.core.Instances;

public class KMeans {
	private static ClusterOperazioak co = new ClusterOperazioak();
	private static CompleteLink completeLink = new CompleteLink();
	private static DistanciaEntreInstancias distanciaMinkowski = new DistanciaEntreInstancias();
	private static FitxategiOperazioak f = new FitxategiOperazioak();
	private static Inicializaciones init = new Inicializaciones();
	private static SingleLink singleLink = new SingleLink();
	private static Silhouette s= new Silhouette();

	public static void main(String[] args) throws Exception {
		List<Cluster> clusters = new ArrayList<Cluster>();

		Instances datos = f.leerArchivos(args[0]);
		int i = 1;
		String inicializacion = args[i];
		int kClusters = 1;

		if (inicializacion.equals("C")) {
			i++;
			kClusters = Integer.valueOf(args[i]);
		}
		i++;
		int minkowski = Integer.valueOf(args[i]);
		i++;
		String single_complete = args[i].toLowerCase();
		i++;
		String convergencia = args[i];
		i++;
		String constante_umbral = args[i];
		clusters = new ArrayList<Cluster>();

		switch (inicializacion) {
		case "A":
			
			Random r = new Random();
			
			for (int a = 0; a < r.nextInt(20); a++) {
				Instances nuevo = new Instances(datos);
				Cluster c = new Cluster(a, datos);
				DenseInstance centroide = new DenseInstance(nuevo.numAttributes());
				centroide.setDataset(nuevo);
				int j=0;
				for (j = 0; j < centroide.numAttributes(); j++) {

					if (centroide.attribute(j).isNumeric()) {
						centroide.setValue(j, r.nextDouble());
					}
				}
				nuevo.add(centroide);
				c.añadirPuntos(nuevo);
				c.CalcularCentroide();
				c.clear();
				clusters.add(c);
				

			}
			break;

		case "B":
			System.out.println("NO IMPLEMENTADO");
			System.exit(-1);
			break;

		case "C":
			clusters = init.inicializacionC(datos, kClusters);
			break;
		}

		

		switch (convergencia) {
		case "iteraciones":

			for (int j = 0; j < Integer.valueOf(constante_umbral); j++) {
				
				co.vaciarTodo(clusters);
				co.meterInstanciasEnClusters(clusters, datos, minkowski);
				co.reCalcularCentroides(clusters);
				co.imprimirClusters(clusters);
			}
			
	

			break;

		case "disimilitud":
			
			while(co.calcularDisimilitudMedia(clusters)>Double.valueOf(constante_umbral)){
				co.vaciarTodo(clusters);
				co.meterInstanciasEnClusters(clusters, datos, minkowski);
				co.reCalcularCentroides(clusters);
				co.imprimirClusters(clusters);
			}
			
			break;
			
			
		}

	}

}
