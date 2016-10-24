package processTFIDF;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class TFIDF {

	public Instances datos;
	
	public TFIDF(){}
	
	public Instances datosTFIDF(Instances datos) throws Exception{
		StringToWordVector stwv=new StringToWordVector();
		stwv.setIDFTransform(true);
		stwv.setTFTransform(true);
		stwv.setAttributeIndicesArray(new int[] { 0 });
		stwv.setLowerCaseTokens(true);
		return Filter.useFilter(datos, stwv);
	}
	
	public Instances convertirDatos(Instances Datos) throws Exception{
		return datosTFIDF(this.datos);
	}
	
	
}
