import java.util.ArrayList;

public abstract class Compte {
    protected String numeroCompte;
    protected float solde;
    protected String dateOuverture;
    protected ArrayList<Operation> tableauOperations = new ArrayList<Operation>();

    protected Compte(String numeroCompte, String dateOuverture, float premierVersement) {
        this.numeroCompte = numeroCompte;
        this.dateOuverture = dateOuverture;
        solde = premierVersement;
        if(premierVersement > 0){
            Operation ouvertureCompte = new Operation("Ouverture du compte", dateOuverture, premierVersement, this) ;
            tableauOperations.add(ouvertureCompte);
        }
    }

    protected String getNumeroCompte() {
        return numeroCompte;
    }

    protected float getSolde() {
        return solde;
    }

    protected void apportCompte(String dateValeur, String libelleOperation, float somme) {
        solde += somme;
        Operation approvissionnement = new Operation(libelleOperation, dateValeur, somme, this) ;
        tableauOperations.add(approvissionnement);
        System.out.println("---------------------------------\nApport sur le compte n°" + numeroCompte);
        approvissionnement.afficherOperation();
        System.out.println("     Nouveau sole      : " + solde + " €");
    }

    protected void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        solde += somme;
        Operation approvissionnement = new Operation(libelleOperation, dateValeur, somme, this, compteDebite) ;
        tableauOperations.add(approvissionnement);
        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        approvissionnement.afficherOperation();
        System.out.println("     Nouveau sole      : " + solde + " €");
    }

    protected void afficherOperations(){
        System.out.println("---------------------------------\nOpérations du compte n°" + numeroCompte);
        if(tableauOperations.size() > 0){
            for(int i=0 ; i<tableauOperations.size() ; i++){
                System.out.println("**** Opération #" + (i+1));
                tableauOperations.get(i).afficherOperation();
            }
        }
        else{
            System.out.println("     Aucune opération à afficher.");
        }
    }

    public String toString() {
        return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde     : " + solde + " €";
    }
}
