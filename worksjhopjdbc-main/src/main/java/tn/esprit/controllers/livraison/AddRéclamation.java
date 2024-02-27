package tn.esprit.controllers.livraison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tn.esprit.models.livraison.Réclamation;
import tn.esprit.services.livraison.ServiceRéclamation;

import java.sql.Timestamp;

public class AddRéclamation {

    @FXML
    private TextArea cause_réclamation;

    private final ServiceRéclamation ps = new ServiceRéclamation();

    @FXML
    void ajouterréclamation(ActionEvent event) {
        String reclamationText = cause_réclamation.getText().trim();
        if (reclamationText.length() >= 10) {
            ps.add(new Réclamation(0, new Timestamp(System.currentTimeMillis()), reclamationText));
            // Display a confirmation message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation added successfully!");
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            // Show an error message if the text is too short
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation must contain at least 10 characters.");
            alert.showAndWait();

        }}

}
