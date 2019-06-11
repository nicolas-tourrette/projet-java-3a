import java.util.ArrayList;

public class Client {

    /**
     * Déclaration des attributs pour la classe 'Client'
     *      - nom, prénom, adresse, ville et numéro de téléphone du client, tableau de ses comptes et de ses moyens de paiement.
     */

    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String villeClient;
    private String telephoneClient;
    private ArrayList<Compte> comptesClient = new ArrayList<Compte>();
    private ArrayList<MoyenPaiement> moyenPaiementsClient = new ArrayList<MoyenPaiement>();

    /**
     * Constructeur de la classe 'Client'
     * @param nom               : nom du client
     * @param prenom            : prénom du client
     * @param adresse           : adresse du client
     * @param ville             : ville du client
     * @param numeroTelephone   : numéro de téléphone du client
     */

    public Client(String nom, String prenom, String adresse, String ville, String numeroTelephone){
        nomClient = nom.toUpperCase();
        prenomClient = prenom;
        adresseClient = adresse;
        villeClient = ville;
        telephoneClient = numeroTelephone;
    }

    /**
     * Créer un compte pour le client et lui affecte ce compte.
     * @param leCompte : compte à affecter au client en question
     */

    public void nouveauCompte(Compte leCompte){
        comptesClient.add(leCompte);
    }

    /**
     * Créer un moyen de paiement pour le client et lui affecter.
     * @param leMoyenPaiement : moyen de paiement à affecter au client en question
     */

    public void nouveauMoyenPaiement(MoyenPaiement leMoyenPaiement){
        moyenPaiementsClient.add(leMoyenPaiement);
    }

    /**
     * Afficher le opérations du client.
     */

    public void afficherOperations(){
        System.out.println("---------------------------------\nAffichage des opérations du client " + nomClient + " " + prenomClient);
        for(int i=0; i<comptesClient.size(); i++){
            comptesClient.get(i).afficherOperations();
        }
    }


    /**
     * Afficher le solde des compte du client.
     */

    public void afficherSolde(){
        System.out.println("---------------------------------\nAffichage des soldes du client " + nomClient + " " + prenomClient);
        for(int i=0; i<comptesClient.size(); i++){
            System.out.println("     Compte n°" + comptesClient.get(i).getNumeroCompte() + " : " + comptesClient.get(i).getSolde() + " €.");
        }
    }

    /**
     * Getter pour le client.
     * @return NOM Prénom du client
     */

    public String getClient() {
        return nomClient + " " + prenomClient;
    }

    /**
     * Méthode toString() dédiée à l'affichage d'un client avec ses comptes et ses moyens de paiement.
     * @return la chaîne de caractères pour le client
     */

    @Override
    public String toString() {
        String affichage = "---------------------------------\nAffichage du client " + nomClient + " " + prenomClient + "\n     Résidant  : " + adresseClient + "\n     Ville     : " + villeClient + "\n     Téléphone : " + telephoneClient + "\n";
        affichage += "**** Comptes ********************\n";
        for(int i=0 ; i<comptesClient.size() ; i++){
            affichage += comptesClient.get(i).toString() + "\n";
        }
        affichage += "\n**** Moyens de paiement *********\n";
        for(int i=0 ; i<moyenPaiementsClient.size() ; i++){
            affichage += moyenPaiementsClient.get(i).toString() + "\n";
        }
        affichage += "*********************************" ;
        return affichage;
    }
}
