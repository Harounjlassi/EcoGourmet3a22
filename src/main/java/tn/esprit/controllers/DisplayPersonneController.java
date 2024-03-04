package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayPersonneController implements Initializable {
    @FXML
    private Button userid;
    @FXML
    private Button add;
    @FXML
    private BorderPane annoncePane;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableColumn<TableModel, String> t_action;

    @FXML
    private TableColumn<TableModel, String> t_age;
    @FXML
    private Button annonce;

    @FXML
    private TableColumn<TableModel, String> t_nom;
    @FXML
    private Button importImageButton;
    @FXML
    private TableColumn<TableModel, String> t_prenom;
    private loginController loginControllerInstance;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Hide the "Annonce" button if the current user role is "livreur"
        if (loginController.role.equals("livreur")) {
            annonce.setVisible(false);
        }
    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }








    public void setLbname(String text) {
    }

    private int userId;

    // Constructor with userId parameter
    public void AjouterAnnonceController(int userId) {
        this.userId = userId;
    }

    // Setter method to set the reference to the loginController
    public void setLoginController(loginController loginControllerInstance) {
        this.loginControllerInstance = loginControllerInstance;
    }

    // Setter method to set userId
    public void setUserId(int userId) {
        this.userId = userId;
    }





    @FXML
    void useraction(ActionEvent event) {
        annoncePane.setCenter(null);

    }

    @FXML
    private void openAjouterPersonne(ActionEvent event) {
        try {
            // Load AjouterPersonne.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Annonce.fxml"));
            Parent ajouterPersonnePane = loader.load();
            AnnonceController annonceController = new AnnonceController();

            // Instantiate AjouterAnnonceController
            AjouterAnnonceController ajouterAnnonceController = new AjouterAnnonceController();

            // Set the reference to AnnonceController in AjouterAnnonceController
            ajouterAnnonceController.setAnnonceController(annonceController);

            // Set AjouterPersonne.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(ajouterPersonnePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openAnnonces(ActionEvent event) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/annonce.fxml"));
            Parent annonce = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(annonce);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openPanier(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panier.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListeCommande(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCommandeArchive(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commandeArchive.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

