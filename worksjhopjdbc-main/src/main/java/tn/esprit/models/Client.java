package tn.esprit.models;

public class Client {
    public int id_client;
    private String nom ;
    private String prenom ;
    private String numero_telephone;
    private String adresse_email;

    public Client(){}

    public Client(int id_client,String nom,String prenom,String numero_telephone,String adresse_email){
        this.id_client=id_client;
        this.nom=nom;
        this.prenom=prenom;
        this.numero_telephone=numero_telephone;
        this.adresse_email=adresse_email;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public String getAdresse_email() {
        return adresse_email;
    }

    public void setAdresse_email(String adresse_email) {
        this.adresse_email = adresse_email;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", numero_telephone='" + numero_telephone + '\'' +
                ", adresse_email='" + adresse_email + '\'' +
                '}';
    }
}
