package tn.esprit.controllers.User;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import tn.esprit.controllers.SignupController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class chefController {
    public Button back;
    public void back (ActionEvent actionEvent) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/User/login.fxml"));
            Parent root = loader.load();
            back.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
