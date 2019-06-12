import java.util.ArrayList;
import java.util.Scanner;

public class Banque {

    /**
     * Déclaration des attributs de la banque, à savoir les tableaux des comptes ouverts, des clients et des moyens de paiement.
     */

    private ArrayList<Client> tableauClients = new ArrayList<Client>();
    private ArrayList<Compte> tableauComptes = new ArrayList<Compte>();
    private ArrayList<MoyenPaiement> tableauMoyensPaiement = new ArrayList<MoyenPaiement>();

    /**
     * Fonction permettant de créer un nouveau client.
     */

    public void nouveauClient() {
        Scanner scanner = new Scanner(System.in);
        String nom, prenom, adresse, ville, telephone;

        System.out.println("/////////  Créer un nouveau client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - NOM               : ");
        nom = scanner.nextLine();
        System.out.print("         - Prénom            : ");
        prenom = scanner.nextLine();
        System.out.print("         - Adresse           : ");
        adresse = scanner.nextLine();
        System.out.print("         - Ville             : ");
        ville = scanner.nextLine();
        System.out.print("         - Num. de téléphone : ");
        telephone = scanner.nextLine();

        Client unClient = new Client(nom, prenom, adresse, ville, telephone);
        tableauClients.add(unClient);
        System.out.println("Le client " + nom.toUpperCase() + " " + prenom + " a bien été ajouté.\n         Numéro de client lors des demandes : " + tableauClients.size());
    }

    /**
     * Fonction permettant d'afficher un client selon ce que défini dans la classe 'Client'
     */

    public void afficherClient() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient;

        System.out.println("/////////  Afficher un client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro du client : ");
        numeroClient = scanner.nextInt();

        if (numeroClient - 1 < tableauClients.size()) {
            System.out.println(tableauClients.get(numeroClient - 1));
        } else {
            System.out.println("     Ce client ne peut pas exister...");
        }
    }

    /**
     * Fonction permettant d'ouvrir un compte en faisant appel à la classe 'Compte' et ses classes dérivées.
     * On affectera ce compte à un client.
     */

    public void ouvrirCompte() {
        Scanner scanner = new Scanner(System.in);
        String typeCompte, numeroCompte, dateOuverture, decouvert;
        boolean decouvertAutorise;
        float premierVersement, taux;
        int numeroClient;

        System.out.println("/////////  Ouvrir un compte");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.println("         - Type de compte");
        System.out.print("              - Courant (C) / Épargne (E)  : ");
        typeCompte = scanner.nextLine();

        System.out.print("         - Numéro de client          : ");
        numeroClient = scanner.nextInt();
        numeroCompte = scanner.nextLine();      //Vidage du buffer pour éviter des bugs de saisie.

        if (numeroClient - 1 < tableauClients.size()) {
            System.out.print("         - Numéro de compte          : ");
            numeroCompte = scanner.nextLine();
            System.out.print("         - Date d'aujourd'hui        : ");
            dateOuverture = scanner.nextLine();

            if (typeCompte.equals("C") || typeCompte.equals("c")) {
                System.out.print("         - Découvert autorisé (O/N)  : ");
                decouvert = scanner.nextLine();
                if (decouvert.equals("O") || decouvert.equals("o")) {
                    decouvertAutorise = true;
                } else {
                    decouvertAutorise = false;
                }
                System.out.print("         - Montant premier versement : ");
                premierVersement = scanner.nextFloat();

                CompteCourant unCompte = new CompteCourant(numeroCompte, dateOuverture, decouvertAutorise, premierVersement);
                tableauComptes.add(unCompte);
                tableauClients.get(numeroClient - 1).nouveauCompte(unCompte);

                System.out.println("Le compte courant n°" + numeroCompte + " a bien été ouvert au nom de " + tableauClients.get(numeroClient - 1).getClient() + ".\n         Numéro de compte lors des demandes : " + tableauComptes.size());
            } else if (typeCompte.equals("E") || typeCompte.equals("e")) {
                System.out.print("         - Type de compte épargne    : ");
                typeCompte = scanner.nextLine();
                System.out.print("         - Taux de rémunération      : ");
                taux = scanner.nextFloat();
                System.out.print("         - Montant premier versement : ");
                premierVersement = scanner.nextFloat();

                CompteEpargne unCompte = new CompteEpargne(numeroCompte, dateOuverture, typeCompte, taux, premierVersement);
                tableauComptes.add(unCompte);
                tableauClients.get(numeroClient - 1).nouveauCompte(unCompte);

                System.out.println("Le compte épargne n°" + numeroCompte + " (" + typeCompte + ") a bien été ouvert au nom de " + tableauClients.get(numeroClient - 1).getClient() + ".\n         Numéro de compte lors des demandes : " + tableauComptes.size());
            } else {
                System.out.println("     Type de compte inconnu...");
            }
        } else {
            System.out.println("     Ce client ne peut pas exister...");
        }
    }

    /**
     * Fonction permettant de créer un nouveau moyen de paiement selon la classe 'MoyenPaiement'
     * On affectera ce moyen de paiement à un client.
     */

    public void nouveauMoyenPaiement() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient, numeroCompte;
        float plafond;
        String moyenPaiement, numero, banque, date, type, controle;
        boolean controleSolde = false;

        System.out.println("/////////  Commander un moyen de paiement");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro du client        : ");
        numeroClient = scanner.nextInt();
        System.out.print("         - Numéro de compte        : ");
        numeroCompte = scanner.nextInt();
        moyenPaiement = scanner.nextLine();      //Vidage du buffer pour éviter des bugs de saisie.

        if (numeroClient - 1 < tableauClients.size() && numeroCompte - 1 < tableauComptes.size()) {
            if (tableauComptes.get(numeroCompte - 1) instanceof CompteCourant) {
                System.out.println("         - Type de moyen de paiement");
                System.out.print("              - CB / CHQ           : ");
                moyenPaiement = scanner.nextLine();

                if (moyenPaiement.equals("CB") || moyenPaiement.equals("cb")) {
                    System.out.print("         - Numéro de carte         : ");
                    numero = scanner.nextLine();
                    System.out.print("         - Date de validité        : ");
                    date = scanner.nextLine();
                    System.out.print("         - Type de carte           : ");
                    type = scanner.nextLine();
                    System.out.print("         - Plafond sur 7j          : ");
                    plafond = scanner.nextFloat();
                    controle = scanner.nextLine();      //Vidage du buffer pour éviter des bugs de saisie.
                    System.out.print("         - Contrôle de solde (O/N) : ");
                    controle = scanner.nextLine();

                    if (controle.equals("O") || controle.equals("o")) {
                        controleSolde = true;
                    }

                    CarteCredit uneCarte = new CarteCredit(tableauClients.get(numeroClient - 1), (CompteCourant) tableauComptes.get(numeroCompte - 1), numero, type, controleSolde, plafond, date);
                    tableauClients.get(numeroClient - 1).nouveauMoyenPaiement(uneCarte);
                    tableauMoyensPaiement.add(uneCarte);

                    System.out.println("La carte bancaire n°" + numero + " (" + type + ") a bien été commandée au nom de " + tableauClients.get(numeroClient - 1).getClient() + ".\n         Numéro de moyen de paiement lors des demandes : " + tableauMoyensPaiement.size());
                } else if (moyenPaiement.equals("CHQ") || moyenPaiement.equals("chq")) {
                    System.out.print("         - Banque émettrice        : ");
                    banque = scanner.nextLine();
                    if (tableauComptes.get(numeroCompte - 1) instanceof CompteCourant) {
                        Chequier unChequier = new Chequier(tableauClients.get(numeroClient - 1), (CompteCourant) tableauComptes.get(numeroCompte - 1), banque);
                        tableauClients.get(numeroClient - 1).nouveauMoyenPaiement(unChequier);
                        tableauMoyensPaiement.add(unChequier);

                        System.out.println("Le chéquier a bien été commandé au nom de " + tableauClients.get(numeroClient - 1).getClient() + ".\n         Numéro de moyen de paiement lors des demandes : " + tableauMoyensPaiement.size());
                    }
                } else {
                    System.out.println("     Type de moyen de paiement inconnu...");
                }
            } else {
                System.out.println("     Ce compte n'est pas un compte courant... On ne peut pas affecter de moyen de paiement.");
            }
        } else {
            System.out.println("     Une erreur est survenue dans la saisie des numéros de compte ou de client...");
        }
    }

    /**
     * Méthode permettant d'approvisionner un compte par les deux méthodes : apport d'espèces et virement depuis un autre compte.
     * On utilisera les méthodes implémentées dans les classes 'Compte'.
     */

    public void approvisionnerCompte() {
        Scanner scanner = new Scanner(System.in);
        int numeroCompte, numeroCompteDebite;
        float somme;
        String type, dateValeur, libelleOperation;

        System.out.println("/////////  Approvisionner un compte");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de compte        : ");
        numeroCompte = scanner.nextInt();
        type = scanner.nextLine();      //Vidage du buffer pour éviter des bugs de saisie.

        if (numeroCompte - 1 < tableauComptes.size()) {
            System.out.print("         - Type d'apport (ESP/VIR) : ");
            type = scanner.nextLine();
            System.out.print("         - Date de valeur          : ");
            dateValeur = scanner.nextLine();
            System.out.print("         - Libellé d'opération     : ");
            libelleOperation = scanner.nextLine();
            System.out.print("         - Montant de l'approvis.  : ");
            somme = scanner.nextFloat();

            if (type.equals("ESP") || type.equals("esp")) {
                tableauComptes.get(numeroCompte - 1).apportCompte(dateValeur, libelleOperation, somme);
            } else if (type.equals("VIR") || type.equals("vir")) {
                System.out.print("         - Numéro du compte débité : ");
                numeroCompteDebite = scanner.nextInt();

                if (numeroCompteDebite - 1 < tableauComptes.size()) {
                    tableauComptes.get(numeroCompte - 1).approvisionnerCompte(dateValeur, tableauComptes.get(numeroCompteDebite - 1), libelleOperation, somme);
                } else {
                    System.out.println("     Ce compte ne peut pas exister...");
                }
            } else {
                System.out.println("     Technique d'approvisionnement inconnue...");
            }
        } else {
            System.out.println("     Ce compte ne peut pas exister...");
        }
    }

    /**
     * Méthode permettant d'effectuer un paiement depuis un compte courant uniquement (instruction instanceof) avec le moyen de paiement désiré.
     * On utilisera la méthode implémentée dans la classe 'CompteCourant'.
     */

    public void effectuerPaiement() {
        Scanner scanner = new Scanner(System.in);
        int numeroCompte, numeroCompteCredite, numeroMoyenPaiement;
        float somme;
        String type, dateValeur, libelleOperation;

        System.out.println("/////////  Effectuer un paiment");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de compte débité      : ");
        numeroCompte = scanner.nextInt();
        System.out.print("         - Numéro de compte crédité     : ");
        numeroCompteCredite = scanner.nextInt();
        dateValeur = scanner.nextLine();

        if (numeroCompte - 1 < tableauComptes.size() && numeroCompteCredite - 1 < tableauComptes.size()) {
            if (tableauComptes.get(numeroCompte - 1) instanceof CompteCourant && tableauComptes.get(numeroCompteCredite - 1) instanceof CompteCourant) {
                System.out.print("         - Date de valeur paiement      : ");
                dateValeur = scanner.nextLine();
                System.out.print("         - Libellé de l'opération       : ");
                libelleOperation = scanner.nextLine();
                System.out.print("         - Montant du paiement (€)      : ");
                somme = scanner.nextFloat();
                System.out.print("         - Numéro du moyen de paiement  : ");
                numeroMoyenPaiement = scanner.nextInt();

                tableauComptes.get(numeroCompte - 1).effectuerPaiement(dateValeur, tableauComptes.get(numeroCompteCredite - 1), libelleOperation, somme, tableauMoyensPaiement.get(numeroMoyenPaiement - 1));
            } else {
                System.out.println("     Les comptes ne sont pas des comptes courants... Le paiement est impossible.");
            }
        } else {
            System.out.println("     Une erreur est survenue dans la saisie des numéros de compte...");
        }
    }

    /**
     * Fonction de calcul des intérêts pour les comptes épargne exclusivement (instruction instanceof).
     * On utilisera la méthode implémentée depuis la classe 'CompteEpargne'.
     */

    public void calculerInterets() {
        Scanner scanner = new Scanner(System.in);
        int numeroCompte, annee;

        System.out.println("/////////  Calculer des intérêts");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de compte épargne : ");
        numeroCompte = scanner.nextInt();

        if (numeroCompte - 1 < tableauComptes.size()) {
            if (tableauComptes.get(numeroCompte - 1) instanceof CompteEpargne) {
                System.out.print("         - Année courante de calcul : ");
                annee = scanner.nextInt();
                tableauComptes.get(numeroCompte - 1).calculInterets(annee);
            } else {
                System.out.println("     Le compte n'est pas un compte épargne... Le calcul des intérêts est impossible.");
            }
        } else {
            System.out.println("     Ce compte ne peut pas exister...");
        }
    }

    /**
     * Fonction qui permet de consulter les comptes d'un client.
     * On fera appel aux méthodes de la classe 'Client'
     */

    public void consulterCompte() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient;

        System.out.println("/////////  Consulter les comptes d'un client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de client : ");
        numeroClient = scanner.nextInt();

        if (numeroClient - 1 < tableauClients.size()) {
            System.out.println(tableauClients.get(numeroClient - 1).afficherComptes());
        } else {
            System.out.println("     Le client ne peut pas exister...");
        }
    }

    /**
     * Fonction qui permet de consulter les soldes des comptes d'un client.
     * On fera appel aux méthodes de la classe 'Client'
     */

    public void consulterSolde() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient;

        System.out.println("/////////  Consulter les soldes d'un client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de client : ");
        numeroClient = scanner.nextInt();

        if (numeroClient - 1 < tableauClients.size()) {
            tableauClients.get(numeroClient - 1).afficherSolde();
        } else {
            System.out.println("     Le client ne peut pas exister...");
        }
    }

    /**
     * Fonction qui permet de consulter les opérations des comptes d'un client.
     * On fera appel aux méthodes de la classe 'Client'
     */

    public void consulterOperations() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient;

        System.out.println("/////////  Consulter les comptes d'un client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de client : ");
        numeroClient = scanner.nextInt();

        if (numeroClient - 1 < tableauClients.size()) {
            tableauClients.get(numeroClient - 1).afficherOperations();
        } else {
            System.out.println("     Le client ne peut pas exister...");
        }
    }

    /**
     * Fonction qui permet de consulter les moyens de paiement d'un client.
     * On fera appel aux méthodes de la classe 'Client'
     */

    public void consulterMoyensPaiement() {
        Scanner scanner = new Scanner(System.in);
        int numeroClient;

        System.out.println("/////////  Consulter les moyens de paiement d'un client");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de client : ");
        numeroClient = scanner.nextInt();

        if (numeroClient - 1 < tableauClients.size()) {
            System.out.println(tableauClients.get(numeroClient - 1).afficherMoyensPaiement());
        } else {
            System.out.println("     Le client ne peut pas exister...");
        }
    }

    /**
     * Fonction qui permet de modifier le découvert sur un compte courant ou de le supprimer.
     * On utilisera les méthodes définies dans la classe 'CompteCourant'
     */

    public void modifierDecouvert() {
        Scanner scanner = new Scanner(System.in);
        int numeroCompte;
        String autorise;
        boolean decouvert = false;

        System.out.println("/////////  Modifier le découvert");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro du compte         : ");
        numeroCompte = scanner.nextInt();
        autorise = scanner.nextLine();

        if (numeroCompte - 1 < tableauComptes.size()) {
            if (tableauComptes.get(numeroCompte - 1) instanceof CompteCourant) {
                System.out.print("         - Découvert autorisé (O/N) : ");
                autorise = scanner.nextLine();
                if (autorise.equals("O") || autorise.equals("o")) {
                    decouvert = true;
                }
                tableauComptes.get(numeroCompte - 1).setDecouvertAutorise(decouvert);
            } else {
                System.out.println("     Le compte n'est pas un compte courant... Modification du découvert impossible sur un compte épargne.");
            }
        } else {
            System.out.println("     Le client ne peut pas exister...");
        }
    }

    /**
     * Méthode permettant de remettre à zéro le plafond de la carte bancaire sélectionnée. On vérifie qu'il s'agit bien d'une CB (instruction instanceof).
     */

    public void razPlafondCarte() {
        Scanner scanner = new Scanner(System.in);
        int numeroCarte;

        System.out.println("/////////  Réinitialiser un plafond de carte bancaire");
        System.out.println("     Veuillez saisir les informations demandées.");

        System.out.print("         - Numéro de carte bancaire : ");
        numeroCarte = scanner.nextInt();

        if (numeroCarte - 1 < tableauMoyensPaiement.size()) {
            if (tableauMoyensPaiement.get(numeroCarte - 1) instanceof CarteCredit) {
                tableauMoyensPaiement.get(numeroCarte - 1).razPlafond();
            } else {
                System.out.println("     Le moyen de paiement n'est pas une carte bancaire... Remise à zéro du plafond impossible sur un chéquier.");
            }
        } else {
            System.out.println("     Le moyen de paiement ne peut pas exister...");
        }
    }
}
