package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Screen;

public class MailDisplay {

    @FXML
    private TextField bodyText;

    @FXML
    private TextField subjectMail;

    @FXML
    private TextField toMail;

    @FXML
    void sendMail(ActionEvent event) {
        MailController mc = new MailController();
        String to = "taki54214@gmail.com";

        // Create a confirmation dialog to ask the user if they want to add an attachment
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmation de pièce jointe");
        confirmation.setHeaderText("Voulez-vous ajouter une pièce jointe ?");
        confirmation.setContentText("Voulez-vous ajouter une pièce jointe à cet e-mail?");

        // Add Yes and No buttons to the confirmation dialog
        confirmation.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        // Show the dialog and wait for user response
        ButtonType userChoice = confirmation.showAndWait().orElse(ButtonType.NO);

        // If the user chooses to add an attachment, proceed
        if (userChoice == ButtonType.YES) {
            // Here, you can add code to prompt the user to select a file to attach
            // For simplicity, let's assume you already have the file path
            String currentPath = System.getProperty("user.dir");
            try {
            // Send email with attachment
            mc.mailsend("yhhhgtub", "fonf ieev iuxr pqgl", "yhhhgtub@gmail.com",
                    toMail.getText(), subjectMail.getText(), bodyText.getText(),
                    currentPath+"\\events.pdf");
                succesPop("E-mail envoyé avec succès");

            } catch (Exception e) {
                // Handle the exception
                Errorpop("Échec de l'envoi de l'e-mail : " + e.getMessage());
                System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
            }


        } else {
            // Send email without attachment
            mc.mailsend("yhhhgtub", "fonf ieev iuxr pqgl", "yhhhgtub@gmail.com",
                    toMail.getText(), subjectMail.getText(), bodyText.getText(),
                    null); // Pass null for attachmentFilePath
        }

    }


    public void succesPop(String s){
        Label label = new Label(s); // "Successfully added!" in French
        label.setStyle("-fx-background-color:  #7A8727;-fx-text-fill: #FFB347; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
        StackPane stackPane = new StackPane(label);
        stackPane.setPrefSize(400, 200); // Set preferred size of the StackPane

        Screen screen = Screen.getPrimary();
        double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
        double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

        Popup popup = new Popup();
        popup.getContent().add(stackPane);
        popup.setAutoHide(true);

        popup.show(toMail.getScene().getWindow(), centerX, centerY);
    }
    public void Errorpop(String s)
    {
        Label label = new Label(s); // "Successfully added!" in French
        label.setStyle("-fx-background-color:  #FF5733;-fx-text-fill: white; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
        StackPane stackPane = new StackPane(label);
        stackPane.setPrefSize(400, 400); // Set preferred size of the StackPane

        Screen screen = Screen.getPrimary();
        double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
        double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

        Popup popup = new Popup();
        popup.getContent().add(stackPane);
        popup.setAutoHide(true);

        popup.show(toMail.getScene().getWindow(), centerX, centerY);
    }


}