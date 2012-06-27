package uniandes.tesis.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

/**
 * Esta clase representa el menú de la aplicación del punto de venta
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

	private JLabel lblTpo9;

	private JLabel lblTpo0;

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
        lblTpo1 = new JLabel("Tipo de Analisis");
        lblTpo0 = new JLabel();
        lblTpo0.setFont(new Font("Default", Font.PLAIN, 8));
        lblTpo2 = new JLabel("Informacion Mercado");
        lblTpo3 = new JLabel("Estilo de Vida");
        lblTpo3.setFont(new Font("Default", Font.PLAIN, 8));
        lblTpo4 = new JLabel("Información Consumo");
        lblTpo9 = new JLabel("Intención de Compra");
        lblTpo9.setFont(new Font("Default", Font.PLAIN, 8));
        lblTpo5 = new JLabel("Caracteristicas de Productos");
        lblTpo5.setFont(new Font("Default", Font.PLAIN, 8));
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
        lblTpo9.setVisible(false);
        lblTpo0.setVisible(false);
        int y = 5;
        panelLbl = new JPanel();
        panelLbl.setLayout(new MigLayout());
        panelLbl.add(lblTpo1, "pos 8 "+y);
        panelLbl.add(lblTpo2, "pos 110 "+y);
        panelLbl.add(lblTpo8, "pos 110 20");
        panelLbl.add(lblTpo2, "pos 110 "+y);
        panelLbl.add(lblTpo3, "pos 260 20");
        panelLbl.add(lblTpo4, "pos 350 "+y);
        panelLbl.add(lblTpo9, "pos 350 20");
        panelLbl.add(lblTpo5, "pos 470 20");
        panelLbl.add(lblTpo6, "pos 560 "+y);
        panelLbl.add(lblTpo7, "pos 625 "+y); 
        panelLbl.add(lblTpo0, "pos 8 20");
        add(panelLbl,BorderLayout.SOUTH);        
        add(info,BorderLayout.CENTER);
       }
    
   
    
    public void refrescar(){
    	String paso = ventanaPrincipal.darPaso();
    	String tipo = ventanaPrincipal.darTipo();
    	lblTpo0.setText(tipo);
    	if(paso.equalsIgnoreCase("Tipo de Analisis")){
    		lblTpo2.setVisible(false);
    		lblTpo3.setVisible(false);
    		lblTpo4.setVisible(false);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo8.setVisible(false);
    		lblTpo9.setVisible(false);
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
    		lblTpo8.setVisible(true);
    		lblTpo9.setVisible(false);
    		lblTpo0.setVisible(true);
    		panelLbl.add(lblTpo2, "pos 110 5");
    		progreso.setValue(20);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Estilo de Vida")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(false);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo9.setVisible(false);
    		panelLbl.add(lblTpo2, "pos 230 5");
    		progreso.setValue(40);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Intencion de Compra")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(false);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo9.setVisible(true);
    		panelLbl.add(lblTpo2, "pos 150 5");
    		panelLbl.add(lblTpo4, "pos 350 5");
    		progreso.setValue(60);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Caracteristicas de Productos")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(false);
    		lblTpo7.setVisible(false);
    		lblTpo9.setVisible(true);
    		panelLbl.add(lblTpo4, "pos 450 5");
    		progreso.setValue(80);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resumen")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(true);
    		lblTpo7.setVisible(false);
    		lblTpo9.setVisible(true);
    		panelLbl.add(lblTpo4, "pos 390 5");
    		progreso.setValue(90);
    		progreso.repaint();
    	}else if(paso.equalsIgnoreCase("Resultados")){
    		lblTpo2.setVisible(true);
    		lblTpo3.setVisible(true);
    		lblTpo4.setVisible(true);
    		lblTpo5.setVisible(true);
    		lblTpo6.setVisible(true);
	    	lblTpo7.setVisible(true);
	    	lblTpo9.setVisible(true);
	    	progreso.setValue(100);
	    	progreso.repaint();	    		
	    }
	    		
	    	
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

   
}