package processTFIDF;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.filters.unsupervised.instance.SparseToNonSparse;

public class TFIDF {

	public TFIDF() {
	}

	public Instances datosTFIDF(Instances datos) throws Exception {
		StringToWordVector stwv = new StringToWordVector();
		stwv.setIDFTransform(true);
		stwv.setTFTransform(true);
		stwv.setLowerCaseTokens(true);
		stwv.setWordsToKeep(Integer.MAX_VALUE);
		stwv.setInputFormat(datos);
		datos= Filter.useFilter(datos,stwv);
		return noDispersa(datos);
	}

	public Instances noDispersa(Instances datos) throws Exception {
		SparseToNonSparse stnp = new SparseToNonSparse();
		stnp.setInputFormat(datos);
		return Filter.useFilter(datos, stnp);
	}
	
	public Instances quitarClase(Instances datos) throws Exception{
		Remove r= new Remove();
		r.setAttributeIndicesArray(new int[datos.classIndex()]);
		return Filter.useFilter(datos, r);
	}

}
