public class Operation {

    /**
     * Création des attributs de la classe 'Operation' correspondant à une opération effectuée sur un compte (débit ou crédit)
     *      - libellé de l'opération, date, montant, comptes crédités et débités, moyen de paiement
     */

    private String libelleOperation;
    private String dateValeur;
    private float montantOperation;
    private Compte compteCredite;
    private Compte compteDebite;
    private MoyenPaiement moyenPaiement;

    /**
     * Différents constructeurs pour l'opération, prenant en compte certains paramètres afin de pouvoir faire toutes les opérations possibles.
     * @param libelle       : libellé de l'opération
     * @param date          : date de l'opération
     * @param montant       : montant de l'opération
     * @param compteCredite : compte crédité recevant le montant
     * @param compteDebite  : compte débité donnant le montant
     * @param moyenPaiement : moyen de paiement avec lequel est réalisé l'opération
     */

    protected Operation(String libelle, String date, float montant, Compte compteCredite, Compte compteDebite, MoyenPaiement moyenPaiement) {
        libelleOperation = libelle;
        dateValeur = date;
        montantOperation = montant;
        this.compteCredite = compteCredite;
        this.moyenPaiement = moyenPaiement;
    }

    // TO BE REMOVED: FOR TEST ONLY
    protected Operation(String libelle, String date, float montant, Compte compteCredite, Compte compteDebite) {
        libelleOperation = libelle;
        dateValeur = date;
        montantOperation = montant;
        this.compteCredite = compteCredite;
        this.compteDebite = compteDebite;
        moyenPaiement = null;
    }

    protected Operation(String libelle, String date, float montant, Compte compteCredite) {
        libelleOperation = libelle;
        dateValeur = date;
        montantOperation = montant;
        this.compteCredite = compteCredite;
        compteDebite = null;
        moyenPaiement = null;
    }

    /**
     * Fonction d'affichage d'une opération.
     * Une autre méthode pour l'afficher aurait été la méthode toString() précédemment employée.
     */

    public void afficherOperation(){
        System.out.println("     Libellé           : " + libelleOperation);
        System.out.println("     Date              : " + dateValeur);
        System.out.println("     Compte crédité    : " + compteCredite.getNumeroCompte());
        if(compteDebite != null){
            System.out.println("     Compte débité     : " + compteDebite.getNumeroCompte());
        }
        System.out.println("     Somme             : " + montantOperation + " €");
        if(moyenPaiement != null){
            System.out.println("     Moyen de paiement : " + moyenPaiement.getNumero());
        }
    }
}
