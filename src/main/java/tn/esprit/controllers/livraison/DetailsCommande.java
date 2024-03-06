package tn.esprit.controllers.livraison;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.livraison.CommandeDetail;

import java.util.List;

public class DetailsCommande {

    @FXML
    private TableView<CommandeDetail> tableView;

    @FXML
    private TableColumn<CommandeDetail, String> nomDuPlatColumn;

    @FXML
    private TableColumn<CommandeDetail, Integer> quantiteColumn;

    @FXML
    private TableColumn<CommandeDetail, Integer> prixTotalColumn;

    @FXML
    private TableColumn<CommandeDetail, String> adresseColumn;

    public void setCommande(List<CommandeDetail> commandeDetailsList) {
        ObservableList<CommandeDetail> data = FXCollections.observableArrayList(commandeDetailsList);

        nomDuPlatColumn.setCellValueFactory(new PropertyValueFactory<>("nomDuPlat"));
        quantiteColumn.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        tableView.setItems(data);
    }


}