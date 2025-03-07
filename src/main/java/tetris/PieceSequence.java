package tetris;

import java.util.ArrayList;
import java.util.List;

public class PieceSequence {
    private List<Tetromino> sequence; // list of preview pieces
    final private int nPieces = 5; // number of preview pieces
    private Bag bag; // 7-randomiser bag


    PieceSequence() {
        this.sequence = new ArrayList<Tetromino>(nPieces);
        this.bag = new Bag();
    }

    // fill up the sequence list until full
    void fillNextSequence() {
        while (sequence.size() < nPieces) {
            sequence.add(bag.getNextPiece());
        }
    }

    // retrieve the next piece in the sequence list then remove that piece from the list.
    public Tetromino getNextPiece() {
        if (sequence.isEmpty()) {
            fillNextSequence();
        }

        return sequence.remove(0);
    }

    // retrieve a deep copy of the list of upcomingPieces
    public List<Tetromino> getUpcomingPieces() {
        return new ArrayList<>(sequence);
    }

    // view the piece at specific location
    // return first piece in sequence if out of bound.
    public Tetromino peekAt(int position) {
        if (sequence.size() < this.nPieces) {
            this.fillNextSequence();
        }

        if (position >= 0 && position < this.nPieces) {
            return sequence.get(position);
        }

        return sequence.get(0);
    }
}
