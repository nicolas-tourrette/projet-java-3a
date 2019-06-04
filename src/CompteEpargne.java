public class CompteEpargne extends Compte {
    private String type;
    private float tauxRemuneration;

    public CompteEpargne(String numeroCompte, String dateOuverture, String type, float tauxRemuneration, float premierVersement){
        super(numeroCompte, dateOuverture, premierVersement);
        this.type = type;
        this.tauxRemuneration = tauxRemuneration;
    }

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
}
