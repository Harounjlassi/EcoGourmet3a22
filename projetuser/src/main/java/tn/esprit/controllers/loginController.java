package tn.esprit.controllers;
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

public class loginController implements Initializable {
    String r;
    Connection connection;

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
                String req = "select Role from user where Email = '" + Email.getText() + "' AND Password='" + mdp.getText() + "'";
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(req);
                if (rs.next()) {
                    r = rs.getString("Role");
                    System.out.println(r);

                    switch (r) {
                        case "admin":
                            loadFXML("/admin.fxml");
                            break;
                        case "client":
                            loadFXML("/market.fxml");
                            break;
                        case "chef":
                            loadFXML("/chef.fxml");
                            break;
                        case "livreur":
                            loadFXML("/livreur.fxml");
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


}
