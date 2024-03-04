package tn.esprit.controllers;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tn.esprit.models.Commande;
import tn.esprit.services.commandeService;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class ListeCommandeController {
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

    public ListeCommandeController() {
    }

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


        TableColumn<Commande, Button> archiveCol = new TableColumn<>("Archive");
        archiveCol.setCellFactory(new ArchiverCommande(commandeService));

        commandeTable.getColumns().add(archiveCol);

        int idClient = loginController.id;
        // Chargez les données des commandes à partir de votre service/commandeService
        // Remplacez `commandes` par la liste des commandes que vous souhaitez afficher
        List<Commande> commandes = commandeService.getAllCommandeNonArchive(idClient); // Utilisez votre service pour obtenir les commandes
        ObservableList<Commande> observableCommandes = FXCollections.observableArrayList(commandes);

        // Ajoutez les commandes à la TableView
        commandeTable.setItems(observableCommandes);

    }
    @FXML
    private ComboBox<String> triComboBox;
    @FXML
    private void trierCommandes() {
        ObservableList<Commande> commandes = commandeTable.getItems();
        String choixTri = triComboBox.getValue();
        if (choixTri != null) {
            switch (choixTri) {
                case "Prix croissant":
                    trierCommandesParPrixCroissant(commandes);
                    break;
                case "Prix décroissant":
                    trierCommandesParPrixDecroissant(commandes);
                    break;

                default:
                    // Gérer les cas non valides si nécessaire
                    break;
            }
        }
    }

    @FXML
    private void archiverCommande(ActionEvent event) {
        Commande commandeSelectionnee = commandeTable.getSelectionModel().getSelectedItem();
        if (commandeSelectionnee != null) {
            int idCommande = commandeSelectionnee.getId_commande(); // Obtenez l'ID de la commande sélectionnée
            commandeService.archiverCommande(idCommande); // Appelez la méthode pour archiver la commande dans la base de données
            // Actualisez la TableView après l'archivage si nécessaire
            initialize(); // Rechargez les données des commandes pour refléter les changements dans la TableView
        } else {
            System.out.println("Veuillez sélectionner une commande à archiver.");
        }
    }

    private void trierCommandesParPrixCroissant(ObservableList<Commande> commandes) {
        commandes.sort(Comparator.comparingInt(Commande::getPrix_total));
    }

    private void trierCommandesParPrixDecroissant(ObservableList<Commande> commandes) {
        commandes.sort(Comparator.comparingInt(Commande::getPrix_total).reversed());
    }
}

