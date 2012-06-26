/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario S�nchez - 25/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Es el panel que contiene los botones para ejecutar los puntos de extensi�n
 */
public class PanelInformacion extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el bot�n 1
     */
    private final String OPCION_1 = "opcion 1";

    /**
     * El comando para el bot�n 2
     */
    private final String OPCION_2 = "opcion 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia a la interfaz de la aplicaci�n
     */
    private InterfazPrograma ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el bot�n 1
     */
    private JButton botonOpcion1;

    /**
     * Es el bot�n 2
     */
    private JButton botonOpcion2;
    
  

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con una referencia a la ventana principal de la aplicaci�n <br>
     * <b>post: </b> Construy� el panel <br>
     * @param ip - Referencia a la ventana principal - ip!=null
     */
    public PanelInformacion( InterfazPrograma ip )
    {
        ventanaPrincipal = ip;
        inicializar( );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Inicializa los componentes del panel <br>
     * <b>post: </b> Se inicializaron y se ubicaron los componentes del panel <br>
     */
    private void inicializar( )
    {
        
        
        this.removeAll();
        
        
        if(ventanaPrincipal.darPaso().equalsIgnoreCase("Tipo de Analisis")){
        JPanel informacion = new JPanel();
        
       
    	informacion.setBorder( new TitledBorder( "Tipo de An�lisis" ) );
    	

        informacion.setLayout( new BorderLayout( ) );
        
        JLabel info = new JLabel("Seleccionar un tipo de an�lisis");
        
        JPanel arriba = new JPanel(new FlowLayout());
        arriba.add(info);
        informacion.add(arriba,BorderLayout.NORTH);
        JRadioButton busqueda = new JRadioButton("B�squeda de mercado para un producto");
        JRadioButton creacion = new JRadioButton("Creaci�n de un nuevo producto");
        busqueda.setActionCommand("BUSQUEDA");
        busqueda.addActionListener(this);
        creacion.setActionCommand("CREACION");
        creacion.addActionListener(this);
        ButtonGroup opciones = new ButtonGroup();
        opciones.add(busqueda);
        opciones.add(creacion);
        JPanel radioGroup = new JPanel(new GridLayout(0,1));
        radioGroup.add(busqueda);
        radioGroup.add(creacion);
        informacion.add(radioGroup,BorderLayout.CENTER);
        informacion.setPreferredSize(new Dimension(350,120));
        add(informacion);
        
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Informacion Mercado")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Informaci�n Mercado" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar informaci�n general de mercado");
        	JPanel arriba = new JPanel(new FlowLayout());
            arriba.add(info);
            informacion.add(arriba,BorderLayout.NORTH);
            
            JButton crearArchivo = new JButton("Crear archivo base");
            crearArchivo.setActionCommand("CREAR_ARCHIVO");
            crearArchivo.addActionListener(this);
            JPanel boton = new JPanel(new FlowLayout());
            boton.add(crearArchivo);
            informacion.add(boton, BorderLayout.CENTER);
            JPanel sur = new JPanel();
            JTextField texto_mercado = new JTextField();
            texto_mercado.setEditable(false);
            texto_mercado.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            sur.add(texto_mercado);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Estilo de Vida")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Informaci�n Mercado" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar informaci�n de estilo de vida de mercado");
        	JPanel arriba = new JPanel(new FlowLayout());
            arriba.add(info);
            informacion.add(arriba,BorderLayout.NORTH);
            
            JButton crearArchivo = new JButton("Crear archivo base");
            crearArchivo.setActionCommand("CREAR_ARCHIVO");
            crearArchivo.addActionListener(this);
            JPanel boton = new JPanel(new FlowLayout());
            boton.add(crearArchivo);
            informacion.add(boton, BorderLayout.CENTER);
            JPanel sur = new JPanel();
            JTextField texto_mercado = new JTextField();
            texto_mercado.setEditable(false);
            texto_mercado.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            sur.add(texto_mercado);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
        		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Intencion de Compra")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Informaci�n Consumo" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar informaci�n de intenci�n de compra");
        	JPanel arriba = new JPanel(new FlowLayout());
            arriba.add(info);
            informacion.add(arriba,BorderLayout.NORTH);
            
            JButton crearArchivo = new JButton("Crear archivo base");
            crearArchivo.setActionCommand("CREAR_ARCHIVO");
            crearArchivo.addActionListener(this);
            JPanel boton = new JPanel(new FlowLayout());
            boton.add(crearArchivo);
            informacion.add(boton, BorderLayout.CENTER);
            JPanel sur = new JPanel();
            JTextField texto_mercado = new JTextField();
            texto_mercado.setEditable(false);
            texto_mercado.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            sur.add(texto_mercado);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Caracteristicas de Productos")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Informaci�n Consumo" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar caracter�sticas de productos de la categor�a");
        	JPanel arriba = new JPanel(new FlowLayout());
            arriba.add(info);
            informacion.add(arriba,BorderLayout.NORTH);
            
            JButton crearArchivo = new JButton("Crear archivo base");
            crearArchivo.setActionCommand("CREAR_ARCHIVO");
            crearArchivo.addActionListener(this);
            JPanel boton = new JPanel(new FlowLayout());
            boton.add(crearArchivo);
            informacion.add(boton, BorderLayout.CENTER);
            JPanel sur = new JPanel();
            JTextField texto_mercado = new JTextField();
            texto_mercado.setEditable(false);
            texto_mercado.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            sur.add(texto_mercado);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
        	add(informacion);
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resumen")){
        	
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resultados")){
    		
        }
        
        
        this.updateUI();
    }
    
    public void refrescar(){
    	inicializar();
    }

    /**
     * Este m�todo se llama cuando se presiona uno de los botones <br>
     * <b>post: </b> Se ejecut� la acci�n correspondiente al bot�n presionado <br>
     * @param event El evento del click en el bot�n
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if(comando.equalsIgnoreCase("BUSQUEDA")||comando.equalsIgnoreCase("CREACION")){
        	ventanaPrincipal.cambiarTipoAnalisis(comando);
        }
        
        
    }
}
