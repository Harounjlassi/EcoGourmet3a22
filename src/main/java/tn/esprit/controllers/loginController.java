package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import tn.esprit.controllers.AjouterAnnonceController;

public class loginController implements Initializable {
    String r;
    Connection connection;
    public int userId; // Declare userId field

    // Getter and setter for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public static int id;
    public static String role;
    public static String nom;
    public static String prenom;
    public Label mdpoublier;
    public TextField Email;
    public Button connexion;
    public Label cnx;
    public PasswordField mdp;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MyDataBase.getInstance().getCnx();
    }

    public void connexion(ActionEvent actionEvent) {
        if (!Email.getText().isEmpty() && !mdp.getText().isEmpty()) {
            connection = MyDataBase.getInstance().getCnx();

            try {
                String req = "select UserID, Role,Nom ,Prenom from user where Email = '" + Email.getText() + "' AND Password='" + mdp.getText() + "'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(req);
                if (rs.next()) {
                    userId = rs.getInt("UserID");
                    role=rs.getString("Role");
                    id = rs.getInt("UserID"); // Store the userId
                    r = rs.getString("Role");
                    nom=rs.getString("Nom");
                    prenom=rs.getString("Prenom");
                    System.out.println(r);

                    switch (r) {
                        case "admin":
                            loadFXML("/displayEvent.fxml");
                            break;
                        case "client":
                            loadFXML("/displayEvent.fxml");
                            break;
                        case "chef":
                            loadFXML("/displayEvent.fxml");
                            break;
                        case "livreur":
                            loadFXML("/displayEvent.fxml");
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
        loadFXML("/signup.fxml");
    }

    public void mdpoubliee(MouseEvent mouseEvent) {
        // Implement password recovery logic here
        // This method is called when the user clicks on "Mot de passe oublié ?" label
        // You can show a password recovery interface or dialog here
        // For demonstration purpose, let's show an alert
        showAlert("Forgot Password clicked! Implement password recovery logic here.");
    }

    // Helper method to load FXML file
    // Helper method to load FXML file
    @FXML
    private void loadFXML(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            // Get the controller associated with the loaded FXML
            Object controller = loader.getController();

            // Check if the loaded controller is DisplayPersonneController


            // Set the root node of the scene
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

    public void togglePasswordVisibility(ActionEvent actionEvent) {
    }
}
