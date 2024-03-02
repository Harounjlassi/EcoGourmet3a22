package tn.esprit.models;

import java.sql.Timestamp;

public class Commande {
    private int id_commande ;
    private int id_client;
    private int prix_total;
    private String adresse;
    private int id_panier;
    private String etatLivraison;
    private Timestamp tempsCommande;

    public Commande(){
    }

    public Commande(int id_commande,int id_client,int prix_total,String adresse,String etatLivraison,Timestamp tempsCommande){
        this.id_commande=id_commande;
        this.id_client=id_client;
        this.prix_total=prix_total;
        this.adresse=adresse;
        this.etatLivraison=etatLivraison;
        this.tempsCommande=tempsCommande;
    }

    public Commande(int id_commande,int id_client,int prix_total,String adresse,String etatLivraison){
        this.id_commande=id_commande;
        this.id_client=id_client;
        this.prix_total=prix_total;
        this.adresse=adresse;
        this.etatLivraison=etatLivraison;

    }
    public Commande(int id_commande,int id_client,int prix_total,String adresse,int id_panier){
        this.id_commande=id_commande;
        this.id_client=id_client;
        this.prix_total=prix_total;
        this.adresse=adresse;

        this.id_panier=id_panier;
    }
    public Commande(int prix_total,String adresse){
        this.prix_total=prix_total;
        this.adresse=adresse;

    }

    public Commande(int id_client,int prix_total,String adresse,int id_panier){
        this.id_client=id_client;
        this.prix_total=prix_total;
        this.adresse=adresse;

        this.id_panier= id_panier;
    }
    public Commande(int id_client,int prix_total,String adresse){
        this.id_client=id_client;
        this.prix_total=prix_total;
        this.adresse=adresse;

    }
    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public String getEtatLivraison() {
        return etatLivraison;
    }

    public void setEtatLivraison(String etatLivraison) {
        this.etatLivraison = etatLivraison;
    }

    public Timestamp getTempsCommande() {
        return tempsCommande;
    }

    public void setTempsCommande(Timestamp tempsCommande) {
        this.tempsCommande = tempsCommande;
    }

    @Override
    public String toString() {
        return "Commande{" +
                "id_commande=" + id_commande +
                ", id_client=" + id_client +
                ", prix_total=" + prix_total +
                ", adresse='" + adresse + '\'' +
                ", id_panier=" + id_panier +
                ", etatLivraison='" + etatLivraison + '\'' +
                ", tempsCommande=" + tempsCommande +
                '}';
    }

}
