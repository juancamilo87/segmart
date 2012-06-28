/**
 * 
 */
package uniandes.tesis.mundo;

import java.io.File;
import org.rosuda.REngine.*;
import org.rosuda.REngine.Rserve.*;

import uniandes.tesis.interfaz.InterfazPrograma;




public class RStatistics {

	private InterfazPrograma vp;
	
	public static void main(String[] args){
	        new RStatistics(args);
	    }
	    
	    /*
	     * Test for simple plots.
	     * This class simply plots a time series array using plot.ts()
	     * function of R.
	     */
	    public RStatistics(String[] args){
	        vp = new InterfazPrograma();
	        try {
	        RConnection c = new RConnection((args.length>0)?args[0]:"127.0.0.1");
	        double d[] = c.eval("rnorm(10)").asDoubles();
            org.rosuda.REngine.REXP x0 = c.eval("R.version.string");
            System.out.println(x0.asString());
            String rutaIntencion = vp.getRutaIntencion().replace(".xls", ".csv");
	        
				c.eval("datos = read.csv(\""+rutaIntencion + "\")");
			} catch (RserveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (REXPMismatchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	        
	        
	        
	        
//	    	try{
//	            RCaller caller = new RCaller();
//	            caller.setRscriptExecutable("C:/Program Files/R/R-2.15.1/bin/x64/Rscript");
//	            
//	            RCode code = new RCode();
//	            code.clear();
//	            double vars = vp.getCols();
//	            String rutaIntencion = vp.getRutaIntencion().replace(".xls", ".csv");
//	            String rutaCaract = vp.getRutaCaract().replace(".xls", ".csv");
//	            File f = new File("data/");
//	            code.addRCode("install.packages(\"rcom\"");
//	            code.addRCode("install.packages(\"R2wd\"");
//	            code.addRCode("library(\"R2wd\"");
//	            System.out.println(f.getCanonicalPath().replace("\\","/"));
//	            code.addRCode("my.file <- \""+"C:/Rtext.txt\"");
//	            code.addRCode("txtStart(my.file)");
//	            code.addRCode("datos = read.csv(\""+rutaIntencion + "\")");
//	            code.addRCode("clust <- mClust(datos[-,1])");
//	            code.addRCode("datos$clust<- clust$classification");
//	            code.addRCode("segmentos <- split(datos,datos$clust");
//	            // Leer numero de clusters para segmentos
//	            
//	            
//	            code.addRCode("caract = read.csv(\"" + rutaCaract + "\")");
//	            String y;
//	            for(int j = 1; j<10;j++){
//	            	y = "Y$" + j + " <- cbind(";
//	            	for(int i = 2; i<vars;i++){
//	            	if(i==1)
//	            	y = y + "segmentos$'" + j + "'[," + i+ "]";
//	            	else
//	            	y = y + ",segmentos$'" + j + "[," + i+ "]";
//	            }
//	            	y = y + ")";
//	            	code.addRCode(y);
//	            }
//	            
//	            
//	            code.addRCode("segc <- split(caract,datos$clust");
//	            String x = "";
//	            for(int j = 1; j<10;j++){
//	            for(int i = 2; i<8;i++){
//	            	if(i==1)
//		            	x = x + "segc$'" + j + "[," + i+ "]";
//		            	else
//		            	x = x + "+sec$'" + j + "[," + i+ "]";
//	            }
//	            }
//	            code.addRCode("txtStop.2wd()");
//	            
//	            code.addRCode("man <- manova( y ~ " + x + ")");
//	           
//	            caller.setRCode(code);
//	            caller.runOnly();
	            
	            
//	            System.out.println(caller.getParser().getNames());
	            /*
	             * Retrieving the 'mean' element of list 'my.all'
	             */
	            
	            
	            
	            
	            
	            
//	        }catch (Exception e){
//	        	e.printStackTrace();
//	            System.out.println(e.toString());
//	        }
	    }
	

	

}
