package tn.esprit.controllers.livraison.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import tn.esprit.models.User.User;
import tn.esprit.services.livraison.ServiceLivraison;

public class DeliveryClient {
    @FXML
    private Button Refresh;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label4;
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

    @FXML
    void Check_livraison(ActionEvent event) {
        System.out.println(new ServiceLivraison().getLastInsertedLivraison().getLivreur());
        if(new ServiceLivraison().getLastInsertedLivraison().getLivreur()==null){

        }
        else {
           if(new ServiceLivraison().getLastInsertedLivraison().isState_delivery()) {
               nameLabel.setVisible(false);
               prenomLabel.setVisible(false);
               telLabel.setVisible(false);
               label3.setVisible(false);
               label4.setVisible(true);
           }
           else if (new ServiceLivraison().getLastInsertedLivraison().isState_reception()){
            label2.setVisible(false);
            label3.setVisible(true);
        }
        else{label1.setVisible(false);
        label2.setVisible(true);
            nameLabel.setText("nom :"+new ServiceLivraison().getLastInsertedLivraison().getLivreur().getNom());
            prenomLabel.setText("prenom :"+new ServiceLivraison().getLastInsertedLivraison().getLivreur().getPrenom());
            telLabel.setText("tel :"+String.valueOf(new ServiceLivraison().getLastInsertedLivraison().getLivreur().getNumero()));
            nameLabel.setVisible(true);
            prenomLabel.setVisible(true);
            telLabel.setVisible(true);}
        }
    }

}
