import java.util.Random;

public class Chequier extends MoyenPaiement {
    private String numeroCheques[] = new String[40];
    private String banqueEmettrice;
    private int nbChequeUtilises;

    public Chequier(Client client, CompteCourant compte, String banqueEmettrice){
        super(client, compte);
        this.banqueEmettrice = banqueEmettrice;
        for(int i=0; i<40; i++){
            String numeroCheque = "CQ ";
            for(int j=0; j<7; j++){
                Random rand = new Random();
                numeroCheque += rand.nextInt(9);
            }
            numeroCheques[i] = numeroCheque;
        }
    }

    public boolean isControleSolde(){
        return false;
    }

    public boolean verifierPlafond(float somme){
        if(nbChequeUtilises < 40){
            nbChequeUtilises++;
            return true;
        }
        else{
            return false;
        }
    }

    public void ajouterPlafond(float somme){}
    public float getDepassementPlafond(float somme){ return 0; }

    @Override
    protected String getNumero(){
        if(nbChequeUtilises==0){
            return numeroCheques[nbChequeUtilises];
        }
        else{
            return numeroCheques[nbChequeUtilises-1];
        }
    }

    @Override
    public String toString() {
        String affichage = "---------------------------------\nAffichage du chéquier\n     Propriétaire     : " + getClient() + "\n     Compte           : " + getCompte() + "\n     Banque émettrice : " + banqueEmettrice + "\n     Déjà utilisé     : " + nbChequeUtilises + " chèques";
        for(int i=0; i<40; i++){
            affichage += "\n     - " + numeroCheques[i];
            if(i < nbChequeUtilises){
                affichage += "  (UTILISÉ)";
            }
        }
        return affichage ;
    }
}
