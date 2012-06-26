/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sánchez - 8/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

/**
 * Esta clase representa el menú de la aplicación del punto de venta
 */
public class BarraProgreso extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String SALIR = "salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del punto de venta
     */
    private InterfazPrograma ventanaPrincipal;

    
    private JProgressBar progreso;
    
    private JPanel info;
    

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el menú e inicializa sus componentes
     * @param ipv Es una referencia a la ventana principal del punto de venta
     */
    public BarraProgreso( InterfazPrograma ipv )
    {
        ventanaPrincipal = ipv;
        setLayout(new BorderLayout());
        progreso = new JProgressBar(0,100);
        progreso.setPreferredSize(new Dimension(600,10));
        
        JLabel text = new JLabel("Progreso");
        text.setHorizontalAlignment(SwingConstants.CENTER);
        add(text,BorderLayout.NORTH);
        info = new JPanel(new BorderLayout());
        JPanel prog = new JPanel(new FlowLayout());
        prog.add(progreso);
        info.add(prog,BorderLayout.CENTER);
        info.add(new JLabel("\n"),BorderLayout.SOUTH);
        add(new JLabel("Tipo de análisis"),BorderLayout.SOUTH);
        
        add(info,BorderLayout.CENTER);
        
        
        
        

        }
    
   
    
    public void refrescar(){
    	String paso = ventanaPrincipal.darPaso();
    	if(paso.equalsIgnoreCase("Tipo de Analisis")){
    		progreso.setValue(0);
    		progreso.repaint();
    		Graphics g = getGraphics();
    		g.drawLine(45,35,45,45);
    		
    	}else if(paso.equalsIgnoreCase("Informacion Mercado")){
    		progreso.setValue(20);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    		progreso.setValue(40);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    		progreso.setValue(60);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    		progreso.setValue(80);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resumen")){
    		progreso.setValue(90);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resultados")){
    		progreso.setValue(100);
    		progreso.repaint();
    	}
    		
    	
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

   
}