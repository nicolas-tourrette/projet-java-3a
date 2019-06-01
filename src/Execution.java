public class Execution {
    public static void main(String[] args) {
        CompteCourant unCompte = new CompteCourant("52153318018", "2019-05-18", true, 3000);
        CompteCourant unDeuxiemeCompte = new CompteCourant("01789710348", "1998-02-15", false, 0);

        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE1\n");
        unCompte.apportCompte("2019-05-31", "Test", 25);

        System.out.println("\nE2\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #1", 150);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE3\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 2900);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE4\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #3", 40);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE5\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #4", 20);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        unCompte.setDecouvertAutorise(false);

        System.out.println("\nE6\n");
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #5", 20);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println("\nE7\n");
        unCompte.afficherOperations();
        unDeuxiemeCompte.afficherOperations();
    }
}
