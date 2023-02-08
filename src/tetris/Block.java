package tetris;

public class Block {
	int x;
	int y;

	Block(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Rotates the block around the pivot point origin (0,0)
	void rotate(boolean isClockwise) {
		int xFactor = isClockwise ? -1 : 1;
		int yFactor = isClockwise ? 1 : -1;

		int tempX = x;
		x = xFactor * y;
		y = yFactor * tempX;
	}

	boolean isPivot() {
		return x == 0 && x == y;
	}
}
