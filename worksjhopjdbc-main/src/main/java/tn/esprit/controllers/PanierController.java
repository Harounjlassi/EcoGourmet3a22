package tn.esprit.controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import tn.esprit.models.Annonce;
import tn.esprit.services.panierService;
import tn.esprit.services.panierService;




import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PanierController {

    private Scene scene;

    private Parent root;
    @FXML
    private BorderPane commandePane;

    @FXML
    private ButtonBar test;

    @FXML
    private TableView<Annonce> annonceTable;

    @FXML
    private TableColumn<Annonce, Integer> idAnnonceColumn;

    @FXML
    private TableColumn<Annonce, String> nomPlatColumn;

    @FXML
    private TableColumn<Annonce, String> descriptionColumn;

    @FXML
    private TableColumn<Annonce, Integer> prixColumn;
    @FXML
    private TableColumn<Annonce, Integer> quantiteColumn;
    @FXML
    private Label prixTotalLabel;

    @FXML
    private TableColumn<Annonce, Void> supprimerColumn;

    @FXML
    private TextField searchField;

    @FXML
    private TextField searchBar;

    private panierService panierService;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField adresseField;

    @FXML
    private VBox commandeForm;

    @FXML
    private ComboBox<String> triComboBox;
    public void initialize() {

        // Initialisez votre service PanierService
        panierService = new panierService();

        supprimerColumn.setCellFactory(new SupprimerAnnonceDuPanier(panierService));


        nomPlatColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNom_annonce());
        });
        descriptionColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getDescription_du_plat());
        });
        prixColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getPrix()).asObject();
        });
        quantiteColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject();
        });

        // Chargez les annonces du panier et peuplez votre TableView
        int idPanier = 1; // Remplacez cela par l'identifiant du panier dont vous souhaitez afficher les annonces
        List<Annonce> annonces = panierService.getAllAnnoncesFromPanier(idPanier);
        annonceTable.getItems().addAll(annonces);
        int prixTotal = panierService.calculerPrixTotalPanier(idPanier);
        prixTotalLabel.setText("" + prixTotal);
        addButtonToTable();

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filtrerAnnonces(newValue); // Appelez la méthode de filtrage avec le nouveau texte de recherche
        });
    }

    private void filtrerAnnonces(String searchText) {
        // Obtenez la liste des annonces à partir du TableView
        ObservableList<Annonce> annonces = annonceTable.getItems();

        // Créez une nouvelle liste pour stocker les annonces filtrées
        ObservableList<Annonce> annoncesFiltrees = FXCollections.observableArrayList();

        if (searchText.isEmpty()) {
            annoncesFiltrees.setAll(panierService.getAllAnnoncesFromPanier(1)); // Remplacez observableListToutesLesAnnonces avec votre liste complète d'annonces
        } else {
            // Parcourez chaque annonce dans la liste
            for (Annonce annonce : annonces) {
                // Vérifiez si le nom de l'annonce contient le texte de recherche (ignorant la casse)
                if (annonce.getNom_annonce().toLowerCase().contains(searchText.toLowerCase())) {
                    // Si oui, ajoutez cette annonce à la liste des annonces filtrées
                    annoncesFiltrees.add(annonce);
                }
            }
        }

        // Mettez à jour le TableView avec la liste des annonces filtrées
        annonceTable.setItems(annoncesFiltrees);
    }

    private void addButtonToTable() {
    }

//    private void trierAnnoncesParPrix() {
//        List<Annonce> annonces = annonceTable.getItems();
//
//        // Comparator pour comparer les annonces par prix
//        Comparator<Annonce> comparator = Comparator.comparingInt(Annonce::getPrix);
//
//        // Trier les annonces selon le comparateur
//        Collections.sort(annonces, comparator);
//        // Mettre à jour le TableView avec les annonces triées
//        annonceTable.setItems(FXCollections.observableArrayList(annonces));
//    }



    @FXML
    private void afficherFormulaireCommande() {
        commandeForm.setVisible(true);
    }

    @FXML
    private void validerCommande() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String adresse = adresseField.getText();
        // Appel à votre méthode d'ajout de commande avec les données saisies
    }



    @FXML
    private void passerCommandeButtonClicked(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/commande.fxml"));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void trierAnnonces() {
        ObservableList<Annonce> annonces = annonceTable.getItems();
        String choixTri = triComboBox.getValue();
        if (choixTri != null) {
            switch (choixTri) {
                case "Prix croissant":
                    trierAnnoncesParPrixCroissant(annonces);
                    break;
                case "Prix décroissant":
                    trierAnnoncesParPrixDecroissant(annonces);
                    break;
                case "Nom A à Z":
                    trierAnnoncesParNomAZ(annonces);
                    break;
                case "Nom Z à A":
                    trierAnnoncesParNomZA(annonces);
                    break;
                default:
                    // Gérer les cas non valides si nécessaire
                    break;
            }
        }
    }

    private void trierAnnoncesParPrixCroissant(ObservableList<Annonce> annonces) {
        annonces.sort(Comparator.comparingInt(Annonce::getPrix));
    }

    private void trierAnnoncesParPrixDecroissant(ObservableList<Annonce> annonces) {
        annonces.sort(Comparator.comparingInt(Annonce::getPrix).reversed());
    }

    private void trierAnnoncesParNomAZ(ObservableList<Annonce> annonces) {
        annonces.sort(Comparator.comparing(Annonce::getNom_annonce));
    }

    private void trierAnnoncesParNomZA(ObservableList<Annonce> annonces) {
        annonces.sort(Comparator.comparing(Annonce::getNom_annonce).reversed());
    }

}
