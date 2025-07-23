package model;

import javax.swing.ImageIcon;

/**
 * The Card class represents an abstract card associated with a specific palace.
 */
public abstract class Card {
    private String palace;





    /**
     * Constructs a Card with the specified palace.
     * 
     * @param palace the name of the palace
     * @pre palace is not null
     * @post a Card is created with the specified palace
     */
    public Card(String palace) {
        this.palace = palace;
    }





    /**
     * Returns the name of the palace associated with this card.
     * 
     * @return the name of the palace
     * @pre none
     * @post the name of the palace is returned
     */
    public String getPalace() {
        return palace;
    }





    /**
     * Returns the icon representing this card.
     * 
     * @return the ImageIcon representing this card
     * @pre none
     * @post the icon representing this card is returned
     */
    public abstract ImageIcon getIcon();





    /**
     * Checks if this card matches the specified card.
     * 
     * @param card the card to check for a match
     * @return true if this card matches the specified card, false otherwise
     * @pre card is not null
     * @post returns true if this card matches the specified card, false otherwise
     */
    public abstract boolean matchCard(Card card);





    /**
     * Returns the value of this card.
     * 
     * @return the value of this card
     * @pre none
     * @post the value of this card is returned
     */
    public abstract int getValue();




    
    /**
     * Returns a string representation of this card.
     * 
     * @return a string representation of this card
     * @pre none
     * @post a string representation of this card is returned
     */
    @Override
    public abstract String toString();
}