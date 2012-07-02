package uniandes.tesis.mundo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;


import sun.font.CreatedFontTracker;

import word.api.interfaces.IDocument;
import word.w2004.Document2004;
import word.w2004.Document2004.Encoding;
import word.w2004.elements.BreakLine;
import word.w2004.elements.Heading1;
import word.w2004.elements.Heading2;
import word.w2004.elements.Heading3;
import word.w2004.elements.Paragraph;
import word.w2004.style.ParagraphStyle.Align;


public class Java2Word {
	
	
	
	public static void ceateDocument(String categoria)
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
			document.addEle(Heading1.with(heading1).create());
			document.addEle(Heading2.with(heading2).create());
			document.addEle(BreakLine.times(1).create());
			document.addEle(Paragraph.with(p1).withStyle().align(Align.JUSTIFIED).create());
			document.addEle(BreakLine.times(2).create());
			document.addEle(Heading3.with(heading3).create());			
			document.addEle(Paragraph.with("Hello World.").create());
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
		Scanner in = new Scanner(System.in);
		String categoria = in.nextLine();
		ceateDocument(categoria);
		in.close();
		

	}

}
