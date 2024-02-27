package tn.esprit.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;

import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.utils.MyDataBase;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DisplayPersonneController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TableColumn<TableModel, String> t_action;

    @FXML
    private TableColumn<TableModel, String> t_age;

    @FXML
    private TableColumn<TableModel, String> t_nom;

    @FXML
    private TableColumn<TableModel, String> t_prenom;

    @FXML
    private TableView<Personne> table;

    public ObservableList<Personne> listView = FXCollections.observableArrayList();
    Personne p = null;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

display();
    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }

    void display() {
//        listView.clear();
//
//        ServicePersonne pcd = new ServicePersonne();
//        listView.addAll(pcd.getAll());
//        t_prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
//        t_nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
//        t_age.setCellValueFactory(new PropertyValueFactory<>("age"));
//
//        table.setItems(listView);

    }






    public void setLbname(String text) {
    }
}
