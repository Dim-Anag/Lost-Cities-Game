package model;



/**
 * The Position class represents a position on the board with an associated score.
 * It is an abstract class that can be extended by specific types of positions.
 */
public abstract class Position {
    private int score;




     /**
     * Constructs a Position with the specified score.
     * 
     * @param score the score associated with the position
     * @pre score is a valid integer
     * @post a Position is created with the specified score
     */
    public Position(int score) {
        this.score = score;
    }





     /**
     * Returns the score of the position.
     * 
     * @return the score of the position
     * @pre none
     * @post the score of the position is returned
     */
    public int getScore() {
        return score;
    }
}