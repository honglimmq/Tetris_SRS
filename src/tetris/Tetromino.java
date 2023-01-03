package tetris;

import java.awt.Color;

public enum Tetromino {
	I(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 2, 0 } }, Color.CYAN),
	J(new int[][] { { -1, 1 }, { -1, 0 }, { 0, 0 }, { 1, 0 } }, Color.BLUE),
	L(new int[][] { { -1, 0 }, { 0, 0 }, { 1, 0 }, { 1, 1 } }, Color.ORANGE),
	O(new int[][] { { -1, 1 }, { 0, 1 }, { -1, 0 }, { 0, 0 } }, Color.YELLOW),
	S(new int[][] { { 0, 1 }, { 1, 1 }, { -1, 0 }, { 0, 0 } }, Color.GREEN),
	Z(new int[][] { { -1, 1 }, { 0, 1 }, { 0, 0 }, { 1, 0 } }, Color.RED),
	T(new int[][] { { 0, 1 }, { -1, 0 }, { 0, 0 }, { 1, 0 } }, new Color(123, 50, 250));
	
	public final int[][] initialForm; 
	public final Color color;
	
	private Tetromino(int[][] initialForm, Color color) {
		this.initialForm = initialForm;
		this.color = color;
	}
}
