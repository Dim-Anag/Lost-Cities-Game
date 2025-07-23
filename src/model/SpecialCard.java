package model;

import javax.swing.ImageIcon;



/**
 * The SpecialCard class represents an abstract special card associated with a specific palace.
 */
public abstract class SpecialCard extends Card {
    private ImageIcon icon;




     /**
     * Constructs a SpecialCard with the specified palace and image path.
     * 
     * @param palace the name of the palace
     * @param imagePath the path to the image of the card
     * @pre palace is not null, imagePath is not null
     * @post a SpecialCard is created with the specified palace and image path
     */
    public SpecialCard(String palace, String imagePath) {
        super(palace);
        this.icon = new ImageIcon(imagePath);
    }






    /**
     * Returns the icon of the card.
     * 
     * @return the icon of the card
     * @pre none
     * @post the icon of the card is returned
     */
    @Override
    public ImageIcon getIcon() {
        return icon;
    }




    /**
     * Returns the value of the card.
     * 
     * @return the value of the card, which is 0 for special cards
     * @pre none
     * @post the value of the card is returned
     */
    @Override
    public int getValue() {
        return 0; 
    }





    /**
     * Returns a string representation of the SpecialCard.
     * 
     * @return a string representation of the SpecialCard
     * @pre none
     * @post a string representation of the SpecialCard is returned
     */
    @Override
    public String toString() {
        return getPalace() + " " + getClass().getSimpleName();
    }
}