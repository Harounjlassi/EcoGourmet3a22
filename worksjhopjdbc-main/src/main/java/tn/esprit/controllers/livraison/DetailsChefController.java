package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.User.User;

public class DetailsChefController {
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



    public void setChef(User chef) {
        idLabel.setText("id :" + String.valueOf(chef.getUserID()));
        nameLabel.setText("nom :" +chef.getNom());
        prenomLabel.setText("prenom :" + chef.getPrenom());
        ageLabel.setText("email :" + String.valueOf(chef.getEmail()));
        telLabel.setText("tel :" + String.valueOf(chef.getNumero()));
    }
}
