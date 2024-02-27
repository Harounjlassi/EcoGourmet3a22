package tn.esprit.controllers.livraison;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.livreur;

public class DetailslivreurController {

    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private Label telLabel;



    public void setLivreur(livreur livreur) {
        idLabel.setText("id :"+String.valueOf(livreur.getId()));
        nameLabel.setText("nom :"+livreur.getNom());
        prenomLabel.setText("prenom :"+livreur.getPrénom());
        ageLabel.setText("age :"+String.valueOf(livreur.getAge()));
        telLabel.setText("tel :"+String.valueOf(livreur.getTel()));

    }
}

