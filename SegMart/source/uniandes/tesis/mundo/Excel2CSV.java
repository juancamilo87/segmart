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
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Clase que genera automáticamente archivos .csv a partir de la lectura de archivos .xls
 * @author Juan Camilo Garc'ia
 * 
 */
public class Excel2CSV {
	
	/**
	 * Número total de filas del archivo .xls
	 */
	private double maxRows;
	
	/**
	 * Número total de columnas del archivo .xls
	 */
	private double maxCols;

	/**
	 * Crea la clase.
	 */
	public Excel2CSV() {
		maxRows = 0;
		maxCols = 0;
	}

	/**
	 * Crea un archivo .csv a paritr de un archivo .xls con un formato espec'ifico para su lectura.
	 * @param path Ruta del archivo .xls del cuál se va a leer información.
	 * @throws Exception Si hay problemas de lectura del archivo.
	 */
	public void echoAsCSV(String path) throws Exception {
		Row row = null;
		String stringCellV = "";
		InputStream inp = null;
		String campos = path.substring(0, path.indexOf("."));
		String nPath = campos + ".csv";
		File file = new File(nPath);
		try {
			copyFile(path);
//			File f = new File(path);
//			FileOutputStream fileOut = new FileOutputStream(f);
//			HSSFWorkbook hwb = new HSSFWorkbook();
//			hwb.write(fileOut);
//			fileOut.close();
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream stream = new FileOutputStream(file);
			PrintWriter writer = new PrintWriter(stream);
			inp = new FileInputStream(path);
			Workbook wb = new HSSFWorkbook(inp);
			Sheet sheet = wb.getSheetAt(0);
			verificarFormatoArchivo(sheet);
			maxRows = sheet.getRow(1).getCell(1).getNumericCellValue()+3;
			maxCols = sheet.getRow(2).getCell(4).getNumericCellValue();
			for (int i = 0; i < maxCols; i++) {
				if (i != (maxCols - 1)) {
					stringCellV += "\""
							+ sheet.getRow(3).getCell(i).getStringCellValue()
							+ "\",";
				} else {
					stringCellV += "\""
							+ sheet.getRow(3).getCell(i).getStringCellValue()
							+ "\"";
				}
			}
			System.out.println(stringCellV);
			writer.println(stringCellV);
			for (int i = 4; i < maxRows; i++) {
				row = sheet.getRow(i);
				for (int j = 0; j < maxCols; j++) {
					if (j == (maxCols - 1)) {
						stringCellV = "\""
								+ row.getCell(j).getNumericCellValue() + "\"";
					} else {
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
			ex.printStackTrace();
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
	 * Verifica si el formato del archivo .xls corresponde con el que se requiere para general el .csv.
	 * @param sheet La hoja del archivo qeu se va a leer.
	 * @return correcto <b>True</b> Si el archivo está en el formato indicado <b>False</b> de lo contrario.
	 * @throws Exception Si se detecta que el formato es inválido.
	 */
	public boolean verificarFormatoArchivo(Sheet sheet) throws Exception {
		boolean correcto = false;
		double cellValue = sheet.getRow(1).getCell(0).getNumericCellValue();
		if (cellValue > 0) {
			throw new Exception("Formato inválido.");
		}
		cellValue = sheet.getRow(2).getCell(3).getNumericCellValue();
		if (cellValue > 0) {
			throw new Exception(
					"Formato inválido, las observaciones no deben incluir textos");
		} else {
			maxRows = sheet.getRow(1).getCell(1).getNumericCellValue();
			maxCols = sheet.getRow(2).getCell(4).getNumericCellValue();
			if(maxRows<=1||maxCols<=1)
			{
				throw new Exception(
				"Formato inválido, datos insuficientes");
			}
			for (int i = 2; i < maxCols; i++) {
				cellValue = sheet.getRow(1).getCell(i).getNumericCellValue();
				if (cellValue != maxRows) {
					throw new Exception(
							"Formato inválido. No debe haber espacios en blanco en las observaciones.");
				}
			}
			correcto = true;
		}
		return correcto;
	}

	/**
	 * Retorna la hoja del documento que se lee.
	 * @param path Ruta del archivo en excel del que se quiere retornar la hoja que contiene la información. 
	 * @return sheet La hoja del archivo.
	 * @throws Exception Si hay problemas de lectura del archivo.
	 */
	public Sheet returnSheet(String path) throws Exception {
		Sheet sheet;
		InputStream inp = null;
		File file = new File(path);
		try {
			inp = new FileInputStream(file);
			Workbook wb = new HSSFWorkbook(inp);
			sheet = wb.getSheetAt(0);
			inp.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return sheet;
	}

	/**
	 * Genera una copia del archivo cuya ruta entra como parámetro, agregándole una "a"al final.
	 * @param ruta Ruta del archivo del cual se va a generar la copia.
	 */
	private void copyFile(String ruta) {
		File fuente = new File(ruta);
		File destino = new File(ruta+"a");
		if (!destino.exists()) {
			try {
				destino.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(fuente).getChannel();

			destination = new FileOutputStream(destino).getChannel();

			destination.transferFrom(source, 0, source.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (destination != null) {
				try {
					destination.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		copyFileSinA(ruta+"a");
	}
	
	/**
	 * Gemnera una copia del archivo original quitándole una a al final de la ruta.
	 * @param ruta Ruta del archivo del cual se va a generar la copia.
	 */
	private void copyFileSinA(String ruta) {
		File fuente = new File(ruta);
		File destino = new File(ruta.substring(0, ruta.lastIndexOf("a")));
		if (!destino.exists()) {
			try {
				destino.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(fuente).getChannel();

			destination = new FileOutputStream(destino).getChannel();

			destination.transferFrom(source, 0, source.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (destination != null) {
				try {
					destination.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			fuente.delete();
		}
		
	}

	/**
	 * Retorna la cantidad de filas del archivo a leer.
	 * @return maxRows Cantidad de filas del archivo.
	 */
	public double getMaxRows() {
		return maxRows;
	}

	/**
	 * Retorna la cantidad de columnas del archivo a leer.
	 * @return maxCols Cantidad de columnas del archivo.
	 */
	public double getMaxCols() {
		return maxCols;
	}

	// public static void main(String[] args) {
	// try
	// {
	// echoAsCSV();
	// }
	// catch (Exception e)
	// {
	// System.out.println(e.getMessage());
	// }
	//
	// }

}
