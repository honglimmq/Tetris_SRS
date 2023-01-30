package tetris;

import java.awt.Color;
import java.util.Random;

public class Piece {
	// This is 
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
	// '1' state resulting from a clockwise ("right") rotation  from spawn, 
	// '2' state resulting from a anti-clockwise ("left") rotation from spawn and, 
	// '3' is the last rotation state before cycling back to the spawn state.
	protected int rotationState; 

	public Piece() {
		//this.setRandomPiece();
		this.setPiece(Tetromino.J); //#tbr
		this.pivotRow = 0;
		this.pivotCol = 0;
	}

	private void setPiece(Tetromino tetromino) {
		this.tetrisPiece = tetromino;
		this.shape = tetromino.getSpawnForm();
		this.rotationState = 0; 
	}

	public void setRandomPiece() {
		// Get an array of all the Tetromino constants. Then select a random Tetromino
		Tetromino[] tetrominoes = Tetromino.values();
		int index = new Random().nextInt(tetrominoes.length);
		setPiece(tetrominoes[index]);
	}
	
	
	public void moveLeft() {
		this.pivotCol -= 1;
	}
	
	public void moveRight() {
		this.pivotCol += 1;
	}
	
	public void moveDown() {
		this.pivotRow += 1;
	}
	
	public void hardDrop() {
		// Dropping the piece down all the way to the bottom of
	}
	

	void rotateRight() {
		rotate(true, true);
	}


	void rotateLeft() {
		rotate(false, true);
	}
	
	void rotate(boolean clockwise, boolean offset) {		
		
		
	}
	
	Color getColor() {
		return this.tetrisPiece.color;
	}

	void printShapeToConsole() {
		System.out.println("Printing " + tetrisPiece);
	}

}
