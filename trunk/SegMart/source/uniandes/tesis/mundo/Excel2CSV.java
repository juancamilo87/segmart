/**
 * 
 */
package uniandes.tesis.mundo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author Juan Camilo Garc'ia
 *
 */
public class Excel2CSV {

	/**
	 * 
	 */
	public Excel2CSV() {

	}

	/**
	 * @param path 
	 * @throws Exception
	 */
	public void echoAsCSV(String path) throws Exception {
		Row row = null;
		double maxRows = 0;
		double maxCols = 0;
		String stringCellV = "";
		InputStream inp = null;
		File file = new File(path);
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			FileOutputStream stream = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(stream);
			inp = new FileInputStream("docs/Base Info General.xls");
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			verificarFormatoArchivo(sheet);
			maxRows = sheet.getRow(1).getCell(1).getNumericCellValue();
			maxCols = sheet.getRow(2).getCell(4).getNumericCellValue();
			for (int i = 0; i < maxCols; i++) 
			{
				if(i != (maxCols-1))
				{
					stringCellV += "\"" + sheet.getRow(3).getCell(i).getStringCellValue()+ "\",";
				}
				else
				{
					stringCellV += "\"" + sheet.getRow(3).getCell(i).getStringCellValue()+ "\"";
				}
			}
			System.out.println(stringCellV);
			writer.println(stringCellV);
			for (int i = 4; i < maxRows; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < maxCols; j++) {
					if(j == (maxCols-1))
					{
						stringCellV = "\""
							+ row.getCell(j).getNumericCellValue() + "\"";
					}
					else
					{
						stringCellV = "\""
							+ row.getCell(j).getNumericCellValue() + "\",";
					}
					writer.print(stringCellV);
					System.out.print(stringCellV);
				}
				writer.println();
				System.out.println();
			}
			writer.close();
			stream.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Excel2CSV.class.getName()).log(Level.SEVERE, null,
					ex);
		} catch (IOException ex) {
			Logger.getLogger(Excel2CSV.class.getName()).log(Level.SEVERE, null,
					ex);
		} finally {
			try {
				inp.close();
			} catch (IOException ex) {
				Logger.getLogger(Excel2CSV.class.getName()).log(Level.SEVERE,
						null, ex);
			}
		}

	}

	/**
	 * @param sheet
	 * @throws Exception
	 */
	private void verificarFormatoArchivo(Sheet sheet) throws Exception {
		double maxRows = 0;
		double maxCols = 0;
		double cellValue = sheet.getRow(1).getCell(0).getNumericCellValue();
		if (cellValue > 0) {
			throw new Exception("Fromato inválido.");
		}
		cellValue = sheet.getRow(2).getCell(3).getNumericCellValue();
		if (cellValue > 0) {
			throw new Exception(
					"Fromato inválido, las observaciones no deben incluiir textos");
		}
		maxRows = sheet.getRow(1).getCell(1).getNumericCellValue();
		maxCols = sheet.getRow(2).getCell(4).getNumericCellValue();
		for (int i = 2; i < maxCols; i++) {
			cellValue = sheet.getRow(1).getCell(i).getNumericCellValue();
			if (cellValue != maxRows) {
				throw new Exception(
						"Formato inválido. No debe haber espacios en blanco en las observaciones.");
			}
		}
	}

//	public static void main(String[] args) {
//		try 
//		{
//			echoAsCSV();
//		} 
//		catch (Exception e) 
//		{
//			System.out.println(e.getMessage());
//		}
//		
//	}

}
