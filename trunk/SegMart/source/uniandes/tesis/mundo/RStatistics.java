/**
 * 
 */
package uniandes.tesis.mundo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.Rengine;

import uniandes.tesis.interfaz.InterfazPrograma;
import uniandes.tesis.interfaz.TextConsole;





public class RStatistics
{
	private InterfazPrograma vp;
	private Rengine re;
	
	
	
	public RStatistics(String[] args){
		
		re=new Rengine(args, false, new TextConsole());
	    System.out.println("Rengine created, waiting for R");
		// the engine creates R is a new thread, so we should wait until it's ready
	    if (!re.waitForR()) {
	        System.out.println("Cannot load R");
	        return;
	        
	    }
		vp = new InterfazPrograma();
		
		try{
            
    		double vars = vp.getCols();
            String rutaIntencion = vp.getRutaIntencion().replace(".xls", ".csv");
            
            String rutaCaract = vp.getRutaCaract().replace(".xls", ".csv");
            File f = new File("data/");
            
            rutaIntencion = "C:/Users/Julio Mendoza/Documents/Workspace/SegMart/docs/Base Info Intencionmod.csv";
            rutaCaract = "";
            
            
            File inten = new File(rutaIntencion);
            File carac = new File(rutaCaract);
            System.out.println(rutaIntencion);
            re.eval("datos = read.csv(\""+inten.getCanonicalPath().replace("\\","/")+ "\")");
            re.eval("install.packages(\"mclust\")");
            re.eval("library(\"mclust\")");
            re.eval("clust <- Mclust(datos[,-1])");
            re.eval("datos$clust<- clust$classification");
            re.eval("segmentos <- split(datos,datos$clust)");
            // Leer numero de clusters para segmentos
            REXP clust = re.eval("datos$clust");
            double[] clusters = clust.asDoubleArray();
            double num_clusters = 0;
            for(int i = 0; i<clusters.length;i++){
            	if(clusters[i]>num_clusters)
            		num_clusters = clusters[i];
            }
            vars= 7;
            System.out.println("El número de clusters es: "+num_clusters);
            
            
            re.eval("caract = read.csv(\"" + carac.getCanonicalPath().replace("\\", "/") + "\")");
            String y;
            for(int j = 1; j<=num_clusters;j++){
            	y = "Y" + j + " <- cbind(";
            	for(int i = 2; i<=vars;i++){
            	if(i==2)
            	y = y + "segmentos$'" + j + "'[," + i+ "]";
            	else
            	y = y + ",segmentos$'" + j + "'[," + i+ "]";
            }
            	y = y + ")";
            	re.eval(y);
            }
            
            REXP cols = re.eval("length(caract[1,])");
            int colu = 0;
            try{
            colu = cols.asInt();
            System.out.println(colu);
            }
            catch(Exception e){
            	e.getStackTrace();
            }
            re.eval("segc <- split(caract,datos$clust)");
            
            String x[] = new String[(int) num_clusters];
            
            for(int j = 1; j<=num_clusters;j++){
            	x[j-1] = "";	
            for(int i = 2; i<=colu;i++){
            	if(i==1)
	            	x[j-1] = x[j-1] + "segc$'" + j + "'[," + i+ "]";
	            	else
	            	x[j-1] = x[j-1] + "+segc$'" + j + "'[," + i+ "]";
            }
            
            }
            for(int j = 1; j<=num_clusters;j++){
            	re.eval("man"+j+" <- manova( y"+j+" ~ " + x[j-1] + ")");
            }
            
//           
//            
//       
//       
//            
            
            if (true) {
        	    // so far we used R as a computational slave without REPL
        	    // now we start the loop, so the user can use the console.
        	    System.out.println("Now the console is yours ... have fun");
        	    re.startMainLoop();
        	} else {
        	    re.end();
        	    System.out.println("end");
        	}
            
            
            
        }catch (Exception e){
        	e.printStackTrace();
            System.out.println(e.toString());
        }
		
		
		
		
	}

	
	
public static void main(String[] args) throws IOException
    {
	
	
	System.out.println("Creating Rengine (with arguments)");
	// 1) we pass the arguments from the command line
	// 2) we won't use the main loop at first, we'll start it later
	//    (that's the "false" as second argument)
	// 3) the callbacks are implemented by the TextConsole class above
	
    new RStatistics(args);
    
	
	        
	        
	        
	    	
	    }


	

	

}
