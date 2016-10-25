package processTFIDF;

import weka.core.Instances;
import weka.core.tokenizers.WordTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.filters.unsupervised.instance.SparseToNonSparse;

public class TFIDF {

	public Instances datos;

	public TFIDF() {
	}

	public Instances convertirDatos(Instances Datos) throws Exception {
		return datosTFIDF(this.datos);
	}

	public Instances datosTFIDF(Instances datos) throws Exception {
		StringToWordVector stwv = new StringToWordVector();
		stwv.setIDFTransform(true);
		stwv.setTFTransform(true);
		stwv.setLowerCaseTokens(true);
		stwv.setAttributeIndicesArray(new int[] { 0 });
		WordTokenizer token = new WordTokenizer();
		String delimitiers = " \r\t\n.,;:\'\"()-><#&*+/^_=[]{}|`~";
		token.setDelimiters(delimitiers);
		stwv.setWordsToKeep(2000);
//		datos= noDispersa(datos);
		return Filter.useFilter(datos, stwv);
	}

	public Instances noDispersa(Instances datos) throws Exception {
		SparseToNonSparse stnp = new SparseToNonSparse();
		stnp.setInputFormat(datos);
		return Filter.useFilter(datos, stnp);
	}

}
