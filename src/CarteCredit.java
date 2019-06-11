public class CarteCredit extends MoyenPaiement {

    /**
     * Déclaration des attributs de la classe 'CarteCredit' dérivée de 'MoyenPaiement'
     *      - type de carte, contrôle de solde ou non, plafond de paiement et plafond actuel, numéro de la carte et date de validité
     * On hérite des attributs de 'MoyenPaiement'
     */

    private String type;
    private boolean controleSolde = false;
    private float plafond;
    private float plafondActuel;
    private String numeroCarte;
    private String dateValidite;

    /***
     * Constructeur de la carte bancaire, surchargé du constructeur de 'MoyenPaiement'
     * @param client        : client propriétaire de la carte bancaire
     * @param compte        : compte sur lequel est rattachée cette carte
     * @param numeroCarte   : numéro de la carte bancaire
     * @param type          : type de carte bancaire (CB, MasterCard, VISA...)
     * @param controleSolde : si la carte dispose d'un contrôle de solde
     * @param plafond       : plafond de paiement sur une période donnée
     * @param dateValidite  : date de validité de la carte
     */

    public CarteCredit(Client client, CompteCourant compte, String numeroCarte, String type, boolean controleSolde, float plafond, String dateValidite){
        super(client, compte);
        this.numeroCarte = numeroCarte;
        this.type = type;
        this.controleSolde = controleSolde;
        this.plafond = plafond;
        this.dateValidite = dateValidite;
    }

    // SI LA CARTE N'A PAS DE CONTRÔLE DE SOLDE, CE CONSTRUCTEUR EST PLUS PRATIQUE.
    public CarteCredit(Client client, CompteCourant compte, String numeroCarte, String type, float plafond, String dateValidite){
        super(client, compte);
        this.numeroCarte = numeroCarte;
        this.type = type;
        this.plafond = plafond;
        this.dateValidite = dateValidite;
    }

    /**
     * Détermine si la carte est à contrôle de solde.
     * @return vrai/faux suivant le type de carte
     */

    public boolean isControleSolde(){
        return controleSolde;
    }

    /**
     * Ajouter au plafond de la carte la somme payée.
     * @param somme : somme payée par carte bancaire
     */

    public void ajouterPlafond(float somme){
        plafondActuel += somme;
    }

    /**
     * Vérifier le plafond de la carte bleue afin d'autoriser ou non le paiement.
     * @param somme : somme payée par carte bancaire
     * @return vrai/faux suivant la somme et le plafond déjà utilisé
     */
    public boolean verifierPlafond(float somme){
        if(plafondActuel+somme <= plafond){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Permet la remise à zéro du plafond au bout de la période (en général 7 jours).
     */
    public void razPlafond(){
        plafondActuel = 0.0f;
    }

    /**
     * Getter pour le dépassement du plafond de la carte bancaire. Permet d'informer le client du dépassement lors d'un
     * paiement en générant un message d'erreur.
     * @param somme : somme payée par carte bancaire
     * @return montant du dépassement
     */
    public float getDepassementPlafond(float somme) {
        return (plafondActuel+somme)-plafond;
    }

    /**
     * Retourne le type et le numéro de la carte bancaire pour mettre dans les opérations et déterminer le type de moyen
     * de paiement au moment de celui-ci.
     * @return chaîne de caractère TYPE + NUMÉRO CARTE
     */

    @Override
    protected String getNumero() {
        return type + " n°" + numeroCarte;
    }

    /**
     * Surcharge de la méthode toString()
     * @return : l'affichage désiré pour la carte bancaire.
     */

    @Override
    public String toString() {
        float sommeRestante = plafond - plafondActuel;
        if (controleSolde) {
            return "---------------------------------\nAffichage de la carte de crédit n°" + numeroCarte + "\n     Propriétaire  : " + getClient() + "\n     Compte        : " + getCompte() + "\n     Type de carte : " + type + "\n     Validité      : " + dateValidite + "\n     Plafond       : " + plafond + " €\n     Déjà dépensé  : " + plafondActuel + " €\n     Reste         : " + sommeRestante + " €\n     Contrôle du solde : OUI";
        } else {
            return "---------------------------------\nAffichage de la carte de crédit n°" + numeroCarte + "\n     Propriétaire  : " + getClient() + "\n     Compte        : " + getCompte() + "\n     Type de carte : " + type + "\n     Validité      : " + dateValidite + "\n     Plafond       : " + plafond + " €\n     Déjà dépensé  : " + plafondActuel + " €\n     Reste         : " + sommeRestante + " €\n     Contrôle du solde : NON";
        }
    }
}
