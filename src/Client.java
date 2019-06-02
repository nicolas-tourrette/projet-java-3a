import java.util.ArrayList;

public class Client {
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String villeClient;
    private String telephoneClient;

    private ArrayList<Compte> comptesClient = new ArrayList<Compte>();
    private ArrayList<MoyenPaiement> moyenPaiementsClient = new ArrayList<MoyenPaiement>();

    public Client(String nom, String prenom, String adresse, String ville, String numeroTelephone){
        nomClient = nom.toUpperCase();
        prenomClient = prenom;
        adresseClient = adresse;
        villeClient = ville;
        telephoneClient = numeroTelephone;
    }

    public void nouveauCompte(Compte leCompte){
        comptesClient.add(leCompte);
    }

    public void nouveauMoyenPaiement(MoyenPaiement leMoyenPaiement){
        moyenPaiementsClient.add(leMoyenPaiement);
    }

    public String getClient() {
        return nomClient + " " + prenomClient;
    }

    @Override
    public String toString() {
        String affichage = "---------------------------------\nAffichage du client " + nomClient + " " + prenomClient + "\n     Résidant  : " + adresseClient + "\n     Ville     : " + villeClient + "\n     Téléphone : " + telephoneClient + "\n";
        affichage += "**** Comptes ********************\n";
        for(int i=0 ; i<comptesClient.size() ; i++){
            affichage += comptesClient.get(i).toString();
        }
        affichage += "\n**** Moyens de paiement *********\n";
        for(int i=0 ; i<moyenPaiementsClient.size() ; i++){
            affichage += moyenPaiementsClient.get(i).toString();
        }
        affichage += "\n*********************************" ;
        return affichage;
    }
}
