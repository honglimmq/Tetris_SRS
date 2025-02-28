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


    void clearLines() {
        // check full rows from bottom to top
        for (int row = nRows - 1; row >= 0; row--) {
            if (isRowFull(row)) {
                // clear this row by shifting all rows above it down by one
                for (int r = row; r > 0; r--) {
                    // copy the row above to the current row
                    System.arraycopy(isOccupied[r - 1], 0, isOccupied[r], 0, nCols);
                }

                // clear the top row
                for (int col = 0; col < nCols; col++) {
                    isOccupied[0][col] = emptyCellColor;
                }

                // since board have shifted rows down, need to recheck the current row
                row++;
            }
        }
    }


    // Returns true if the given row is full (i.e. no empty cells)
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
