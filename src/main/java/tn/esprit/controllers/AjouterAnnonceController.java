package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.models.Annonce;
import tn.esprit.services.ServiceAnnonce;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import java.io.File;

public class AjouterAnnonceController {

    @FXML
    public Button importImageButton;

    @FXML
    private TextField nomPlatTextField;

    @FXML
    private TextField descriptionPlatTextField;

    @FXML
    private TextField prixTextField;

    @FXML
    private TextField categorieTextField;

    @FXML
    private TextField ingredientTextField;

    @FXML
    private Button ajouterButton;
    // Assuming PopulateTilePaneController is the class where populateTilePane() is defined



    private String imagePath; // Variable to store image path

    private final ServiceAnnonce sa = new ServiceAnnonce();
    private AnnonceController annonceController;

    // Method to set AnnonceController reference
    public void setAnnonceController(AnnonceController annonceController) {
        this.annonceController = annonceController;
    }

    private static final String API_URL = "https://graph.facebook.com/%s/photos";
    private static final String ACCESS_TOKEN = "EAAF4PQJaaJsBOZC2J5JYwbcBDrK1lZBFbIPPlxlMFA7EREZBP84luJVeCTpnCqy8bmPJLmjDFMRVp44GCZCdaInQ7FhDgZAZBsYDnKQ4uYC5jXHYtT1cZANZANGVQnpLI8iZB9cEHhVruRA9dj1ZCLPFa3Dljy5wMcZB2Vlcr0xtUZA3Gao5ovpWZA94tli0OQCkFIlMvWi2l9OZCEo6LfEyPOONdRkfQZD";
    private static final String PAGE_ID = "193731830479603";
    @FXML
    void importImageClicked(ActionEvent event) {
        // Open a file chooser dialog
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        File selectedFile = fileChooser.showOpenDialog(ajouterButton.getScene().getWindow());

        // If a file is selected, get its path
        if (selectedFile != null) {
            imagePath = selectedFile.getAbsolutePath();
        }
    }


    @FXML
    private void ajouterAnnonce(ActionEvent event) {
        // Get the values from the text fields
        String nomPlat = nomPlatTextField.getText();
        String descriptionPlat = descriptionPlatTextField.getText();
        String prixText = prixTextField.getText();
        String ingredients = ingredientTextField.getText();
        String categorie = categorieTextField.getText();

        // Validate input fields
        if (nomPlat.isEmpty() || descriptionPlat.isEmpty() || prixText.isEmpty() || ingredients.isEmpty() || categorie.isEmpty()) {
            // Show an error message indicating that all fields are required
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "All fields are required.", "Please fill in all the fields.");
            return;
        }

        float prix;
        try {
            prix = Float.parseFloat(prixText);
        } catch (NumberFormatException e) {
            // Show an error message indicating that prix must be a valid number
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "Invalid Prix", "Prix must be a valid number.");
            return;
        }

        // Create an Annonce object and set its attributes
        Annonce annonce = new Annonce();
        annonce.setNom_du_plat(nomPlat);
        annonce.setDescription_du_plat(descriptionPlat);
        annonce.setPrix(prix);
        // Set ID_du_chef if needed
        annonce.setUserID(loginController.id);
        annonce.setIngredients(ingredients);
        annonce.setCategorie_de_plat(categorie);
        annonce.setImage_plat(imagePath); // Set the image path
        String message = "New announcement: " + nomPlat + "\nDescription: " + descriptionPlat + "\nPrix: " + prixText + "\nIngredients: " + ingredients + "\nCategorie: " + categorie;

        // Add the Annonce to the database using the service
        sa.add(annonce);

        annonceController.handleAnnouncementAdded();
        annonceController.sortedAnnonces = sa.getAll();
        annonceController.handleAnnouncementAdded();
        FacebookAnnouncementHandler.postAnnouncementOnFacebook(message,imagePath);



        // Attempt to post the announcement on Facebook
        //postAnnouncementOnFacebook(message, imagePath);
    }


    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

}
