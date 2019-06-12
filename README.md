# projet-java-3a
Mini Java project for ESIREM

Lire ce fichier avant d'utiliser le programme.


#   1-  PROGRAMME
    NOM     : GESTIONNAIRE DE BANQUE
    VERSION : 1.0 Build 1
    DEV.    : TOURRETTE Nicolas <ESIREM Dijon>
              nicolas_tourrette@etu.u-bourgogne.fr
    DESCRI. : Ce programme a été developpé dans le cadre du module ITC315_JAVA de l'ESIREM Dijon en autonomie sur le langage de programmation Java.
              Ce programme permet de gérer un établissement bancaire avec des clients, leurs comptes (courant ou épargne) et leurs moyens de paiement.
              Les concepts mis en œuvre : héritages, classes et méthodes abstraites, affichage d'un objet avec la méthode toString(), surcharge de méthodes, tableaux dynamiques, test du type d'objets, tableaux d'objets...
              Cette liste n'est sans doute pas exhaustive et le correcteur saura repérer les concepts oubliés par mégarde.


#   2-  Instructions d'utilisation

Pour l'utilisation du programme, on pourra utiliser l'archive JAR exécutable en mode console sur un PC équipé de Java.
L'environnement de développement utilisé est LINUX Mint 19.1 (4.15.0-51-generic), IDE IntelliJ IDEA Community Edition, Java JDK 11.0.2.

1. Se placer dans le dossier où se situe l'archive JAR.
2. Exécuter la commande 'java -jar TOURRETTE.jar'.
3. Suivre les instructions affichées à l'écran.

NOTES IMPORTANTES
1. Le séparateur décimal pour la saisie de nombres à virgule est la VIRGULE et non pas le POINT. Cela est dû à la fonction du Scanner nextFloat() qui utilise ce séparateur.
2. Quand des numéros de compte, de clientou de moyen de paiement sont demandés lors d'un paiement ou d'un approvisionnement par exemple, il s'agit de l'index NATUREL dans le tableau (c'est-à-dire que le premier élément a l'index 1).
3. Le programme ne sauvegarde les saisies que dans la mémoire RAM car aucun système de sauvegarde n'a été implémenté. À chaque démarrage, la banque est donc vide et doit être alimentée dans l'ordre suivant :
    a. Créer au moins un client.
    b. Créer au moins un compte par client.
    c. Créer au moins un moyen de paiement par client.
    d. Faire en sorte que chaque compte soit suffisamment alimenté, même si le premier versement peut être nul.
4. Pour créer un moyen de paiement, afin de garantir le bon fonctionnement du programme, il faut veiller au respect de la convention suivante :
    a. Le type de carte banquaire doit OBLIGATOIREMENT commencer par "CB" (casse obligatoire).


#   3-  EN CAS DE BUG À L'EXÉCUTION DE L'ARCHIVE JAR

L'archive fournit les codes sources du programme. Ils peuvent donc être exécutés à partir d'un IDE. La classe contenant la fonction main() permettant d'exécuter le programme est la classe 'Execution'.
