package processTFIDF;

import weka.core.Instances;
import weka.filters.Filter;
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
		stwv.setInputFormat(datos);
		// datos= noDispersa(datos);
return Filter.useFilter(datos, stwv);
	}

	public Instances noDispersa(Instances datos) throws Exception {
		SparseToNonSparse stnp = new SparseToNonSparse();
		stnp.setInputFormat(datos);
		return Filter.useFilter(datos, stnp);
	}

}
