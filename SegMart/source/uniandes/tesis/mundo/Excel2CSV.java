/**
 * 
 */
package uniandes.tesis.mundo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel2CSV {

	public Excel2CSV() {

	}

	public static void echoAsCSV() throws Exception {
		Row row = null;
		double maxRows = 0;
		double maxCols = 0;
		double cellValue = 0;
		verificarFormatoArchivo(sheet);
		InputStream inp = null;
		try {
			inp = new FileInputStream("docs/Base Info General.xls");
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			maxRows = sheet.getRow(1).getCell(1).getNumericCellValue();
			maxCols = sheet.getRow(2).getCell(4).getNumericCellValue();
			for (int i = 0; i < maxCols; i++) 
			{
				//cellValue = sheet.getRow(arg0)
			}
			
			
			for (int i = 4; i < 11; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < 4; j++) {
					System.out.print("\""
							+ row.getCell(j).getNumericCellValue() + "\",");
				}
				System.out.println();
			}
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

	public static void verificarFormatoArchivo(Sheet sheet) throws Exception {
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

	public static void main(String[] args) {
		InputStream inp = null;
		echoAsCSV();
	}

}
