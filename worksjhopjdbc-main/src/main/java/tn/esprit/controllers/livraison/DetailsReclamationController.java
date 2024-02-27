package tn.esprit.controllers.livraison;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import tn.esprit.models.livraison.Réclamation;

public class DetailsReclamationController {
    @FXML
    private Label idLabel;
    @FXML
    private Label date_réclamation;
    @FXML
    private Label cause_réclamation;

    // Other fields...

    public void setFeedback(Réclamation réclamation) {
        idLabel.setText("id :" + String.valueOf(réclamation.getId()));
        date_réclamation.setText("date_réclamation :" + réclamation.getDate_Réclamation());
        cause_réclamation.setText("cause_réclamation :" + réclamation.getCause_Réclamation());

    }
}
