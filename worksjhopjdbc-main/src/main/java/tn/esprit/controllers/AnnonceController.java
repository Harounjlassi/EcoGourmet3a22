package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import tn.esprit.models.Annonce;
import tn.esprit.services.annonceService;
import tn.esprit.services.panierService;
import tn.esprit.services.panierService;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class AnnonceController {
    @FXML
    private TableView<Annonce> annonceTable;
    private annonceService annonceService;
    @FXML
    private TableColumn<Annonce, Void> addToCartColumn;

    @FXML
    private TableColumn<Annonce, Void> deleteColumn;

    // Injectez la classe de service PanierService
    private panierService panierService;

    public void initialize() {

        // Initialisez votre service PanierService
        panierService = new panierService();

        // Configurez la cellule de la colonne "Ajouter au panier"
        addToCartColumn.setCellFactory(new ButtonTableCellFactoryAddToCart(panierService));

        //addToCartColumn.setCellFactory((Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>>)
                //new ButtonTableCellFactoryAddToCart());
        //deleteColumn.setCellFactory(new ButtonTableCellFactoryDelete());


        // Initialisez votre service
        annonceService = new annonceService();

        // Récupérez les annonces et mettez-les dans la table
        List<Annonce> annonces = annonceService.getAllAnnonces();
        ObservableList<Annonce> observableList = FXCollections.observableArrayList(annonces);
        annonceTable.setItems(observableList);
    }
}
