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
import tn.esprit.models.Commande;
import tn.esprit.models.User;
import tn.esprit.models.livraison.*;
import tn.esprit.services.UserService;
import tn.esprit.services.commandeService;
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
    private ComboBox<User> livreur;
    @FXML
    private ComboBox<User> chef;
    @FXML
    private Button feedbackButton;
    @FXML
    private Button reclamationButton;
    @FXML
    private ComboBox<Commande> commande;
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
            List<User> livreurs = new UserService().getAllLivreurs();
            List<User> chefs = new UserService().getAllChefs();
            List<Feedback_livraison> feedbacks = new Service_FeedBack_livraison().getAll();
            List<Réclamation> reclamations = new ServiceRéclamation().getAll();
            List<Commande> commandes = new commandeService().getAll();

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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Both source and destination addresses are required.");
                alert.showAndWait();
                return;
            }

            if (!source.matches(".*[a-zA-Z].*") || !destination.matches(".*[a-zA-Z].*")) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Source and destination must contain alphabetic characters.");
                alert.showAndWait();
                return;
            }
            System.out.println("i'm here");
            serviceLivraison.add(new livraison(
                    0,
                    livreur.getValue(),
                    chef.getValue(),
                    adresse_source.getText(),
                    adresse_destination.getText(),
                    new Service_FeedBack_livraison().getLastInsertedFeedback(),
                    new ServiceRéclamation().getLastInsertedReclamation(),
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

