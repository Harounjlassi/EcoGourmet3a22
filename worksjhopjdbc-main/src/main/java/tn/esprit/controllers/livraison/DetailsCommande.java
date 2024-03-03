package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.commande;

import java.util.List;
import java.util.Map;

public class DetailsCommande {
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;



    public void setCommande(Map<String, Object> commande) {
        if (commande.containsKey("Nom_du_plat")) {
            label1.setText("Nom du plat: " + (String) commande.get("Nom_du_plat"));
        }
        if (commande.containsKey("quantite")) {
            label2.setText("Quantite: " + Integer.toString((Integer) commande.get("quantite")));
        }
        if (commande.containsKey("prix_total")) {
            label3.setText("Prix totale: " + Integer.toString((Integer) commande.get("prix_total")));
        }
    }
}
