/**
 * 
 */
package uniandes.tesis.mundo;

import java.io.File;
import java.util.ArrayList;

import org.rosuda.JRI.REXP;
import org.rosuda.JRI.RList;
import org.rosuda.JRI.Rengine;

import uniandes.tesis.interfaz.InterfazPrograma;
import uniandes.tesis.interfaz.TextConsole;





/**
 * @author Juan Camilo García
 *
 */
public class RStatistics
{
	/**
	 * 
	 */
	private InterfazPrograma vp;
	
	/**
	 * 
	 */
	private Rengine re;
	
	
	
	/**
	 * @param args
	 * @param nVp
	 */
	public RStatistics(String[] args, InterfazPrograma nVp){
		
		re=new Rengine(args, false, new TextConsole());
		
		
		
	    System.out.println("Rengine created, waiting for R");
		// the engine creates R is a new thread, so we should wait until it's ready
	    if (!re.waitForR()) {
	        System.out.println("Cannot load R");
	        return;
	        
	    }
		vp = nVp;
		
		try{
            
    		double vars = vp.getCols();
            String rutaIntencion = vp.getRutaIntencion().replace(".xls", ".csv");
            
            String rutaCaract = vp.getRutaCaract().replace(".xls", ".csv");
            @SuppressWarnings("unused")
			File f = new File("data/");
            
            
            File inten = new File(rutaIntencion);
            File carac = new File(rutaCaract);
            System.out.println(rutaIntencion);
            re.eval("datos = read.csv(\""+inten.getCanonicalPath().replace("\\","/")+ "\")");
            re.eval("install.packages(\"mclust\")");
            re.eval("library(\"mclust\")");
            re.eval("clust <- Mclust(datos[,-1])");
            
            // Leer numero de clusters para segmentos
            REXP clust = re.eval("clust$classification");
            double[] clusters = clust.asDoubleArray();
            double num_clusters = 0;
            for(int i = 0; i<clusters.length;i++){
            	if(clusters[i]>num_clusters)
            		num_clusters = clusters[i];
            }
            System.out.println("El número de clusters es: "+num_clusters);
            re.eval("nclust <- kmeans(datos[,-1],"+num_clusters+")");
            re.eval("datos$cluster <- nclust$cluster");
            REXP sumac = re.eval("nclust$withinss");
            double[] ss = sumac.asDoubleArray();
            
            for (double d : ss) {
				System.out.println(d);
			}
            
            
            
            re.eval("segmentos <- split(datos,datos$cluster)");
            
            vars= 7;
            
            
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
            	re.eval("man"+j+" <- manova( Y"+j+" ~ " + x[j-1] + ")");
            	re.eval("sum_man"+j+" <- summary.lm(manova( Y"+j+" ~ " + x[j-1] + "))");
            }
            
            String man_car = "";
            for(int i = 2; i<=colu;i++){
            	if(i==1)
	            	man_car = man_car + "caract[," + i+ "]";
	            	else
	            	man_car = man_car + "+caract[," + i+ "]";
            }
            
            String z;
            
            	z = "Z <- cbind(";
            	for(int i = 2; i<=vars;i++){
            	if(i==2)
            	z = z + "datos[," + i+ "]";
            	else
            	z = z + ",datos[," + i+ "]";
            }
            	z = z + ")";
            	re.eval(z);
            	re.eval("md <- manova("+ z+" ~ " + man_car + ")");
            	re.eval("sum_man <- summary.lm(manova("+ z+" ~ " + man_car + "))");
            	
            	//datos word
            	double[] revision = re.eval("md$coefficients[,1]").asDoubleArray();
            	
            	ArrayList<Double> orden = new ArrayList<Double>();
            	for(int i = 0; i<revision.length;i++)
            	{
            		if(!Double.isNaN(revision[i]))
            		{
            		orden.add((double) (i+1));	
            		}
            		
            	}
            	
            	double[] carSig = re.eval("sum_man$coefficients[,4]").asDoubleArray();
            	
            	ArrayList<ArrayList<Double>> carSigG = new ArrayList<ArrayList<Double>>();
            	for(int i = 0; i<carSig.length;i++){
            		if(carSig[i]<0.05){
            			ArrayList<Double> temp = new ArrayList<Double>();
            			temp.add(orden.get(i));
            			temp.add(carSig[i]);
            			carSigG.add(temp);
            		}
            	}
            	RList nVars = re.eval("caract[0,-1]").asList();
            	
            	String[] nombres = nVars.keys();
            	
            	ArrayList<String> significativas = new ArrayList<String>();
            	re.eval("correrMCar <- summary(caract)[4,]");
            	String correrMCar = "correrMCar[c(";
            	String correrVCar = "var(caract[c(";
            	ArrayList<String> vCar = new ArrayList<String>();
            	for(ArrayList<Double> m : carSigG){
            		significativas.add(nombres[m.get(0).intValue()-1]);
            		int temp = m.get(0).intValue()+1;
            		correrMCar += temp+",";
            		
            		
            		vCar.add(Math.sqrt(re.eval(correrVCar+temp+")])").asDouble())+"");
            		
            	}
            	correrMCar = correrMCar.substring(0, correrMCar.length()-1);
            	correrMCar += ")]";
            	
            	String[] mCar = re.eval(correrMCar).asStringArray();
            	for(int i = 0; i<mCar.length;i++)
            	{
            		int indice = mCar[i].indexOf(":");
            		mCar[i] = mCar[i].substring(indice+1).trim();
            	}
            	vp.setAvgCar(mCar);
            	vp.setSigCar(significativas);
            	vp.setDispCar(vCar);
            	
            	
            	
            	
            	
            	
            	ArrayList<ArrayList<Double>> significativasC = new ArrayList<ArrayList<Double>>();
            	String rutaGeneral= vp.getRutaInfoGen().replace(".xls", ".csv");
                
                String rutaEstilo = vp.getRutaEstilo().replace(".xls", ".csv");
                
                
                File general = new File(rutaGeneral);
                File estilo = new File(rutaEstilo);
                
                re.eval("general = read.csv(\""+general.getCanonicalPath().replace("\\","/")+ "\")");
                re.eval("estilo = read.csv(\""+estilo.getCanonicalPath().replace("\\","/")+ "\")");
            	
            	re.eval("generals <- split(general,datos$clust)");
            	re.eval("estilos <- split(estilo,datos$clust)");
            	
            	
            	ArrayList<String[]> mGeneral = new ArrayList<String[]>();
            	ArrayList<String[]> mEstilo = new ArrayList<String[]>();
            	ArrayList<Cluster> clustersa = new ArrayList<Cluster>();
            	//Ahora por cluster
            	for(int j = 1; j<=num_clusters;j++){
           		 
                 	
                 
            	double[] revisionC = re.eval("man" + j + "$coefficients[,1]").asDoubleArray();
            	
            	ArrayList<Double> ordenC = new ArrayList<Double>();
            	for(int i = 0; i<revisionC.length;i++)
            	{
            		if(!Double.isNaN(revisionC[i]))
            		{
            		ordenC.add((double) (i+1));	
            		}
            		
            	}
            	
            	double[] carSigC = re.eval("sum_man"+ j + "$coefficients[,4]").asDoubleArray();
            	
            	ArrayList<ArrayList<Double>> carSigGC = new ArrayList<ArrayList<Double>>();
            	for(int i = 0; i<carSigC.length;i++){
            		if(carSigC[i]<0.05){
            			ArrayList<Double> temp = new ArrayList<Double>();
            			temp.add(ordenC.get(i));
            			temp.add(carSigC[i]);
            			carSigGC.add(temp);
            		}
            	}
            	
            	ArrayList<String> significativasClu = new ArrayList<String>();
            	re.eval("correrMCar"+j+" <- summary(segc$'"+j+"')[4,]");
            	String correrMCarClu = "correrMCar"+j+"[c(";
            	String correrVCarClu = "var(segc$'"+j+"'[c(";
            	ArrayList<String> vCarClu = new ArrayList<String>();
            	for(ArrayList<Double> m : carSigGC){
            		significativasClu.add(nombres[m.get(0).intValue()-1]);
            		int temp = m.get(0).intValue()+1;
            		correrMCarClu += temp+",";
            		
            		
            		vCarClu.add(Math.sqrt(re.eval(correrVCarClu+temp+")])").asDouble())+"");
            		
            	}
            	correrMCarClu = correrMCarClu.substring(0, correrMCarClu.length()-1);
            	correrMCarClu += ")]";
            	
            	String[] mCarClu = re.eval(correrMCarClu).asStringArray();
            	for(int i = 0; i<mCarClu.length;i++)
            	{
            		int indice = mCarClu[i].indexOf(":");
            		mCarClu[i] = mCarClu[i].substring(indice+1).trim();
            	}
            	
            	
            	
            	ArrayList<Double> significativasCa = new ArrayList<Double>();
            	for(ArrayList<Double> m : carSigGC){
            		significativasCa.add((Double) m.get(0));
            	}
            	significativasC.add(significativasCa);
            	
            	
            	
            	
            	
            	//Información General por Cluster
            	
            	
            	
            	String[] dGene = re.eval("summary(generals$'"+ j + "')[4,-1]").asStringArray();
            	String[] dEst = re.eval("summary(estilos$'"+ j + "')[4,-1]").asStringArray();
            	
            	mGeneral.add(dGene);
            	mEstilo.add(dEst);
            	
            	for(int i = 0; i<dGene.length;i++){
        			int indice = dGene[i].indexOf(":");
        			dGene[i] = dGene[i].substring(indice+1).trim();
        		}
            	for(int i = 0; i<dEst.length;i++){
        			int indice = dEst[i].indexOf(":");
        			dEst[i] = dEst[i].substring(indice+1).trim();
        		}
            	
            	String[] nomGen = re.eval("general[0,-1]").asList().keys();
            	String[] nomEst = re.eval("estilo[0,-1]").asList().keys();
            	

				clustersa.add(new Cluster(significativasClu,mCarClu,vCarClu,nomGen,dGene,nomEst,dEst));				
            	}
            	
            	vp.setClusters((int)num_clusters);
            	
            	vp.setArrCluesters(clustersa);
            	
            	
            	
            	
            	
            
            
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

	

/*public static void main(String[] args) 
    {
	
	
	
	
	System.out.println("Creating Rengine (with arguments)");
	// 1) we pass the arguments from the command line
	// 2) we won't use the main loop at first, we'll start it later
	//    (that's the "false" as second argument)
	// 3) the callbacks are implemented by the TextConsole class above
	
    new RStatistics(args, new InterfazPrograma(args));
    
	
	        
	        
	        
	    	
	    }*/


	

	

}
