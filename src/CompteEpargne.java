public class CompteEpargne extends Compte {

    /**
     * Création des attributs de la classe 'CompteEpargne' dérivée de classe 'Compte'
     *      - type de compte épargne, taux de rémunération du compte (calcul des intérêts)
     * On hérite des attributs de 'Compte' (numéro, date d'ouverture, solde et opérations).
     */

    private String type;
    private float tauxRemuneration;

    /**
     * Constructeur de la classe 'CompteEpargne' surchargé de la classe 'Compte'
     * @param numeroCompte      : numéro du compte
     * @param dateOuverture     : date d'ouverture
     * @param type              : type de compte épargne
     * @param tauxRemuneration  : taux de rémunération du compte
     * @param premierVersement  : premier versement sur le compte
     */

    public CompteEpargne(String numeroCompte, String dateOuverture, String type, float tauxRemuneration, float premierVersement){
        super(numeroCompte, dateOuverture, premierVersement);
        this.type = type;
        this.tauxRemuneration = tauxRemuneration;
    }

    /**
     * Fonction de calcul des intérêts pour l'année passée en paramètres
     * @param annee : année de calcul des intérêts
     */

    public void calculInterets(int annee){
        float interets = tauxRemuneration*solde;
        System.out.println("---------------------------------\nCalcul des intérêts du compte n°" + numeroCompte);
        System.out.println("     Votre solde     : " + solde + " €");
        System.out.println("     Intérêts acquis : " + interets + " €");
        solde += interets;
        System.out.println("     Nouveau solde   : " + solde + " €");
        Operation versementInterets = new Operation("Versement d'intérêts", annee + "-12-31", interets, this) ;
        tableauOperations.add(versementInterets);
    }

    /**
     * Surcharge de la méthode toString() de la classe 'Compte' pour l'adapter au compte épargne.
     * @return : l'affichage désiré pour le compte.
     */

    @Override
    public String toString() {
        return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde     : " + solde + " €\n     Type      : " + type + "\n     Taux      : " + tauxRemuneration + "%";
    }
}
