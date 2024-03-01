package tn.esprit.models.Annonce;

public class Annonce {
    private int id_annonce;
    private String nom_annonce;
    private String description_du_plat;
    private int prix;
    private int id_du_chef;
    private String ingredients;
    private String categorie_de_plat;
    private int quantite ;

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public String getNom_annonce() {
        return nom_annonce;
    }

    public void setNom_annonce(String nom_annonce) {
        this.nom_annonce = nom_annonce;
    }

    public String getDescription_du_plat() {
        return description_du_plat;
    }

    public void setDescription_du_plat(String description_du_plat) {
        this.description_du_plat = description_du_plat;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_du_chef() {
        return id_du_chef;
    }

    public void setId_du_chef(int id_du_chef) {
        this.id_du_chef = id_du_chef;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getCategorie_de_plat() {
        return categorie_de_plat;
    }

    public void setCategorie_de_plat(String categorie_de_plat) {
        this.categorie_de_plat = categorie_de_plat;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id_annonce=" + id_annonce +
                ", nom_annonce='" + nom_annonce + '\'' +
                ", description_du_plat='" + description_du_plat + '\'' +
                ", prix=" + prix +
                ", id_du_chef=" + id_du_chef +
                ", ingredients='" + ingredients + '\'' +
                ", categorie_de_plat='" + categorie_de_plat + '\'' +
                ", quantite=" + quantite +
                '}';
    }
}
