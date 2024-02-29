package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import tn.esprit.models.User;
import tn.esprit.services.UserService;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupController implements Serializable {
    public TextField tel;
    Connection connection;
    public TextField Nom;
    public TextField Email;
    public Button inscription;
    public TextField Prenom;
    public PasswordField mdp; // Change to PasswordField for secure password input
    public SplitMenuButton Role;
    public Label back;
    public ToggleButton showPasswordToggleButton; // ToggleButton to show/hide password
    UserService uti = new UserService();

    public void inscription(ActionEvent actionEvent) {
        // Basic input validation
        if (Nom.getText().isEmpty() || Prenom.getText().isEmpty() || Email.getText().isEmpty() || mdp.getText().isEmpty() || tel.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez remplir tous les champs.");
            return;
        }

        // Validate email format
        if (!isValidEmail(Email.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Veuillez entrer une adresse email valide.");
            return;
        }

        // Validate password strength
        if (!isValidPassword(mdp.getText())) {
            showAlert(Alert.AlertType.ERROR, "Error", "Le mot de passe doit contenir au moins 8 caractères, une majuscule, une minuscule et un chiffre.");
            return;
        }

        // Validate phone number
        if (!isValidTel(tel.getText())) {
            return;
        }

        connection = MyDataBase.getInstance().getCnx();
        User u = new User();
        u.setEmail(Email.getText());
        u.setNom(Nom.getText());
        u.setPrenom(Prenom.getText());
        u.setRole(Role.getText());
        u.setPassword(mdp.getText());
        u.setNumero(tel.getText());
        uti.add(u);
        showAlert(Alert.AlertType.CONFIRMATION, "Success", "Votre inscription est bien enregistrée !");
        redirectToLogin();
    }

    public void back(MouseEvent actionEvent) {
        redirectToLogin();
    }

    public void chef(ActionEvent actionEvent) {
        Role.setText("chef");
    }

    public void client(ActionEvent actionEvent) {
        Role.setText("client");
    }

    public void livreur(ActionEvent actionEvent) {
        Role.setText("livreur");
    }

    public void togglePasswordVisibility(ActionEvent event) {
        if (showPasswordToggleButton.isSelected()) {
            // Show the password
            mdp.setPromptText(mdp.getText());
            mdp.setText("");
            mdp.setDisable(true);
        } else {
            // Hide the password
            mdp.setText(mdp.getPromptText());
            mdp.setPromptText("");
            mdp.setDisable(false);
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }


    private boolean isValidEmail(String email) {
        // Use a simple regular expression for email validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }

    private boolean isValidPassword(String password) {
        // Password must contain at least 8 characters, one uppercase letter, one lowercase letter, and one digit
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$";
        return password.matches(passwordRegex);
    }

    private boolean isValidTel(String tel) {
        // Validate phone number length
        if (tel.length() != 8) {
            showAlert(Alert.AlertType.ERROR, "Error", "Le numéro de téléphone doit contenir exactement 8 chiffres.");
            return false;
        }
        return true;
    }

    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            inscription.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
