package tn.esprit.controllers.livraison;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.User;

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



    public void setLivreur(User livreur) {
        idLabel.setText("id :"+String.valueOf(livreur.getUserID()));
        nameLabel.setText("nom :"+livreur.getNom());
        prenomLabel.setText("prenom :"+livreur.getPrenom());
        ageLabel.setText("email :"+String.valueOf(livreur.getEmail()));
        telLabel.setText("tel :"+String.valueOf(livreur.getNumero()));

    }
}

