package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import tn.esprit.models.Client;

public class CommandeController {
    public BorderPane commandePane;
    @FXML
    private Label nomLabel;

    @FXML
    private Label prenomLabel;

    @FXML
    private TextField adresseField;


    private Client client; // Vous devrez injecter le client dans ce contrôleur

    @FXML
    private void initialize() {
        if (client != null) {
            nomLabel.setText(client.getNom());
            prenomLabel.setText(client.getPrenom());
        }
    }


    public String getAdresse() {
        return adresseField.getText();
    }
}

