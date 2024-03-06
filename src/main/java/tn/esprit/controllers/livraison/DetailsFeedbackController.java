package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.Feedback_livraison;

public class DetailsFeedbackController {
    @FXML
    private Label idLabel;
    @FXML
    private Label FB_livreur;
    @FXML
    private Label FB_duration;



    public void setFeedback(Feedback_livraison feedbackLivraison) {
        idLabel.setText("id :" + String.valueOf(feedbackLivraison.getId()));
        FB_livreur.setText("Feedback livreur / 5 :" + feedbackLivraison.getFB_livreur());
        FB_duration.setText("Feedback temps / 5 :" + String.valueOf(feedbackLivraison.getFB_duration()));

    }
}
