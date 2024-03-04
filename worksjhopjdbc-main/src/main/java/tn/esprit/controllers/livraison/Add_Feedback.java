package tn.esprit.controllers.livraison;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.livraison.Feedback_livraison;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.livraison.ServiceLivraison;
import tn.esprit.services.livraison.ServiceRéclamation;
import tn.esprit.services.livraison.Service_FeedBack_livraison;

import java.net.URL;
import java.util.ResourceBundle;


public class Add_Feedback implements Initializable {

    @FXML
    private ToggleGroup groupLivreur;
    @FXML
    private ToggleGroup groupTemps;
    @FXML
    private Button submit;
    @FXML
    private Text label1;
    @FXML
    private Text label2;


    private livraison liv;
    public void initialize(URL url, ResourceBundle rb) {
        submit.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
    }

    // Method to set the livraison object
    public void setLivraison(livraison liv) {
        this.liv = liv;

        // Now you can use this.liv in your controller
    }

    @FXML
    void ajouterfeedback(ActionEvent event) {
        try {
            RadioButton selectedLivreur = (RadioButton) groupLivreur.getSelectedToggle();
            RadioButton selectedTemps = (RadioButton) groupTemps.getSelectedToggle();

            int feedbackLivreur = Integer.parseInt(selectedLivreur.getText());
            int feedbackTemps = Integer.parseInt(selectedTemps.getText());

            Service_FeedBack_livraison serviceFeedback = new Service_FeedBack_livraison();
            serviceFeedback.add(new Feedback_livraison(0,feedbackLivreur,feedbackTemps));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Feedback added successfully thank you!");
            ServiceLivraison nliv=new ServiceLivraison();
            nliv.updateLivraisonField(liv.getId(),"Feedback_liv",new Service_FeedBack_livraison().getLastInsertedFeedback().getId());
            alert.showAndWait();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
            Stage newStage = new Stage();

            // Load the FXML file for the new view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/chef/annonce.fxml"));
            Parent root = fxmlLoader.load();

            // Set the scene on the new stage
            newStage.setScene(new Scene(root));

            // Show the new stage
            newStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
