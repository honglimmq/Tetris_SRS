package tetris;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TetrisTest {

    @Test
    void testPieceCreation() {
        Piece piece = new Piece(); // Ensure Piece has a valid constructor

        assertNotNull(piece, "Piece object should not be null");
    }

}
