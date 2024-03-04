package tn.esprit.models;

public class Annonce {

    private int id_Annonce, UserID, quantite;
    private String nom_du_plat, Description_du_plat, Ingredients, Categorie_de_plat,adresse;
    private float Prix;
    private String image_plat;

    public Annonce() {
    }

    public Annonce(int id_Annonce, String nom_du_plat, String description_du_plat, float prix, int UserID, String ingredients, String categorie_de_plat,int quantite,String adresse) {
        this.id_Annonce = id_Annonce;
        this.nom_du_plat = nom_du_plat;
        this.Description_du_plat = description_du_plat;
        this.Prix = prix;
        this.UserID = UserID;
        this.Ingredients = ingredients;
        this.Categorie_de_plat = categorie_de_plat;
        this.quantite=quantite;
        this.adresse=adresse;


    }

    public Annonce(int id_Annonce, String nom_du_plat, String description_du_plat, float prix, String ingredients, String categorie_de_plat,String image_plat) {
        this.id_Annonce = id_Annonce;
        this.nom_du_plat = nom_du_plat;
        this.Description_du_plat = description_du_plat;
        this.Prix = prix;
        this.Ingredients = ingredients;
        this.Categorie_de_plat = categorie_de_plat;
        this.image_plat=image_plat;

    }

    public Annonce(int idAnnonce, String nomDuPlat, String descriptionDuPlat, float prix, int UserID, String ingredients, String categorieDePlat, String imagePlat) {
    }

    public Annonce(int idAnnonce, String nomDuPlat, String descriptionDuPlat, float prix, int userID, String ingredients, String categorieDePlat, String imagePlat, int quantite, String adresse) {
    }


    public String getImage_plat() {
        return image_plat;
    }

    public void setImage_plat(String image_plat) {
        this.image_plat = image_plat;
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

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int ID_du_chef) {
        this.UserID = ID_du_chef;
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
                ", UserID=" + UserID +
                ", quantite=" + quantite +
                ", nom_du_plat='" + nom_du_plat + '\'' +
                ", Description_du_plat='" + Description_du_plat + '\'' +
                ", Ingredients='" + Ingredients + '\'' +
                ", Categorie_de_plat='" + Categorie_de_plat + '\'' +
                ", adresse='" + adresse + '\'' +
                ", Prix=" + Prix +
                ", image_plat='" + image_plat + '\'' +
                '}';
    }
}