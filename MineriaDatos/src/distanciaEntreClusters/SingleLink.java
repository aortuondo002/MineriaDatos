package distanciaEntreClusters;

import clustering.Cluster;
import distanciaMinkowski.DistanciaEntreInstancias;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class SingleLink {
	public DistanciaEntreInstancias d1 = new DistanciaEntreInstancias();

	public double calcularSingleLink(Cluster c1, Cluster c2, int distancia) {
		Instances grupo = new Instances(c1.getPoints());
		grupo.delete();
		Instance instancia1 = new DenseInstance(c1.getPoints().numAttributes());
		Instance instancia2 = new DenseInstance(c1.getPoints().numAttributes());
		grupo.add(instancia1);
		grupo.add(instancia2);

		double min = Double.MAX_VALUE;
		double actual = 0;
		double resultado = 0;
		for (int i = 0; i < c1.getPoints().size(); i++) {
			instancia1 = c1.getPoints().get(i);
			for (int j = 0; j < c2.getPoints().size(); j++) {
				instancia2 = c2.getPoints().get(j);
				actual = d1.calcularDistancia(instancia1, instancia2, distancia);
				if (actual < min) {
					resultado = actual;

				}
			}
		}
		return resultado;
	}
	
	
}
