/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$ 
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sánchez - 25/08/2005 
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * Es el panel que contiene los botones para ejecutar los puntos de extensión
 */
public class PanelInformacion extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el botón 1
     */
    private final String OPCION_1 = "opcion 1";

    /**
     * El comando para el botón 2
     */
    private final String OPCION_2 = "opcion 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia a la interfaz de la aplicación
     */
    private InterfazPrograma ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón 1
     */
    private JButton botonOpcion1;

    /**
     * Es el botón 2
     */
    private JButton botonOpcion2;
    
    private JTextField rutaInfoGen;
    
    private JTextField rutaEstilo;
    
    private JTextField rutaIntencion;
    
    private JTextField rutaCaract;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con una referencia a la ventana principal de la aplicación <br>
     * <b>post: </b> Construyó el panel <br>
     * @param ip - Referencia a la ventana principal - ip!=null
     */
    public PanelInformacion( InterfazPrograma ip )
    {
        ventanaPrincipal = ip;
        inicializar( );
    }

    // -----------------------------------------------------------------
    // Métodos
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
        
       
    	informacion.setBorder( new TitledBorder( "Tipo de Análisis" ) );
    	

        informacion.setLayout( new BorderLayout( ) );
        
        JLabel info = new JLabel("Seleccionar un tipo de análisis");
        
        JPanel arriba = new JPanel(new FlowLayout());
        arriba.add(info);
        informacion.add(arriba,BorderLayout.NORTH);
        JRadioButton busqueda = new JRadioButton("Búsqueda de mercado para un producto");
        JRadioButton creacion = new JRadioButton("Creación de un nuevo producto");
        busqueda.setActionCommand("BUSQUEDA");
        busqueda.addActionListener(this);
        creacion.setActionCommand("CREACION");
        creacion.addActionListener(this);
        ButtonGroup opciones = new ButtonGroup();
        if(ventanaPrincipal.darTipo().equalsIgnoreCase("BUSQUEDA"))
        	busqueda.setSelected(true);
        else if(ventanaPrincipal.darTipo().equalsIgnoreCase("CREACION"))
        	creacion.setSelected(true);
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
        	informacion.setBorder( new TitledBorder( "Información Mercado" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar información general de mercado");
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
            rutaInfoGen = new JTextField();
            rutaInfoGen.setEditable(false);
            rutaInfoGen.setPreferredSize(new Dimension(200,25));
            rutaInfoGen.setText(ventanaPrincipal.getRutaInfoGen());
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            examinar.setActionCommand("EXAMINAR");
            examinar.addActionListener(this);
            
            
            
            
            sur.add(rutaInfoGen);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Estilo de Vida")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Información Mercado" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar información de estilo de vida de mercado");
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
            rutaEstilo = new JTextField();
            rutaEstilo.setEditable(false);
            rutaEstilo.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            examinar.setActionCommand("EXAMINARE");
            examinar.addActionListener(this);
            sur.add(rutaEstilo);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
        		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Intencion de Compra")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Información Consumo" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar información de intención de compra");
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
            rutaIntencion = new JTextField();
            rutaIntencion.setEditable(false);
            rutaIntencion.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            examinar.setActionCommand("EXAMINARI");
            examinar.addActionListener(this);
            sur.add(rutaIntencion);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
            
        	add(informacion);
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Caracteristicas de Productos")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Información Consumo" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	JLabel info = new JLabel("Importar características de productos de la categoría");
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
            rutaCaract = new JTextField();
            rutaCaract.setEditable(false);
            rutaCaract.setPreferredSize(new Dimension(200,25));
            sur.setLayout(new FlowLayout());
            JButton examinar = new JButton("Examinar");
            examinar.setActionCommand("EXAMINARC");
            examinar.addActionListener(this);
            sur.add(rutaCaract);
            sur.add(examinar);
            informacion.add(sur,BorderLayout.SOUTH);
            informacion.setPreferredSize(new Dimension(350,120));
        	add(informacion);
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resumen")){
        	JPanel informacion = new JPanel();
        	informacion.setBorder( new TitledBorder( "Resumen" ) );
        	informacion.setLayout( new BorderLayout( ) );
        	
        	
        	JPanel centro = new JPanel();
        	centro.setLayout(new BoxLayout(centro, BoxLayout.PAGE_AXIS));
        	FlowLayout gl = new FlowLayout();
        	gl.setHgap(10);
        	JPanel tipo = new JPanel(gl);
        	JLabel lTipo = new JLabel("Tipo de análisis:");
        	String sTipo = ventanaPrincipal.darTipo();
        	JLabel rTipo;
        	if(sTipo.equals("BUSQUEDA"))
        		rTipo = new JLabel("Búsqueda de Mercado");
        	else
        		rTipo = new JLabel("Creación Producto");
        	tipo.add(lTipo);
        	tipo.add(rTipo);
        	
        	centro.add(tipo);
        	
        	GridLayout glm = new GridLayout(2,2);
        	glm.setHgap(10);
        	JPanel mercado = new JPanel(glm);
        	JLabel lMercado = new JLabel("Información General:");
        	JLabel rMercado = new JLabel(ventanaPrincipal.getRutaInfoGen());
        	JLabel lEstilo = new JLabel("Estilo de vida:");
        	JLabel rEstilo = new JLabel(ventanaPrincipal.getRutaEstilo());
        	rMercado.setToolTipText(ventanaPrincipal.getRutaInfoGen());
        	rEstilo.setToolTipText(ventanaPrincipal.getRutaEstilo());
        	mercado.add(lMercado);
        	mercado.add(rMercado);
        	mercado.add(lEstilo);
        	mercado.add(rEstilo);
        	
        	
        	glm.setHgap(10);
        	JPanel producto = new JPanel(glm);
        	JLabel lIntencion = new JLabel("Intención de compra:");
        	JLabel rIntencion = new JLabel(ventanaPrincipal.getRutaIntencion());
        	JLabel lProducto = new JLabel("Características de productos:");
        	JLabel rProducto = new JLabel(ventanaPrincipal.getRutaCaract());
        	rIntencion.setToolTipText(ventanaPrincipal.getRutaIntencion());
        	rProducto.setToolTipText(ventanaPrincipal.getRutaCaract());
        	producto.add(lIntencion);
        	producto.add(rIntencion);
        	producto.add(lProducto);
        	producto.add(rProducto);
        	JTabbedPane tp = new JTabbedPane();
        	tp.addTab("Información Mercado", mercado);
        	tp.addTab("Información Consumo", producto);
        	centro.add(tp);
        	informacion.add(centro,BorderLayout.CENTER);
        	informacion.setPreferredSize(new Dimension(350,120));
        	add(informacion);
        	
        	
    		
        }else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resultados")){
        	GridLayout gl = new GridLayout(0,1);
        	gl.setVgap(2);
        	JPanel informacion = new JPanel(gl);
        	
        	informacion.setBorder( new TitledBorder( "Resultados" ) );
        	
        	JPanel informacion1 = new JPanel(new FlowLayout());
        	JPanel informacion2 = new JPanel(new FlowLayout());
        	JPanel informacion3 = new JPanel(new FlowLayout());
        	
        	
        	JLabel info = new JLabel("Análisis exitoso");
        	JButton word = new JButton("Crear reporte en Word");
        	JButton excel = new JButton("Crear reporte en Excel");
        	
        	informacion1.add(info);
        	informacion2.add(word);
        	informacion3.add(excel);
        	informacion.setPreferredSize(new Dimension(350,120));
        	informacion.add(informacion1);
        	informacion.add(informacion2);
        	informacion.add(informacion3);
        	add(informacion);
        }
        
        
        this.updateUI();
    }
    
    public void refrescar(){
    	inicializar();
    }

    /**
     * Este método se llama cuando se presiona uno de los botones <br>
     * <b>post: </b> Se ejecutó la acción correspondiente al botón presionado <br>
     * @param event El evento del click en el botón
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if(comando.equalsIgnoreCase("BUSQUEDA")||comando.equalsIgnoreCase("CREACION")){
        	ventanaPrincipal.cambiarTipoAnalisis(comando);
        }
        else if (comando.equalsIgnoreCase("EXAMINAR")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				ventanaPrincipal.setRutaInfoGen(file.getPath());
				rutaInfoGen.setText(ventanaPrincipal.getRutaInfoGen());
				// This is where a real application would open the file.

			}
		}

        else if (comando.equalsIgnoreCase("EXAMINARE")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				ventanaPrincipal.setRutaEstilo(file.getPath());
				rutaEstilo.setText(ventanaPrincipal.getRutaEstilo());
				// This is where a real application would open the file.

			}
		}

        else if (comando.equalsIgnoreCase("EXAMINARI")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				ventanaPrincipal.setRutaIntencion(file.getPath());
				rutaIntencion.setText(ventanaPrincipal.getRutaIntencion());
				// This is where a real application would open the file.

			}
		}

        else if (comando.equalsIgnoreCase("EXAMINARC")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				ventanaPrincipal.setRutaCaract(file.getPath());
				rutaCaract.setText(ventanaPrincipal.getRutaCaract());
				// This is where a real application would open the file.

			}
		}
        
        
    }
}
