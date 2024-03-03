package tn.esprit.models;
public class User {
    private int UserID;
    private String Nom;
    private String Prenom;
    private String Email;
    private String Numero;
    private String Password;
    private String Role;

    public User()
    {

    }

    public User(int UserID, String Nom, String Prenom, String Email, String Numero, String Password, String Role) {
        this.UserID = UserID;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Numero = Numero;
        this.Password = Password;
        this.Role = Role;
    }

    public User(String Nom, String Prenom, String Email, String Numero, String Password, String Role) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.Email = Email;
        this.Numero = Numero;
        this.Password = Password;
        this.Role = Role;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserID=" + UserID +
                ", Nom='" + Nom + '\'' +
                ", Prenom='" + Prenom + '\'' +
                ", Email='" + Email + '\'' +
                ", Numero='" + Numero + '\'' +
                ", Password='" + Password + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
