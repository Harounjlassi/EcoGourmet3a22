package tn.esprit.controllers.commande;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.models.Personne;

import javax.swing.table.TableModel;
import java.net.URL;
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
