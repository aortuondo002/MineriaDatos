package clustering;


import java.util.ArrayList;
import java.util.List;

import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.PrincipalComponents;
import weka.attributeSelection.Ranker;
import weka.core.Instances;


public class KMeans {

   
   private Instances points;
   private List<Cluster> clusters;
   
   public KMeans() {
   	this.clusters = new ArrayList<Cluster>();    	
   }
   
   public static void main(String[] args) throws Exception {
	
   }
   
   
   
   
}
