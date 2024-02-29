package tn.esprit.controllers.User;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class mdpController implements Initializable {
    public TextField emailField;
    public Button recoverButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic, if any
    }

    public void recoverPassword() {
        // Method to handle password recovery process
        String emailOrUsername = emailField.getText();

        // Validate the email or username and initiate the password recovery process
        // You can implement the password recovery logic here, such as sending a recovery email

        // For demonstration, let's just print the email or username for now
        System.out.println("Initiating password recovery for: " + emailOrUsername);
    }
}
