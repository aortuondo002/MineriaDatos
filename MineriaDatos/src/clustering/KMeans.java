package clustering;

import java.util.ArrayList;
import java.util.List;

import distanciaEntreClusters.CompleteLink;
import distanciaEntreClusters.SingleLink;
import distanciaMinkowski.DistanciaEntreInstancias;
import inicializaciones.Inicializaciones;
import weka.core.Instances;

public class KMeans {
	private static List<Cluster> clusters;
	private static FitxategiOperazioak f = new FitxategiOperazioak();
	private static CompleteLink completeLink= new CompleteLink();
	private static SingleLink singleLink= new SingleLink();
	private static DistanciaEntreInstancias distanciaMinkowski= new DistanciaEntreInstancias();
	private static Inicializaciones init= new Inicializaciones();
	
	public static void main(String[] args) throws Exception {
		
		
		Instances datos= f.leerArchivos(args[0]);
		int i=1;
		String inicializacion= args[i];
		int kClusters=1;

		if(inicializacion.equals("C")){
			i++;
			kClusters=Integer.valueOf(args[i]);
		}
		i++;
		int minkowski=Integer.valueOf(args[i]);
		i++;
		String single_complete= args[i].toLowerCase();
		i++;
		String convergencia= args[i];
		i++;
		String constante_umbral=args[i];
		
		
		
		switch (inicializacion) {
		case "A":
			clusters=init.inicializacionA(datos);
			break;

		case "B":
			System.out.println("NO IMPLEMENTADO");
			System.exit(-1);
			break;
				
		case "C":
			clusters=init.inicializacionC(datos, kClusters);
			break;
		}
		
		switch (single_complete) {
		case "single":
			
			break;

		case "complete":
			break;
		}
		
		
		
	}

	

}
