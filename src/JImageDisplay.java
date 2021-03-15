
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public class JImageDisplay extends JComponent {

		private static final long serialVersionUID = 1L;
		
		
		private BufferedImage displayImage;
		
		public JImageDisplay(int width, int height) {
			displayImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			Dimension imageDimension = new Dimension(width, height);
			super.setPreferredSize(imageDimension);
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(displayImage, 0, 0, displayImage.getWidth(), displayImage.getHeight(), null);
		}
		
		public void clearImage() {
			int[] array = new int[getWidth() * getHeight()];
			displayImage.setRGB(0, 0, getWidth(), getHeight(), array, 0, 1);
		}
		
		public void drawPixel(int x, int y, int rgbColor) {
			displayImage.setRGB(x, y, rgbColor);
		}
	
}