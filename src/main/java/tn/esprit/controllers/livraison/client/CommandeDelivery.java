package tn.esprit.controllers.livraison.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.models.Commande;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.commandeService;
import tn.esprit.services.livraison.ServiceLivraison;

import java.io.IOException;

public class CommandeDelivery {
    @FXML
    private Button button1;

    @FXML
    void Valider_Commande(ActionEvent event) {
        ServiceLivraison sliv=new ServiceLivraison();
        commandeService scom=new commandeService();
        Commande last=scom.getLastInsertedCommande();
        livraison liv=new livraison(1,null,scom.getChefDetailsFromCommande(last.getId_commande()),new commandeService().getAdresseFromCommande(last.getId_commande()),last.getAdresse(),null,null,false,false,null,null,last);
        sliv.add(liv);

        try {
            // Load the FXML file for the new view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/client/Delivery_client.fxml"));

            // Create a new scene
            Scene scene = new Scene(fxmlLoader.load());

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

            // Show the new scene
            stage.show();
            Stage currentStage = (Stage) button1.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
