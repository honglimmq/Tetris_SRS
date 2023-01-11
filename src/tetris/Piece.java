package tetris;

import java.awt.Color;
import java.util.Random;

public class Piece {
	protected Tetromino tetrisPiece;
	protected int[][] shape;
	// protected int[] boardLocation;
	protected int row;
	protected int col;

	public Piece() {
		this.shape = new int[4][2];
		this.setRandomPiece();
		this.row = 0;
		this.col = 0;
	}

	private void setPiece(Tetromino tetromino) {
		this.tetrisPiece = tetromino;
		System.arraycopy(tetromino.initialForm, 0, this.shape, 0, tetromino.initialForm.length);
	}

	public void setRandomPiece() {
		// Get an array of all the Tetromino constants. Then select a random Tetromino
		Tetromino[] tetrominoes = Tetromino.values();
		int index = new Random().nextInt(tetrominoes.length);

		setPiece(tetrominoes[index]);
	}
	
	public void moveLeft() {
		this.col -= 1;
	}
	
	public void moveRight() {
		this.col += 1;
	}
	
	public void moveDown() {
		this.row += 1;
	}
	
	public void hardDrop() {
		// Dropping the piece down all the way to the bottom of
	}
	
	// Return a shape of the given shape rotated anti-clockwise 90 degrees.
	// Rotation transformation matrix for when theta is 90
	// |cos 90 -sin 90|
	// |sin 90 cos 90|
	// therefore, x' = -y and y' = x
	/**
	 * @pre rotate the current piece
	 */
	void rotateLeft() {
		// Do not rotate for O tetromino piece.
		if (tetrisPiece == Tetromino.O) {
			return;
		}

		// Rotate the piece around its pivot square
		for (int i = 0; i < shape.length; i++) {
			// The matrix transformation equation for rotating left where x' = -y and y' = x
			int xValue = -1 * shape[i][1];
			int yValue = shape[i][0];

			shape[i][0] = xValue;
			shape[i][1] = yValue;
		}
	}

	// Return a shape of the given shape rotated clockwise 90 degrees.
	// x' = y and y' = -x
	void rotateRight() {
		// Shouldn't need to rotate O tetromino pieces.
		if (tetrisPiece == Tetromino.O) {
			return;
		}
		
		for (int i = 0; i < shape.length; i++) {
			// The matrix transformation equation for rotating a 2D piece 90 degree where x' = y and y' = -x
			int xValue = shape[i][1];
			int yValue = -1 * shape[i][0];

			shape[i][0] = xValue;
			shape[i][1] = yValue;
		}

	}
	
	Color getColor() {
		return this.tetrisPiece.color;
	}

	void printShapeToConsole() {
		System.out.println("Printing " + tetrisPiece);
		for (int i = 0; i < shape.length; i++) {
			System.out.print("{");
			for (int k = 0; k < shape[i].length; k++) {
				System.out.print(shape[i][k]);
				if (k == shape[i].length - 1) {
					System.out.println("}");
				} else {
					System.out.print(" ,");
				}
			}
		}
	}
}
