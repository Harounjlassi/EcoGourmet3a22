package tn.esprit.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Commande;
import tn.esprit.services.commandeService;

import java.time.LocalDateTime;
import java.util.List;

public class CommandeArchive {
    @FXML
    private TableView<Commande> commandeTable;

    @FXML
    private TableColumn<Commande, Integer> idCommandeColumn;

    @FXML
    private TableColumn<Commande, Integer> idClientColumn;

    @FXML
    private TableColumn<Commande, Integer> prixTotalColumn;

    @FXML
    private TableColumn<Commande, String> adresseColumn;

    @FXML
    private TableColumn<Commande, Integer> idPanierColumn;

    @FXML
    private TableColumn<Commande, String> etatLivraisonColumn;

    @FXML
    private TableColumn<Commande, LocalDateTime> tempsCommandeColumn;

    private commandeService commandeService;
    public void initialize() {
        commandeService = new commandeService();
        TableColumn<Commande, Integer> indexColumn = new TableColumn<>("N°Commande");
        indexColumn.setSortable(false);
        indexColumn.setCellValueFactory(
                column -> new ReadOnlyObjectWrapper<>(commandeTable.getItems().indexOf(column.getValue()) + 1));
        commandeTable.getColumns().add(0, indexColumn);
        prixTotalColumn.setCellValueFactory(new PropertyValueFactory<>("prix_total"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        etatLivraisonColumn.setCellValueFactory(new PropertyValueFactory<>("etatLivraison"));
        tempsCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("tempsCommande"));


        TableColumn<Commande, Button> archiveCol = new TableColumn<>("non archive");
        archiveCol.setCellFactory(new nonArchiverCommande(commandeService));

        commandeTable.getColumns().add(archiveCol);

        int idClient = loginController.id;

        List<Commande> commandes = commandeService.getAllCommandeArchive(idClient);
        ObservableList<Commande> observableCommandes = FXCollections.observableArrayList(commandes);

        // Ajoutez les commandes à la TableView
        commandeTable.setItems(observableCommandes);

    }


}
