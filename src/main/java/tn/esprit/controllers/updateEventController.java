package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import tn.esprit.models.Events;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServiceVotes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class updateEventController {

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

    private Events events;
    File selectedFile = null;


    @FXML
    void UpdateEvent(ActionEvent event) {
        ServiceEvents se = new ServiceEvents();
        ServiceCategories sc = new ServiceCategories();
        ServiceVotes sv = new ServiceVotes();
        if (selectedFile != null) {
           // System.out.println(new Events(0, t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName((String) t_categorie.getValue()), new FileInputStream(selectedFile)));

            // Create a FileInputStream from the selected file
            try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                se.update(new Events(events.getEvent_id(), t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName((String) t_categorie.getValue()), new FileInputStream(selectedFile)));
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

    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }

    public void initData(Events ev) {
        this.events= ev;
    }
    @FXML
    void initialize() {
        ServiceCategories sc= new ServiceCategories();
        ServiceVotes sv= new ServiceVotes();

        t_categorie.getItems().addAll(sc.getAllName());
        ObservableList<String> items = FXCollections.observableArrayList("True", "False");
        t_vote.setItems(items);        //t_vote.getItems().addAll(sc.getAllName());
}
}
