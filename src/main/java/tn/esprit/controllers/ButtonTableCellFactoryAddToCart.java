package tn.esprit.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import tn.esprit.models.Annonce;
import tn.esprit.services.panierService;

public class ButtonTableCellFactoryAddToCart implements Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>> {

    private panierService panierService;
    public void PanierController(panierService panierService) {
        this.panierService = panierService;
    }

    public ButtonTableCellFactoryAddToCart(tn.esprit.services.panierService panierService) {
        this.panierService = panierService;
    }


    @Override
    public TableCell<Annonce, Void> call(TableColumn<Annonce, Void> param) {
        return new TableCell<Annonce, Void>() {
            private final Button addButton = new Button("Ajouter au panier");

            {
                addButton.setOnAction(event -> {
                    Annonce annonce = getTableView().getItems().get(getIndex());
                    // Appelez la méthode de service pour ajouter la commande dans le panier
                    panierService.ajouterAnnonceAuPanier(1,annonce.getId_Annonce());
                    getTableView().refresh(); // Rafraîchissez la vue

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(addButton);
                }
            }
        };
    }







    //    @Override
//    public TableCell<Annonce, Void> call(TableColumn<Annonce, Void> param) {
//        return new TableCell<Annonce, Void>() {
//            private final Button addButton = new Button("Ajouter au panier");
//
//            {
//                addButton.setOnAction(event -> {
//                    Annonce annonce = getTableView().getItems().get(getIndex());
//                    // Ajoutez ici la logique pour traiter l'ajout de cette annonce au panier
//                    System.out.println("Annonce ajoutée au panier : " + annonce.getId_annonce());
//                });
//            }
//
//            @Override
//            protected void updateItem(Void item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty) {
//                    setGraphic(null);
//                } else {
//                    setGraphic(addButton);
//                }
//            }
//        };
//    }
}
