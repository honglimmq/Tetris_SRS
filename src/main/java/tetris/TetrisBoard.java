package tetris;

import java.awt.Color;

// Handles grid state
public class TetrisBoard {
    final int nRows = 20;
    final int nCols = 10;
    final int sizeBox = 30;
    final Color emptyCellColor = Color.WHITE;
    Color[][] isOccupied;

    public TetrisBoard() {
        isOccupied = new Color[nRows][nCols];

        // Initialize occupied table with default empty cell color to keeps track of pieces on the board.
        for (int i = 0; i < nRows; i++) {
            for (int k = 0; k < nCols; k++) {
                isOccupied[i][k] = emptyCellColor;
            }
        }
    }

    // Check for filled rows and clearing them
    void clearLines() {
        boolean hasFullRow = false;

        // mark rows to be cleared
        for (int row = 0; row < this.nRows; row++) {
            if(isRowFull(row)){
                hasFullRow = true;
                for(int col = 0; col < nCols; col++) {
                    isOccupied[row][col] = Color.lightGray;
                }
            }
        }

        if (!hasFullRow) return;
    }


    private boolean isRowFull(int row){
        for(int i = 0; i < nCols; i++){
            if(getColorAt(row, i).equals(this.emptyCellColor)) {
                return false;
            }
        }
        return true;
    }

    void placePiece(Piece piece) {
        for (int i = 0; i < piece.shape.length; i++) {
            int col = piece.pivotCol + piece.shape[i].x;
            int row = piece.pivotRow + piece.shape[i].y;

            isOccupied[row][col] = piece.getColor();
        }
    }


    public Color getColorAt(int row, int col) {
        if (row < 0 || col < 0 || row >= nRows || col >= nCols) {

            System.out.printf("Out of board   row: %d, col: %d%n", row, col);
            return emptyCellColor;
        }

        return isOccupied[row][col];
    }


    public int getWidth() {
        return this.nCols * this.sizeBox;
    }

    public int getHeight() {
        return this.nRows * this.sizeBox;
    }

}
