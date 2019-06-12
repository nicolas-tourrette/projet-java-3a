public abstract class MoyenPaiement {

    /**
     * Classe abstraite 'MoyenPaiement' qui servira de base aux autres moyens de paiement : chèques, carte bleue...
     * Les attributs communs sont déclarés ici :
     * - client possédant le moyen de paiement, compte auquel celui-ci est rattaché.
     */

    protected Client client;
    protected Compte compte;

    /**
     * Constructeur d'un moyen de paiement qui sera surchargé ensuite.
     *
     * @param client : client possédant le moyen de paiement
     * @param compte : compte de rattachement
     */

    MoyenPaiement(Client client, Compte compte) {
        this.client = client;
        this.compte = compte;
    }

    /**
     * Getter pour le client possesseur du moyen de paiement.
     *
     * @return client.
     */

    public String getClient() {
        return client.getClient();
    }

    /**
     * Getter pour le compte rattaché au moyen de paiement.
     *
     * @return numeroCompte.
     */

    public String getCompte() {
        return compte.numeroCompte;
    }

    /**
     * Méthodes abstraites utilisées ou non pour les moyens de paiements.
     *
     * @return
     */

    protected abstract boolean isControleSolde();
    protected abstract boolean verifierPlafond(float somme);
    protected abstract void ajouterPlafond(float somme);
    protected abstract float getDepassementPlafond(float somme);
    protected abstract String getNumero();
    public abstract void razPlafond();
}
