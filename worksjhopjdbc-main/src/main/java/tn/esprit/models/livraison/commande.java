package tn.esprit.models.livraison;

public class commande {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public commande(int id) {
        this.id = id;
    }

    public commande() {
    }

    @Override
    public String toString() {
        return "commande{" +
                "id=" + id +
                '}';
    }
}
