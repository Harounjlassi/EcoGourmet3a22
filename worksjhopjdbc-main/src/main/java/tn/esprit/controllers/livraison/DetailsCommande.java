package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.commande;

public class DetailsCommande {
    @FXML
    private Label idLabel;



    public void setFeedback(commande commande) {
        idLabel.setText("id:" + String.valueOf(commande.getId()));
    }
}
