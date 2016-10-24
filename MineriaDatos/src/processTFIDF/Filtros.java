package processTFIDF;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.PrincipalComponents;
import weka.attributeSelection.Ranker;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.InterquartileRange;
import weka.filters.unsupervised.attribute.Normalize;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.RemoveUseless;
import weka.filters.unsupervised.instance.RemoveWithValues;
import weka.filters.unsupervised.instance.SparseToNonSparse;

public class Filtros {

		private int[] lista;
	
		public Instances PCAAtributteEval(Instances data) throws Exception{
			AttributeSelection as = new AttributeSelection();
			PrincipalComponents pca = new PrincipalComponents();
			Ranker r= new Ranker();
			data.setClassIndex(0);
			r.setNumToSelect(600);
			as.setSearch(r);
			as.setEvaluator(pca);
			lista = as.selectedAttributes();
			return this.quitarAtributos(data);
			
		}
		public Instances quitarAtributos(Instances data) throws Exception{
			Remove r= new Remove();
			r.setAttributeIndicesArray(lista);
			r.setInvertSelection(true);
			r.setInputFormat(data);
			return Filter.useFilter(data, r);
		}
		
		
		public Instances transformacionNonSparse(Instances data) throws Exception{
			SparseToNonSparse stns = new SparseToNonSparse();
			stns.setInputFormat(data);
			return Filter.useFilter(data, stns);
		}
		
		public Instances normalizar(Instances data) throws Exception{
			Normalize norm=new Normalize();
			norm.setInputFormat(data);
			return Filter.useFilter(data, norm);
		}
		
		public Instances quitarInutiles (Instances data) throws Exception{
			RemoveUseless ru= new RemoveUseless();
			ru.setInputFormat(data);
			return Filter.useFilter(data, ru);			
		}
		
		public Instances rangoInterquartil(Instances data) throws Exception{
			InterquartileRange ir = new InterquartileRange();
			ir.setInputFormat(data);
			return Filter.useFilter(data, ir);
		}
		public Instances quitarValoresExtraños(Instances data) throws Exception{
			RemoveWithValues rmv = new RemoveWithValues();
			rmv.setInputFormat(data);
			String[] aukerak = new String[] { "-C", String.valueOf(data.numAttributes() - 1), "-L", "2" };
			rmv.setOptions(aukerak);
			return Filter.useFilter(data, rmv);
		}
		public Instances quitarValoresdeIR(Instances data) throws Exception{
			Remove rm= new Remove();
			rm.setInputFormat(data);
			rm.setAttributeIndices(
					new String(String.valueOf(data.numAttributes() - 1) + "," + String.valueOf(data.numAttributes() - 2)));
			data = Filter.useFilter(data, rm);
			return data;
		}
		
		
}
