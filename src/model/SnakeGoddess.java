package model;



/**
 * The SnakeGoddess class represents a specific type of finding in the game.
 */
public class SnakeGoddess extends Finding {
    private static final String IMAGE_PATH = "project_assets/images/findings/snakes.jpg";
    private static final String SPECIFIC_TEXT = "Brikes ena agalma tis THeas ton Fidion!!!;Os i thea me ta fidia onomazetai o typos agalmatidioy poy brethike se anaskafes stoys Minoikoys arxaiologikoys topoys poy paroysiazei gynaika na krataei fidia. Ta agalmatida xronologoyntai ston 16o aiona p.X.. Liges plirofories exoyme gia tin ermineia ton agalmatidion. O Ebans syndeei tin thea ton ofeon me tin Aigyptiaki thea Oyatzet. Einai propompos tis kritikis Reas kai paroysiazei megali omoiotita me tin frygiki Kybeli kai tin efesia Artemida.\r\n" + 
                "Βρήκες ένα άγαλμα της Θεάς των Φιδιών!!!;Ως η θεά με τα φίδια ονομάζεται ο τύπος αγαλματίδιου που βρέθηκε σε ανασκαφές στους Μινωικούς αρχαιολογικούς τόπους που παρουσιάζει γυναίκα να κρατάει φίδια. Τα αγαλματίδα χρονολογούνται στον 16ο αιώνα π.Χ.. Λίγες πληροφορίες έχουμε για την ερμηνεία των αγαλματιδίων. Ο Έβανς συνδέει την θεά των όφεων με την Αιγυπτιακή θεά Ουατζέτ. Είναι προπομπός της κρητικής Ρέας και παρουσιάζει μεγάλη ομοιότητα με την φρυγική Κυβέλη και την εφεσία Αρτέμιδα.\r\n" ;





     /**
     * Constructs a SnakeGoddess with the specified description and value.
     * 
     * @param description the description of the SnakeGoddess
     * @param value the value of the SnakeGoddess
     * @pre description is not null, value is a valid integer
     * @post a SnakeGoddess is created with the specified description and value
     */
    public SnakeGoddess(String description, int value) {
        super(description, value);
    }




    /**
     * Returns the image path for the SnakeGoddess.
     * 
     * @return the image path for the SnakeGoddess
     * @pre none
     * @post the image path for the SnakeGoddess is returned
     */
    @Override
    public String getImagePath() {
        return IMAGE_PATH;
    }




     /**
     * Returns the specific text for the SnakeGoddess.
     * 
     * @return the specific text for the SnakeGoddess
     * @pre none
     * @post the specific text for the SnakeGoddess is returned
     */
    @Override
    public String getSpecificText() {
        return SPECIFIC_TEXT;
    }
}