package model;

/**
 * The AriadneCard class represents a special card associated with a specific palace.
 */
public class AriadneCard extends SpecialCard {
    private static final String IMAGE_PATH_KNOSSOS = "project_assets/images/cards/knossosAri.jpg";
    private static final String IMAGE_PATH_PHAISTOS = "project_assets/images/cards/phaistosAri.jpg";
    private static final String IMAGE_PATH_MALIA = "project_assets/images/cards/maliaAri.jpg";
    private static final String IMAGE_PATH_ZAKROS = "project_assets/images/cards/zakrosAri.jpg";





    /**
     * Constructs an AriadneCard with the specified palace.
     * 
     * @param palace the name of the palace
     * @pre palace is not null and is one of "Knossos", "Phaistos", "Malia", or "Zakros"
     * @post an AriadneCard is created with the specified palace and corresponding image path
     */
    public AriadneCard(String palace) {
        super(palace, getImagePath(palace));
    }






    /**
     * Returns the image path for the specified palace.
     * 
     * @param palace the name of the palace
     * @return the image path for the specified palace
     * @pre palace is not null and is one of "Knossos", "Phaistos", "Malia", or "Zakros"
     * @post the image path corresponding to the specified palace is returned
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
     * @param card the card to check for a match
     * @return true if the specified card is an AriadneCard and has the same palace, false otherwise
     * @pre card is not null
     * @post returns true if the specified card is an AriadneCard and has the same palace, false otherwise
     */
    @Override
    public boolean matchCard(Card card) {
        return card instanceof AriadneCard && this.getPalace().equals(card.getPalace());
    }



    
    /**
     * Returns a string representation of the AriadneCard.
     * 
     * @return a string representation of the AriadneCard
     * @pre none
     * @post returns a string representation of the AriadneCard
     */
    @Override
    public String toString() {
        return getPalace() + " Ariadne";
    }
}