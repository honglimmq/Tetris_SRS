package tetris;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;


public class TetrisUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6337517243329484422L;
	private TetrisGame tetrisGame;
	private TetrisBoard board;
	private Piece currentPiece;
	private BufferedImage backgroundImage;

	private BufferedImage backBuffer;
	private Graphics2D g2d;

	public TetrisUI(TetrisBoard board, Piece piece, BufferedImage backgroundImage, TetrisGame tetrisGame) {		
		this.tetrisGame = tetrisGame;
		this.board = board;
		this.currentPiece = piece;
		this.backgroundImage = backgroundImage;
		
		// Setting up background image
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));

		// Create the back buffer to reduce flicker and allow smoother game-play
		setDoubleBuffered(true);
		backBuffer = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_RGB);
		g2d = backBuffer.createGraphics();
		
		// Initialize key listener
		addKeyListener(new TetrisKeyAdapter());
		setFocusable(true);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Paint background color
		super.paintComponent(g);

		renderGame();

		g.drawImage(backBuffer, 0, 0, this);
	}

	protected void renderGame() {
		int h = board.getHeight();
		int w = board.getWidth();
		g2d.clearRect(0, 0, w, h);

		// Draw background Image
		g2d.drawImage(backgroundImage, 0, 0, w, h, null);

		// Draw inside grid

		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(1));
		for (int i = 1; i < board.nRows; i++) {
			// Draw horizontal lines
			g2d.drawLine(0, i * board.sizeBox, w, i * board.sizeBox);
		}
		for (int k = 1; k < board.nCols; k++) {
			// Draw vertical lines
			g2d.drawLine(k * board.sizeBox, 0, k * board.sizeBox, h);
		}

		// Draw pieces
		drawCurrentPiece();
		drawPlacedPieces();

		// Draw outside border
		g2d.setColor(Color.WHITE);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(0, 0, w, h);

	}

	protected void drawCurrentPiece() {
		g2d.setColor(currentPiece.getColor());
		for (int i = 0; i < currentPiece.shape.length; i++) {
			if (currentPiece.shape[i].isPivot()) {
				g2d.setColor(Color.PINK);
			} else {
				g2d.setColor(this.currentPiece.getColor());
			}
			int x = currentPiece.pivotCol + currentPiece.shape[i].x;
			int y = currentPiece.pivotRow + currentPiece.shape[i].y;
			System.out.printf("shape[%d] x: %d  y: %d%n", i, x, y);
			g2d.fill3DRect(x * board.sizeBox, y * board.sizeBox, board.sizeBox, board.sizeBox, true);
		}

	}

	protected void drawPlacedPieces() {
		for (int i = 0; i < board.nRows; i++) {
			for(int k = 0; k < board.nCols; k++) {
				if(board.getColorAt(i, k) != Color.WHITE) {
					
					g2d.setColor(board.getColorAt(i, k));
					g2d.fill3DRect(k * board.sizeBox, i * board.sizeBox, board.sizeBox, board.sizeBox, true);
				}
			}
		}

	}
	
	protected void updateCurrentPiece(Piece newPiece) {
		this.currentPiece = newPiece;
	}
	
	
	private class TetrisKeyAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			// handle key press event
			int key = e.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {
				tetrisGame.move(GameAction.MoveLeft);
				repaint();
				// move left
			}
			if (key == KeyEvent.VK_RIGHT) {
				tetrisGame.move(GameAction.MoveRight);
				repaint();
				// move right
			}
			if (key == KeyEvent.VK_UP) {
				tetrisGame.move(GameAction.RotateRight);
				repaint();	
				// rotate right/clockwise
			}
			if (key == KeyEvent.VK_Z) {
				tetrisGame.move(GameAction.RotateLeft);
				repaint();
				// rotate left
			}
			if (key == KeyEvent.VK_DOWN) {
				tetrisGame.move(GameAction.MoveDown);
				repaint();
				// move down
			}

			if (key == KeyEvent.VK_SPACE) {
				tetrisGame.move(GameAction.HardDrop);
				repaint();
			}
		}

		public void keyReleased(KeyEvent e) {
			// handle key release event
		}
	}
}
