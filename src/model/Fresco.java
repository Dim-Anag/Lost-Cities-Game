package model;

import java.util.HashMap;
import java.util.Map;




/**
 * The Fresco class represents a specific type of finding with predefined values, images, and texts.
 */
public class Fresco extends Finding {
    private static final Map<String, Integer> FRESCOES = new HashMap<>();
    private static final Map<String, String> FRESCOES_IMAGES = new HashMap<>();
    private static final Map<String, String> FRESCOES_TEXTS = new HashMap<>(); // Add this map

    static {
        FRESCOES.put("Fresco 1", 20);
        FRESCOES.put("Fresco 2", 20);
        FRESCOES.put("Fresco 3", 15);
        FRESCOES.put("Fresco 4", 15);
        FRESCOES.put("Fresco 5", 15);
        FRESCOES.put("Fresco 6", 20);

        FRESCOES_IMAGES.put("Fresco 1", "project_assets/images/findings/fresco4_20.jpg");
        FRESCOES_IMAGES.put("Fresco 2", "project_assets/images/findings/fresco1_20.jpg");
        FRESCOES_IMAGES.put("Fresco 3", "project_assets/images/findings/fresco5_15.jpg");
        FRESCOES_IMAGES.put("Fresco 4", "project_assets/images/findings/fresco3_15.jpg");
        FRESCOES_IMAGES.put("Fresco 5", "project_assets/images/findings/fresco6_15.jpg");
        FRESCOES_IMAGES.put("Fresco 6", "project_assets/images/findings/fresco2_20.jpg");
        
        FRESCOES_TEXTS.put("Fresco 1", "Fotografises tin Toixografia: O prigkipas me ta krina;Eikonizetai epiblitiki andriki morfi, poy badizei pros ta aristera se aprosdioristo erythro fonto. Foraei to typiko minoiko perizoma me fardia zoni, perideraio sto laimo kai ploysio kalymma kefalis diakosmimeno me krina kai ftera pagonioy. I stasi ton xerion toy deixnei oti isos eserne me to aristero toy xeri ena zoo i kapoio mythiko teras, grypa i sfigga. O neos onomasthike apo toys ereynites «prigkipas», giati theorithike oti apodidei to basilia-ierea, poy zoyse sto anaktoro tis Knosoy.!!! \n" + 
        "Φωτογράφισες την Τοιχογραφία: Ο πρίγκιπας με τα κρίνα;Εικονίζεται επιβλητική ανδρική μορφή, που βαδίζει προς τα αριστερά σε απροσδιόριστο ερυθρό φόντο. Φοράει το τυπικό μινωικό περίζωμα με φαρδιά ζώνη, περιδέραιο στο λαιμό και πλούσιο κάλυμμα κεφαλής διακοσμημένο με κρίνα και φτερά παγωνιού. Η στάση των χεριών του δείχνει ότι ίσως έσερνε με το αριστερό του χέρι ένα ζώο ή κάποιο μυθικό τέρας, γρύπα ή σφίγγα. Ο νέος ονομάσθηκε από τους ερευνητές «πρίγκηπας», γιατί θεωρήθηκε ότι αποδίδει το βασιλιά-ιερέα, που ζούσε στο ανάκτορο της Κνωσού.!!!\r\n");
        FRESCOES_TEXTS.put("Fresco 2", "Fotografises tin Toixografia: Oi galazeis kyries!!!;Omorfes Minoitisses poy koybentiazoyn. Exoyn oraia foremata, symfona me ti moda tis epoxis, omorfa xtenismena mallia kai polytima kosmimata.\r\n" + 
                        "Φωτογράφισες την Τοιχογραφία: Οι γαλάζεις κυρίες!!!;Όμορφες Μινωίτισσες που κουβεντιάζουν. Έχουν ωραία φορέματα, σύμφωνα με τη μόδα της εποχής, όμορφα χτενισμένα μαλλιά και πολύτιμα κοσμήματα.\r\n");
        FRESCOES_TEXTS.put("Fresco 4", "Fotografises tin Toixografia: Ta delfinia!!!;I toixografia ayti proerxetai apo to megaro tis basilissas. Delfinia kolympoyn anamesa se psaria, mesa sta kymata.\r\n" + 
                        "Φωτογράφισες την Τοιχογραφία: Τα δελφίνια!!!;Η τοιχογραφία αυτή προέρχεται από τo μέγαρο της βασίλισσας. Δελφίνια κολυμπούν ανάμεσα σε ψάρια, μέσα στα κύματα.\r\n");
        FRESCOES_TEXTS.put("Fresco 3", "Fotografises tin Toixografia: Pompi neon!!!;Neoi lambanoyn meros se thriskeytiki pompi kai feroyn aggeia me dora gia ti theotita i gia to basilia. I toixografia ayti kosmoyse ton legomeno «diadromo tis pompis» toy anaktoroy tis Knosoy.\r\n" + //
                        "Φωτογράφισες την Τοιχογραφία: Πομπή νέων!!!;Νέοι λαμβάνουν μέρος σε θρησκευτική πομπή και φέρουν αγγεία με δώρα για τη θεότητα ή για το βασιλιά. Η τοιχογραφία αυτή κοσμούσε τον λεγόμενο «διάδρομο της πομπής» του ανακτόρου της Κνωσού.\r\n");
        FRESCOES_TEXTS.put("Fresco 5", "Fotografises tin Toixografia: I pariziana!!!;Eikonizetai mia gynaika aristokratikis katagogis se thesi profil.  Onomastike «Pariziana» apo ton Άrthoyr Ebans, giati to 1903 (etos poy anakalyfthike) ta megala matia, ta katsara mallia, ta entona kokkina xeili kai i anasikomeni myti theoroyntan ta ideodi tis gynaikeias omorfias, ta opoia mono mia omorfi gynaika apo … to Parisi mporoyse na ta ensarkosei.\r\n" + //
                        "Φωτογράφισες την Τοιχογραφία: Η παριζιάνα!!!;Εικονίζεται μια γυναίκα αριστοκρατικής καταγωγής σε θέση προφίλ.  Ονομάστηκε «Παριζιάνα» από τον Άρθουρ Έβανς, γιατί το 1903 (έτος που ανακαλύφθηκε) τα μεγάλα μάτια, τα κατσαρά μαλλιά, τα έντονα κόκκινα χείλη και η ανασηκωμένη μύτη θεωρούνταν τα ιδεώδη της γυναικείας ομορφιάς, τα οποία μόνο μια όμορφη γυναίκα από … το Παρίσι μπορούσε να τα ενσαρκώσει.\r\n");
        FRESCOES_TEXTS.put("Fresco 6", "Fotografises tin Toixografia: Ta tayrokathapsia!!!;Ta tayrokathapsia itan ena agonisma poy synithizotan poly sta minoika xronia. Perilambane to piasimo toy tayroy apo ta kerata, to epikindyno alma sti raxi toy zooy kai telos to pidima sto edafos piso toy.\r\n" + //
                        "Φωτογράφισες την Τοιχογραφία: Τα ταυροκαθάψια!!!;Τα ταυροκαθάψια ήταν ένα αγώνισμα που συνηθιζόταν πολύ στα μινωικά χρόνια. Περιλάμβανε το πιάσιμο του ταύρου από τα κέρατα, το επικίνδυνο άλμα στη ράχη του ζώου και τέλος το πήδημα στο έδαφος πίσω του.\r\n");
    }





    /**
     * Constructs a Fresco with the specified description.
     * 
     * @param description the description of the fresco
     * @pre description is not null
     * @post a Fresco is created with the specified description and corresponding value
     */
    public Fresco(String description) {
        super(description, FRESCOES.get(description));
    }





    /**
     * Returns the image path of the fresco.
     * 
     * @return the image path of the fresco
     * @pre none
     * @post the image path of the fresco is returned
     */
    @Override
    public String getImagePath() {
        return FRESCOES_IMAGES.get(getDescription());
    }






    /**
     * Returns specific text for the fresco.
     * 
     * @return specific text for the fresco
     * @pre none
     * @post specific text for the fresco is returned
     */
    @Override
    public String getSpecificText() {
        return FRESCOES_TEXTS.get(getDescription());
    }






    /**
     * Returns the map of frescoes with their values.
     * 
     * @return the map of frescoes with their values
     * @pre none
     * @post the map of frescoes with their values is returned
     */
    public static Map<String, Integer> getFrescoes() {
        return FRESCOES;
    }




    
    /**
     * Returns the score of the fresco.
     * 
     * @return the score of the fresco
     * @pre none
     * @post the score of the fresco is returned
     */
    public int getScore() {
        return FRESCOES.get(getDescription());
    }
}