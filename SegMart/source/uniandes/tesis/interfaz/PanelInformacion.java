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

package uniandes.tesis.interfaz;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
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

    private static final String WORD = "Word";

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
    
    private JTextField rutaInfoGen;
    
    private JTextField rutaEstilo;
    
    private JTextField rutaIntencion;
    
    private JTextField rutaCaract;

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
     * Inicializa y refresca todo lo que hace el programa componentes del panel <br>
     * <b>post: </b> Se inicializaron y se ubicaron los componentes del panel <br>
     */
    private void inicializar( )
    {
        
        
        this.removeAll();
        
        
        if(ventanaPrincipal.darPaso().equalsIgnoreCase("Tipo de Analisis")){
        JPanel informacion = new JPanel();
        
       
    	informacion.setBorder( new TitledBorder( "Tipo de An�lisis" ) );
    	

        informacion.setLayout( new BorderLayout( ) );
        
//        JLabel info = new JLabel("Seleccionar un tipo de an�lisis");
//        
//        JPanel arriba = new JPanel(new FlowLayout());
//        arriba.add(info);
//        informacion.add(arriba,BorderLayout.NORTH);
        JRadioButton busqueda = new JRadioButton("B�squeda de mercado para un producto");
        JRadioButton creacion = new JRadioButton("Creaci�n de un nuevo producto");
        busqueda.setActionCommand("BUSQUEDA");
        busqueda.addActionListener(this);
        creacion.setActionCommand("CREACION");
        creacion.addActionListener(this);
        ButtonGroup opciones = new ButtonGroup();
        if(ventanaPrincipal.darTipo().equalsIgnoreCase("BUSQUEDA"))
        	busqueda.setSelected(true);
        else if(ventanaPrincipal.darTipo().equalsIgnoreCase("CREACION"))
        	creacion.setSelected(true);
        JLabel label = new JLabel("An�lsis de mercado para creaci�n de un nuevo producto.");
        opciones.add(busqueda);
        opciones.add(creacion);
        JPanel radioGroup = new JPanel(new GridLayout(0,1));
//        radioGroup.add(busqueda);
//        radioGroup.add(creacion);
        radioGroup.add(label);
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
            rutaInfoGen = new JTextField();
            rutaInfoGen.setEditable(false);
            rutaInfoGen.setPreferredSize(new Dimension(200,25));
            rutaInfoGen.setText(ventanaPrincipal.getRutaInfoGen());
            rutaInfoGen.setToolTipText(ventanaPrincipal.getRutaInfoGen());
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
            rutaEstilo = new JTextField();
            rutaEstilo.setEditable(false);
            rutaEstilo.setPreferredSize(new Dimension(200,25));
            rutaEstilo.setText(ventanaPrincipal.getRutaEstilo());
            rutaEstilo.setToolTipText(ventanaPrincipal.getRutaEstilo());
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
            rutaIntencion = new JTextField();
            rutaIntencion.setEditable(false);
            rutaIntencion.setPreferredSize(new Dimension(200,25));
            rutaIntencion.setText(ventanaPrincipal.getRutaIntencion());
            rutaIntencion.setToolTipText(ventanaPrincipal.getRutaIntencion());
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
            rutaCaract = new JTextField();
            rutaCaract.setEditable(false);
            rutaCaract.setPreferredSize(new Dimension(200,25));
            rutaCaract.setText(ventanaPrincipal.getRutaCaract());
            rutaCaract.setToolTipText(ventanaPrincipal.getRutaCaract());
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
        	JLabel lTipo = new JLabel("Tipo de an�lisis:");
        	String sTipo = ventanaPrincipal.darTipo();
        	JLabel rTipo;
        	if(sTipo.equals("BUSQUEDA"))
        		rTipo = new JLabel("B�squeda de Mercado");
        	else
        		rTipo = new JLabel("Creaci�n Producto");
        	tipo.add(lTipo);
        	tipo.add(rTipo);
        	
        	centro.add(tipo);
        	
        	GridLayout glm = new GridLayout(2,2);
        	glm.setHgap(10);
        	JPanel mercado = new JPanel(glm);
        	JLabel lMercado = new JLabel("Informaci�n General:");
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
        	JLabel lIntencion = new JLabel("Intenci�n de compra:");
        	JLabel rIntencion = new JLabel(ventanaPrincipal.getRutaIntencion());
        	JLabel lProducto = new JLabel("Caracter�sticas de productos:");
        	JLabel rProducto = new JLabel(ventanaPrincipal.getRutaCaract());
        	rIntencion.setToolTipText(ventanaPrincipal.getRutaIntencion());
        	rProducto.setToolTipText(ventanaPrincipal.getRutaCaract());
        	producto.add(lIntencion);
        	producto.add(rIntencion);
        	producto.add(lProducto);
        	producto.add(rProducto);
        	JTabbedPane tp = new JTabbedPane();
        	tp.addTab("Informaci�n Mercado", mercado);
        	tp.addTab("Informaci�n Consumo", producto);
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
        	
        	
        	JLabel info = new JLabel("An�lisis exitoso");
        	JButton word = new JButton("Crear reporte en Word");
        	word.setActionCommand(WORD);
        	word.addActionListener(this);
        	informacion1.add(info);
        	informacion2.add(word);
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
    
    public String copiarArchivo(String ruta){
    	File fuente = new File(ruta);
    	String nombre = fuente.getName().substring(0,fuente.getName().lastIndexOf("."));
    	nombre = nombre+"mod";
    	String ext = fuente.getName().substring(fuente.getName().lastIndexOf("."));
    	int index = ruta.lastIndexOf("/");
    	File destino = new File(ruta.substring(0, index)+"/"+nombre+ext);
    	if(!destino.exists())
    	{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            finally{
        
            if(source != null) {
                try {
					source.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            if(destination != null) {
                try {
					destination.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            }
            try {
				return destino.getCanonicalPath();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	return destino.getPath();
    	
    	
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
        else if (comando.equalsIgnoreCase("EXAMINAR")) {
			
        	JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				if(file.exists()&&file.getPath()!=null){
				ventanaPrincipal.setRutaInfoGen(file.getPath());
				rutaInfoGen.setText(ventanaPrincipal.getRutaInfoGen());
				// This is where a real application would open the file.
				}
				else{
					JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo", "Seleccionar Archivo", JOptionPane.INFORMATION_MESSAGE);
					
				}
			}
			
		}

        else if (comando.equalsIgnoreCase("EXAMINARE")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				if(file.exists()){
				ventanaPrincipal.setRutaEstilo(file.getPath());
				rutaEstilo.setText(ventanaPrincipal.getRutaEstilo());
				// This is where a real application would open the file.
			}
			else{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo", "Seleccionar Archivo", JOptionPane.INFORMATION_MESSAGE);
				
			}
			}
		}

        else if (comando.equalsIgnoreCase("EXAMINARI")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				if(file.exists()){
				try {
					ventanaPrincipal.setRutaIntencion(file.getCanonicalPath());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rutaIntencion.setText(ventanaPrincipal.getRutaIntencion());
				// This is where a real application would open the file.
			}
			else{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo", "Seleccionar Archivo", JOptionPane.INFORMATION_MESSAGE);
				
			}
			}
		}

        else if (comando.equalsIgnoreCase("EXAMINARC")) {
			JFileChooser jfc = new JFileChooser();
			int returnval = jfc.showOpenDialog(this);
			if (returnval == JFileChooser.APPROVE_OPTION) {
				File file = jfc.getSelectedFile();
				if(file.exists()){
				try {
					ventanaPrincipal.setRutaCaract(file.getCanonicalPath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				rutaCaract.setText(ventanaPrincipal.getRutaCaract());
				// This is where a real application would open the file.
			}
			else{
				JOptionPane.showMessageDialog(this,"Debe seleccionar un archivo", "Seleccionar Archivo", JOptionPane.INFORMATION_MESSAGE);
				
			}
			}
		}
        else if (comando.equalsIgnoreCase("CREAR_ARCHIVO")){
        	JOptionPane.showMessageDialog(this,"Ingrese los datos a analizar y guarde el archivo en la direcci�n predeterminada.","Archivo base",JOptionPane.INFORMATION_MESSAGE);
        	if(ventanaPrincipal.darPaso().equalsIgnoreCase("Informacion Mercado")){
        		String nuevo_archivo = copiarArchivo("docs/Base Info General.xls");
        		try {  
        		     Desktop.getDesktop().open(new File(nuevo_archivo));  
        		} catch (IOException e) {e.printStackTrace();}
        		
        		ventanaPrincipal.setRutaInfoGen(nuevo_archivo);
        		rutaInfoGen.setText(ventanaPrincipal.getRutaInfoGen());
        		
        	}
        	else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Estilo de Vida")){
        		String nuevo_archivo = copiarArchivo("docs/Base Info Estilo.xls");
        		try {  
        		     Desktop.getDesktop().open(new File(nuevo_archivo));  
        		} catch (IOException e) {e.printStackTrace();}
        		
        		ventanaPrincipal.setRutaEstilo(nuevo_archivo);
        		rutaEstilo.setText(ventanaPrincipal.getRutaEstilo());
        		
        	}
        	else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Intencion de Compra")){
        		String nuevo_archivo = copiarArchivo("docs/Base Info Intencion.xls");
        		try {  
        		     Desktop.getDesktop().open(new File(nuevo_archivo));  
        		} catch (IOException e) {e.printStackTrace();}
        		
        		ventanaPrincipal.setRutaIntencion(nuevo_archivo);
        		rutaIntencion.setText(ventanaPrincipal.getRutaIntencion());
        		
        	}
        	else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Caracteristicas de Productos")){
        		String nuevo_archivo = copiarArchivo("docs/Base Info Caracteristicas.xls");
        		try {  
        		     Desktop.getDesktop().open(new File(nuevo_archivo));  
        		} catch (IOException e) {e.printStackTrace();}
        		
        		ventanaPrincipal.setRutaCaract(nuevo_archivo);
        		rutaCaract.setText(ventanaPrincipal.getRutaCaract());
        		
        	}
        }
        else if (comando.equalsIgnoreCase(WORD))
        {
        	ventanaPrincipal.crearReporte();
        }
        
        
    }
}
