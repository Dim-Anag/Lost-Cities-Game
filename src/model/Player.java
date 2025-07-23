package model;

import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;





/**
 * The Player class represents a player in the game.
 * It contains information about the player's name, pawns, hand, score, findings, and other game-related data.
 */
public class Player {
    private String name;
    private List<Pawn> pawns;
    private List<Card> hand;
    private int score;
    private List<Finding> findings;
    private List<String> pawnPaths; 
    private int archaeologistCount = 3;
    private int theseusCount = 1;
    private Map<String, Integer> highestValueCards; 
    private JLabel scoreLabel; 






    /**
     * Constructs a Player with the specified name.
     * 
     * @param name the name of the player
     * @pre name is not null
     * @post a Player is created with the specified name and initialized fields
     */
    public Player(String name) {
        this.name = name;
        this.pawns = new ArrayList<>();
        this.hand = new ArrayList<>();
        this.score = 0;
        this.findings = new ArrayList<>();
        this.pawnPaths = new ArrayList<>();
        this.highestValueCards = new HashMap<>(); 
        this.scoreLabel = new JLabel("Score: " + score); 

        pawns.add(new Pawn("Archaeologist", this, "A1"));
        pawns.add(new Pawn("Archaeologist", this, "A2"));
        pawns.add(new Pawn("Archaeologist", this, "A3"));
        pawns.add(new Pawn("Theseus", this, "T1"));
    }





     /**
     * Returns the name of the player.
     * 
     * @return the name of the player
     * @pre none
     * @post the name of the player is returned
     */
    public String getName() {
        return name;
    }




    /**
     * Returns the list of pawns owned by the player.
     * 
     * @return the list of pawns
     * @pre none
     * @post the list of pawns is returned
     */
    public List<Pawn> getPawns() {
        return pawns;
    }





     /**
     * Returns the pawn at the specified index.
     * 
     * @param index the index of the pawn
     * @return the pawn at the specified index
     * @pre index is a valid index in the list of pawns
     * @post the pawn at the specified index is returned
     */
    public Pawn getPawnByIndex(int index) {
        return pawns.get(index);
    }






    /**
     * Returns the player's hand of cards.
     * 
     * @return the list of cards in the player's hand
     * @pre none
     * @post the list of cards in the player's hand is returned
     */
    public List<Card> getHand() {
        return hand;
    }






    /**
     * Adds a card to the player's hand.
     * 
     * @param card the card to add
     * @pre card is not null
     * @post the card is added to the player's hand
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }





    /**
     * Returns the player's score.
     * 
     * @return the player's score
     * @pre none
     * @post the player's score is returned
     */
    public int getScore() {
        return score;
    }






    /**
     * Sets the player's score.
     * 
     * @param score the new score
     * @pre score is a valid integer
     * @post the player's score is updated and the score label is updated
     */
    public void setScore(int score) {
        this.score = score;
        this.scoreLabel.setText("Score: " + score); 
    }






    /**
     * Adds points to the player's score.
     * 
     * @param points the points to add
     * @pre points is a valid integer
     * @post the player's score is incremented by the specified points and the score label is updated
     */
    public void addScore(int points) {
        score += points;
        this.scoreLabel.setText("Score: " + score); 
    }






     /**
     * Returns the list of findings collected by the player.
     * 
     * @return the list of findings
     * @pre none
     * @post the list of findings is returned
     */
    public List<Finding> getFindings() {
        return findings;
    }



    /**
     * Adds a finding to the player's collection.
     * 
     * @param finding the finding to add
     * @pre finding is not null
     * @post the finding is added to the player's collection
     */
    public void addFinding(Finding finding) {
        findings.add(finding);
    }




    /**
     * Returns the number of archaeologists the player has.
     * 
     * @return the number of archaeologists
     * @pre none
     * @post the number of archaeologists is returned
     */
    public int getArchaeologistCount() {
        return archaeologistCount;
    }






    /**
     * Returns an available archaeologist pawn.
     * 
     * @return an available archaeologist pawn, or null if none are available
     * @pre none
     * @post an available archaeologist pawn is returned, or null if none are available
     */
    public Pawn getArchaeologist() {
        for (Pawn pawn : pawns) {
            if (pawn.getType().equals("Archaeologist") && pawn.getPath().isEmpty()) {
                return pawn;
            }
        }
        return null; 
    }







    /**
     * Returns an available Theseus pawn.
     * 
     * @return an available Theseus pawn, or null if none are available
     * @pre none
     * @post an available Theseus pawn is returned, or null if none are available
     */
    public Pawn getTheseus() {
        for (Pawn pawn : pawns) {
            if (pawn.getType().equals("Theseus") && pawn.getPath().isEmpty()) {
                return pawn;
            }
        }
        return null; 
    }





    /**
     * Returns the number of Theseus pawns the player has.
     * 
     * @return the number of Theseus pawns
     * @pre none
     * @post the number of Theseus pawns is returned
     */
    public int getTheseusCount() {
        return theseusCount;
    }




    /**
     * Checks if the player has a pawn in the specified path.
     * 
     * @param palace the name of the palace
     * @return true if the player has a pawn in the specified path, false otherwise
     * @pre palace is not null
     * @post returns true if the player has a pawn in the specified path, false otherwise
     */
    public boolean hasPawnInPath(String palace) {
        return pawnPaths.contains(palace);
    }





    /**
     * Adds a path to the list of paths that have pawns.
     * 
     * @param palace the name of the palace
     * @pre palace is not null
     * @post the path is added to the list of paths that have pawns
     */
    public void addPawnPath(String palace) {
        if (!pawnPaths.contains(palace)) {
            pawnPaths.add(palace);
        }
    }






    /**
     * Returns the pawn in the specified path.
     * 
     * @param palace the name of the palace
     * @return the pawn in the specified path, or null if no pawn is in this path
     * @pre palace is not null
     * @post the pawn in the specified path is returned, or null if no pawn is in this path
     */
    public Pawn getPawnInPath(String palace) {
        for (Pawn pawn : pawns) {
            if (pawn.getPath().equals(palace)) {
                return pawn;
            }
        }
        return null;
    }




     /**
     * Returns the list of paths that have pawns.
     * 
     * @return the list of paths that have pawns
     * @pre none
     * @post the list of paths that have pawns is returned
     */
    public List<String> getPawnPaths() {
        return pawnPaths;
    }





    /**
     * Returns the position of the pawn in the specified path.
     * 
     * @param palace the name of the palace
     * @return the position of the pawn in the specified path, or -1 if no pawn is in this path
     * @pre palace is not null
     * @post the position of the pawn in the specified path is returned, or -1 if no pawn is in this path
     */
    public int getPawnPosition(String palace) {
        for (Pawn pawn : pawns) {
            if (pawn.getPath().equals(palace)) {
                return pawn.getPosition();
            }
        }
        return -1; 
    }





    /**
     * Decrements the number of archaeologists the player has.
     * 
     * @pre none
     * @post the number of archaeologists is decremented by 1
     */
    public void decrementArchaeologistCount() {
        if (archaeologistCount > 0) {
            archaeologistCount--;
        }
    }





    /**
     * Decrements the number of Theseus pawns the player has.
     * 
     * @pre none
     * @post the number of Theseus pawns is decremented by 1
     */
    public void decrementTheseusCount() {
        if (theseusCount > 0) {
            theseusCount--;
        }
    }





    /**
     * Returns the highest value card played for the specified palace.
     * 
     * @param palace the name of the palace
     * @return the highest value card played for the specified palace
     * @pre palace is not null
     * @post the highest value card played for the specified palace is returned
     */
    public int getHighestValueCard(String palace) {
        return highestValueCards.getOrDefault(palace, 0);
    }





    /**
     * Updates the highest value card played for the specified palace.
     * 
     * @param palace the name of the palace
     * @param value the value of the card
     * @pre palace is not null, value is a valid card value
     * @post the highest value card played for the specified palace is updated
     */
    public void updateHighestValueCard(String palace, int value) {
        highestValueCards.put(palace, value);
    }





    /**
     * Returns the score label for the player.
     * 
     * @return the score label for the player
     * @pre none
     * @post the score label for the player is returned
     */
    public JLabel getScoreLabel() {
        return scoreLabel;
    }
}