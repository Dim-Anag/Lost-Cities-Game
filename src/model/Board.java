package model;

/**
 * The Board class represents the game board, which consists of multiple paths.
 */
public class Board {
    private Path[] paths;





    /**
     * Constructs a Board with predefined paths for each palace.
     * 
     * @pre none
     * @post the board is initialized with paths for Knossos, Phaistos, Malia, and Zakros
     */
    public Board() {
        paths = new Path[4];
        paths[0] = new Path("Knossos", new String[]{
            "project_assets/images/paths/knossos.jpg", 
            "project_assets/images/paths/knossos2.jpg",
            "project_assets/images/paths/knossos.jpg", 
            "project_assets/images/paths/knossos2.jpg",
            "project_assets/images/paths/knossos.jpg", 
            "project_assets/images/paths/knossos2.jpg",
            "project_assets/images/paths/knossos.jpg", 
            "project_assets/images/paths/knossos2.jpg",
            "project_assets/images/paths/knossosPalace.jpg" 
        });
        paths[1] = new Path("Phaistos", new String[]{
            "project_assets/images/paths/phaistos.jpg", 
            "project_assets/images/paths/phaistos2.jpg",
            "project_assets/images/paths/phaistos.jpg", 
            "project_assets/images/paths/phaistos2.jpg",
            "project_assets/images/paths/phaistos.jpg", 
            "project_assets/images/paths/phaistos2.jpg",
            "project_assets/images/paths/phaistos.jpg", 
            "project_assets/images/paths/phaistos2.jpg",
            "project_assets/images/paths/phaistosPalace.jpg" 
        });
        paths[2] = new Path("Malia", new String[]{
            "project_assets/images/paths/malia.jpg", 
            "project_assets/images/paths/malia2.jpg",
            "project_assets/images/paths/malia.jpg", 
            "project_assets/images/paths/malia2.jpg",
            "project_assets/images/paths/malia.jpg", 
            "project_assets/images/paths/malia2.jpg",
            "project_assets/images/paths/malia.jpg", 
            "project_assets/images/paths/malia2.jpg",
            "project_assets/images/paths/maliaPalace.jpg" 
        });
        paths[3] = new Path("Zakros", new String[]{
            "project_assets/images/paths/zakros.jpg", 
            "project_assets/images/paths/zakros2.jpg",
            "project_assets/images/paths/zakros.jpg", 
            "project_assets/images/paths/zakros2.jpg",
            "project_assets/images/paths/zakros.jpg", 
            "project_assets/images/paths/zakros2.jpg",
            "project_assets/images/paths/zakros.jpg", 
            "project_assets/images/paths/zakros2.jpg",
            "project_assets/images/paths/zakrosPalace.jpg" 
        });
    }




    
    /**
     * Returns the paths on the board.
     * 
     * @return an array of Path instances representing the paths on the board
     * @pre none
     * @post the paths on the board are returned
     */
    public Path[] getPaths() {
        return paths;
    }




     /**
     * Returns the path for the specified palace.
     * 
     * @param palace the name of the palace
     * @return the Path instance for the specified palace, or null if not found
     * @pre palace is not null
     * @post the path for the specified palace is returned, or null if not found
     */
    public Path getPathByPalace(String palace) {
        for (Path path : paths) {
            if (path.getPalaceName().equals(palace)) {
                return path;
            }
        }
        return null;
    }
}