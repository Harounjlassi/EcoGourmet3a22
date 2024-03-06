package tn.esprit.models.livraison;

public class Feedback_livraison {
    private int id;
    private int FB_livreur;
    private int FB_duration;

    public int getId() {
        return id;
    }

    public Feedback_livraison(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFB_livreur() {
        return FB_livreur;
    }

    public void setFB_livreur(int FB_livreur) {
        this.FB_livreur = FB_livreur;
    }

    public int getFB_duration() {
        return FB_duration;
    }

    public void setFB_duration(int FB_duration) {
        this.FB_duration = FB_duration;
    }

    public Feedback_livraison(int id, int FB_livreur, int FB_duration) {
        this.id = id;
        this.FB_livreur = FB_livreur;
        this.FB_duration = FB_duration;
    }

    public Feedback_livraison() {
    }

    @Override
    public String toString() {
        return "Feedback_livraison{" +
                "id=" + id +
                ", FB_livreur=" + FB_livreur +
                ", FB_duration=" + FB_duration +
                '}';
    }
}
