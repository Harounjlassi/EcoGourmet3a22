package tn.esprit.models.livraison;

import java.sql.Timestamp;

public class Réclamation {
    private int id;
    private Timestamp Date_Réclamation;
    private String Cause_Réclamation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate_Réclamation() {
        return Date_Réclamation;
    }

    public void setDate_Réclamation(Timestamp date_Réclamation) {
        Date_Réclamation = date_Réclamation;
    }

    public String getCause_Réclamation() {
        return Cause_Réclamation;
    }

    public void setCause_Réclamation(String cause_Réclamation) {
        Cause_Réclamation = cause_Réclamation;
    }

    public Réclamation() {
    }

    public Réclamation(int id) {
        this.id = id;
    }

    public Réclamation(int id, Timestamp date_Réclamation, String cause_Réclamation) {
        this.id = id;
        Date_Réclamation = date_Réclamation;
        Cause_Réclamation = cause_Réclamation;
    }

    @Override
    public String toString() {
        return "Réclamation{" +
                "id=" + id +
                ", Date_Réclamation=" + Date_Réclamation +
                ", Cause_Réclamation='" + Cause_Réclamation + '\'' +
                '}';
    }
}
