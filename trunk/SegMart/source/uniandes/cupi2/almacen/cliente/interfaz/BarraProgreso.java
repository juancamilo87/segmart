/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario S�nchez - 8/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

/**
 * Esta clase representa el men� de la aplicaci�n del punto de venta
 */
public class BarraProgreso extends JPanel
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
    
    private JPanel panelLbl;
    
    private JLabel lblTpo1;
    
    private JLabel lblTpo2;

    private JLabel lblTpo3;
    
    private JLabel lblTpo4;
    
    private JLabel lblTpo5;
    
    private JLabel lblTpo6;
    
    private JLabel lblTpo7;

	private JLabel lblTpo8;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el men� e inicializa sus componentes
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
        lblTpo1 = new JLabel("Tipo de Analisis");
        lblTpo2 = new JLabel("Informacion Mercado");
        lblTpo3 = new JLabel("Estilo de Vida");
        lblTpo4 = new JLabel("Intencion de Compra");
        lblTpo5 = new JLabel("Caracteristicas de Productos");
        lblTpo6 = new JLabel("Resumen");
        lblTpo7 = new JLabel("Resultados");
        lblTpo8 = new JLabel("Informaci'on General");
        lblTpo8.setFont(new Font("Default", Font.PLAIN, 8));
        lblTpo2.setVisible(false);
        lblTpo3.setVisible(false);
        lblTpo4.setVisible(false);
        lblTpo5.setVisible(false);
        lblTpo6.setVisible(false);
        lblTpo7.setVisible(false);
        lblTpo8.setVisible(false);
        int y = 5;
        panelLbl = new JPanel();
        panelLbl.setLayout(new MigLayout());
        panelLbl.add(lblTpo1, "pos 8 "+y);
        panelLbl.add(lblTpo2, "pos 110 "+y);
        panelLbl.add(lblTpo8, "pos 110 20");
        panelLbl.add(lblTpo2, "pos 110 "+y);
        panelLbl.add(lblTpo3, "pos 20 "+y);
        panelLbl.add(lblTpo4, "pos 20 "+y);
        panelLbl.add(lblTpo5, "pos 20 "+y);
        panelLbl.add(lblTpo6, "pos 20 "+y);
        panelLbl.add(lblTpo7, "pos 20 "+y);        
        add(panelLbl,BorderLayout.SOUTH);        
        add(info,BorderLayout.CENTER);
       }
    
   
    
    public void refrescar(){
    	String paso = ventanaPrincipal.darPaso();
    	String tipo = ventanaPrincipal.darTipo();
    	
    	if(paso.equalsIgnoreCase("Tipo de Analisis")){
    		lblTpo2.setVisible(false);
    		lblTpo3.setVisible(false);
    		lblTpo4.setVisible(false);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo8.setVisible(false);
    		progreso.setValue(0);
    		progreso.repaint();
    		Graphics g = getGraphics();
    		g.drawLine(45,35,45,45);
    		
    	}else if(paso.equalsIgnoreCase("Informacion Mercado")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(false);
    		lblTpo4.setVisible(false);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo8.setVisible(false);
    		progreso.setValue(20);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(false);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo8.setVisible(true);
    		progreso.setValue(40);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		progreso.setValue(60);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		progreso.setValue(80);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resumen")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(true);
    		lblTpo7.setVisible(false);
    		progreso.setValue(90);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resultados")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(true);
	    		lblTpo7.setVisible(true);
	    		progreso.setValue(100);
	    		progreso.repaint();
	    	}
	    		
	    	
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

   
}