//import java.sql.* ; Librairie Java à importer pour utiliser la base de données

import java.util.Scanner;

import static java.lang.Math.abs;

public class Compte {
    private String numeroCompte;
    private float solde;
    private String dateOuverture;
    private boolean decouvertAutorise;
    private float decouvertMaximal;
    private float decouvertActuel;

    public Compte(String numeroCompte, String dateOuverture, boolean decouvertAutorise) {
        this.numeroCompte = numeroCompte;
        this.dateOuverture = dateOuverture;
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

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setDecouvertAutorise(boolean decouvertAutorise) {
        this.decouvertAutorise = decouvertAutorise;
        if (!decouvertAutorise) {
            decouvertMaximal = 0.0f;
        } else {
            System.out.print("Le découvert autorisé est modifié, veuillez saisir sa nouvelle valeur : ");
            Scanner sc = new Scanner(System.in);
            float decouvertMaxi = sc.nextFloat();
            decouvertMaximal = decouvertMaxi;
        }
    }

    public void calculDecouvert(float somme) {
        if (somme > solde) {
            float decouvert = -solde + somme;
            if (decouvert <= decouvertMaximal) {
                decouvertActuel = -solde + somme;
            }
        }
    }

    public void approvisionnerCompte(String dateValeur, String libelleOperation, float somme) {
        solde += somme;
        if (decouvertActuel > 0) {
            decouvertActuel -= somme;
        }
        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        System.out.println("     Date          : " + dateValeur);
        System.out.println("     Somme         : " + somme + " €");
        System.out.println("     Libellé       : " + libelleOperation);
        System.out.println("     Nouveau sole  : " + solde + " €");
    }

    public void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        if (decouvertActuel > 0) {
            decouvertActuel -= somme;
        }
        solde += somme;
        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        System.out.println("     Date          : " + dateValeur);
        System.out.println("     Compte débité : " + compteDebite.getNumeroCompte());
        System.out.println("     Somme         : " + somme + " €");
        System.out.println("     Libellé       : " + libelleOperation);
        System.out.println("     Nouveau sole  : " + solde + " €");
    }

    public void effectuerPaiement(String dateValeur, Compte compteCredite, String libelleOperation, float somme) {
        calculDecouvert(somme);
        if (solde > somme || (decouvertAutorise && decouvertActuel <= decouvertMaximal)) {
            solde -= somme;
            compteCredite.approvisionnerCompte(dateValeur, this, "Paiement de " + numeroCompte, somme);
            System.out.println("---------------------------------\nPaiement depuis le compte n°" + numeroCompte);
            System.out.println("     Date           : " + dateValeur);
            System.out.println("     Compte crédité : " + compteCredite.getNumeroCompte());
            System.out.println("     Somme          : " + somme + " €");
            System.out.println("     Libellé        : " + libelleOperation);
        } else {
            System.out.println("---------------------------------\nLe solde n'est pas suffisant pour effectuer ce paiement ! Opération refusée. Réapprovisionnez votre compte.");
            System.out.println("     Somme manquante : " + (solde - somme + decouvertMaximal) + " €.");
        }
    }

    public String toString() {
        if (decouvertAutorise) {
            float decouvertRestant = decouvertMaximal - decouvertActuel;
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde : " + solde + " €\n     Découvert : " + decouvertActuel + " €\n     Découvert restant : " + decouvertRestant + " €";
        } else {
            return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde : " + solde + " €\n     Découvert : NON AUTORISÉ";
        }

    }
}
