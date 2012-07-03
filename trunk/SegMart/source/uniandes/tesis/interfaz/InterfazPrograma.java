package uniandes.tesis.interfaz;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.ss.usermodel.Sheet;

import uniandes.tesis.mundo.Cluster;
import uniandes.tesis.mundo.Excel2CSV;
import uniandes.tesis.mundo.Java2Word;
import uniandes.tesis.mundo.RStatistics;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazPrograma extends JFrame {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * El tipo de análisis a realizar.
	 */
	private String tipo_analisis;

	/**
	 * El paso en el que va el usuario en la aplicación.
	 */
	private String paso;

	/**
	 * Ruta del archivo .xls de Información General
	 */
	private String rutaInfoGen;

	/**
	 * Ruta del archivo .xls de Estilo de Vida
	 */
	private String rutaEstilo;

	/**
	 * Ruta del archivo .xls de Intención de Compra
	 */
	private String rutaIntencion;

	/**
	 * Ruta del archivo .xls de características.
	 */
	private String rutaCaract;

	/**
	 * Instancia de la clase que genera automáticamente archivos .csv
	 */
	private Excel2CSV excel2csv;

	/**
	 * 
	 */
	private String[] arguments;

	/**
	 * 
	 */
	private String categoria;

	/**
	 * 
	 */
	private int clusters;

	/**
	 * Arreglo que contiene las características significativas.
	 */
	private ArrayList<String> sigCar;
	
	/**
	 * 
	 */
	private double[] sumaCuadrados;

	/**
	 * Arreglo que contiene el promedio de las características significativas.
	 */
	private String[] avgCar;

	/**
	 * Arreglo que contiene la dispersión de las características significativas.
	 */
	private ArrayList<String> dispCar;

	/**
	 * Es el panel que se usa para registrar un producto
	 */
	private PanelLogo panelLogo;

	/**
	 * Es el panel donde se muestran los botones del punto de venta
	 */
	private BarraProgreso barraProgreso;

	/**
	 * Es la barra con el menú para la aplicación
	 */
	private PanelInformacion panelInformacion;

	/**
	 * Es el panel con los botones para los puntos de extensión
	 */
	private PanelBotones panelBotones;
	
	/**
	 * Arreglo de clústeres que contienen la información de las características significativas.
	 */
	private ArrayList<Cluster> arrCluesters;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Descripción <br>
	 * <b>post: </b> Descripción
	 * 
	 * @param args
	 * @param archivo
	 *            El archivo de propiedades con la configuración para el punto
	 *            de venta
	 */
	public InterfazPrograma(String[] args) {
		paso = "Tipo de Analisis";
		tipo_analisis = "";
		construirForma();
		excel2csv = new Excel2CSV();
		arguments = args;
		sigCar = new ArrayList<String>();
		dispCar = new ArrayList<String>();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Este método sirve para construir la forma inicializando cada uno de los
	 * componentes. <br>
	 * <b>pre: </b> La ventana está vacía <br>
	 * <b>post: </b> Se inicializaron los componentes gráficos de la aplicación
	 */
	private void construirForma() {
		rutaInfoGen = "";
		rutaIntencion = "";
		rutaEstilo = "";
		rutaCaract = "";
		BorderLayout orden = new BorderLayout();
		orden.setHgap(10);
		orden.setVgap(10);

		JPanel panelContenedor = new JPanel(orden);
		panelLogo = new PanelLogo(this);
		add(panelLogo, BorderLayout.NORTH);
		barraProgreso = new BarraProgreso(this);
		panelContenedor.add(barraProgreso, BorderLayout.NORTH);
		panelInformacion = new PanelInformacion(this);
		panelContenedor.add(panelInformacion, BorderLayout.CENTER);
		add(panelContenedor, BorderLayout.CENTER);
		panelBotones = new PanelBotones(this);

		add(panelBotones, BorderLayout.SOUTH);
		setSize(700, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Segmart");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este es el método llamado cuando se cierra la ventana principal. <br>
	 * Este método desconecta el punto de venta del almacén y cierra la
	 * aplicación.
	 */
	public void dispose() {
		limpiar();
		super.dispose();
	}

	/**
	 * @param tipo
	 */
	public void cambiarTipoAnalisis(String tipo) {
		panelBotones.cambiarListo();
		tipo_analisis = tipo;
	}

	/**
	 * @return paso. Paso en el que va el proceso de ejecución.
	 */
	public String darPaso() {
		return paso;
	}

	/**
	 * @param ruta
	 *            Ruta del archivo a verificar.
	 * @return correct <b>True</b> si el archivo está en formato correcto,
	 *         <b>False</b> de lo contrario.
	 */
	public boolean verificarArchivo(String ruta) {
		boolean correct = false;
		Sheet sheet;
		try {
			sheet = excel2csv.returnSheet(ruta);
			correct = excel2csv.verificarFormatoArchivo(sheet);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return correct;
	}

	/**
	 * Actualiza la interfaz dependiendo de la acción tomada por el usuario.
	 * 
	 * @param accion
	 */
	public void refrescar(String accion) {

		if (accion.equalsIgnoreCase("Siguiente")) {
			if (paso.equalsIgnoreCase("Tipo de Analisis")) {
				cambiarTipoAnalisis("CREACION");
				paso = "Informacion Mercado";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();

			} else if (paso.equalsIgnoreCase("Informacion Mercado")) {

				if (rutaInfoGen.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,
							"Debe seleccionar un archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				else if (verificarArchivo(rutaInfoGen)) {
					echoAsCSV(rutaInfoGen);
					paso = "Estilo de Vida";
					barraProgreso.refrescar();
					panelInformacion.refrescar();
					panelBotones.refrescar();
				} else
					JOptionPane
							.showMessageDialog(
									this,
									"El archivo ingresado no tiene el formato correcto.",
									"Error", JOptionPane.ERROR_MESSAGE);
			} else if (paso.equalsIgnoreCase("Estilo de Vida")) {
				if (rutaEstilo.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,
							"Debe seleccionar un archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				else if (verificarArchivo(rutaEstilo)) {
					echoAsCSV(rutaEstilo);
					paso = "Intencion de Compra";
					barraProgreso.refrescar();
					panelInformacion.refrescar();
					panelBotones.refrescar();
				} else
					JOptionPane
							.showMessageDialog(
									this,
									"El archivo ingresado no tiene el formato correcto.",
									"Error", JOptionPane.ERROR_MESSAGE);
			} else if (paso.equalsIgnoreCase("Intencion de Compra")) {
				if (rutaIntencion.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,
							"Debe seleccionar un archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				else if (verificarArchivo(rutaIntencion)) {
					echoAsCSV(rutaIntencion);
					paso = "Caracteristicas de Productos";
					barraProgreso.refrescar();
					panelInformacion.refrescar();
					panelBotones.refrescar();
				} else
					JOptionPane
							.showMessageDialog(
									this,
									"El archivo ingresado no tiene el formato correcto.",
									"Error", JOptionPane.ERROR_MESSAGE);
			} else if (paso.equalsIgnoreCase("Caracteristicas de Productos")) {
				if (rutaCaract.equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(this,
							"Debe seleccionar un archivo.", "Error",
							JOptionPane.ERROR_MESSAGE);
				else {
					try {
						categoria = JOptionPane.showInputDialog(this,
								"Ingresar la categoría:", "Categoría",
								JOptionPane.QUESTION_MESSAGE);
						File f1 = new File(
								"./docs/Base Info Caracteristicas.xls");
						File f2 = new File("./data/");
						System.out.println(rutaCaract.replace(".xls", ".csv"));
						File file = new File(rutaCaract.replace(".xls", ".csv"));
						file.delete();
						file.deleteOnExit();
						Runtime.getRuntime().exec(
								"cmd /c start "
										+ "data/calling.vbs "
										+ "\""
										+ rutaCaract
										+ "\""
										+ " "
										+ "\""
										+ rutaIntencion.replace(".xls", ".csv")
										+ "\""
										+ " "
										+ "\""
										+ f1.getCanonicalPath().replace(".xls",
												".csv") + "\"" + " " + "\""
										+ f2.getCanonicalPath() + "\"");

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (verificarCaract(rutaCaract.replace(".xls", ".csv"))) {
					// Correr la macro
					paso = "Resumen";
					barraProgreso.refrescar();
					panelInformacion.refrescar();
					panelBotones.refrescar();
				} else
					JOptionPane
							.showMessageDialog(
									this,
									"El archivo ingresado no tiene el formato correcto.",
									"Error", JOptionPane.ERROR_MESSAGE);
			} else if (paso.equalsIgnoreCase("Resumen")) {
				paso = "Resultados";
				JOptionPane.showMessageDialog(this,
						"Escoja un servidor para descargar las librerías de R.",
						"Escojer servidor", JOptionPane.INFORMATION_MESSAGE);
				@SuppressWarnings("unused")
				RStatistics rStatistics = new RStatistics(arguments, this);
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			}

		} else if (accion.equalsIgnoreCase("Anterior")) {

			if (paso.equalsIgnoreCase("Informacion Mercado")) {
				paso = "Tipo de Analisis";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			} else if (paso.equalsIgnoreCase("Estilo de Vida")) {
				paso = "Informacion Mercado";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			} else if (paso.equalsIgnoreCase("Intencion de Compra")) {
				paso = "Estilo de Vida";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			} else if (paso.equalsIgnoreCase("Caracteristicas de Productos")) {
				paso = "Intencion de Compra";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			} else if (paso.equalsIgnoreCase("Resumen")) {
				paso = "Caracteristicas de Productos";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			} else if (paso.equalsIgnoreCase("Resultados")) {
				paso = "Resumen";
				barraProgreso.refrescar();
				panelInformacion.refrescar();
				panelBotones.refrescar();
			}

		} else if (accion.equalsIgnoreCase("Reiniciar")) {
			limpiar();
			paso = "Tipo de Analisis";
			barraProgreso.refrescar();
			panelInformacion.refrescar();
			panelBotones.refrescar();
			panelBotones.cambiarListo2();

		}
	}


	/**
	 * Retorna el tipo de análisis elegido por el usuario.
	 * 
	 * @return tipo_analisis El tipo de análisis elegido por el usuario.
	 */
	public String darTipo() {
		return tipo_analisis;
	}

	// -----------------------------------------------------------------
	// Main
	// -----------------------------------------------------------------

	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		InterfazPrograma interfaz = new InterfazPrograma(args);
		interfaz.setVisible(true);
		interfaz.setResizable(false);
	}

	/**
	 * Retorna la ruta del archivo .xls de información general.
	 * 
	 * @return rutaInfoGen La ruta del archivo .xls de información general.
	 */
	public String getRutaInfoGen() {
		return rutaInfoGen;
	}

	/**
	 * Cambia la ruta del archivo .xls de información general.
	 * 
	 * @param rutaInfoGen
	 *            La nueva ruta del archivo .xls de información general.
	 */
	public void setRutaInfoGen(String rutaInfoGen) {
		this.rutaInfoGen = rutaInfoGen;
	}

	/**
	 * Limpia la interfaz para reiniciar la aplicaicón.
	 */
	public void limpiar() {
		tipo_analisis = "";
		paso = "";
		File file = new File(rutaCaract.replace(".xls", ".csv"));
		file.delete();
		rutaInfoGen = "";
		rutaCaract = "";
		rutaEstilo = "";
		rutaIntencion = "";

	}

	/**
	 * Retorna la ruta del archivo .xls de esilo de vida.
	 * 
	 * @return rutaEstilo La ruta del archivo .xls de esilo de vida.
	 */
	public String getRutaEstilo() {
		return rutaEstilo;
	}

	/**
	 * Cambia la ruta del archvo .xls de estilo de vida.
	 * 
	 * @param rutaEstilo
	 *            La nueva ruta del archivo .xls de esilo de vida.
	 */
	public void setRutaEstilo(String rutaEstilo) {
		this.rutaEstilo = rutaEstilo;
	}

	/**
	 * Retorna la ruta del archivo .xls de intención de compra.
	 * 
	 * @return rutaIntencion La ruta del archivo .xls de intención de compra.
	 */
	public String getRutaIntencion() {
		return rutaIntencion;
	}

	/**
	 * Cambia la ruta del archivo .xls de intención de compra.
	 * 
	 * @param rutaIntencion
	 *            La ruta del archivo .xls de intención de compra.
	 */
	public void setRutaIntencion(String rutaIntencion) {
		this.rutaIntencion = rutaIntencion;
	}

	/**
	 * Retorna la ruta del archivo .xls de características.
	 * 
	 * @return rutaCaract La ruta del archivo .xls de características.
	 */
	public String getRutaCaract() {
		return rutaCaract;
	}

	/**
	 * Cambia la ruta del archivo .xls de características.
	 * 
	 * @param rutaCaract
	 *            La ruta del archivo .xls de características.
	 */
	public void setRutaCaract(String rutaCaract) {
		this.rutaCaract = rutaCaract;
	}

	/**
	 * Creates a CSV file from an existing .xls file.
	 * 
	 * @param path
	 */
	public void echoAsCSV(String path) {
		try {
			excel2csv.echoAsCSV(path);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * @param path
	 *            La ruta del archivo .xls de caracteríticas.
	 * @return correct <b>True</b> si el achivo está en formato correcto
	 *         <b>False</b> de lo contrario.
	 */
	public boolean verificarCaract(String path) {
		LoadingDialog dialog = new LoadingDialog(this);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		boolean correct = false;
		File file = new File(path);
		System.out.println("Waiting");
		while (!file.exists()) {
			
		}
		try {
			System.out.println("Waiting for close");
			Thread.sleep(10000);
			System.out.println("Closed");
			dialog.cerrar();
			dialog.setVisible(false);
			setEnabled(true);
		} catch (InterruptedException e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(file)));
			String code = reader.readLine();
			reader.close();
			if (code.equalsIgnoreCase("Error")) {
				throw new Exception(
						"Los archivos de intención de compra y caracteristicas de producto no coinciden.");
			} else {
				correct = true;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return correct;
	}

	/**
	 * Retorna el máximo de columnas de un archivo .csv.
	 * 
	 * @return <code>excel2csv.getMaxCols()</code> El máximo de columnas del
	 *         archivo .csv procesado en el momento.
	 */
	public double getCols() {
		return excel2csv.getMaxCols();
	}

	/**
	 * Retorna la categoría ingresada por el usuario a través de la aplicación.
	 * 
	 * @return the categoria La categoría ingresada por el usuario
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * Cambia la categoría por la ingresada por el usuario.
	 * 
	 * @param categoria
	 *            La categoría a cambiar.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * Retorna los clústeres encontrados al hacer el análisis de los archivos
	 * con R.
	 * 
	 * @return Los clústeres encontrados.
	 */
	public int getClusters() {
		return clusters;
	}

	/**
	 * Cambia la cantidad de clústeres encontrados.
	 * 
	 * @param clusters
	 *            La nueva cantidad de clústeres a encontrar.
	 */
	public void setClusters(int clusters) {
		this.clusters = clusters;
	}

	/**
	 * Crea un archivo en word con el reporte generado.
	 */
	public void crearReporte() {
		JFileChooser chooser = new JFileChooser("reporte.doc");
		chooser.addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "*.doc";
			}
			
			@Override
			public boolean accept(File f) {
				 if (f.isDirectory())
		          {
		            return false;
		          }

		         String s = f.getName();

		        return s.endsWith(".doc");
			}
		});
		int res = chooser.showSaveDialog(this);
		if(res == JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			
			@SuppressWarnings("unused")
			Java2Word word = new Java2Word(this, file);
		}
		
	}

	/**
	 * Retorna el arreglo de características significativas.
	 * @return the sigCar El arreglo de características.
	 */
	public ArrayList<String> getSigCar() {
		return sigCar;
	}

	/**
	 * Cambia el arreglo de caracetrísticas siginificativas por el parámetro. 
	 * @param sigCar
	 *            El nuevo arreglo.
	 */
	public void setSigCar(ArrayList<String> sigCar) {
		this.sigCar = sigCar;
	}

	/**
	 * Retorna el arreglo de promedio de las características significativas.
	 * @return the avgCar El arreglo de promedios.
	 */
	public String[] getAvgCar() {
		return avgCar;
	}

	/**
	 * Cambia el arreglo de promedios de caracetrísticas siginificativas por el parámetro
	 * @param avgCar
	 *            El nuevo arreglo
	 */
	public void setAvgCar(String[] avgCar) {
		this.avgCar = avgCar;
	}

	/**
	 * Retorna el arreglo de las dispersiones de las características significativas.
	 * @return the dispCar El arreglo de dispersiones.
	 */
	public ArrayList<String> getDispCar() {
		return dispCar;
	}

	/**
	 * Cambia el arreglo de dispersiones de caracetrísticas siginificativas por el parámetro
	 * @param dispCar 
	 *            El nuevo arreglo de dispersiones.
	 */
	public void setDispCar(ArrayList<String> dispCar) {
		this.dispCar = dispCar;
	}

	/**
	 * Retorna el arreglo de clústeres.
	 * @return the arrCluesters El arreglo de clústeres.
	 */
	public ArrayList<Cluster> getArrCluesters() {
		return arrCluesters;
	}

	/**
	 * Cambia el arreglo de clústeres.
	 * @param arrCluesters El nuevo arreglo de clústeres. 
	 */
	public void setArrCluesters(ArrayList<Cluster> arrCluesters) {
		this.arrCluesters = arrCluesters;
	}

	public double[] getSumaCuadrados() {
		return sumaCuadrados;
	}

	public void setSumaCuadrados(double[] sumaCuadrados) {
		this.sumaCuadrados = sumaCuadrados;
	}

}