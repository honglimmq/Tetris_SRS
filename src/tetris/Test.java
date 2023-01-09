package tetris;

import java.util.Random;

public class Test {

	public static void main(String[] args) {
		
		testingRotations();
		
	}
	
	public static void testingRotations() {
		Piece piece = new Piece();
		
		System.out.println("rotating left");
		piece.printShapeToConsole();
		piece.rotateLeft();
		piece.printShapeToConsole();
		piece.rotateLeft();
		piece.printShapeToConsole();
		piece.rotateLeft();
		piece.printShapeToConsole();
		piece.rotateLeft();
		piece.printShapeToConsole();
		piece.rotateLeft();
		piece.printShapeToConsole();
		piece.rotateLeft();
		
		System.out.println("rotating right now ");
		piece.printShapeToConsole();
		piece.rotateRight();
		piece.printShapeToConsole();
		piece.rotateRight();
		piece.printShapeToConsole();
		piece.rotateRight();
		piece.printShapeToConsole();
		piece.rotateRight();
		piece.printShapeToConsole();
		piece.rotateRight();
	}
	
}
