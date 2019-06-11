import java.util.Scanner;

public class Execution {
    public static void MenuPrincipal() {
        System.out.println("Choissez parmi l'une des actions suivantes :");
        System.out.println("     (1)   Créer un nouveau client");
        System.out.println("     (2)   Afficher un client");
        System.out.println("     (3)   Ouvrir un compte");
        System.out.println("     (4)   Commander un moyen de paiement");
        System.out.println("     (5)   Approvisionner un compte");
        System.out.println("     (6)   Effectuer un paiment");
        System.out.println("     (7)   Calculer des intérêts");
        System.out.println("     (8)   Consulter les comptes d'un client");
        System.out.println("     (9)   Consulter les opérations d'un client");
        System.out.println("     (10)  Consulter les soldes d'un client");
        System.out.println("     (11)  Consulter les moyens de paiement d'un client");
        System.out.println("     (12)  Modifier le découvert");
        System.out.println("     (13)  Réinitialiser un plafond de carte bancaire");
        System.out.println("     (XX)  Choix hors de la plage pour quitter");
    }

    public static void main(String[] args) {
        Banque laBanque = new Banque();
        Scanner scanner = new Scanner(System.in);
        int choix = 0;
        String version = "1.0  Build 1";

        System.out.println("************   GESTIONNAIRE DE BANQUE   ************");
        System.out.println("                Version " + version);
        System.out.println("\n/!\\ NOTE : le séparateur décimal est la VIRGULE pour la saisie des nombres flottants. /!\\");

        do {
            System.out.println();
            MenuPrincipal();
            System.out.print("Choix souhaité : ");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    laBanque.nouveauClient();
                    break;
                case 2:
                    laBanque.afficherClient();
                    break;
                case 3:
                    laBanque.ouvrirCompte();
                    break;
                case 4:
                    laBanque.nouveauMoyenPaiement();
                    break;
                case 5:
                    laBanque.approvisionnerCompte();
                    break;
                case 6:
                    laBanque.effectuerPaiement();
                    break;
                case 7:
                    laBanque.calculerInterets();
                    break;
                case 8:
                    laBanque.consulterCompte();
                    break;
                case 9:
                    laBanque.consulterOperations();
                    break;
                case 10:
                    laBanque.consulterSolde();
                    break;
                case 11:
                    laBanque.consulterMoyensPaiement();
                    break;
                case 12:
                    laBanque.modifierDecouvert();
                    break;
                case 13:
                    laBanque.razPlafondCarte();
                    break;
                default:
                    System.out.println("À bientôt !");
            }
        } while (choix > 0 && choix < 14);
    }
}
