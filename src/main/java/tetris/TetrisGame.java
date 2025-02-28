package tetris;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Timer;

// manages game logic and piece movement
public class TetrisGame {
    TetrisBoard board;
    TetrisUI gameUI;
    Piece currentPiece;

    Timer gameTimer;

    private final int FPS = 30; // set frame rate to 10 FPS
    private final int frameDelay = 700;

    boolean running = false;
    boolean gameOver = false;


    TetrisGame() {
        //
        board = new TetrisBoard();
        currentPiece = new Piece();

        // Import background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO
                    .read(new File("C:\\Users\\Ty\\eclipse-workspace\\Tetris\\src\\tetris\\img\\bg_space.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameUI = new TetrisUI(board, null, backgroundImage, this);
    }

    public void startGame() {
        gameUI.updateCurrentPiece(currentPiece);
        currentPiece.setPivotCol(board.nCols / 2 - 1);
        // Start game timer
        running = true;
        gameTimer = new Timer(frameDelay, new GameTimerListener());
        gameTimer.start();
    }

    public void run() {
        while (!gameOver) {
            // Update game state

        }
    }

    public boolean rotatePiece(boolean isClockwise, boolean shouldOffset) {
        int oldRotationState = currentPiece.getRotationState();
        currentPiece.rotate(isClockwise);
        int newRotationState = currentPiece.getRotationState();

        if (!canOffset(oldRotationState, newRotationState)) {
            rotatePiece(!isClockwise, false);
            return false;
        }
        return true;
    }

    // calculate exactly how far the current piece can drop
    private int calculateDropDistance() {
        int dropDistance = board.nRows; // starts from bottom of the board;

        for (Block block : currentPiece.shape) {
            int col = currentPiece.pivotCol + block.x;
            int row = currentPiece.pivotRow + block.y;
            int distance = 0;

            // the number of cells below until it would collide with an occupied cell or the bottom of the board
            while (row + distance + 1 < board.nRows
                    && board.getColorAt(row + distance + 1, col) == board.emptyCellColor) {
                distance++;
            }

            dropDistance = Math.min(dropDistance, distance);
        }
        return dropDistance;
    }

    public boolean hardDrop() {
        // Calculate then instantly move to final position
        int dropDistance = calculateDropDistance();
        currentPiece.pivotRow += dropDistance;

        // Lock piece in place
        board.placePiece(currentPiece);


        board.clearLines();
        // Spawn a new piece
        currentPiece = new Piece();
        currentPiece.setPivotCol(board.nCols / 2 - 1);
        gameUI.updateCurrentPiece(currentPiece);

        return true;
    }

    public boolean tryMove(int deltaX, int deltaY) {

        for (int i = 0; i < currentPiece.shape.length; i++) {
            int newXPos = currentPiece.pivotCol + currentPiece.shape[i].x + deltaX;
            int newYPos = currentPiece.pivotRow + currentPiece.shape[i].y + deltaY;

            // Check boundaries and if a cell is already occupied
            if (newXPos < 0 || newXPos >= board.nCols || newYPos >= board.nRows || newYPos < 0 || board.getColorAt(newYPos, newXPos) != board.emptyCellColor) {
                return false;
            }
        }

        currentPiece.movePiece(deltaX, deltaY);
        return true;
    }


    public boolean canOffset(int oldRotationIdx, int newRotationIdx) {
        Block[][] offsetData;

        // Get the corresponding offset data
        if (currentPiece.tetrisPiece == Tetromino.O) {
            offsetData = Piece.oOffset;
        } else if (currentPiece.tetrisPiece == Tetromino.I) {
            offsetData = Piece.iOffset;
        } else {
            offsetData = Piece.jlstzOffset;
        }

        int deltaX = 0, deltaY = 0;

        for (int testID = 0; testID < offsetData.length; testID++) {
            deltaX = offsetData[testID][oldRotationIdx].x - offsetData[testID][newRotationIdx].x;
            deltaY = offsetData[testID][oldRotationIdx].y - offsetData[testID][newRotationIdx].y;

            if (tryMove(deltaX, deltaY)) {
                return true;
            }
        }


        return false;
    }

    protected boolean move(GameAction action) {
        switch (action) {
            case MoveLeft:
                return tryMove(-1, 0);
            case MoveRight:
                return tryMove(1, 0);
            case MoveDown:
                return tryMove(0, 1);
            case RotateLeft:
                return rotatePiece(false, true);
            case RotateRight:
                return rotatePiece(true, true);
            case HardDrop:
				return hardDrop();

        }
        return false;
    }

    

    private class GameTimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!move(GameAction.MoveDown)) {
                board.placePiece(currentPiece);
                board.clearLines();
                currentPiece = new Piece();
                currentPiece.setPivotCol(board.nCols / 2 - 1);
                gameUI.updateCurrentPiece(currentPiece);
            }

            gameUI.repaint();
        }
    }

}
