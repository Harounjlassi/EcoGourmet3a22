package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import tn.esprit.models.Commande;
import tn.esprit.services.commandeService;

public class nonArchiverCommande implements Callback<TableColumn<Commande, Button>, TableCell<Commande, Button>> {
    private commandeService commandeService; // Utilisez le bon type pour le service

    public nonArchiverCommande(commandeService commandeService) {
        this.commandeService = commandeService;
    }

    @Override
    public TableCell<Commande, Button> call(TableColumn<Commande, Button> param) {
        return new TableCell<>() {
            final Button nonArchiverButton = new Button("Non Archiver");

            @Override
            protected void updateItem(Button item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(nonArchiverButton);
                    nonArchiverButton.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            Commande commande = getTableView().getItems().get(getIndex());
                            int idCommande = commande.getId_commande();
                            commandeService.nonArchiverCommande(idCommande);
                            getTableView().getItems().remove(commande); // Supprimez la commande de la TableView
                        }
                    });
                }
            }
        };
    }
}
