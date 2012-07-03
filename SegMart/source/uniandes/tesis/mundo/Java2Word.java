package uniandes.tesis.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import uniandes.tesis.interfaz.InterfazPrograma;
import word.api.interfaces.IDocument;
import word.utils.Utils;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Image;
import word.w2004.elements.Paragraph;
import word.w2004.elements.ParagraphPiece;
import word.w2004.elements.Table;
import word.w2004.elements.tableElements.TableEle;
import word.w2004.style.ParagraphStyle.Align;


/**
 * http://java2word.blogspot.com/p/all-in-one-example.html
 * @author Juan Camilo García
 *
 */
public class Java2Word {
	
	/**
	 * Inicializa la clase.
	 * @param nVp a interfaz que muestra la aplicación.
	 */
	public Java2Word(InterfazPrograma nVp, File file)	
	{
		crearDocumento(nVp, file);
	}
	
	
	
	/**
	 * http://java2word.blogspot.com/p/all-in-one-example.html
	 * Crea un documento en word con el contenido generado dinámicamente.
	 * @param nVp La interfaz que muestra la aplicación. 
	 */
	public void crearDocumento(InterfazPrograma nVp, File file)
	{
		try {
			InterfazPrograma vp = nVp;
			double[] sCs = vp.getSumaCuadrados();
			double minV = sCs[0];
			double maxV = sCs[0];
			int iMax = 0;
			int iMin = 0;
			for (int i = 1; i < sCs.length; i++) {
				double d = sCs[i];
				if(d>maxV)
				{
					maxV = d;
					iMax = i;
				}
				
			}
			for (int i = 1; i < sCs.length; i++) {
				double d = sCs[i];
				if(d<minV)
				{
					minV = d;
					iMin = i;
				}
				
			}
			ArrayList<Cluster> arrClusters = vp.getArrCluesters();
			String categoria = vp.getCategoria();
			String[] avgCar = vp.getAvgCar();
			ArrayList<String> sigCar = vp.getSigCar();
			ArrayList<String> dispCar = vp.getDispCar();
			int clusters = vp.getClusters();
			File texto = new File("data/texto reporte.txt");
			BufferedReader reader = new BufferedReader(new FileReader(texto));
			categoria = categoria.trim();
			String[] words = categoria.split(" ");
			categoria = "";
			for (String word : words) {
				if (word.length()>2) {
					String mayus = word.toUpperCase();
					String mins = word.substring(1).toLowerCase();
					word = mayus.charAt(0)+mins;					
				}
				categoria += " "+word;
			}
			categoria = categoria.trim();			
			if(file.exists())
			{
				file.delete();
				file.createNewFile();
			}
			else
			{
				file.createNewFile();
			}
			IDocument document = new Document2004();
			document.encoding(Encoding.ISO8859_1);
			String heading1 = reader.readLine();
			String heading2 = reader.readLine() + categoria;
			String p1 = reader.readLine();
			String heading3 = reader.readLine();
			String p2 = reader.readLine();
			String[] tHeaderG = reader.readLine().split(",");
			String pCluster = reader.readLine();
			String[] tHeaderC1 = reader.readLine().split(",");
			String pCluster1 = reader.readLine();
			String[] tHeaderC2 = reader.readLine().split(",");
			String pCluster2 = reader.readLine();
			String[] tHeaderC3 = reader.readLine().split(",");
//			document.addEle(Image.from_WEB_URL("http://s16.postimage.org/xmh2lbsdt/img.png"));
			 System.out.println( Utils.getAppRoot()+ "\\data\\img.png");
			document.addEle(Heading1.with(heading1).create());
			document.addEle(Heading2.with(heading2).create());
			document.addEle(Paragraph.with(p1).withStyle().align(Align.JUSTIFIED).create());
			document.addEle(BreakLine.times(1).create());
			document.addEle(Heading3.with(heading3).create());
			document.addEle(Paragraph.with(p2).withStyle().align(Align.JUSTIFIED).create());
			document.addEle(Paragraph.with("Según la segmentaión del mercado se encontró que el número de clústeres óptimo es "+clusters).create());
			Table table = new Table();
			table.addTableEle(TableEle.TH, tHeaderG[0], tHeaderG[1], tHeaderG[2]);			
			int min = Math.min(avgCar.length, Math.min(dispCar.size(), sigCar.size()));
			for (int i = 0; i <min; i++) {
				table.addTableEle(TableEle.TD, sigCar.get(i),avgCar[i],dispCar.get(i));
			}
			document.addEle(table);
			for(int i = 0; i < clusters; i ++)
			{
				Cluster cluster = arrClusters.get(i);
				String h4 = "Clúster "+(i+1);
				document.addEle(Heading3.with(h4).create());
				document.addEle(Paragraph.with(pCluster).withStyle().align(Align.JUSTIFIED).create());
				String[] avgCarC = cluster.getAvgCar();
				ArrayList<String> sigCarC = cluster.getCarSig();
				ArrayList<String> dispCarC = cluster.getDispCar();
				Table tableC1 = new Table();
				tableC1.addTableEle(TableEle.TH, tHeaderC1[0], tHeaderC1[1], tHeaderC1[2]);
				min = Math.min(avgCarC.length, Math.min(dispCarC.size(), sigCarC.size()));
				for (int j = 0; j <min; j++) {
					tableC1.addTableEle(TableEle.TD, sigCarC.get(j),avgCarC[j],dispCarC.get(j));
				}
				document.addEle(BreakLine.times(1).create());
				document.addEle(tableC1);
				document.addEle(BreakLine.times(1).create());
				document.addEle(Paragraph.with(pCluster1).withStyle().align(Align.JUSTIFIED).create());
				String[] avgGenC = cluster.getAvgGen();
				String[] carGenC = cluster.getNamGen();
				Table tableC2 = new Table();
				tableC2.addTableEle(TableEle.TH, tHeaderC2[0], tHeaderC2[1]);
				min = Math.min(avgGenC.length, carGenC.length);
				for (int j = 0; j <min; j++) {
					tableC2.addTableEle(TableEle.TD, carGenC[j],avgGenC[j]);
				}
				document.addEle(BreakLine.times(1).create());
				document.addEle(tableC2);
				document.addEle(BreakLine.times(1).create());
				document.addEle(Paragraph.with(pCluster2).withStyle().align(Align.JUSTIFIED).create());
				String[] avgProbC = cluster.getAvgProb();
				String[] carProbC = cluster.getNamProb();
				Table tableC3 = new Table();
				tableC3.addTableEle(TableEle.TH, tHeaderC3[0], tHeaderC3[1]);
				min = Math.min(avgProbC.length, carProbC.length);
				for (int j = 0; j <min; j++) {
					tableC3.addTableEle(TableEle.TD, carProbC[j],avgProbC[j]);
				}
				document.addEle(BreakLine.times(1).create());
				document.addEle(tableC3);
			}
			document.addEle(BreakLine.times(1).create());
			document.addEle(Paragraph.with("Después de realizar el análisis por clústeres se encontró que el segmento "+(iMin+1)+" es el más" +
					"homogéneo. Por esto un producto fácilemente puede suplir las necesidades de todo el clúster y por tanto ser exitoso. Por " +
					"otro lado el segmento "+ (iMax+1) +" es el más heterogéneo razón por la cual existe la oportunidad de entrar a definir el segemento" +
							"con un fuerte posicionamiento y así lograr una alta participación en el mercado.").withStyle().align(Align.JUSTIFIED).create());
			reader.close();
			PrintWriter pw = new PrintWriter(file);
			pw.write(document.getContent());
			pw.close();
			System.out.println("Created");
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		
	}

	/*
	public static void main(String[] args) {
		System.out.println("Categoría:");
		Scanner in = new Scanner(System.in);
		System.out.println("Clusters:");
		String categoria = in.nextLine();
		int clusters = Integer.parseInt(in.nextLine());
		ceateDocument(categoria, clusters);
		in.close();
	}*/

}
