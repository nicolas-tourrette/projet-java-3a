public class Execution {
    public static void main(String[] args) {
        CompteCourant unCompte = new CompteCourant("52153318018", "2019-05-18", true, 3000);
        CompteCourant unDeuxiemeCompte = new CompteCourant("01789710348", "1998-02-15", false, 0);
        Client nicolasTOURRETTE = new Client("TOURRETTE", "Nicolas", "19, Avenenue Alain Savary", "Dijon", "03.80.69.39.03.");
        nicolasTOURRETTE.nouveauCompte(unCompte);
        CarteCredit uneCarteCredit = new CarteCredit(nicolasTOURRETTE, unCompte, "169785784526", "CB MasterCard", 5000, "2022-03-01");
        nicolasTOURRETTE.nouveauMoyenPaiement(uneCarteCredit);

        System.out.println("\n\n" + uneCarteCredit.getNumero().substring(0,2) + "\n\n");

        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE1\n");
        unCompte.apportCompte("2019-05-31", "Test", 25);

        System.out.println("\nE2\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #1", 150, uneCarteCredit);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
        System.out.println(uneCarteCredit);

        System.out.println("\nE3\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 2900, uneCarteCredit);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
        System.out.println(uneCarteCredit);

        System.out.println("\nE4\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #3", 40, uneCarteCredit);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
        System.out.println(uneCarteCredit);

        System.out.println("\nE5\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #4", 20, uneCarteCredit);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
        System.out.println(uneCarteCredit);

        //unCompte.setDecouvertAutorise(false);

        System.out.println("\nE6\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #5", 20, uneCarteCredit);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
        System.out.println(uneCarteCredit);

        System.out.println("\nE7\n");
        unCompte.afficherOperations();
        unDeuxiemeCompte.afficherOperations();

        System.out.println("\nE8\n");
        System.out.println(nicolasTOURRETTE);
    }
}
