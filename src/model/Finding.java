package model;



/**
 * The Finding class represents an abstract finding with a description, value, and the player who photographed it.
 */
public abstract class Finding {
    private String description;
    private int value;
    private Player photographedBy; 




    /**
     * Constructs a Finding with the specified description and value.
     * 
     * @param description the description of the finding
     * @param value the value of the finding
     * @pre description is not null
     * @post a Finding is created with the specified description and value, and photographedBy is initialized as null
     */
    public Finding(String description, int value) {
        this.description = description;
        this.value = value;
        this.photographedBy = null; 
    }





    /**
     * Returns the description of the finding.
     * 
     * @return the description of the finding
     * @pre none
     * @post the description of the finding is returned
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the value of the finding.
     * 
     * @return the value of the finding
     * @pre none
     * @post the value of the finding is returned
     */
    public int getValue() {
        return value;
    }





    /**
     * Returns the player who photographed the finding.
     * 
     * @return the player who photographed the finding, or null if not photographed
     * @pre none
     * @post the player who photographed the finding is returned, or null if not photographed
     */
    public Player getPhotographedBy() {
        return photographedBy;
    }




    /**
     * Sets the player who photographed the finding.
     * 
     * @param player the player who photographed the finding
     * @pre player is not null
     * @post the player who photographed the finding is set
     */
    public void setPhotographedBy(Player player) {
        this.photographedBy = player;
    }





    /**
     * Returns the image path of the finding.
     * 
     * @return the image path of the finding
     * @pre none
     * @post the image path of the finding is returned
     */
    public abstract String getImagePath();




    
    /**
     * Returns specific text for the finding.
     * 
     * @return specific text for the finding
     * @pre none
     * @post specific text for the finding is returned
     */
    public abstract String getSpecificText();
}