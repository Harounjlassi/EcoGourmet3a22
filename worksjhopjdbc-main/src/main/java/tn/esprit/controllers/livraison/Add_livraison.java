package tn.esprit.controllers.livraison;

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
            alert.showAndWait();
            openNewStage("/Gestion_livraisons.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

