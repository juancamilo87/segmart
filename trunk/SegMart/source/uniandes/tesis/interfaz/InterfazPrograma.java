/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario S�nchez - 06-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.tesis.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.ss.usermodel.Sheet;

import uniandes.tesis.mundo.Excel2CSV;



/**
 * Esta es la ventana principal de la aplicaci�n.
 */
public class InterfazPrograma extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
//    private Datos datos;
	
	private String tipo_analisis;
	
	private String paso;
	
	private String rutaInfoGen;
	
	private String rutaEstilo;
	
	private String rutaIntencion;
	
	private String rutaCaract;
	
	private Excel2CSV excel2csv;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel que se usa para registrar un producto
     */
    private PanelLogo panelLogo;

    /**
     * Es el panel donde se muestran los botones del punto de venta
     */
    private BarraProgreso barraProgreso;

    /**
     * Es la barra con el men� para la aplicaci�n
     */
    private PanelInformacion panelInformacion;

    /**
     * Es el panel con los botones para los puntos de extensi�n
     */
    private PanelBotones panelBotones;
    
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripci�n <br>
     * <b>post: </b> Descripci�n
     * @param archivo El archivo de propiedades con la configuraci�n para el punto de venta
     * @throws Exception Se lanza esta excepci�n si hay problemas cargando el archivo de propiedades
     */
    public InterfazPrograma(  )
    {
        // Crea la clase principal
//    	datos = new Datos();
    	paso = "Tipo de Analisis";
    	tipo_analisis="";
        construirForma( );
        excel2csv = new Excel2CSV();
//        construirMenu( );
//        conectar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Este m�todo sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana est� vac�a <br>
     * <b>post: </b> Se inicializaron los componentes gr�ficos de la aplicaci�n
     */
    private void construirForma( )
    {
        // organizar el panel principal
    	rutaInfoGen="";
    	rutaIntencion="";
    	rutaEstilo="";
    	rutaCaract="";
    	BorderLayout orden = new BorderLayout();
    	orden.setHgap(10);
    	orden.setVgap(10);
    	
        JPanel panelContenedor = new JPanel( orden );
        panelLogo = new PanelLogo( this );
        add( panelLogo, BorderLayout.NORTH);
        barraProgreso = new BarraProgreso( this );
        panelContenedor.add(barraProgreso,BorderLayout.NORTH);
        panelInformacion = new PanelInformacion(this);
        panelContenedor.add(panelInformacion,BorderLayout.CENTER);
        add( panelContenedor, BorderLayout.CENTER );
        panelBotones = new PanelBotones( this );
        
        add( panelBotones, BorderLayout.SOUTH );
        setSize( 700, 350);
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Segmentacion" );
    }

    

    /**
     * Este es el m�todo llamado cuando se cierra la ventana principal. <br>
     * Este m�todo desconecta el punto de venta del almac�n y cierra la aplicaci�n.
     */
    public void dispose( )
    {
        super.dispose( );
        
    }
    
    public void cambiarTipoAnalisis(String tipo){
    	panelBotones.cambiarListo();
    	tipo_analisis=tipo;
    }
    
    public String darPaso(){
    	return paso;
    }
    
    public boolean verificarArchivo(String ruta){
    	boolean correct = false;
    	Sheet sheet;
		try {
			sheet = excel2csv.returnSheet(ruta);
			correct = excel2csv.verificarFormatoArchivo(sheet);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
    	return correct;
    }
    
    public void refrescar(String accion){
    	
    	if(accion.equalsIgnoreCase("Siguiente"))
    	{
    		if(paso.equalsIgnoreCase("Tipo de Analisis")){
    			paso = "Informacion Mercado";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    			
    		}else if(paso.equalsIgnoreCase("Informacion Mercado")){
    			
    			if(rutaInfoGen.equalsIgnoreCase(""))
    				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo.","Error",JOptionPane.ERROR_MESSAGE);
    			else if(verificarArchivo(rutaInfoGen)){
    				echoAsCSV(rutaInfoGen);
    				paso = "Estilo de Vida";
    				barraProgreso.refrescar();
    				panelInformacion.refrescar();
    				panelBotones.refrescar();}
    			else
    				JOptionPane.showMessageDialog(this,"El archivo ingresado no tiene el formato correcto.","Error",JOptionPane.ERROR_MESSAGE);
    		}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    			if(rutaEstilo.equalsIgnoreCase(""))
    				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo.","Error",JOptionPane.ERROR_MESSAGE);
    			else if(verificarArchivo(rutaEstilo)){
    				echoAsCSV(rutaEstilo);
    			paso = "Intencion de Compra";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    			}else
    				JOptionPane.showMessageDialog(this,"El archivo ingresado no tiene el formato correcto.","Error",JOptionPane.ERROR_MESSAGE);
    		}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    			if(rutaIntencion.equalsIgnoreCase(""))
    				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo.","Error",JOptionPane.ERROR_MESSAGE);
    			else if(verificarArchivo(rutaIntencion)){
    				echoAsCSV(rutaIntencion);
    			paso = "Caracteristicas de Productos";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    			}else
    				JOptionPane.showMessageDialog(this,"El archivo ingresado no tiene el formato correcto.","Error",JOptionPane.ERROR_MESSAGE);
    		}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    			if(rutaCaract.equalsIgnoreCase(""))
    				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo.","Error",JOptionPane.ERROR_MESSAGE);
    			else{
    				try {
    					File f1 = new File("./docs/Base Info Caracteristicas.csv");
    					String a = f1.getCanonicalPath();
    					File f2 = new File("./data/");
    					String b = f2.getCanonicalPath();
						Runtime.getRuntime().exec("cmd /c start " + f2.getCanonicalPath()+ "calling.vbs " + rutaCaract + " " + rutaIntencion.replace(".xls",".csv") + " " + f1.getCanonicalPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			if(verificarArchivo(rutaCaract)){
    				//Correr la macro
    			paso = "Resumen";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    			}else
    				JOptionPane.showMessageDialog(this,"El archivo ingresado no tiene el formato correcto.","Error",JOptionPane.ERROR_MESSAGE);
    		}else if(paso.equalsIgnoreCase("Resumen")){
    			paso = "Resultados";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}
    		
    	}
    	else if(accion.equalsIgnoreCase("Anterior"))
    	{
    	
    		if(paso.equalsIgnoreCase("Informacion Mercado")){
    			paso = "Tipo de Analisis";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    			paso = "Informacion Mercado";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    			paso = "Estilo de Vida";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    			paso = "Intencion de Compra";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}else if(paso.equalsIgnoreCase("Resumen")){
    			paso = "Caracteristicas de Productos";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}else if(paso.equalsIgnoreCase("Resultados")){
    			paso = "Resumen";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar();
    		}
    		
    	}
    	else if(accion.equalsIgnoreCase("Reiniciar")){
    		limpiar();
    		paso = "Tipo de Analisis";
    		barraProgreso.refrescar();
    		panelInformacion.refrescar();
    		panelBotones.refrescar();
    		panelBotones.cambiarListo2();
    		
    	}
    }
    

    

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * M�todo para la extensi�n 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = "resultado1";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * M�todo para la extensi�n 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = "resultado2";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    
    @PostConstruct
    public void refrescarProgreso() {
    	barraProgreso.refrescar();
        // populates the movie cache upon initialization...
    }
    public void progresoRefescar(){
    	paso = "Tipo de Analisis";
    	barraProgreso.refrescar();
    }
    public String darTipo(){
    	return tipo_analisis;
    }
    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
    	
    	InterfazPrograma interfaz = new InterfazPrograma();
    	interfaz.setVisible( true );
    	interfaz.setResizable(false);
    	
//        try
//        {
//            InterfazPrograma interfaz = new InterfazPrograma( "./data/puntoDeVenta.properties" );
//            
//        }
//        catch( Exception e )
//        {
//            System.out.println( e.getMessage( ) );
//        }
    }

	public String getRutaInfoGen() {
		return rutaInfoGen;
	}

	public void setRutaInfoGen(String rutaInfoGen) {
		this.rutaInfoGen = rutaInfoGen;
	}
	
	public void limpiar(){
		tipo_analisis="";
		paso="";
		rutaInfoGen="";
		rutaCaract="";
		rutaEstilo="";
		rutaIntencion="";
	}

	public String getRutaEstilo() {
		return rutaEstilo;
	}

	public void setRutaEstilo(String rutaEstilo) {
		this.rutaEstilo = rutaEstilo;
	}

	public String getRutaIntencion() {
		return rutaIntencion;
	}

	public void setRutaIntencion(String rutaIntencion) {
		this.rutaIntencion = rutaIntencion;
	}

	public String getRutaCaract() {
		return rutaCaract;
	}

	public void setRutaCaract(String rutaCaract) {
		this.rutaCaract = rutaCaract;
	}
	
	/**
	 * Creates a CSV file from an existing .xls file.
	 * @param path
	 */
	public void echoAsCSV(String path)
	{
		try 
		{
			excel2csv.echoAsCSV(path);
		} 
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}