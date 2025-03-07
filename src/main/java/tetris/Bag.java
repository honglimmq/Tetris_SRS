package tetris;

import java.util.*;


// bag generators
public class Bag {
    private List<Tetromino> bag;
    private Random rand;

    Bag() {
        this.bag = new LinkedList<>();
        this.rand = new Random();
        resetBag();
    }

    // look at the next piece to grab in the bag
    Tetromino peek() {
        if (bag.isEmpty()) {
            return null;
        }

        return bag.get(0);
    }

    Tetromino getNextPiece() {
        if(bag.isEmpty()){
            this.resetBag();
        }
        Tetromino next = bag.get(0);
        bag.remove(0);

        return next;
    }


    // randomise the current bag
    void shuffle() {
        Collections.shuffle(this.bag, this.rand);
    }

    // grab a new set of pieces
    void resetBag() {
        if (!bag.isEmpty()) {
            bag = new ArrayList<Tetromino>();
        }
        bag.addAll(Arrays.asList(Tetromino.values()));
        shuffle();
    }

    boolean isEmpty() {
        return bag.isEmpty();
    }
}
