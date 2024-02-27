package tn.esprit.controllers.livraison;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.models.livraison.*;
import tn.esprit.services.livraison.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;


public class Add_livraison {

    @FXML
    private TextField adresse_source;
    @FXML
    private TextField adresse_destination;
    @FXML
    private ComboBox<livreur> livreur;
    @FXML
    private ComboBox<chef> chef;
    @FXML
    private Button feedbackButton;
    @FXML
    private Button reclamationButton;
    @FXML
    private ComboBox<commande> commande;
    @FXML
    private Button mySubmitButton;
    private boolean isFeedbackAdded = false;
    private boolean isReclamationAdded = false;



    private void openNewStage(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize() {
        try {
            livreur.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());
            chef.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());
            commande.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());
            feedbackButton.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());
            reclamationButton.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());
            List<livreur> livreurs = new ServiceLivreur().getAll();
            List<chef> chefs = new ServiceChef().getAll();
            List<Feedback_livraison> feedbacks = new Service_FeedBack_livraison().getAll();
            List<Réclamation> reclamations = new ServiceRéclamation().getAll();
            List<commande> commandes = new ServiceCommande().getAll();

            livreur.getItems().addAll(livreurs);
            chef.getItems().addAll(chefs);
            feedbackButton.setOnAction(event -> openNewStage("/livraison/add_feedback.fxml"));
            reclamationButton.setOnAction(event -> openNewStage("/livraison//add_réclamation.fxml"));
            commande.getItems().addAll(commandes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void ajouterLivraison(ActionEvent event) {
        try {
            ServiceLivraison serviceLivraison = new ServiceLivraison();

            String source = adresse_source.getText().trim();
            String destination = adresse_destination.getText().trim();

            if (source.isEmpty() || destination.isEmpty()) {
                // Show an error message if either field is empty
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Both source and destination addresses are required.");
                alert.showAndWait();
                return; // Stop execution if validation fails
            }

            // Validate alphabetic characters
            if (!source.matches(".*[a-zA-Z].*") || !destination.matches(".*[a-zA-Z].*")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Source and destination must contain alphabetic characters.");
                alert.showAndWait();
                return; // Stop execution if validation fails
            }



            Feedback_livraison feedback = null;
            Réclamation reclamation = null;

            if (feedbackButton.isPressed()) {
                feedback = new Service_FeedBack_livraison().getLastInsertedFeedback();
            }

            if (reclamationButton.isPressed()) {
                reclamation = new ServiceRéclamation().getLastInsertedReclamation();
            }

            serviceLivraison.add(new livraison(
                    0,
                    livreur.getValue(),
                    chef.getValue(),
                    adresse_source.getText(),
                    adresse_destination.getText(),
                    feedback,
                    reclamation,
                    false,
                    false,
                    new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()),
                    commande.getValue()
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Livraison added successfully!");
            // Close the alert after 2 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> {
                alert.close();
                openNewStage("/livraison/Gestion_livraisons.fxml");
            });
            pause.play();

            alert.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

