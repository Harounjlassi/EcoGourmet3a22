package tn.esprit.models;

public class Client {
    public int id_client;
    private String nom ;
    private String prenom ;

    public Client(){}

    public Client(int id_client,String nom,String prenom){
        this.id_client=id_client;
        this.nom=nom;
        this.prenom=prenom;
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

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
