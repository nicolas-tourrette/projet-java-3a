import java.util.Random; //Gérération des numéros de chèques de manière aléatoire

public class Chequier extends MoyenPaiement {

    /**
     * Déclaration des attributs de la classe 'Chequier' dérivée de 'MoyenPaiement'
     * - numéro des chèques contenus dans le chéquier, banque émettrice du chéquier, nombre de chèques déjà utilisés
     * On hérite des attributs de 'MoyenPaiement'
     */

    private String numeroCheques[] = new String[40];
    private String banqueEmettrice;
    private int nbChequeUtilises;

    /**
     * Constructeur de la classe 'Chequier' surchargé de 'MoyenPaiement'
     *
     * @param client          : client popriétaire du chéquier
     * @param compte          : compte rattaché au chéquier (CompteCourant)
     * @param banqueEmettrice : banque émettrice du chéquier
     */

    public Chequier(Client client, CompteCourant compte, String banqueEmettrice) {
        super(client, compte);
        this.banqueEmettrice = banqueEmettrice;
        for (int i = 0; i < 40; i++) {
            String numeroCheque = "CQ ";
            for (int j = 0; j < 7; j++) {
                Random rand = new Random();
                numeroCheque += rand.nextInt(9);
            }
            numeroCheques[i] = numeroCheque;
        }
    }

    /**
     * Le chéquier n'est pas un moyen de paiement à contrôle de solde.
     *
     * @return false;
     */

    public boolean isControleSolde() {
        return false;
    }

    /**
     * La fonction verifierPlafond est utilisée pour vérifier le nombre de chèques disponibles.
     *
     * @param somme : paramètre inutile pour l'application mais obligatoire pour la surcharge de la méthode abstraite
     * @return vrai ou faux suivant s'il reste des chèques
     */

    public boolean verifierPlafond(float somme) {
        if (nbChequeUtilises < 40) {
            nbChequeUtilises++;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Méthodes inutilisées pour les chéquiers.
     */

    public void ajouterPlafond(float somme) {}
    public float getDepassementPlafond(float somme) { return 0; }
    public void razPlafond() {}

    /**
     * Retourne le numéro du chèque utilisé pour déterminer le type de moyen de paiement au moment de celui-ci et remplir
     * le tableau des opérations.
     *
     * @return premier chèque disponible sous la forme CQ*******
     */

    @Override
    protected String getNumero() {
        if (nbChequeUtilises == 0) {
            return numeroCheques[nbChequeUtilises];
        } else {
            return numeroCheques[nbChequeUtilises - 1];
        }
    }

    /**
     * Surcharge de la méthode toString()
     *
     * @return : l'affichage désiré pour le chèque
     */

    @Override
    public String toString() {
        String affichage = "---------------------------------\nAffichage du chéquier\n     Propriétaire     : " + getClient() + "\n     Compte           : " + getCompte() + "\n     Banque émettrice : " + banqueEmettrice + "\n     Déjà utilisé     : " + nbChequeUtilises + " chèques";
        for (int i = 0; i < 40; i++) {
            affichage += "\n     - " + numeroCheques[i];
            if (i < nbChequeUtilises) {
                affichage += "  (UTILISÉ)";
            }
        }
        return affichage;
    }
}
