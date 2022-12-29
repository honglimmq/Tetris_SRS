package tetris;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Board extends JPanel implements ActionListener {
	final int nRows = 20;
	final int nCols = 10;
	final int sizeBox = 30;
	int speed = 1000;
	int tempY = -1;
	
	BufferedImage backgroundImage = null;
	boolean running = false; 
	Timer gameTimer;


	Board() {
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
		startGame();
	}
	
	public void startGame() {
		running = true;
		gameTimer = new Timer(speed, this);
		gameTimer.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		// Paint background colour
		super.paintComponent(g); 
		// Using Graphics2D because it has more feature
		Graphics2D g2d = (Graphics2D) g; 
		
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
		
		drawShape(g2d);
		
		g2d.setColor(Color.WHITE);
		// Draw outside border
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(0, 0, w, h);
	}

	private void drawShape(Graphics2D g2d) {
		g2d.setColor(Color.RED);
		g2d.fillRect(5*sizeBox, sizeBox*tempY, sizeBox, sizeBox);
		tempY++;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		
		repaint();
	}

	
	
}
