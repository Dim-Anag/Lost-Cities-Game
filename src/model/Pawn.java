package model;

import javax.swing.ImageIcon;
import java.awt.Image;



/**
 * The Pawn class represents a pawn in the game, which can be either an "Archaeologist" or "Theseus".
 * It contains information about its position, state, and the player who owns it.
 */
public class Pawn {
    private String type; 
    private int position;
    private boolean revealed;
    private String path; 
    private Player player; 
    private String uniqueId; 
    private boolean immobilized;
    private ImageIcon imageIcon;
    private int immobilizedTurns;
    private int findingsDestroyed; 
    private boolean hidden;
    private static final String ARCHAEOLOGIST_IMAGE_PATH = "project_assets/images/pionia/arch.jpg";
    private static final String THESEUS_IMAGE_PATH = "project_assets/images/pionia/theseus.jpg";
    private static final String HIDDEN_IMAGE_PATH = "project_assets/images/pionia/question.jpg"; 
   
   
   
   
   
   /**
     * Constructs a Pawn with the specified type, player, and unique identifier.
     * 
     * @param type the type of the pawn ("Archaeologist" or "Theseus")
     * @param player the player who owns this pawn
     * @param uniqueId the unique identifier for the pawn
     * @pre type is "Archaeologist" or "Theseus", player is not null, uniqueId is not null
     * @post a Pawn is created with the specified type, player, and unique identifier
     */
    public Pawn(String type, Player player, String uniqueId) {
        this.type = type;
        this.position = 0;
        this.revealed = false;
        this.path = ""; 
        this.player = player; 
        this.uniqueId = uniqueId; 
        this.immobilized = false;
        this.immobilizedTurns = 0;
        this.findingsDestroyed = 0;
        if (type.equals("Archaeologist")) {
            this.imageIcon = new ImageIcon(new ImageIcon(ARCHAEOLOGIST_IMAGE_PATH).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        } else if (type.equals("Theseus")) {
            this.imageIcon = new ImageIcon(new ImageIcon(THESEUS_IMAGE_PATH).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
        }
    }




     /**
     * Returns the type of the pawn.
     * 
     * @return the type of the pawn
     * @pre none
     * @post the type of the pawn is returned
     */
    public String getType() {
        return type;
    }




    /**
     * Returns the position of the pawn.
     * 
     * @return the position of the pawn
     * @pre none
     * @post the position of the pawn is returned
     */
    public int getPosition() {
        return position;
    }




    /**
     * Sets the position of the pawn.
     * 
     * @param position the new position of the pawn
     * @pre position is a valid position on the board
     * @post the position of the pawn is updated
     */
    public void setPosition(int position) {
        this.position = position;
    }



    /**
     * Moves the pawn by the specified number of steps.
     * 
     * @param steps the number of steps to move the pawn
     * @pre steps is a valid number of steps
     * @post the position of the pawn is updated by the specified number of steps
     */
    public void move(int steps) {
        position += steps;
    }





    /**
     * Returns whether the pawn is revealed.
     * 
     * @return true if the pawn is revealed, false otherwise
     * @pre none
     * @post returns true if the pawn is revealed, false otherwise
     */
    public boolean isRevealed() {
        return revealed;
    }




    /**
     * Reveals the pawn.
     * 
     * @pre none
     * @post the pawn is revealed
     */
    public void reveal() {
        revealed = true;
    }




    /**
     * Returns the path the pawn is on.
     * 
     * @return the path the pawn is on
     * @pre none
     * @post the path the pawn is on is returned
     */
    public String getPath() {
        return path;
    }



    /**
     * Sets the path the pawn is on.
     * 
     * @param path the new path for the pawn
     * @pre path is a valid path
     * @post the path of the pawn is updated
     */
    public void setPath(String path) {
        this.path = path;
    }




    /**
     * Returns the player who owns this pawn.
     * 
     * @return the player who owns this pawn
     * @pre none
     * @post the player who owns this pawn is returned
     */
    public Player getPlayer() {
        return player;
    }




    /**
     * Returns the unique identifier for the pawn.
     * 
     * @return the unique identifier for the pawn
     * @pre none
     * @post the unique identifier for the pawn is returned
     */
    public String getUniqueId() {
        return uniqueId;
    }





    /**
     * Returns the image icon of the pawn.
     * 
     * @return the image icon of the pawn
     * @pre none
     * @post the image icon of the pawn is returned
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }





    /**
     * Returns whether the pawn is immobilized.
     * 
     * @return true if the pawn is immobilized, false otherwise
     * @pre none
     * @post returns true if the pawn is immobilized, false otherwise
     */
    public boolean isImmobilized() {
        return immobilized;
    }





    /**
     * Sets the immobilized state of the pawn.
     * 
     * @param immobilized the new immobilized state of the pawn
     * @pre none
     * @post the immobilized state of the pawn is updated
     */
    public void setImmobilized(boolean immobilized) {
        this.immobilized = immobilized;
        if (immobilized) {
            this.immobilizedTurns = 1; 
        } else {
            this.immobilizedTurns = 0; 
        }
    }




    /**
     * Returns the number of immobilized turns remaining for the pawn.
     * 
     * @return the number of immobilized turns remaining
     * @pre none
     * @post the number of immobilized turns remaining is returned
     */
    public int getImmobilizedTurns() {
        return immobilizedTurns;
    }




    /**
     * Sets the number of immobilized turns remaining for the pawn.
     * 
     * @param immobilizedTurns the new number of immobilized turns
     * @pre immobilizedTurns is a valid number of turns
     * @post the number of immobilized turns remaining is updated
     */
    public void setImmobilizedTurns(int immobilizedTurns) {
        this.immobilizedTurns = immobilizedTurns;
    }





    /**
     * Returns the number of findings destroyed by the pawn.
     * 
     * @return the number of findings destroyed by the pawn
     * @pre none
     * @post the number of findings destroyed by the pawn is returned
     */
    public int getFindingsDestroyed() {
        return findingsDestroyed;
    }





    /**
     * Increments the number of findings destroyed by the pawn.
     * 
     * @pre none
     * @post the number of findings destroyed by the pawn is incremented
     */
    public void incrementFindingsDestroyed() {
        this.findingsDestroyed++;
    }




    /**
     * Returns whether the pawn is hidden.
     * 
     * @return true if the pawn is hidden, false otherwise
     * @pre none
     * @post returns true if the pawn is hidden, false otherwise
     */
    public boolean isHidden() {
        return hidden;
    }




     /**
     * Sets the hidden state of the pawn.
     * 
     * @param hidden the new hidden state of the pawn
     * @pre none
     * @post the hidden state of the pawn is updated
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }




     /**
     * Returns the hidden image icon of the pawn.
     * 
     * @return the hidden image icon of the pawn
     * @pre none
     * @post the hidden image icon of the pawn is returned
     */
    public ImageIcon getHiddenImageIcon() {
        return new ImageIcon(new ImageIcon(HIDDEN_IMAGE_PATH).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
    }
}