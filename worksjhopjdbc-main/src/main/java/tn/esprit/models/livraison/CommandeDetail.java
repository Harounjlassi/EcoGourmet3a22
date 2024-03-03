package tn.esprit.models.livraison;

public class CommandeDetail {
    private String nomDuPlat;
    private int quantite;
    private int prixTotal;
    private String adresse;

    public CommandeDetail() {
    }

    public CommandeDetail(String nomDuPlat, int quantite, int prixTotal, String adresse) {
        this.nomDuPlat = nomDuPlat;
        this.quantite = quantite;
        this.prixTotal = prixTotal;
        this.adresse = adresse;
    }

    public String getNomDuPlat() {
        return nomDuPlat;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getPrixTotal() {
        return prixTotal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setNomDuPlat(String nomDuPlat) {
        this.nomDuPlat = nomDuPlat;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setPrixTotal(int prixTotal) {
        this.prixTotal = prixTotal;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
