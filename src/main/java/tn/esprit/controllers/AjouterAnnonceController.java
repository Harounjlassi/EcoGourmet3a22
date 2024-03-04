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
    private TextField adresseTextField;

    @FXML
    private TextField quantiteTextField;

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
        String quantiteText = quantiteTextField.getText(); // Get quantité value
        String adresse = adresseTextField.getText(); // Get adresse value

        // Validate input fields
        if (nomPlat.isEmpty() || descriptionPlat.isEmpty() || prixText.isEmpty() || ingredients.isEmpty() || categorie.isEmpty() || quantiteText.isEmpty() || adresse.isEmpty()) {
            // Show an error message indicating that all fields are required
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "All fields are required.", "Please fill in all the fields.");
            return;
        }

        float prix;
        int quantite; // Variable to hold quantité value
        try {
            prix = Float.parseFloat(prixText);
            quantite = Integer.parseInt(quantiteText); // Parse quantité as an integer
        } catch (NumberFormatException e) {
            // Show an error message indicating that prix and quantité must be valid numbers
            showAlert(Alert.AlertType.ERROR, "Input Validation Error", "Invalid Prix or Quantité", "Prix and Quantité must be valid numbers.");
            return;
        }

        // Create an Annonce object and set its attributes
        Annonce annonce = new Annonce();
        annonce.setNom_du_plat(nomPlat);
        annonce.setDescription_du_plat(descriptionPlat);
        annonce.setPrix(prix);
        annonce.setQuantite(quantite); // Set quantité
        annonce.setAdresse(adresse); // Set adresse
        // Set ID_du_chef if needed
        annonce.setUserID(loginController.id);
        annonce.setIngredients(ingredients);
        annonce.setCategorie_de_plat(categorie);
        annonce.setImage_plat(imagePath);

        // Set the image path
        String message = "New announcement: " + nomPlat + "\nDescription: " + descriptionPlat + "\nPrix: " + prixText + "\nIngredients: " + ingredients + "\nCategorie: " + categorie + "\nQuantité Disponible: " + quantiteText;

        // Add the Annonce to the database using the service
        sa.add(annonce);

        annonceController.handleAnnouncementAdded();
        annonceController.sortedAnnonces = sa.getAll();
        annonceController.handleAnnouncementAdded();
        FacebookAnnouncementHandler.postAnnouncementOnFacebook(message, imagePath);
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }

}
