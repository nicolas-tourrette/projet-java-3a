import java.util.Scanner;

public class CompteCourant extends Compte {

    /**
     * Création des attributs propres à la classe 'CompteCourant', dérivée de 'Compte'
     *      - decouvert autorisé (oui/non), découvert maximal sur le compte, découvert actuel.
     * On hérite des attributs de 'Compte' (numéro, date d'ouverture, solde et opérations).
     */

    private boolean decouvertAutorise;
    private float decouvertMaximal;
    private float decouvertActuel;

    /**
     * Constructeur de la classe 'CompteCourant' surchargé de la classe 'Compte'
     * @param numeroCompte      : numéro du compte
     * @param dateOuverture     : date d'ouverture
     * @param decouvertAutorise : découvert autorisé (booléen)
     * @param premierVersement  : premier versement sur le compte
     */

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

    /**
     * Modification du découvert autorisé sur le compte.
     * @param decouvertAutorise : on modifie la valeur du booléen. Si passage à faux, on supprime le découvert autorisé.
     *                            Si on fournit vrai, on demande la valeur du découvert maximal autorisé.
     */

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

    /**
     * On calcul le découvert pour la suite d'un paiement. Si le découvert dépasse celui autorisé, on retourne faux.
     * Sinon on retourne vrai pour autoriser la transaction, en ajoutant la somme au découvert si nécessaire.
     * @param somme : montant de la transaction demandée.
     * @return      : booléen VRAI/FAUX pour autoriser la transaction ou non.
     */

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

    /**
     * Surcharge de la méthode d'approvisionnement pour tenir compte du découvert dans le cas des comptes courants.
     * @param dateValeur        : date de valeur de l'opération
     * @param compteDebite      : compte débité de la somme passée en paramètres
     * @param libelleOperation  : libellé pour mémoire
     * @param somme             : somme d'argent apportée sur le compte qui sera ajoutée au solde et débitée au compteDebite
     */

    @Override
    public void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        if (decouvertActuel > 0) {
            decouvertActuel -= somme;
        }
        super.approvisionnerCompte(dateValeur, compteDebite, libelleOperation, somme);
    }

    /**
     * Procédure de paiement : on effectue les opérations nécessaires sur les différents comptes en remplissant les tableaux
     * d'opérations.
     * @param dateValeur        : date du paiement
     * @param compteCredite     : compte créditeur recevant le paiement
     * @param libelleOperation  : libellé pour mémoire
     * @param somme             : montant du paiement
     * @param moyenDePaiement   : moyen de paiement
     */

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

    /**
     * Fonction qui effectue un paiement en fonction du type de moyen de paiement et de ses différentes caractéristiques.
     * @param dateValeur        : date du paiement
     * @param compteCredite     : compte créditeur recevant le paiement
     * @param libelleOperation  : libellé pour mémoire
     * @param somme             : montant du paiement
     * @param moyenDePaiement   : moyen de paiement
     */

    @Override
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

    public void calculInterets(int annee){}

    /**
     * Surcharge de la méthode toString() de la classe 'Compte' pour l'adapter au compte courant.
     * @return : l'affichage désiré pour le compte.
     */

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
