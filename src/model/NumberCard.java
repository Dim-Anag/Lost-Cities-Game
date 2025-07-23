package model;

import javax.swing.ImageIcon;
import java.util.HashMap;
import java.util.Map;




/**
 * The NumberCard class represents a card with a specific value and associated with a specific palace.
 */
public class NumberCard extends Card {
    private int value;
    private ImageIcon icon;


    private static final Map<String, String> NUMBER_CARD_IMAGES = new HashMap<>();
    static {

        for (int i = 1; i <= 10; i++) {
            NUMBER_CARD_IMAGES.put("Knossos_" + i, "project_assets/images/cards/knossos" + i + ".jpg");
            NUMBER_CARD_IMAGES.put("Phaistos_" + i, "project_assets/images/cards/phaistos" + i + ".jpg");
            NUMBER_CARD_IMAGES.put("Malia_" + i, "project_assets/images/cards/malia" + i + ".jpg");
            NUMBER_CARD_IMAGES.put("Zakros_" + i, "project_assets/images/cards/zakros" + i + ".jpg");
        }
    }


    /**
     * Constructs a NumberCard with the specified palace and value.
     * 
     * @param palace the name of the palace
     * @param value the value of the card
     * @pre palace is not null and is a valid palace name, value is between 1 and 10
     * @post a NumberCard is created with the specified palace and value, and the corresponding image path is set
     */
    public NumberCard(String palace, int value) {
        super(palace);
        this.value = value;
        String imagePath = NUMBER_CARD_IMAGES.get(palace + "_" + value);
        this.icon = new ImageIcon(imagePath);
    }



    /**
     * Returns the value of the card.
     * 
     * @return the value of the card
     * @pre none
     * @post the value of the card is returned
     */
    @Override
    public int getValue() {
        return value;
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
     * Checks if this card matches the specified card.
     * 
     * @param card the card to match
     * @return true if the cards match, false otherwise
     * @pre card is not null
     * @post returns true if the cards match, false otherwise
     */
    @Override
    public boolean matchCard(Card card) {
        if (card instanceof NumberCard) {
            NumberCard numberCard = (NumberCard) card;
            return this.getPalace().equals(numberCard.getPalace()) && this.value <= numberCard.getValue();
        }
        return false;
    }




    /**
     * Returns a string representation of the NumberCard.
     * 
     * @return a string representation of the NumberCard
     * @pre none
     * @post returns a string representation of the NumberCard
     */
    @Override
    public String toString() {
        return getPalace() + " " + getValue();
    }
}