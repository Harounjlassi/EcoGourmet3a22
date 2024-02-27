package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.chef;

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



    public void setChef(chef chef) {
        idLabel.setText("id :" + String.valueOf(chef.getId()));
        nameLabel.setText("nom :" +chef.getNom());
        prenomLabel.setText("prenom :" + chef.getPrénom());
        ageLabel.setText("age :" + String.valueOf(chef.getAge()));
        telLabel.setText("tel :" + String.valueOf(chef.getTel()));
    }
}
