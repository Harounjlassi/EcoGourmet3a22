package tn.esprit.controllers.livraison.client;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.controllers.livraison.AddRéclamation;
import tn.esprit.controllers.livraison.Add_Feedback;
import tn.esprit.controllers.livraison.DetailsCommande;
import tn.esprit.controllers.livraison.chef.AnnonceDelivery2;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.commande.commandeService;
import tn.esprit.services.livraison.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
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
    @FXML
    private Button button1;
    private Timeline timeline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label2.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label3.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label4.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            ServiceLivraison sliv = new ServiceLivraison();
            if (sliv.getLastInsertedLivraison().getRéclamation() == null) {
                if (sliv.getLastInsertedLivraison().getLivreur() == null) {

                } else {
                    if (sliv.getLastInsertedLivraison().isState_delivery()) {
                        timeline.stop();
                        nameLabel.setVisible(false);
                        prenomLabel.setVisible(false);
                        telLabel.setVisible(false);
                        label3.setVisible(false);
                        label4.setVisible(true);

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event1 -> {
                            try {
                                Stage currentStage = (Stage) label1.getScene().getWindow(); // Get the Stage from button1
                                currentStage.close();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/add_feedback.fxml"));
                                Parent root = fxmlLoader.load();
                                // Get the controller of the new view
                                Add_Feedback controller = fxmlLoader.getController();

                                // Pass sliv to the controller
                                controller.setLivraison(sliv.getLastInsertedLivraison());

                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        pause.play();

                    } else if (sliv.getLastInsertedLivraison().isState_reception()) {
                        label2.setVisible(false);
                        label3.setVisible(true);
                    } else {
                        label1.setVisible(false);
                        label2.setVisible(true);
                        nameLabel.setText("nom :" + sliv.getLastInsertedLivraison().getLivreur().getNom());
                        prenomLabel.setText("prenom :" + sliv.getLastInsertedLivraison().getLivreur().getPrenom());
                        telLabel.setText("tel :" + String.valueOf(sliv.getLastInsertedLivraison().getLivreur().getNumero()));
                        nameLabel.setVisible(true);
                        prenomLabel.setVisible(true);
                        telLabel.setVisible(true);
                    }
                }
            } else {
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
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
    @FXML
    void Command_Details(ActionEvent event) {
        try {
            // Get the livreur details
            commandeService serviceCommande = new commandeService();
            Map<String, Object> commandedetails = serviceCommande.getCommandeDetails(new ServiceLivraison().getLastInsertedLivraison().getCommande().getId_commande());

            // Load the FXML file for the new stage
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DétailsCommande.fxml"));
            Parent root = fxmlLoader.load();

            // Get the controller of the new stage
            DetailsCommande controller = fxmlLoader.getController();

            // Pass the livreur details to the controller
            controller.setCommande(commandedetails);

            // Create the new stage
            Stage detailsStage = new Stage();
            detailsStage.setTitle("Commande Details");
            detailsStage.setScene(new Scene(root));

            // Show the new stage
            detailsStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
