package tn.esprit.controllers.livraison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import tn.esprit.models.livraison.Réclamation;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.livraison.ServiceLivraison;
import tn.esprit.services.livraison.ServiceRéclamation;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class AddRéclamation implements Initializable {

    @FXML
    private TextArea cause_réclamation;
    @FXML
    private Button ajouter;

    private livraison liv;

    // Method to set the livraison object
    public void setLivraison(livraison liv) {
        this.liv = liv;

        // Now you can use this.liv in your controller
    }
    public void initialize(URL url, ResourceBundle rb) {
        ajouter.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
    }

    private final ServiceRéclamation ps = new ServiceRéclamation();

    @FXML
    void ajouterréclamation(ActionEvent event) {
        String reclamationText = cause_réclamation.getText().trim();
        if (reclamationText.length() >= 10) {
            ps.add(new Réclamation(0, new Timestamp(System.currentTimeMillis()), reclamationText));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation added successfully!");
            ServiceLivraison nliv=new ServiceLivraison();
            nliv.updateLivraisonField(liv.getId(),"Réclamation",new ServiceRéclamation().getLastInsertedReclamation().getId());
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/livreur/livreur_acceuil.fxml"));
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setScene(new Scene(root));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation must contain at least 10 characters.");
            alert.showAndWait();

        }}

}
