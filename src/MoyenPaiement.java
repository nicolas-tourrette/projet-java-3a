public abstract class MoyenPaiement {
    protected Client client;
    protected Compte compte;

    MoyenPaiement(Client client, Compte compte){
        this.client = client;
        this.compte = compte;
    }

    public String getClient() {
        return client.getClient();
    }

    public String getCompte() {
        return compte.numeroCompte;
    }

    protected abstract boolean isControleSolde();
    protected abstract boolean verifierPlafond(float somme);
    protected abstract void ajouterPlafond(float somme);
    protected abstract float getDepassementPlafond(float somme);
    protected abstract String getNumero();
}
