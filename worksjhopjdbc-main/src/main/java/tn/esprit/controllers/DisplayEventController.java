package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DisplayEventController {

    @FXML
    private Button add;

    @FXML
    private TableColumn<?, ?> t_categorie;

    @FXML
    private TableColumn<?, ?> t_date;

    @FXML
    private TableColumn<?, ?> t_location;

    @FXML
    private TableColumn<?, ?> t_name;

    @FXML
    private TableColumn<?, ?> t_photo;

    @FXML
    private TableColumn<?, ?> t_vote;

    @FXML
    private TableView<?> table;

    @FXML
    void ajouter_btn(ActionEvent event) {

    }

}
