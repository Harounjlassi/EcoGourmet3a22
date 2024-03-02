package tn.esprit.controllers.livraison.chef;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import tn.esprit.controllers.livraison.YourController;
import tn.esprit.services.livraison.ServiceLivraison;
import tn.esprit.controllers.User.loginController;
import tn.esprit.models.livraison.livraison;

import java.io.IOException;

public class AnnonceDelivery1 {
    @FXML
    private Button refresh;

    @FXML
    void refresh(ActionEvent event) {
        //if(new ServiceLivraison().getLastInsertedLivraison().getChef()==loginController.logged_in_user){
        try {

            ServiceLivraison sliv = new ServiceLivraison();
            livraison liv=sliv.getLastInsertedLivraison();
            System.out.println(liv);

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/chef/chef_delivery.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller of the new view
            AnnonceDelivery2 controller = fxmlLoader.getController();

            // Pass sliv to the controller
            controller.setLivraison(liv);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            Stage currentStage = (Stage) refresh.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //}

    }
}
