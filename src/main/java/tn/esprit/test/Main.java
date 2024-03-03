package tn.esprit.test;

import tn.esprit.models.User;
import tn.esprit.services.UserService;

public class Main {
    public static void main(String[] args) {

        //Appeel Services pour utiliser les méthodes de crud

        UserService uti = new UserService() ;

        //Appel Entité mteaana + test ajout
        //User u = new User("salah","hmiden","h@h.com","20202020","motdepasse","admin");
       // uti.add(u);

        //Test la modification de l'utilisateur ajouter
       // User u2 = new User(8,"Mohamed","hmiden","h@h.com","20202020","motdepasse","admin");
        //uti.modifier(u2);


        //Test Supp
        //uti.supprimer(9);


        //test Affichage resultat sur le terminal
        uti.getall();



    }
}