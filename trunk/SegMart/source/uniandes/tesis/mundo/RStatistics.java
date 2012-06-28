/**
 * 
 */
package uniandes.tesis.mundo;

import java.io.File;
import java.io.FileInputStream;

import rcaller.RCaller;
import rcaller.RCode;

public class RStatistics {

		public static void main(String[] args){
	        new RStatistics();
	    }
	    
	    /*
	     * Test for simple plots.
	     * This class simply plots a time series array using plot.ts()
	     * function of R.
	     */
	    public RStatistics(){
	        try{
	            RCaller caller = new RCaller();
	            caller.setRscriptExecutable("C:/Program Files/R/R-2.15.1/bin/x64/Rscript");
	            
	            
	            
	            
	            RCode code = new RCode();
	            code.clear();
	            
	            code.addRCode("datos = read.csv(\"C:/Users/Cami/Google Drive/Dropbox/Dropbox/Andes/2012/Tesis/prueba2.csv\")");
	            code.addRCode("clust <- mClust(datos[-,1])");
	            code.addRCode("datos$clust<- clust$classification");
	            code.addRCode("segmentos <- split(datos,datos$clust");
	            // Leer numero de clusters para segmentos
	            
	            
	            code.addRCode("datos2 = read.csv(\"C:/Users/Cami/Google Drive/Dropbox/Dropbox/Andes/2012/Tesis/prueba.csv\")");
	            String y = "Y <- cbind(";
	            for(int i = 1; i<4;i++){
	            	if(i==1)
	            	y = y + "datos[," + i+ "]";
	            	else
	            	y = y + ",datos[," + i+ "]";
	            }
	            y = y + ")";
	            code.addRCode(y);
	            
	            String x = "";
	            for(int i = 1; i<4;i++){
	            	if(i==1)
		            	x = x + "datos2[," + i+ "]";
		            	else
		            	x = x + "+datos2[," + i+ "]";
	            }
	            code.addRCode("man <- manova( y ~ " + x + ")");
	           
	            caller.setRCode(code);
	            caller.runOnly();
	            
	            
	            System.out.println(caller.getParser().getNames());
	            /*
	             * Retrieving the 'mean' element of list 'my.all'
	             */
	            
	            
	            
	            
	            
	            
	        }catch (Exception e){
	        	e.printStackTrace();
	            System.out.println(e.toString());
	        }
	    }
	

	

}
