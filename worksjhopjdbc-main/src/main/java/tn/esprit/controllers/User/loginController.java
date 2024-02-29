package tn.esprit.controllers.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import tn.esprit.utils.MyDataBase;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class loginController implements Initializable {
    String r;
    Connection connection;

    public Label mdpoublier;
    public TextField Email;
    public Button connexion;
    public Label cnx;
    public PasswordField mdp;
    @FXML
    private ToggleButton showPasswordToggleButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MyDataBase.getInstance().getCnx();
    }

    public void connexion(ActionEvent actionEvent) {
        if (!Email.getText().isEmpty() && !mdp.getText().isEmpty()) {
            connection = MyDataBase.getInstance().getCnx();

            try {
                String req = "select Role from user where Email = '" + Email.getText() + "' AND Password='" + mdp.getText() + "'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(req);
                if (rs.next()) {
                    r = rs.getString("Role");
                    System.out.println(r);

                    switch (r) {
                        case "admin":
                            loadFXML("/User/admin.fxml");
                            break;
                        case "client":
                            loadFXML("/User/market.fxml");
                            break;
                        case "chef":
                            loadFXML("/User/displayEvent.fxml");
                            break;
                        case "livreur":
                            loadFXML("/User/livreur.fxml");
                            break;
                    }
                } else {
                    showAlert("Vérifiez vos paramètres s'il vous plaît !");
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        } else {
            showAlert("Remplissez vos coordonnées s'il vous plaît !");
        }
    }

    public void inscription(MouseEvent mouseEvent) {
        loadFXML("/User/signup.fxml");
    }

    public void mdpoubliee(MouseEvent mouseEvent) {
        // Implement password recovery logic here
        // This method is called when the user clicks on "Mot de passe oublié ?" label
        // You can show a password recovery interface or dialog here
        // For demonstration purpose, let's show an alert
        showAlert("Forgot Password clicked! Implement password recovery logic here.");
    }

    // Helper method to load FXML file
    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            connexion.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Helper method to show alert
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.show();
    }

    // Method to toggle password visibility
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
}
