/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sánchez - 6/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.cliente.interfaz;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se registra un producto para agregarlo a la lista de la compra
 */
public class PanelBotones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del punto de venta
     */
    private InterfazPrograma ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el campo de texto donde se debe escribir el código del producto
     */
    private JTextField txtCodigo;

    /**
     * Es el botón usado para registrar el producto indicado en la venta
     */
    private JButton botonRegistrar;

    /**
     * Es la etiqueta Código del Producto
     */
    private JLabel etiquetaCodigo;

    /**
     * Es la etiqueta Total
     */
    private JLabel etiquetaTotal;

    /**
     * Es el campo donde se muestra el total de la compra
     */
    private JTextField txtTotal;
    
    private JButton anterior;
    
    private JButton siguiente;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ipv Es una referencia a la ventana principal del punto de venta
     */
    public PanelBotones( InterfazPrograma ipv )
    {
        ventanaPrincipal = ipv;

        setLayout( new GridLayout(1,0 ) );
        anterior = new JButton("Anterior");
        siguiente = new JButton("Continuar");
        anterior.setActionCommand("REGRESAR");
        anterior.addActionListener(this);
        
        anterior.setVisible(false);
        
        siguiente.setActionCommand("SEGUIR");
        siguiente.addActionListener(this);
        FlowLayout fl = new FlowLayout();
        fl.setHgap(100);
        JPanel panel = new JPanel(fl);
        
        panel.add(anterior);
        panel.add(siguiente);
        add(panel);
            }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el total mostrado para la compra
     * @param total El valor total de la compra
     */
    public void actualizarTotal( int total )
    {
        txtTotal.setText( "$" + total );
    }

    /**
     * Limpia el campo de texto del código de barras
     */
    public void limpiar( )
    {
        txtCodigo.setText( "" );
    }
    
    public void refrescar(String paso, String tipo_analisis){
    	if(!ventanaPrincipal.darPaso().equalsIgnoreCase("Tipo de Analisis")){
    		anterior.setVisible(true);
    		if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resumen"))
    				siguiente.setText("Crear");
    		else
    			siguiente.setText("Siguiente");
    	}
    	else{
    		anterior.setVisible(false);
    		siguiente.setText("Continuar");
    	}
    		
    	
    }

    /**
     * Es el método que se ejecuta cuando se hace click sobre el botón de registrar
     * @param evento Es el evento del click sobre el botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if(comando.equalsIgnoreCase("SEGUIR"))
            ventanaPrincipal.refrescar("Siguiente");
        else if(comando.equalsIgnoreCase("REGRESAR"))
        	ventanaPrincipal.refrescar("Anterior");
    }
}