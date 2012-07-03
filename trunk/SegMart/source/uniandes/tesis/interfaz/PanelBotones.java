/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogot� - Colombia)
 * Departamento de Ingenier�a de Sistemas y Computaci�n 
 * Licenciado bajo el esquema Academic Free License versi�n 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario S�nchez - 6/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.tesis.interfaz;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
    
    private Boolean listo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el campo de texto donde se debe escribir el c�digo del producto
     */
    private JTextField txtCodigo;

    /**
     * Es el bot�n usado para registrar el producto indicado en la venta
     */
    private JButton botonRegistrar;

    /**
     * Es la etiqueta C�digo del Producto
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
    
    private JButton close;

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
        listo = false;
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
        close = new JButton("Cerrar");
        close.setActionCommand("CERRAR");
        close.addActionListener(this);
        close.setVisible(false);
        panel.add(anterior);
        panel.add(siguiente);
        panel.add(close);
        
        
        add(panel);
            }

    // -----------------------------------------------------------------
    // M�todos
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
     * Limpia el campo de texto del c�digo de barras
     */
    public void limpiar( )
    {
        txtCodigo.setText( "" );
    }
    
    public void refrescar(){
    	if(!ventanaPrincipal.darPaso().equalsIgnoreCase("Tipo de Analisis")&&!ventanaPrincipal.darPaso().equalsIgnoreCase("Resultados")){
    		anterior.setVisible(true);
    		if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resumen"))
    				siguiente.setText("Crear");
    		else
    			siguiente.setText("Siguiente");
    		siguiente.setActionCommand("SEGUIR");
    	}
    	else if(ventanaPrincipal.darPaso().equalsIgnoreCase("Resultados")){
    		anterior.setVisible(false);
    		siguiente.setText("Reiniciar An�lisis");
    		siguiente.setActionCommand("Reiniciar");
    		close.setVisible(true);
    	}
    	else
    	{
    		anterior.setVisible(false);
    		siguiente.setText("Continuar");
    		siguiente.setActionCommand("SEGUIR");
    	}
    		
    	
    }
    
    public void cambiarListo(){
    	listo = true;
    }
    public void cambiarListo2(){
    	listo = false;
    }

    /**
     * Es el m�todo que se ejecuta cuando se hace click sobre el bot�n de registrar
     * @param evento Es el evento del click sobre el bot�n
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if(comando.equalsIgnoreCase("SEGUIR"))
        	if(listo)
            ventanaPrincipal.refrescar("Siguiente");
        	else
        		JOptionPane.showMessageDialog(this,"Seleccione un tipo de an�lisis","Informaci�n Incompleta", JOptionPane.INFORMATION_MESSAGE);
        else if(comando.equalsIgnoreCase("REGRESAR"))
        	ventanaPrincipal.refrescar("Anterior");
        else if(comando.equalsIgnoreCase("Reiniciar"))
        	ventanaPrincipal.refrescar("Reiniciar");
        else if(comando.equalsIgnoreCase("CERRAR"))
        {
        	ventanaPrincipal.dispose();
        }
    }
}