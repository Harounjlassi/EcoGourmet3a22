package tn.esprit.models;

import java.util.Date;
import java.util.List;

public class Panier {
    private int id_panier;
    private int id_client;

    //list<anonce>
    List<Annonce> listAnnonce;
    private Date dateCreation;
    private Date dateModification;

    public Panier(){}

//    public Panier(Date dateCreation,Date dateModification){
//        this.dateCreation=dateCreation;
//        this.dateModification=dateModification;
//    }

    public Panier(int id_panier, int id_client, List<Annonce> listAnnonce) {
        this.id_panier = id_panier;
        this.id_client = id_client;
        this.listAnnonce = listAnnonce;
    }

    public Panier(int id_panier, int id_client, List<Annonce> listAnnonce,Date dateCreation,Date dateModification) {
        this.id_panier = id_panier;
        this.id_client = id_client;
        this.listAnnonce = listAnnonce;
        this.dateCreation=dateCreation;
        this.dateModification=dateModification;
    }

    public Panier(int id_panier,int id_client){
        this.id_panier=id_panier;
        this.id_client=id_client;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void ajouterAnnonce(Annonce annonce) {
        this.listAnnonce.add(annonce);
    }
    public List<Annonce> getAnnonces() {
        return this.listAnnonce;
    }

    public List<Annonce> getListAnnonce() {
        return listAnnonce;
    }

    public void setListAnnonce(List<Annonce> listAnnonce) {
        this.listAnnonce = listAnnonce;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateModification() {
        return dateModification;
    }

    public void setDateModification(Date dateModification) {
        this.dateModification = dateModification;
    }

    @Override
    public String toString() {
        return "Panier{" +
                "id_panier=" + id_panier +
                ", id_client=" + id_client +
                ", listAnnonce=" + listAnnonce +
                ", dateCreation=" + dateCreation +
                ", dateModification=" + dateModification +
                '}';
    }
}
