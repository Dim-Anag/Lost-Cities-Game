package model;

/**
 * The FindingPosition class represents a position on the board that contains a finding.
 */
public class FindingPosition extends Position {
    private Finding finding;




    
    /**
     * Constructs a FindingPosition with the specified score and finding.
     * 
     * @param score the score of the position
     * @param finding the finding at the position
     * @pre finding is not null
     * @post a FindingPosition is created with the specified score and finding
     */
    public FindingPosition(int score, Finding finding) {
        super(score);
        this.finding = finding;
    }






    /**
     * Returns the finding at this position.
     * 
     * @return the finding at this position
     * @pre none
     * @post the finding at this position is returned
     */
    public Finding getFinding() {
        return finding;
    }





    /**
     * Sets the finding at this position.
     * 
     * @param finding the finding to set at this position
     * @pre finding is not null
     * @post the finding at this position is set to the provided finding
     */
    public void setFinding(Finding finding) {
        this.finding = finding;
    }





    /**
     * Checks if a finding is available at this position.
     * 
     * @return true if a finding is available, false otherwise
     * @pre none
     * @post returns true if a finding is available, false otherwise
     */
    public boolean isFindingAvailable() {
        return finding != null;
    }





    /**
     * Collects the finding at this position, making it unavailable.
     * 
     * @pre none
     * @post the finding at this position is set to null
     */
    public void collectFinding() {
        this.finding = null;
    }
}