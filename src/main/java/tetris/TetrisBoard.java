package tetris;

import java.awt.Color;

public class TetrisBoard {
	final int nRows = 20;
	final int nCols = 10;
	final int sizeBox = 30;
	final Color emptyCellColor = Color.WHITE;
	Color[][] isOccupied;

	public TetrisBoard() {
		isOccupied = new Color[nRows][nCols];

		// Initialize occupied table with default empty cell color to keeps track of pieces on the board. 
		for (int i = 0; i < nRows; i++) {
			for (int k = 0; k < nCols; k++) {
				isOccupied[i][k] = emptyCellColor;
			}
		}
	}
	
	// Check for filled rows and clearing them
	void clearFilled() {
		
	}
	
	void placePiece(Piece piece) {
		for(int i = 0; i < piece.shape.length; i++) {
			int col =  piece.pivotCol + piece.shape[i].x;
			int row = piece.pivotRow + piece.shape[i].y;
			
			isOccupied[row][col] = piece.getColor();
		}
	}
	
	
	public Color getColorAt(int row, int col) {
		if(row < 0 || col < 0 || row >= nRows || col >= nCols) {
			
			System.out.printf("Out of board   row: %d, col: %d%n", row, col);
			return emptyCellColor;
		}
		
		return isOccupied[row][col];
	}
	
	
	
	public int getWidth() {
		return this.nCols * this.sizeBox;
	}

	public int getHeight() {
		return this.nRows * this.sizeBox;
	}
	
}
