package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TetrisBoard2 extends JPanel {
	private static final long serialVersionUID = 1L;
	final static int nRows = 20;
	final static int nCols = 10;
	final int sizeBox = 30;
	final Color emptyCellColour = Color.WHITE;
	Color[][] isOccupied;

	BufferedImage backgroundImage = null;
	boolean running = false;
	Timer gameTimer;

	private final int FPS = 4; // set frame rate to 10 FPS
	private final int frameDelay = 1000 / FPS;
	private BufferedImage backBuffer;
	private Graphics2D g2d;

	Piece fallingPiece;

	public TetrisBoard2() {
		// initialize key listener
		addKeyListener(new MyKeyAdapter());
		setFocusable(true);

		// setting up background image
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(this.boardWidth(), this.boardHeight()));
		try {
			// getClass().getResource("bg_space.jpg");
			backgroundImage = ImageIO
					.read(new File("C:\\Users\\Ty\\eclipse-workspace\\Tetris\\src\\tetris\\img\\bg_space.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// initializing board
		this.isOccupied = new Color[nRows][nCols];

		// initializing occupied table that keeps track of pieces on the board
		for (int i = 0; i < nRows; i++) {
			for (int k = 0; k < nCols; k++) {
				isOccupied[i][k] = Color.WHITE;

			}
		}

		startGame();
	}

	public void startGame() {
		setDoubleBuffered(true);
		// create the back buffer
		backBuffer = new BufferedImage(this.boardWidth(), this.boardHeight(), BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();

		running = true;
		gameTimer = new Timer(frameDelay, new GameTimerListener());
		gameTimer.start();
		
		// Create a new falling piece and align it's column to the middle of the board
		this.fallingPiece = new Piece();
		fallingPiece.pivotCol = nCols / 2 - 1;
	}

	private void placePiece() {
		for(int i = 0; i < fallingPiece.shape.length; i++) {
			int col =  fallingPiece.pivotCol + fallingPiece.shape[i].x;
			int row = fallingPiece.pivotRow + fallingPiece.shape[i].y;
			
			isOccupied[row][col] = fallingPiece.getColor();
		}
		fallingPiece = null;
		fallingPiece = new Piece();
	}

	public boolean tryMove(int deltaX, int deltaY) {
		
		for(int i = 0; i < fallingPiece.shape.length; i++) {
			int newXPos = fallingPiece.pivotCol + fallingPiece.shape[i].x + deltaX;
			int newYPos = fallingPiece.pivotRow + fallingPiece.shape[i].y + deltaY;
			
			// Check boundaries and if a cell is already occupied
			if(newXPos < 0 || newXPos >= TetrisBoard2.nCols || newYPos >= TetrisBoard2.nRows ||  newYPos < 0 || isOccupied[newYPos][newXPos] != this.emptyCellColour) {
				return false;
			}
		}
		
		fallingPiece.movePiece(deltaX, deltaY);
		return true;
	}
	
	public boolean rotatePiece(boolean clockwise, boolean offset) {		
		return false;
	}
	
	boolean offset(int currentRotationIdx, int nextRotationIdx) {
		return false;
	}

	public int boardWidth() {
		return this.nCols * this.sizeBox;
	}

	public int boardHeight() {
		return this.nRows * this.sizeBox;
	}

	@Override
	public void paintComponent(Graphics g) {
		// Paint background color
		super.paintComponent(g);
		renderGame();

		g.drawImage(backBuffer, 0, 0, this);
	}

	private void renderGame() {
		int h = this.getHeight();
		int w = this.getWidth();
		g2d.clearRect(0, 0, w, h);

		// Draw background image
		g2d.drawImage(backgroundImage, 0, 0, this.boardWidth(), this.boardHeight(), null);

		// Draw inside grid
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

		// draw pieces
		drawCurrentPiece();
		drawPlacedPieces();
		
		// Draw outside border
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(0, 0, w, h);
	}

	private void drawCurrentPiece() {
		g2d.setColor(this.fallingPiece.getColor());
		for (int i = 0; i < this.fallingPiece.shape.length; i++) {
			if (fallingPiece.shape[i].isPivot()) {
				g2d.setColor(Color.PINK);
			} else {
				g2d.setColor(this.fallingPiece.getColor());
			}
			int x = fallingPiece.pivotCol + fallingPiece.shape[i].x;
			int y = fallingPiece.pivotRow + fallingPiece.shape[i].y;

			g2d.fill3DRect(x * sizeBox, y * sizeBox, sizeBox, sizeBox, true);
		}

	}

	private void drawPlacedPieces() {
		for (int i = 0; i < isOccupied.length; i++) {
			for(int k = 0; k < isOccupied[i].length; k++) {
				if(isOccupied[i][k] != Color.WHITE) {
					g2d.setColor(isOccupied[i][k]);
					
					g2d.fill3DRect(k * sizeBox, i * sizeBox, sizeBox, sizeBox, true);
				}
			}
		}
	}


	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// handle key press event
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				tryMove(-1, 0);
				repaint();
				// move left
			}
			if (key == KeyEvent.VK_RIGHT) {
				tryMove(1, 0);
				repaint();
				// move right
			}
			if (key == KeyEvent.VK_UP) {
				// rotate right/clockwise
			}
			if (key == KeyEvent.VK_DOWN) {
				tryMove(0, 1);
				repaint();
				// move down
			}
			if (key == KeyEvent.VK_Z) {
				placePiece();
				repaint();
				// move down
			}
		}

		public void keyReleased(KeyEvent e) {
			// handle key release event
		}
	}

	private class GameTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/*
			 * // perform actions on timer tick if (canMoveDown(fallingPiece)) {
			 * fallingPiece.moveDown(); repaint(); }
			 */
			if(!tryMove(0, 1)) {
				placePiece();
			}
			repaint();
		}
	}

}
