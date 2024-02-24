package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tn.esprit.models.Events;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class AfficherPhotoEvent {
    @FXML
    private ImageView imageView;

    public void setPhoto(FileInputStream fileInputStream) {
        try {
            // Convert FileInputStream to Image
            Image image = new Image(fileInputStream);

            // Set the image in the ImageView
            imageView.setImage(image);

            // Resize the image to fit the size of the ImageView
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(image.getWidth()); // Set the width of ImageView to the width of the image
            imageView.setFitHeight(image.getHeight()); // Set the height of ImageView to the height of the image
        } catch (Exception e) {
            e.printStackTrace(); // Handle any potential exceptions
        }
    }
    @FXML
    private void handleNextButton(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            imageView.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }    }
}