import java.util.Scanner;

public class CompteCourant extends Compte {
    private boolean decouvertAutorise;
    private float decouvertMaximal;
    private float decouvertActuel;

    public CompteCourant(String numeroCompte, String dateOuverture, boolean decouvertAutorise, float premierVersement) {
        super(numeroCompte, dateOuverture, premierVersement) ;
        this.decouvertAutorise = decouvertAutorise;
        if (!decouvertAutorise) {
            decouvertMaximal = 0.0f;
        } else {
            System.out.println("---------------------------------\nCréation du compte n°" + numeroCompte);
            System.out.println("     Découvert autorisé. Nous avons besoin de plus d'informations...");
            Scanner sc = new Scanner(System.in);
            System.out.print("     Montant du découvert maximal autorisé : ");
            float decouvertMaxi = sc.nextFloat();
            decouvertMaximal = decouvertMaxi;
        }
    }

    public void setDecouvertAutorise(boolean decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
        if (!decouvertAutorise) {
            decouvertMaximal = 0.0f;
            System.out.println("---------------------------------\nModification du compte n°" + numeroCompte);
            System.out.println("     Le découvert n'est plus autorisé sur ce compte.");
            if(solde < 0){
                System.out.println("     Votre dette est de : " + (-solde) + " €.");
            }
        } else {
            System.out.println("---------------------------------\nModification du compte n°" + numeroCompte);
            Scanner sc = new Scanner(System.in);
            System.out.print("     Montant du découvert maximal autorisé : ");
            float decouvertMaxi = sc.nextFloat();
            decouvertMaximal = decouvertMaxi;
        }
    }

    private boolean calculDecouvert(float somme) {
        if (somme > solde) {
            float decouvert = -solde + somme;
            if (decouvert <= decouvertMaximal) {
                decouvertActuel = -solde + somme;
                return true ;
            }
            else{
                return false ;
            }
        }
        return true ;
    }

    @Override
    public void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        if (decouvertActuel > 0) {
            decouvertActuel -= somme;
        }
        super.approvisionnerCompte(dateValeur, compteDebite, libelleOperation, somme);
    }

    public void effectuerPaiement(String dateValeur, Compte compteCredite, String libelleOperation, float somme) {
        boolean autorisation = calculDecouvert(somme);
        if (solde > somme || (decouvertAutorise && decouvertActuel <= decouvertMaximal) && autorisation) {
            solde -= somme;
            System.out.println("---------------------------------\nPaiement depuis le compte n°" + numeroCompte);
            Operation paiement = new Operation(libelleOperation, dateValeur, somme, compteCredite, this);
            tableauOperations.add(paiement);
            paiement.afficherOperation();
            compteCredite.approvisionnerCompte(dateValeur, this, "Paiement de " + numeroCompte, somme);
        } else {
            System.out.println("---------------------------------\nLe solde n'est pas suffisant pour effectuer ce paiement ! Opération refusée. Réapprovisionnez votre compte.");
            System.out.println("     Somme manquante : " + (solde - somme + decouvertMaximal) + " €.");
        }
    }

    @Override
    public String toString() {
        if (decouvertAutorise) {
            float decouvertRestant = decouvertMaximal - decouvertActuel;
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde : " + solde + " €\n     Découvert : " + decouvertActuel + " €\n     Découvert restant : " + decouvertRestant + " €";
        } else {
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde : " + solde + " €\n     Découvert : NON AUTORISÉ";
        }
    }
}
