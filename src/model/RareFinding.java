package model;

import java.util.HashMap;
import java.util.Map;



    /**
    * Constructs a RareFinding with the specified description.
     * 
     * @param description the description of the rare finding
     * @pre description is not null and exists in RARE_FINDINGS
     * @post a RareFinding is created with the specified description and corresponding score
     */
public class RareFinding extends Finding {
    private static final Map<String, Integer> RARE_FINDINGS = new HashMap<>();
    private static final Map<String, String> RARE_FINDINGS_IMAGES = new HashMap<>();
    private static final Map<String, String> RARE_FINDINGS_TEXTS = new HashMap<>(); 

    static {
        RARE_FINDINGS.put("Phaistos Disc", 35);
        RARE_FINDINGS.put("Minoas Ring (Knossos)", 25);
        RARE_FINDINGS.put("Malia Jewel", 25);
        RARE_FINDINGS.put("Zakros Rhyton", 25);

        RARE_FINDINGS_IMAGES.put("Phaistos Disc", "project_assets/images/findings/diskos.jpg");
        RARE_FINDINGS_IMAGES.put("Minoas Ring (Knossos)", "project_assets/images/findings/ring.jpg");
        RARE_FINDINGS_IMAGES.put("Malia Jewel", "project_assets/images/findings/kosmima.jpg");
        RARE_FINDINGS_IMAGES.put("Zakros Rhyton", "project_assets/images/findings/ruto.jpg");

        
        RARE_FINDINGS_TEXTS.put("Phaistos Disc", "Ανακάλυψες το Δίσκο της Φαιστού!!!;Ο Δίσκος της Φαιστού είναι ένα αρχαιολογικό εύρημα από τη Μινωική πόλη της Φαιστού στη νότια Κρήτη και χρονολογείται πιθανώς στον 17ο αιώνα π.Χ.. Αποτελεί ένα από τα γνωστότερα μυστήρια της αρχαιολογίας, αφού ο σκοπός της κατασκευής του και το νόημα των όσων αναγράφονται σε αυτόν παραμένουν άγνωστα. Ο δίσκος ανακαλύφθηκε στις 3 Ιουλίου 1908 από τον Ιταλό αρχαιολόγο Λουίτζι Περνιέ (Luigi Pernier) και φυλάσσεται σήμερα στο Αρχαιολογικό Μουσείο Ηρακλείου.\r\n" + //
                        "Anakalypses to Disko tis Faistoy!!!;O Diskos tis Faistoy einai ena arxaiologiko eyrima apo ti Minoiki poli tis Faistoy sti notia Kriti kai xronologeitai pithanos ston 17o aiona p.X.. Apotelei ena apo ta gnostotera mystiria tis arxaiologias, afoy o skopos tis kataskeyis toy kai to noima ton oson anagrafontai se ayton paramenoyn agnosta. O diskos anakalyfthike stis 3 Ioylioy 1908 apo ton Italo arxaiologo Loyitzi Pernie (Luigi Pernier) kai fylassetai simera sto Arxaiologiko Moyseio Irakleioy. \r\n");
        RARE_FINDINGS_TEXTS.put("Minoas Ring (Knossos)", "Anakalypses to Daxtylidi toy Minoa !!!;To «Daxtylidi toy Minoa», ena apo ta megalytera kai spaniotera xrysa sfragidika ston kosmo, theoreitai apo ta kalytera deigmata tis kritomykinaIkis sfragidikis. Ferei syntheti thriskeytiki parastasi, poy apeikonizei morfes oi opoies entassontai stin kritomykinaIki thematologia, dentrolatria me kathisti thea, oyrano, gi kai thalassa, me iero ploio poy exei morfi ippokampoy.\r\n" + //
                        "Ανακάλυψες το Δαχτυλίδι του Μίνωα !!!;Το «Δαχτυλίδι του Μίνωα», ένα από τα μεγαλύτερα και σπανιότερα χρυσά σφραγιδικά στον κόσμο, θεωρείται από τα καλύτερα δείγματα της κρητομυκηναϊκής σφραγιδικής. Φέρει σύνθετη θρησκευτική παράσταση, που απεικονίζει μορφές οι οποίες εντάσσονται στην κρητομυκηναϊκή θεματολογία, δεντρολατρία με καθιστή θεά, ουρανό, γη και θάλασσα, με ιερό πλοίο που έχει μορφή ιππόκαμπου.\r\n");
        RARE_FINDINGS_TEXTS.put("Malia Jewel", "Anakalypses to Kosmima ton Malion!!!;To xryso kosmima ton melisson poy filoxeneitai sto Arxaiologiko Moyseio Irakleioy, einai diasimo arxaiologiko eyrima apo ton Xrysolakko, ton tafiko peribolo tis nekropolis ton Malion.\r\n" + //
                        "Ανακάλυψες το Κόσμημα των Μαλίων!!!;Το χρυσό κόσμημα των μελισσών που φιλοξενείται στο Αρχαιολογικό Μουσείο Ηρακλείου, είναι διάσημο αρχαιολογικό εύρημα από τον Χρυσόλακκο, τον ταφικό περίβολο της νεκρόπολης των Μαλίων.\r\n");
        RARE_FINDINGS_TEXTS.put("Zakros Rhyton", "Anakalypses to Ryto tis Zakroy!!!;To aggeio brethike sto thisayrofylakio toy anaktoroy tis Zakroy mazi me alla monadika sto eidos toys teletoyrgika skeyi tis neoanaktorikis epoxis kai apotelei xaraktiristiko paradeigma tis efeyretikotitas kai kalaisthisias ton Minoiton texniton.\r\n" + //
                        "Ανακάλυψες το Ρυτό της Ζάκρου!!!;Το αγγείο βρέθηκε στο θησαυροφυλάκιο του ανακτόρου της Ζάκρου μαζί με άλλα μοναδικά στο είδος τους τελετουργικά σκεύη της νεοανακτορικής εποχής και αποτελεί χαρακτηριστικό παράδειγμα της εφευρετικότητας και καλαισθησίας των Μινωιτών τεχνιτών.\r\n");
    }





/**
 * Constructs a RareFinding with the specified description.
 * 
 * @param description the description of the rare finding
 * @pre description is not null and exists in RARE_FINDINGS
 * @post a RareFinding is created with the specified description and corresponding score
 */
    public RareFinding(String description) {
        super(description, RARE_FINDINGS.get(description));
    }




      /**
     * Returns the image path for the rare finding.
     * 
     * @return the image path for the rare finding
     * @pre none
     * @post the image path for the rare finding is returned
     */
    @Override
    public String getImagePath() {
        return RARE_FINDINGS_IMAGES.get(getDescription());
    }





     /**
     * Returns the specific text for the rare finding.
     * 
     * @return the specific text for the rare finding
     * @pre none
     * @post the specific text for the rare finding is returned
     */
    @Override
    public String getSpecificText() {
        return RARE_FINDINGS_TEXTS.get(getDescription());
    }





    /**
     * Returns the map of rare findings and their scores.
     * 
     * @return the map of rare findings and their scores
     * @pre none
     * @post the map of rare findings and their scores is returned
     */
    public static Map<String, Integer> getRareFindings() {
        return RARE_FINDINGS;
    }





     /**
     * Returns the score of the rare finding.
     * 
     * @return the score of the rare finding
     * @pre none
     * @post the score of the rare finding is returned
     */
    public int getScore() {
        return RARE_FINDINGS.get(getDescription());
    }
}