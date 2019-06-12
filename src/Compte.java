import java.util.ArrayList;

public abstract class Compte {

    /**
     * Création des attributs de la classe 'Compte'
     * - numéro du compte, solde, date d'ouverture et tableau des opérations effectuées sur le compte.
     * La classe 'Compte' est une classe abstraite qui n'a pas vocation a être utilisée. On crée des comptes courant
     * ou des comptes épargne mais pas des comptes "vides".
     */

    protected String numeroCompte;
    protected float solde;
    protected String dateOuverture;
    protected ArrayList<Operation> tableauOperations = new ArrayList<Operation>();

    /**
     * Constructeur de la classe abstraire 'Compte' qui sera surchargé.
     *
     * @param numeroCompte     : le numéro du compte
     * @param dateOuverture    : la date d'ouverture
     * @param premierVersement : le premier versement effectué
     */

    protected Compte(String numeroCompte, String dateOuverture, float premierVersement) {
        this.numeroCompte = numeroCompte;
        this.dateOuverture = dateOuverture;
        solde = premierVersement;
        if (premierVersement > 0) {
            Operation ouvertureCompte = new Operation("Ouverture du compte", dateOuverture, premierVersement, this);
            tableauOperations.add(ouvertureCompte);
        }
    }

    /**
     * Getter pour le numéro du compte.
     *
     * @return numeroCompte
     */
    protected String getNumeroCompte() {
        return numeroCompte;
    }

    /**
     * Getter pour le solde du compte.
     *
     * @return
     */
    protected float getSolde() {
        return solde;
    }

    /**
     * Approvisionnement du compte par simple apport (exemple d'une remise d'espèces).
     *
     * @param dateValeur       : date de valeur de l'opération
     * @param libelleOperation : libellé pour mémoire
     * @param somme            : somme d'argent apportée sur le compte qui sera ajoutée au solde
     */
    protected void apportCompte(String dateValeur, String libelleOperation, float somme) {
        solde += somme;
        Operation approvissionnement = new Operation(libelleOperation, dateValeur, somme, this);
        tableauOperations.add(approvissionnement);
        System.out.println("---------------------------------\nApport sur le compte n°" + numeroCompte);
        approvissionnement.afficherOperation();
        System.out.println("     Nouveau sole      : " + solde + " €");
    }

    /**
     * Surcharge de la méthode précédente pour ajouter un compte débité (par exemple un virement depuis un autre compte).
     *
     * @param dateValeur       : date de valeur de l'opération
     * @param compteDebite     : compte débité de la somme passée en paramètres
     * @param libelleOperation : libellé pour mémoire
     * @param somme            : somme d'argent apportée sur le compte qui sera ajoutée au solde et débitée au compteDebite
     */
    protected void approvisionnerCompte(String dateValeur, Compte compteDebite, String libelleOperation, float somme) {
        solde += somme;
        Operation approvissionnement = new Operation(libelleOperation, dateValeur, somme, this, compteDebite);
        tableauOperations.add(approvissionnement);

        compteDebite.solde -= somme;
        Operation debit = new Operation(libelleOperation, dateValeur, somme, this, compteDebite);
        compteDebite.tableauOperations.add(debit);

        System.out.println("---------------------------------\nApprovisionnement du compte n°" + numeroCompte);
        approvissionnement.afficherOperation();
        System.out.println("     Nouveau sole      : " + solde + " €");
    }

    /**
     * Afficher les opérations effectuées sur le compte.
     */
    protected void afficherOperations() {
        System.out.println("---------------------------------\nOpérations du compte n°" + numeroCompte);
        if (tableauOperations.size() > 0) {
            for (int i = 0; i < tableauOperations.size(); i++) {
                System.out.println("**** Opération #" + (i + 1));
                tableauOperations.get(i).afficherOperation();
            }
        } else {
            System.out.println("     Aucune opération à afficher.");
        }
    }

    /**
     * Méthodes abstraites permettant de les utiliser dans le programme principal.
     */

    public abstract void effectuerPaiement(String dateValeur, Compte compteCredite, String libelleOperation, float somme, MoyenPaiement moyenDePaiement);
    public abstract void calculInterets(int annee);
    public abstract void setDecouvertAutorise(boolean decouvertAutorise);

    /**
     * Surcharge de la méthode toString() pour afficher le compte basique.
     *
     * @return : l'affichage désiré pour le compte.
     */
    public String toString() {
        return "---------------------------------\nAffichage du compte n°" + numeroCompte + "\n     Ouvert le : " + dateOuverture + "\n     Solde     : " + solde + " €";
    }
}
