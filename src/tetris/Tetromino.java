package tetris;

import java.awt.Color;

public enum Tetromino {
	// shape is in the form of shape[0] == x coordinate, shape[1] == y coordinate
	// the first set of the coordinate of the shape i.e. shape[0][0] and shape[0][1], is the pivot point
	I(new Block[]{new Block(-1,0), new Block(0,0), new Block(1, 0), new Block(2, 0)}, Color.CYAN),
	J(new Block[] {new Block(-1,-1), new Block(-1,0), new Block(0, 0), new Block(1, 0)}, Color.BLUE),
	L(new Block[]{new Block(1,-1), new Block(-1,0), new Block(0, 0), new Block(1,0)}, Color.ORANGE),
	O(new Block[]{new Block(0,-1), new Block(1,-1), new Block(0, 0), new Block(1,0)}, Color.YELLOW),
	S(new Block[]{new Block(0,-1), new Block(1,-1), new Block(-1, 0), new Block(0,0)}, Color.GREEN),
	Z(new Block[]{new Block(-1,-1), new Block(0,-1), new Block(0, 0), new Block(1,0)}, Color.RED),
	T(new Block[]{new Block(0,-1), new Block(-1,0), new Block(0, 0), new Block(1,0)}, new Color(123, 50, 250)); //purple
	/*
	I(new Block{} {{ 0, 0 },  { -1, 0 }, { 1, 0 }, { 2, 0} }, Color.CYAN),
	J(new Block[] {{ 0, 0 },  { -1, 0 }, { 1, 0 }, { 1, 1 } }, Color.BLUE),
	L(new Block[] {{ 0, 0 }, { -1, 0 }, { 1, 0 }, { 1,1 } }, Color.ORANGE),
	O(new Block[] {{ 0, 0 }, { -1, 1 }, { 0, 1 }, { -1, 0 }}, Color.YELLOW),
	S(new Block[] { { 0, 0 }, { 1, 1 }, { -1, 0 }, { 0, 1 } }, Color.GREEN),
	Z(new Block[] { { 0, 0 }, { 0, 1 }, { -1, 1 }, { 1, 0 } }, Color.RED),
	T(new Block[] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } }, new Color(123, 50, 250)); //purple */
	

	public final Block[] initialForm; 
	public final Color color;
	
	private Tetromino(Block[] initialForm, Color color) {
		this.initialForm = initialForm;
		this.color = color;
	}
	
	public Block[] getSpawnForm() {
		return this.initialForm;
	}
}	