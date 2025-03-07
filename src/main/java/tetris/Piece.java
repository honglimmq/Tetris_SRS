package tetris;

import java.awt.Color;
import java.util.Random;

public class Piece {
	// This is current playing piece
	protected Tetromino tetrisPiece;

	// This is a 2D int array that store the form of the Piece.
	protected Block[] shape;

	// Piece keeps track of its pivot coordinate relative to the tetris board, where
	// 'pivotRow' is the pivot's row position and,
	// 'pivotCol' is the pivot's column position
	protected int pivotRow;
	protected int pivotCol;

	// A piece has 4 rotation state where:
	// '0' is the spawn state,
	// '1' state resulting from a clockwise ("right") rotation from spawn,
	// '2' state resulting from a anti-clockwise ("left") rotation from spawn and,
	// '3' is the last rotation state before cycling back to the spawn state.
	protected int rotationState;

	// The following are offset data for each tetromino types. They help with
	// correcting the relative piece position of the tetromino after a rotation on
	// the game board.
	// The type of offset data are stored in a 2D array. Each row in the array
	// represents the offset test ID and each column represent
	// the rotation ID.
	// J, L, S, T, Z Tetromino Offset Data
	final static Block[][] jlstzOffset = new Block[][] {
			{ new Block(0, 0), new Block(0, 0), new Block(0, 0), new Block(0, 0) },
			{ new Block(0, 0), new Block(1, 0), new Block(0, 0), new Block(-1, 0) },
			{ new Block(0, 0), new Block(1, -1), new Block(0, 0), new Block(-1, -1) },
			{ new Block(0, 0), new Block(0, 2), new Block(0, 0), new Block(0, 2) },
			{ new Block(0, 0), new Block(1, 2), new Block(0, 0), new Block(-1, 2) } };

	// I Tetromino Offset Data
	final static Block[][] iOffset = new Block[][] {
			{ new Block(0, 0), new Block(-1, 0), new Block(-1, 1), new Block(0, 1) },
			{ new Block(0, 0), new Block(1, 0), new Block(0, 0), new Block(0, 1) },
			{ new Block(2, 0), new Block(0, 0), new Block(-2, 1), new Block(0, 1) },
			{ new Block(-1, 0), new Block(0, 1), new Block(1, 0), new Block(0, -1) },
			{ new Block(2, 0), new Block(0, -2), new Block(-2, 0), new Block(0, 2) } };

	// O Tetromino Offset Data
	final static Block[][] oOffset = new Block[][] {
			{ new Block(0, 0), new Block(0, 1), new Block(-1, 1), new Block(-1, 0) } };

	public Piece() {
		this.setRandomPiece();
		this.pivotRow = 0;
		this.pivotCol = 0;

		this.rotationState = 0;
	}

	public Piece(Piece type){
		this.tetrisPiece = type.tetrisPiece;
		this.pivotRow = 0;
		this.pivotCol = 0;
		this.shape = this.tetrisPiece.getSpawnForm();
		this.rotationState = 0;
	}


	// TODO: Next to change this to use piece sequence
	public void setRandomPiece() {
		// Get an array of all the Tetromino constants. Then select a random Tetromino
		Tetromino[] tetrominoes = Tetromino.values();
		int index = new Random().nextInt(tetrominoes.length);
		this.tetrisPiece = tetrominoes[index];
		this.shape = this.tetrisPiece.getSpawnForm();
	}


	void movePiece(int deltaX, int deltaY) {
		this.pivotCol += deltaX;
		this.pivotRow += deltaY;
	}

	void rotate(boolean isClockwise) {
		setRotationState(this.rotationState+= isClockwise ? 1 : -1);
		for(int i = 0; i < shape.length; i++) {
			shape[i].rotate(isClockwise);
		}
	}
	
	int getRotationState() {
		return rotationState;
	}

	private void setRotationState(int rotationState) {
		// x is dividend
		// y is divisor
		// (x % m + m) % m 
		
		this.rotationState = (rotationState % 4 + 4) % 4;
	}

	int getPivotRow() {
		return pivotRow;
	}

	void setPivotRow(int pivotRow) {
		this.pivotRow = pivotRow;
	}

	int getPivotCol() {
		return pivotCol;
	}

	void setPivotCol(int pivotCol) {
		this.pivotCol = pivotCol;
	}

	Color getColor() {
		return this.tetrisPiece.color;
	}

	void printShapeToConsole() {
		System.out.println("Printing " + tetrisPiece);
	}
}
