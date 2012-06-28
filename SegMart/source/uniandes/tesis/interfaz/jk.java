package uniandes.tesis.interfaz;



	import java.awt.BorderLayout;
	import java.awt.Dimension;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.io.File;
	import java.io.IOException;
	import javax.imageio.ImageIO;
	import javax.swing.JFrame;
	import javax.swing.JPanel;
	import org.rosuda.JRI.Rengine;
	public class jk {
	public static void main(String[] args) throws IOException
	    {
	        //Set some labels for the plot
	        String title = "R Plot in JFrame";
	        String xlab = "X Label";
	        String ylab = "Y Label";
	        //Start R
	        Rengine r = new Rengine(args, false, null);
	        //Do some calcs and plot the chart but save as a png in the working folder
	        r.eval("a        r.eval(\"b        r.eval(\"png(file=\"graph2.png\",width=1600,height=1600,res=400)");
	        r.eval("plot(a,b,type='o',col=\"Blue\",main=\"" + title + "\",xlab=\"" + xlab + "\",ylab=\"" + ylab + "\")");
	        r.eval("dev.off()");
	        //get the image and create a new imagepanel
	        File file = new File("*EDIT WORKING FOLDER PATH*\\graph2.png");
	        Image image = ImageIO.read(file);
	        imagePanel myPanel = new imagePanel(image);
	        //Create a new frame and add the imagepanel
	        JFrame aFrame = new JFrame();
	        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        aFrame.getContentPane().add(myPanel, BorderLayout.CENTER);
	        aFrame.pack();
	        aFrame.setVisible(true);
	        aFrame.setSize(new Dimension(600, 600));
	    }
	 
	    static class imagePanel extends JPanel
	    {
	        Image image = null;
	 
	        public imagePanel(Image image)
	        {
	            this.image = image;
	        }
	 
	        @Override
	        public void paintComponent(Graphics g)
	        {
	            super.paintComponent(g);
	            //there is a picture: draw it
	            if (image != null)
	            {
	                int height = this.getSize().height;
	                int width = this.getSize().width;
	                g.drawImage(image, 0, 0, width, height, this);
	            }
	        }
	    }
	
	
}
