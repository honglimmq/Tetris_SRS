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

public class TetrisBoard extends JPanel {
	private static final long serialVersionUID = 1L;
	final int nRows = 20;
	final int nCols = 10;
	final int sizeBox = 30;
	final Color empty = Color.WHITE;
	Color[][] isOccupied;

	BufferedImage backgroundImage = null;
	boolean running = false;
	Timer gameTimer;

	private final int FPS = 1; // set frame rate to 10 FPS
	private final int frameDelay = 1000 / FPS;
	private BufferedImage backBuffer;
	private Graphics2D g2d;

	Piece fallingPiece;

	public TetrisBoard() {
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
		fallingPiece.col = nCols / 2 - 1;
	}

	public void addPiece() {
	}

	// Check whether the given piece can be move down a column.
	// Return false if the given piece can not legally be move down a column on the
	// board.
	// Return true if possible and legal
	public boolean canMoveDown(Piece piece) {
		// Check if out of bound
		for (int i = 0; i < piece.shape.length; i++) {
			if (piece.row + piece.shape[i][0] + 1 > nRows - 1) {
				return false;
			}
		}
		return true;
	}

	public boolean canMoveLeft(Piece piece) {
		// Check whether the piece can move left a column

		// Check if out of bound
		for (int i = 0; i < piece.shape.length; i++) {
			if (piece.col + piece.shape[i][1] <=  0) {
				return false;
			}
		}

		return true;
	}

	public boolean canMoveRight(Piece piece) {
		// Check whether the piece can move right a column

		// Check if out of bound
		for (int i = 0; i < piece.shape.length; i++) {
			if (piece.col + piece.shape[i][1] + 1 >= nCols) {
				return false;
			}
		}

		return true;
	}

	public boolean canRotateLeft(Piece piece) {
		// Check whether the piece can rotate to 90 degree anti-clockwise

		for (int i = 0; i < piece.shape.length; i++) {
			int xPosition = -1 * piece.shape[i][1] + piece.col;
			int yPosition = piece.shape[i][0] + piece.row;

			if (xPosition < 0 && xPosition >= this.nCols && yPosition < 0 && yPosition > this.nRows) {
				return false;
			}
		}

		return true;
	}

	public boolean canRotateRight(Piece piece) {
		// Check whether the piece can rotate to 90 degree clockwise

		for (int i = 0; i < piece.shape.length; i++) {
			int xPosition = piece.col + piece.shape[i][1];
			int yPosition = piece.row - 1 * piece.shape[i][0];

			if (xPosition < 0 && xPosition > this.nCols && yPosition < 0 && yPosition > this.nRows) {
				return false;
			}
		}

		return true;
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

		// draw current piece
		drawCurrentPiece(g2d);
		// Draw outside border
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(0, 0, w, h);
	}

	private void drawCurrentPiece(Graphics2D g2d) {
		int[][] shapeToDisplay = this.fallingPiece.shape;

		g2d.setColor(this.fallingPiece.getColor());
		for (int i = 0; i < this.fallingPiece.shape.length; i++) {
			g2d.fill3DRect((this.fallingPiece.col + shapeToDisplay[i][1]) * sizeBox,
					(this.fallingPiece.row + shapeToDisplay[i][0]) * sizeBox, sizeBox, sizeBox, true);
		}

	}

	private class MyKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// handle key press event
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				// move left
				if (canMoveLeft(fallingPiece)) {
					fallingPiece.moveLeft();
					repaint();
				}
			}
			if (key == KeyEvent.VK_RIGHT) {
				// move right
				if (canMoveRight(fallingPiece)) {
					fallingPiece.moveRight();
					repaint();
				}
			}
			if (key == KeyEvent.VK_UP) {
				// rotate right
				if (canRotateRight(fallingPiece)) {
					fallingPiece.rotateRight();
					repaint();
				}
			}
			if (key == KeyEvent.VK_DOWN) {
				// move down
				if (canMoveDown(fallingPiece)) {
					fallingPiece.moveDown();
					repaint();
				}
			}
		}

		public void keyReleased(KeyEvent e) {
			// handle key release event
		}
	}

	private class GameTimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// perform actions on timer tick
			if (canMoveDown(fallingPiece)) {
				fallingPiece.moveDown();
				repaint();
			}
			
		}
	}

}
