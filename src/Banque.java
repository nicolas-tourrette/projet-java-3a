public class Banque {
    public static void main(String[] args) {
        CompteCourant unCompte = new CompteCourant("52153318018", "2019-05-18", true);
        CompteCourant unDeuxiemeCompte = new CompteCourant("01789710348", "1998-02-15", false);

        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println();
        unCompte.approvisionnerCompte("2019-05-18", "Salaire Mai 2019", 3000.0f);
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #1", 150);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println();
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 2900);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println();
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 40);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println();
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 20);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);

        System.out.println();
        unCompte.approvisionnerCompte("2019-08-19", "Réappro", 20);
        System.out.println(unCompte);

        unCompte.setDecouvertAutorise(false);

        System.out.println();
        unCompte.effectuerPaiement("2019-05-18", unDeuxiemeCompte, "Paiement #2", 20);
        System.out.println(unCompte);
        System.out.println(unDeuxiemeCompte);
    }
}
