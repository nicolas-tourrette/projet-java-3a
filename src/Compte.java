//import java.sql.* ; Librairie Java à importer pour utiliser la base de données

public class Compte {
    protected String numeroCompte;
    protected float solde;
    protected String dateOuverture;

    protected Compte(String numeroCompte, String dateOuverture) {
        this.numeroCompte = numeroCompte;
        this.dateOuverture = dateOuverture;
    }

    protected String getNumeroCompte() {
        return numeroCompte;
    }

    protected void approvisionnerCompte(String dateValeur, String libelleOperation, float somme) {
        solde += somme;
        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        System.out.println("     Date          : " + dateValeur);
        System.out.println("     Somme         : " + somme + " €");
        System.out.println("     Libellé       : " + libelleOperation);
        System.out.println("     Nouveau sole  : " + solde + " €");
    }

    protected void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        solde += somme;
        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        System.out.println("     Date          : " + dateValeur);
        System.out.println("     Compte débité : " + compteDebite.getNumeroCompte());
        System.out.println("     Somme         : " + somme + " €");
        System.out.println("     Libellé       : " + libelleOperation);
        System.out.println("     Nouveau sole  : " + solde + " €");
    }

    public String toString() {
        return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde : " + solde + " €";
    }
}
