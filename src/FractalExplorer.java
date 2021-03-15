
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JButton;
import javax.swing.JFrame;


public class FractalExplorer {

		private int displaySize;
		private JImageDisplay display;
		private FractalGenerator fractal;
		private Rectangle2D.Double range;
		
		
		public FractalExplorer(int size) {
			displaySize = size;
			
	        fractal = new Mandelbort();
	        range = new Rectangle2D.Double();
	        fractal.getInitialRange(range);
	        display = new JImageDisplay(displaySize, displaySize);
		}
		
		public void createAndShowGUI() {
			display.setLayout(new BorderLayout());
			JFrame frame = new JFrame("Fractal Explorer");
			frame.add(display, BorderLayout.CENTER);
			
			JButton resetBut = new JButton("Reset Display");
			ResetHandler handler = new ResetHandler();
			resetBut.addActionListener(handler);
			
			frame.add(resetBut, BorderLayout.SOUTH);

			MouseHandler click = new MouseHandler();
			display.addMouseListener(click);
			
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(false);			
		}
		
		private void drawFractal() {
			
			for (int x=0; x<displaySize; x++) {
	            for (int y=0; y<displaySize; y++) {
	                double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);	                
	                double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
	                
	                int iteration = fractal.numIterations(xCoord, yCoord);
	                
	                if (iteration == -1) {
	                    display.drawPixel(x, y, 0);
	                } else {
	                    float hue = 0.7f + (float) iteration / 200f;
	                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
	                
	                    display.drawPixel(x, y, rgbColor);
	                }
	                
	            }
	        }
			
	        display.repaint();
	    }
		
		private class ResetHandler implements ActionListener {
			
				public void actionPerformed(ActionEvent e) {
					fractal.getInitialRange(range);
					drawFractal();
				}
			
		}
		
		private class MouseHandler extends MouseAdapter {
			
				public void mouseClicked(MouseEvent e) {
					int x = e.getX();
		            double xCoord = FractalGenerator.getCoord(range.x, range.x + range.width, displaySize, x);
		            
		            int y = e.getY();
		            double yCoord = FractalGenerator.getCoord(range.y, range.y + range.height, displaySize, y);
		                    
		            fractal.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
		                  
		            drawFractal();
				}			
		}
		
		public static void main(String[] args) {
				
			FractalExplorer display = new FractalExplorer(2000);
			display.createAndShowGUI();
			display.drawFractal();
			
		}
}