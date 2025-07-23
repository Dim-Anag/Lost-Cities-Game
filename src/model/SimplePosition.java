package model;



/**
 * The SimplePosition class represents a simple position on the board with an associated score.
 */
public class SimplePosition extends Position {



     /**
     * Constructs a SimplePosition with the specified score.
     * 
     * @param score the score associated with the position
     * @pre score is a valid integer
     * @post a SimplePosition is created with the specified score
     */
    public SimplePosition(int score) {
        super(score);
    }
}