package tn.esprit.models.Annonce;

public class Annonce {

    private int id_Annonce,ID_du_chef;
    private String nom_du_plat, Description_du_plat, Ingredients, Categorie_de_plat;
    private float Prix;
    private String imagePlat;

    public Annonce() {
    }

    public Annonce( int id_Annonce,String nom_du_plat, String description_du_plat, float prix, int ID_du_chef, String ingredients, String categorie_de_plat) {
        this.id_Annonce = id_Annonce;
        this.nom_du_plat = nom_du_plat;
        this.Description_du_plat = description_du_plat;
        this.Prix = prix;
        this.ID_du_chef=ID_du_chef;
        this.Ingredients = ingredients;
        this.Categorie_de_plat = categorie_de_plat;

    }
    public Annonce( int id_Annonce,String nom_du_plat, String description_du_plat, float prix,String ingredients, String categorie_de_plat) {
        this.id_Annonce = id_Annonce;
        this.nom_du_plat = nom_du_plat;
        this.Description_du_plat = description_du_plat;
        this.Prix = prix;
        this.Ingredients = ingredients;
        this.Categorie_de_plat = categorie_de_plat;

    }


    public String getImagePlat() {
        return imagePlat;
    }

    public void setImagePlat(String imagePlat) {
        this.imagePlat = imagePlat;
    }

    public int getId_Annonce() {
        return id_Annonce;
    }

    public void setId_Annonce(int id_Annonce) {
        this.id_Annonce = id_Annonce;
    }

    public String getNom_du_plat() {
        return nom_du_plat;
    }

    public void setNom_du_plat(String nom_du_plat) {
        this.nom_du_plat = nom_du_plat;
    }

    public String getDescription_du_plat() {
        return Description_du_plat;
    }

    public void setDescription_du_plat(String description_du_plat) {
        Description_du_plat = description_du_plat;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getCategorie_de_plat() {
        return Categorie_de_plat;
    }

    public int getID_du_chef() {
        return ID_du_chef;
    }

    public void setID_du_chef(int ID_du_chef) {
        this.ID_du_chef = ID_du_chef;
    }

    public void setCategorie_de_plat(String categorie_de_plat) {
        Categorie_de_plat = categorie_de_plat;
    }

    public float getPrix() {
        return Prix;
    }

    public void setPrix(float prix) {
        Prix = prix;
    }

    @Override
    public String toString() {
        return "Annonce{" +
                "id_Annonce=" + id_Annonce +
                ", ID_du_chef=" + ID_du_chef +
                ", nom_du_plat='" + nom_du_plat + '\'' +
                ", Description_du_plat='" + Description_du_plat + '\'' +
                ", Ingredients='" + Ingredients + '\'' +
                ", Categorie_de_plat='" + Categorie_de_plat + '\'' +
                ", Prix=" + Prix +
                ", imagePlat='" + imagePlat + '\'' +
                '}';
    }
}