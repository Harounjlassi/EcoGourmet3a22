package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;

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
    private TableColumn<TableModel, String> t_nom;
    @FXML
    private Button importImageButton;
    @FXML
    private TableColumn<TableModel, String> t_prenom;



    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }








    public void setLbname(String text) {
    }

    @FXML
    private void openAjouterPersonne(ActionEvent event) {
        try {
            // Load AjouterPersonne.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterPersonnes.fxml"));
            Parent ajouterPersonnePane = loader.load();

            // Set AjouterPersonne.fxml as the center of the mainBorderPane
            annoncePane.setCenter(ajouterPersonnePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void useraction(ActionEvent event) {
        annoncePane.setCenter(null);

    }



}

