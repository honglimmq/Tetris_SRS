package tetris;

import java.awt.Color;

public enum Tetromino {
	
	// A tetromino is consist of 4 Blocks. Each Block has its base x and y value to be used to project onto the game board.
	// Each tetromino shall be rotated on its pivot point, where it is also the origin point i.e. (0,0)
	I(new Block[]{new Block(-1,0), new Block(0,0), new Block(1, 0), new Block(2, 0)}, Color.CYAN),
	J(new Block[] {new Block(-1,-1), new Block(-1,0), new Block(0, 0), new Block(1, 0)}, Color.BLUE),
	L(new Block[]{new Block(1,-1), new Block(-1,0), new Block(0, 0), new Block(1,0)}, Color.ORANGE),
	O(new Block[]{new Block(0,-1), new Block(1,-1), new Block(0, 0), new Block(1,0)}, Color.YELLOW),
	S(new Block[]{new Block(0,-1), new Block(1,-1), new Block(-1, 0), new Block(0,0)}, Color.GREEN),
	Z(new Block[]{new Block(-1,-1), new Block(0,-1), new Block(0, 0), new Block(1,0)}, Color.RED),
	T(new Block[]{new Block(0,-1), new Block(-1,0), new Block(0, 0), new Block(1,0)}, new Color(123, 50, 250)); //purple


	public final Block[] initialForm; 
	public final Color color;
	
	private Tetromino(Block[] initialForm, Color color) {
		this.initialForm = initialForm;
		this.color = color;
	}


	// returns a deep copy version of an initial Tetromino piece.
	public Block[] getSpawnForm() {
		Block[] copy = new Block[initialForm.length];
		for(int i = 0; i < initialForm.length; i++){
			copy[i] = new Block(this.initialForm[i].x, this.initialForm[i].y);
		}
		return copy;
	}
}	