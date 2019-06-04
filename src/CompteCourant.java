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

    private void procederPaiement(String dateValeur, Compte compteCredite, String libelleOperation, float somme, MoyenPaiement moyenDePaiement){
        boolean autorisation = calculDecouvert(somme);
        if (solde > somme || (decouvertAutorise && decouvertActuel <= decouvertMaximal) && autorisation) {
            moyenDePaiement.ajouterPlafond(somme);
            solde -= somme;
            System.out.println("---------------------------------\nPaiement depuis le compte n°" + numeroCompte);
            Operation paiement = new Operation(libelleOperation, dateValeur, somme, compteCredite, this, moyenDePaiement);
            tableauOperations.add(paiement);
            paiement.afficherOperation();
            compteCredite.approvisionnerCompte(dateValeur, this, "Paiement de " + numeroCompte, somme);
        } else {
            System.out.println("---------------------------------\nLe solde n'est pas suffisant pour effectuer ce paiement ! Opération refusée. Réapprovisionnez votre compte.");
            System.out.println("     Somme manquante : " + (solde - somme + decouvertMaximal) + " €.");
        }
    }

    public void effectuerPaiement(String dateValeur, Compte compteCredite, String libelleOperation, float somme, MoyenPaiement moyenDePaiement) {
        if(moyenDePaiement.getNumero().substring(0,2).equals("CB")){
            if(moyenDePaiement.verifierPlafond(somme)){
                if(moyenDePaiement.isControleSolde()){
                    if(solde > somme){
                        procederPaiement(dateValeur, compteCredite, libelleOperation, somme, moyenDePaiement);
                    }
                    else{
                        System.out.println("---------------------------------\nPaiement refusé : le contrôle de solde n'a pas permis la transaction.");
                    }
                }
                else{
                    procederPaiement(dateValeur, compteCredite, libelleOperation, somme, moyenDePaiement);
                }
            }
            else{
                System.out.println("---------------------------------\nPaiement refusé : le plafond est atteint. Vous êtes en dépassement de " + moyenDePaiement.getDepassementPlafond(somme) + " €.");
            }
        }
        else if(moyenDePaiement.getNumero().substring(0,2).equals("CQ")){
            if(moyenDePaiement.verifierPlafond(somme)){
                procederPaiement(dateValeur, compteCredite, libelleOperation, somme, moyenDePaiement);
            }
            else{
                System.out.println("---------------------------------\nPaiement refusé : le chéquier est épuisé.");
            }
        }
        else{
            procederPaiement(dateValeur, compteCredite, libelleOperation, somme, moyenDePaiement);
        }
    }

    @Override
    public String toString() {
        if (decouvertAutorise) {
            float decouvertRestant = decouvertMaximal - decouvertActuel;
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde     : " + solde + " €\n     Découvert : " + decouvertActuel + " €\n     Découvert restant : " + decouvertRestant + " €";
        } else {
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde     : " + solde + " €\n     Découvert : NON AUTORISÉ";
        }
    }
}
