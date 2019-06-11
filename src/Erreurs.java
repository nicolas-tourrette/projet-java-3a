public class Erreurs extends Exception {
    private int index, taille;

    public Erreurs(int index, int taille){
        this.index = index;
        this.taille = taille;
    }

    @Override
    public String toString() {
        String messageErreur = "";
        if(index < taille){
            messageErreur += "Vous dépassez la capacité. Veuillez saisir un index moins grand.";
        }
        return messageErreur;
    }
}
