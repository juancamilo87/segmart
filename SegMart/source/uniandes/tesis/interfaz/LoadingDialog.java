/**
 * 
 */
package uniandes.tesis.interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Juan Camilo García
 *
 */
public class LoadingDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InterfazPrograma vp;
	private JLabel label;
	private JLabel imgL;
	
	public LoadingDialog(InterfazPrograma nVp)
	{
		vp = nVp;
		setTitle("Cargando");		
		setEnabled(false);
		vp.setFocusable(false);
		vp.setEnabled(false);
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		label = new JLabel("Generando archivos, espere un momnto por favor.");
		imgL = new JLabel();
		imgL.setIcon(new ImageIcon("data/loading.gif"));
		panel.add(label);
		panel.add(imgL);
		getContentPane().add(panel);
		pack();
	}
	
	public void cerrar()
	{
		dispose();
	}

}
