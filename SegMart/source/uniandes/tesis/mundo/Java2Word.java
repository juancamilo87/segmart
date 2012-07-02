package uniandes.tesis.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;
import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.style.ParagraphStyle.Align;


/**
 * @author Julio Mendoza
 *
 */
public class Java2Word {
	
	
	
	/**
	 * @param categoria
	 * @param clusters
	 */
	public static void ceateDocument(String categoria, int clusters)
	{
		try {
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
			System.out.println(categoria);
			File file = new File("docs/reporte.doc");
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
			String heading3_2 = reader.readLine(); 
			document.addEle(Heading1.with(heading1).create());
			document.addEle(Heading2.with(heading2).create());
			document.addEle(Paragraph.with(p1).withStyle().align(Align.JUSTIFIED).create());
			document.addEle(BreakLine.times(1).create());
			document.addEle(Heading3.with(heading3).create());
			document.addEle(Paragraph.with(p2).withStyle().align(Align.JUSTIFIED).create());
			document.addEle(Heading3.with(heading3_2).create());
			String p = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi molestie quam nec nisl feugiat imperdiet ut placerat nulla. ";
			for(int i = 0; i < clusters; i ++)
			{
				String h4 = "Clúster "+i;
				document.addEle(Heading3.with(h4).create());
				document.addEle(Paragraph.with(p).withStyle().align(Align.JUSTIFIED).create());
			}
			reader.close();
			PrintWriter pw = new PrintWriter(file);
			pw.write(document.getContent());
			pw.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Categoría:");
		Scanner in = new Scanner(System.in);
		System.out.println("Clusters:");
		String categoria = in.nextLine();
		int clusters = Integer.parseInt(in.nextLine());
		ceateDocument(categoria, clusters);
		in.close();
	}

}
