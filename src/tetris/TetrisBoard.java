package tetris;

public class TetrisBoard {
	final static int nRows = 20;
	final static int nCols = 10;
	final int sizeBox = 30;
	boolean[][] isOccupied;
	
	public TetrisBoard() {
		this.isOccupied = new boolean[nRows][nCols];
		
		for(int i = 0; i<nRows; i++) {
			for(int k = 0; k<nCols; i++) {
				isOccupied[i][k] = false;
			}
		}
	}

	public void addPiece() {
		Piece newPiece = new Piece();
		
		
	}

	// Check whether the given piece can be move down a column. 
	// Return false if the given piece can not legally be move down a column on the board.
	// Return true if possible and legal 			
	public boolean canMoveDown(Piece piece) {
		

		// Check if out of bound
		for (int i = 0; i < piece.shape.length; i++) {
			if (piece.row + piece.shape[i][1] - 1 < 0) {
				return false;
			}
		}

		return true;
	}

	public boolean canMoveLeft(Piece piece) {
		// Check whether the piece can move left a column

		// Check if out of bound
		for (int i = 0; i < piece.shape.length; i++) {
			if (piece.row + piece.shape[i][0] - 1 < 0) {
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

			if (xPosition < 0 && xPosition >= TetrisBoard.nCols && yPosition < 0 && yPosition >= TetrisBoard.nRows) {
				return false;
			}
		}

		return true;
	}

	public boolean canRotateRight(Piece piece) {
		// Check whether the piece can rotate to 90 degree clockwise

		for (int i = 0; i < piece.shape.length; i++) {
			int xPosition = piece.shape[i][1];
			int yPosition = -1 * piece.shape[i][0];
			
			if (xPosition < 0 && xPosition >= TetrisBoard.nCols && yPosition < 0 && yPosition >= TetrisBoard.nRows) {
				return false;
			}
		}

		return true;
	}

}
