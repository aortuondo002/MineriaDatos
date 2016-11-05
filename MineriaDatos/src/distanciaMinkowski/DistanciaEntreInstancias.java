package distanciaMinkowski;

import weka.core.Instance;

public class DistanciaEntreInstancias {

	public DistanciaEntreInstancias() {

	}

	public double calcularDistancia(Instance ins1, Instance ins2, int distancia) {
		double valor = 0;
		double sumatorio = 0;

		for (int i = 0; i < ins1.numAttributes(); i++) {
			
			if (ins1.attribute(i).isNumeric()) {
				double valorIns1=ins1.value(i);
				double valorIns2=ins2.value(i);
				sumatorio = sumatorio + Math.pow(Math.abs(valorIns1-valorIns2), distancia);
			}
		}
		if (distancia==1){
			return sumatorio;
		}else if(distancia==2){
		valor = Math.sqrt(sumatorio);
		}else if(distancia==3){
		valor = Math.cbrt(sumatorio);

		}
		return valor;

	}

	
}
