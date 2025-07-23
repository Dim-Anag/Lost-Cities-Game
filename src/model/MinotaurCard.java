package model;


/**
 * The MinotaurCard class represents a special card associated with a specific palace.
 */
public class MinotaurCard extends SpecialCard {
    private static final String IMAGE_PATH_KNOSSOS = "project_assets/images/cards/knossosMin.jpg";
    private static final String IMAGE_PATH_PHAISTOS = "project_assets/images/cards/phaistosMin.jpg";
    private static final String IMAGE_PATH_MALIA = "project_assets/images/cards/maliaMin.jpg";
    private static final String IMAGE_PATH_ZAKROS = "project_assets/images/cards/zakrosMin.jpg";


    /**
     * Constructs a MinotaurCard with the specified palace.
     * 
     * @param palace the name of the palace
     * @pre palace is not null and is a valid palace name
     * @post a MinotaurCard is created with the specified palace and corresponding image path
     */
    public MinotaurCard(String palace) {
        super(palace, getImagePath(palace));
    }



     /**
     * Returns the image path for the specified palace.
     * 
     * @param palace the name of the palace
     * @return the image path for the palace
     * @pre palace is not null and is a valid palace name
     * @post the image path for the palace is returned
     */
    private static String getImagePath(String palace) {
        switch (palace) {
            case "Knossos":
                return IMAGE_PATH_KNOSSOS;
            case "Phaistos":
                return IMAGE_PATH_PHAISTOS;
            case "Malia":
                return IMAGE_PATH_MALIA;
            case "Zakros":
                return IMAGE_PATH_ZAKROS;
            default:
                throw new IllegalArgumentException("Unknown palace: " + palace);
        }
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
        return card instanceof MinotaurCard && this.getPalace().equals(card.getPalace());
    }
    



    /**
     * Returns a string representation of the MinotaurCard.
     * 
     * @return a string representation of the MinotaurCard
     * @pre none
     * @post returns a string representation of the MinotaurCard
     */
    @Override
    public String toString() {
        return getPalace() + " Minotaur";
    }
}