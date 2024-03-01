package tn.esprit.controllers.commande;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tn.esprit.models.Annonce.Annonce;
import tn.esprit.services.Annonce.annonceService;
import tn.esprit.services.commande.panierService;

import java.util.List;

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

        // Initialisez votre service
        annonceService = new annonceService();

        // Récupérez les annonces et mettez-les dans la table
        List<Annonce> annonces = tn.esprit.services.Annonce.annonceService.getAllAnnonces();
        ObservableList<Annonce> observableList = FXCollections.observableArrayList(annonces);
        annonceTable.setItems(observableList);
    }
}
