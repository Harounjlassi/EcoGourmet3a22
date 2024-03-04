package tn.esprit.models;

public class PanierAnnonce {
    private int id_PanierAnnonce;
    private int id_panier;
    private int id_annonce;
    private int quantite;

    public PanierAnnonce(){
    }
    public PanierAnnonce(int id_panier, int id_annonce){
        this.id_panier = id_panier;
        this.id_annonce = id_annonce;
    }
    public PanierAnnonce(int id_PanierAnnonce,int id_panier, int id_annonce){
        this.id_PanierAnnonce=id_PanierAnnonce;
        this.id_panier = id_panier;
        this.id_annonce = id_annonce;
    }
    public PanierAnnonce(int id_PanierAnnonce, int id_panier, int id_annonce, int quantite) {
        this.id_PanierAnnonce = id_PanierAnnonce;
        this.id_panier = id_panier;
        this.id_annonce = id_annonce;
        this.quantite = quantite;
    }

    public int getId_PanierAnnonce() {
        return id_PanierAnnonce;
    }

    public void setId_PanierAnnonce(int id_PanierAnnonce) {
        this.id_PanierAnnonce = id_PanierAnnonce;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
