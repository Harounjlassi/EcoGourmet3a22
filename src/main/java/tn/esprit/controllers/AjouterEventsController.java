package tn.esprit.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.models.Events;
import tn.esprit.models.Personne;
import tn.esprit.models.Votes;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServicePersonne;
import tn.esprit.services.ServiceVotes;

public class AjouterEventsController {

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> t_categorie;

    @FXML
    private DatePicker t_date;

    @FXML
    private TextField t_location;

    @FXML
    private TextField t_nom;

    @FXML
    private Button t_photo;

    @FXML
    private ComboBox<String> t_vote;
    File selectedFile = null;

    private final ServiceEvents ps = new ServiceEvents();
    @FXML
    void ajouter_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterEvents.fxml"));
        try {
            Parent root = loader.load();
            AjouterEventsController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_nom.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML

    void AjouterEvent(ActionEvent event) throws FileNotFoundException {
        ServiceEvents se = new ServiceEvents();
        ServiceCategories sc = new ServiceCategories();
        ServiceVotes sv = new ServiceVotes();
        if (selectedFile != null) {
            System.out.println(new Events(0, t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName(t_categorie.getValue()), new FileInputStream(selectedFile)));

            // Create a FileInputStream from the selected file
            try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                se.add(new Events(0, t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName(t_categorie.getValue()), new FileInputStream(selectedFile)));
                //sv.add(new Votes(0,"",se.getIdByName(t_nom.getText()),Integer.parseInt(t_vote.getValue()),25));
                System.out.println("ajouté");
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception
            }
        }
    }


    @FXML
    void addPicture( ActionEvent event  ) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        fileChooser.setTitle("Choose an image");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(selectedFile.toString());
        }
    }





    @FXML
    void afficherPersonne(ActionEvent event) {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root=loader.load();
            DisplayEventController dc= loader.getController();
            //dc.se(nomtf.getText());
            t_nom.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {
        ServiceCategories sc= new ServiceCategories();
        ServiceVotes sv= new ServiceVotes();

        t_categorie.getItems().addAll(sc.getAllName());
        ObservableList<String> items = FXCollections.observableArrayList("True", "False");
        t_vote.setItems(items);        //t_vote.getItems().addAll(sc.getAllName());

        //assert ageTF != null : "fx:id=\"ageTF\" was not injected: check your FXML file 'AjouterEvents.fxml'.";

        //assert prenomTF != null : "fx:id=\"prenomTF\" was not injected: check your FXML file 'AjouterEvents.fxml'.";

    }

    public void setLbname(String name) {

    }


}
