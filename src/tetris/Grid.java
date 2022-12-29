package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Grid extends JPanel {
	final int nRows = 20;
	final int nCols = 10;
	final int sizeBox = 30;
	BufferedImage backgroundImage = null;


	Grid() {
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(sizeBox * 10, sizeBox * 20));
		try {
			//getClass().getResource("bg_space.jpg");
			backgroundImage = ImageIO.read(new File("C:\\Users\\Ty\\eclipse-workspace\\Tetris\\src\\tetris\\img\\bg_space.jpg"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		super.paint(g); // Paint background colour
		Graphics2D g2d = (Graphics2D) g; // using Graphics2D because it has more feature
		int h = this.getHeight();
		int w = this.getWidth();

		// Draw background image
		g2d.drawImage(backgroundImage, 0, 0, nRows*sizeBox, 1080, null);
		
		// Draw grid
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(1));
		for (int i = 1; i < nRows; i++) {
			// Draw horizontal lines
			g2d.drawLine(0, i * sizeBox, w, i * sizeBox);
		}
		for (int k = 1; k < nCols; k++) {
			// Draw vertical lines
			g2d.drawLine(k * sizeBox, 0, k * sizeBox, h);
		}

		// Draw outside border
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(0, 0, w, h);
	}
	
	
	
}
