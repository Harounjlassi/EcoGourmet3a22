package tn.esprit.controllers.livraison.chef;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.controllers.livraison.YourController;
import tn.esprit.services.livraison.ServiceLivraison;
import tn.esprit.controllers.User.loginController;
import tn.esprit.models.livraison.livraison;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AnnonceDelivery1 /*implements Initializable*/ {
    @FXML
    private Button refresh;
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> refresh()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
    void  refresh(){
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

    }*/

    @FXML
    void refresh(ActionEvent event) {
        if(/*new ServiceLivraison().getLastInsertedLivraison().getChef()==loginController.logged_in_user && */new ServiceLivraison().getLastInsertedLivraison().getLivreur()!=null && new ServiceLivraison().getLastInsertedLivraison().getFeedback_liv()==null && new ServiceLivraison().getLastInsertedLivraison().getRéclamation()==null){
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
        }}

        //}

    }
}
