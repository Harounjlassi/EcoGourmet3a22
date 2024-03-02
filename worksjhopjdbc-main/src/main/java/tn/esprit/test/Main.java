package tn.esprit.test;

import tn.esprit.models.Panier;
import tn.esprit.models.Personne;
import tn.esprit.services.*;
import tn.esprit.utils.MyDataBase;

import java.sql.SQLException;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws SQLException {
        commandeService sp = new commandeService();
        panierService sp1 = new panierService();
        annonceService sp2 = new annonceService();
        ClientService sp3 = new ClientService();
        /*Commande c1 = new Commande(1,1,"sahabi","termine",1);
        Commande c2 = new Commande(2,1,"minchiya","termine",6);
        Commande c3 = new Commande(2,1,"wled mana3","termine",6);
        sp.Ajouter(c1);
        sp.Ajouter(c2);
        sp.Ajouter(c3);*/
        //System.out.println(sp.getAll());
        //sp.Supprimer(6);
        //sp.Supprimer(39);
        //sp.Modifier(50,"miami","en train de lviraison");
        //System.out.println(sp.readCommande(46));
        //System.out.println(sp.trierCommandesParPrixDecroissant());
        //System.out.println(sp.rechercherCommandesParPrix(30,50));
        //Annonce annonce1 = new Annonce();
        //Annonce annonce2 = new Annonce();
        //Annonce annonce3 = new Annonce();
        //annonce1.setId_annonce(1);
        //annonce1.setId_annonce(2);
        //annonce1.setId_annonce(3);
        //sp1.ajouterAnnonceAuPanier(1,1);
        //sp1.ajouterAnnonceAuPanier(1,2);
        //sp1.ajouterAnnonceAuPanier(1,3);
        //System.out.println(sp1.getAll());
        //sp.ajouterCommande(1,1,50,"beb tounis","not yet");
        //System.out.println(sp.prixCommande(1));
        //System.out.println(sp1.rechercherAnnoncesDansPanierParNom(1,"sssssss"));
        //System.out.println(sp2.getAllAnnonces());
        //System.out.println(sp1.getAllAnnoncesFromPanier(1));
        //System.out.println(sp3.afficherInformationsClient(1));
//        Panier panier1=new Panier();
//        panier1.setId_client(1); // Définir l'ID du client
//        panier1.setDateCreation(new Date()); // Définir la date de création
//        panier1.setDateModification(new Date()); // Définir la date de modification
//        sp1.Ajouter(panier1);
//        System.out.println(sp1.getAll());
    }
}