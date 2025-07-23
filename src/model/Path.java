package model;


/**
 * The Path class represents a path associated with a specific palace.
 * It contains positions, image paths, and the palace name.
 */
public class Path {
    private Position[] positions;
    private String[] imagePaths;
    private String palaceName;
    private static final int CHECKPOINT_POSITION = 6; 





    /**
     * Constructs a Path with the specified palace name and image paths.
     * 
     * @param palaceName the name of the palace
     * @param imagePaths an array of image paths
     * @pre palaceName is not null, imagePaths is not null and has exactly 9 elements
     * @post a Path is created with the specified palace name and image paths
     */
    public Path(String palaceName, String[] imagePaths) {
        if (imagePaths.length != 9) {
            throw new IllegalArgumentException("There must be exactly 9 image paths.");
        }
        this.palaceName = palaceName;
        this.imagePaths = imagePaths;

        positions = new Position[9]; 
        positions[0] = new SimplePosition(-20);
        positions[1] = new FindingPosition(-15, null);
        positions[2] = new SimplePosition(-10);
        positions[3] = new FindingPosition(5, null);
        positions[4] = new SimplePosition(10);
        positions[5] = new FindingPosition(15, null);
        positions[6] = new SimplePosition(30);
        positions[7] = new FindingPosition(35, null);
        positions[8] = new FindingPosition(50, null); 
    }



    /**
     * Returns the positions in the path.
     * 
     * @return an array of Position instances
     * @pre none
     * @post the positions in the path are returned
     */
    public Position[] getPositions() {
        return positions;
    }




    /**
     * Returns the image path for the specified position.
     * 
     * @param position the position index
     * @return the image path for the position
     * @pre position is between 0 and 8 (inclusive)
     * @post the image path for the position is returned
     */
    public String getImagePath(int position) {
        return imagePaths[position];
    }




    /**
     * Returns the name of the palace.
     * 
     * @return the name of the palace
     * @pre none
     * @post the name of the palace is returned
     */
    public String getPalaceName() {
        return palaceName;
    }




     /**
     * Checks if the specified position is a checkpoint.
     * 
     * @param position the position index
     * @return true if the position is a checkpoint, false otherwise
     * @pre position is between 0 and 8 (inclusive)
     * @post returns true if the position is a checkpoint, false otherwise
     */
    public boolean isCheckpoint(int position) {
        return position == CHECKPOINT_POSITION;
    }
}