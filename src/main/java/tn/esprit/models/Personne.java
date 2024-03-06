package tn.esprit.models;

import java.awt.*;

public class Personne {

    private int id , age ;
    private String nom , prenom;
    private Button button;

    public Personne() {
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public Personne(int id , String nom, String prenom, int age) {
        this.id = id;
        this.age = age;
        this.nom = nom;
        this.prenom = prenom;
        this.button = new Button("Supprimer");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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
        return "Personne{" +
                "id=" + id +
                ", age=" + age +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                "}\n";
    }

    public void setButton(javafx.scene.control.Button supprimer) {

    }
}
