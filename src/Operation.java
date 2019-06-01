public class Operation {
    private String libelleOperation;
    private String dateValeur;
    private float montantOperation;
    private Compte compteCredite;
    private Compte compteDebite;
    private MoyenPaiement moyenPaiement;

    protected Operation(String libelle, String date, float montant, Compte compteCredite, Compte compteDebite, MoyenPaiement moyenPaiement) {
        libelleOperation = libelle;
        dateValeur = date;
        montantOperation = montant;
        this.compteCredite = compteCredite;
        this.moyenPaiement = moyenPaiement;
    }

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

    public void afficherOperation(){
        System.out.println("     Libellé           : " + libelleOperation);
        System.out.println("     Date              : " + dateValeur);
        System.out.println("     Compte crédité    : " + compteCredite.getNumeroCompte());
        if(compteDebite != null){
            System.out.println("     Compte débité     : " + compteDebite.getNumeroCompte());
        }
        System.out.println("     Somme             : " + montantOperation + " €");
        System.out.println("     Moyen de paiement : " + moyenPaiement);
    }
}
