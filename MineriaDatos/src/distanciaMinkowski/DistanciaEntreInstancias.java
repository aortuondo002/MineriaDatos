package distanciaMinkowski;

import weka.core.Instance;

public class DistanciaEntreInstancias {

	public DistanciaEntreInstancias(){
		
	}
	
	public double calcularDistancia(Instance ins1,Instance ins2, int distancia){
		
		double valor = 0;
		double sumatorio = 0;
		
		for (int i = 0; i < ins1.numAttributes(); i++) {
			if(ins1.attribute(i).isNumeric()){
			sumatorio = sumatorio + (Math.pow(ins1.value(i) - ins2.value(i), distancia));
			}
		}
    	valor=Math.pow(sumatorio, 1/distancia);
    	return valor;
    
	}
}
