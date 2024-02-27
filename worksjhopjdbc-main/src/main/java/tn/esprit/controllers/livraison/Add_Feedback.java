package tn.esprit.controllers.livraison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import tn.esprit.models.livraison.Feedback_livraison;
import tn.esprit.services.livraison.Service_FeedBack_livraison;


public class Add_Feedback {
    @FXML
    private ToggleGroup groupLivreur;
    @FXML
    private ToggleGroup groupTemps;

    @FXML
    void ajouterfeedback(ActionEvent event) {
        try {
            // Get the selected values
            RadioButton selectedLivreur = (RadioButton) groupLivreur.getSelectedToggle();
            RadioButton selectedTemps = (RadioButton) groupTemps.getSelectedToggle();

            int feedbackLivreur = Integer.parseInt(selectedLivreur.getText());
            int feedbackTemps = Integer.parseInt(selectedTemps.getText());

            // Add the feedback
            Service_FeedBack_livraison serviceFeedback = new Service_FeedBack_livraison();
            serviceFeedback.add(new Feedback_livraison(0,feedbackLivreur,feedbackTemps));

            // Display a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Feedback added successfully!");
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
