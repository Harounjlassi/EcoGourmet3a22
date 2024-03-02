package tn.esprit.controllers.livraison.client;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.controllers.livraison.AddRéclamation;
import tn.esprit.controllers.livraison.Add_Feedback;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.livraison.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeliveryClient implements Initializable{
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Refresh.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label2.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label3.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label4.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
    }

    @FXML
    void Check_livraison(ActionEvent event) {
        System.out.println(new ServiceLivraison().getLastInsertedLivraison().getLivreur());
        if(new ServiceLivraison().getLastInsertedLivraison().getRéclamation()==null){
        if(new ServiceLivraison().getLastInsertedLivraison().getLivreur()==null){

        }
        else {
            if(new ServiceLivraison().getLastInsertedLivraison().isState_delivery()) {
                nameLabel.setVisible(false);
                prenomLabel.setVisible(false);
                telLabel.setVisible(false);
                label3.setVisible(false);
                label4.setVisible(true);

                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event1 -> {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/add_Feedback.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Add_Feedback controller = fxmlLoader.getController();
                        controller.setLivraison(new ServiceLivraison().getLastInsertedLivraison());

                        // Create a new scene

                        // Get the current stage
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        // Set the new scene to the stage
                        stage.setScene(scene);

                        // Show the new scene
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pause.play();
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
        }}else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation is done by delivery man!");

            alert.showAndWait();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Close the stage
            stage.close();


        }
    }

}
