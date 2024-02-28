package tn.esprit.controllers;

import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import tn.esprit.models.Annonce;
import tn.esprit.services.panierService;

public class SupprimerAnnonceDuPanier implements Callback<TableColumn<Annonce, Void>, TableCell<Annonce, Void>> {
    private final panierService panierService;

    public SupprimerAnnonceDuPanier(panierService panierService) {
        this.panierService = panierService;
    }
    @Override
    public TableCell<Annonce, Void> call(TableColumn<Annonce, Void> param) {
        return new TableCell<Annonce, Void>() {
            private final Button deleteButton = new Button("Supprimer");

            {
                deleteButton.setOnAction(event -> {
                    Annonce annonce = getTableView().getItems().get(getIndex());
                    panierService.supprimerAnnonceDuPanier(1,annonce.getId_annonce()); // Appelez la méthode de service pour supprimer l'annonce
                    getTableView().getItems().remove(annonce); // Supprimez l'élément de la liste affichée dans la TableView
                    getTableView().refresh(); // Mettez à jour la vue
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        };
    }
}
