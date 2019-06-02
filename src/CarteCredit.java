public class CarteCredit extends MoyenPaiement {
    private String type;
    private boolean controleSolde = false;
    private float plafond;
    private float plafondActuel;
    private String numeroCarte;
    private String dateValidite;

    public CarteCredit(Client client, CompteCourant compte, String numeroCarte, String type, boolean controleSolde, float plafond, String dateValidite){
        super(client, compte);
        this.numeroCarte = numeroCarte;
        this.type = type;
        this.controleSolde = controleSolde;
        this.plafond = plafond;
        this.dateValidite = dateValidite;
    }

    public CarteCredit(Client client, CompteCourant compte, String numeroCarte, String type, float plafond, String dateValidite){
        super(client, compte);
        this.numeroCarte = numeroCarte;
        this.type = type;
        this.plafond = plafond;
        this.dateValidite = dateValidite;
    }

    public boolean isControleSolde(){
        return controleSolde;
    }

    public void ajouterPlafond(float somme){
        plafondActuel += somme;
    }

    public boolean verifierPlafond(float somme){
        if(plafondActuel+somme <= plafond){
            return true;
        }
        else{
            return false;
        }
    }

    public void razPlafond(){
        plafondActuel = 0.0f;
    }

    public float getDepassementPlafond(float somme) {
        return (plafondActuel+somme)-plafond;
    }

    @Override
    protected String getNumero() {
        return type + " n°" + numeroCarte;
    }

    @Override
    public String toString() {
        float sommeRestante = plafond - plafondActuel;
        if (controleSolde) {
            return "---------------------------------\nAffichage de la carte de crédit n°" + numeroCarte + "\n     Propriétaire  : " + getClient() + "\n     Compte        : " + getCompte() + "\n     Type de carte : " + type + "\n     Validité      : " + dateValidite + "\n     Plafond       : " + plafond + " €\n     Déjà dépensé  : " + plafondActuel + " €\n     Reste         : " + sommeRestante + " €\n     Contrôle du solde : OUI";
        } else {
            return "---------------------------------\nAffichage de la carte de crédit n°" + numeroCarte + "\n     Propriétaire  : " + getClient() + "\n     Compte        : " + getCompte() + "\n     Type de carte : " + type + "\n     Validité      : " + dateValidite + "\n     Plafond       : " + plafond + " €\n     Déjà dépensé  : " + plafondActuel + " €\n     Reste         : " + sommeRestante + " €\n     Contrôle du solde : NON";
        }
    }
}
