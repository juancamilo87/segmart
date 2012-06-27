/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sánchez - 06-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



/**
 * Esta es la ventana principal de la aplicación.
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
     * Es la barra con el menú para la aplicación
     */
    private PanelInformacion panelInformacion;

    /**
     * Es el panel con los botones para los puntos de extensión
     */
    private PanelBotones panelBotones;
    
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     * @param archivo El archivo de propiedades con la configuración para el punto de venta
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    public InterfazPrograma(  )
    {
        // Crea la clase principal
//    	datos = new Datos();
    	paso = "Tipo de Analisis";
    	tipo_analisis="";
        construirForma( );
        
//        construirMenu( );
//        conectar( );
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Este método sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana está vacía <br>
     * <b>post: </b> Se inicializaron los componentes gráficos de la aplicación
     */
    private void construirForma( )
    {
        // organizar el panel principal
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
        setSize( 700, 300);
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Segmentacion" );
    }

    

    /**
     * Este es el método llamado cuando se cierra la ventana principal. <br>
     * Este método desconecta el punto de venta del almacén y cierra la aplicación.
     */
    public void dispose( )
    {
        super.dispose( );
        
    }
    
    public void cambiarTipoAnalisis(String tipo){
    	tipo_analisis=tipo;
    }
    
    public String darPaso(){
    	return paso;
    }
    
    public void refrescar(String accion){
    	
    	if(accion.equalsIgnoreCase("Siguiente"))
    	{
    		if(paso.equalsIgnoreCase("Tipo de Analisis")){
    			paso = "Informacion Mercado";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    			
    		}else if(paso.equalsIgnoreCase("Informacion Mercado")){
    			paso = "Estilo de Vida";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    			paso = "Intencion de Compra";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    			paso = "Caracteristicas de Productos";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    			paso = "Resumen";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Resumen")){
    			paso = "Resultados";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}
    		
    	}
    	else if(accion.equalsIgnoreCase("Anterior"))
    	{
    	
    		if(paso.equalsIgnoreCase("Informacion Mercado")){
    			paso = "Tipo de Analisis";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    			paso = "Informacion Mercado";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    			paso = "Estilo de Vida";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    			paso = "Intencion de Compra";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Resumen")){
    			paso = "Caracteristicas de Productos";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}else if(paso.equalsIgnoreCase("Resultados")){
    			paso = "Resumen";
    			barraProgreso.refrescar();
    			panelInformacion.refrescar();
    			panelBotones.refrescar(paso,tipo_analisis);
    		}
    		
    	}
    }
    

    

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Método para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = "resultado1";
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Método para la extensión 2
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
     * Este método ejecuta la aplicación, creando una nueva interfaz
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
}