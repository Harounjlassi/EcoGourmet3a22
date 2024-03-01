package tn.esprit.controllers.livraison.livreur;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.controllers.livraison.*;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.Feedback_livraison;
import tn.esprit.models.livraison.Réclamation;
import tn.esprit.models.livraison.commande;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.User.UserService;
import tn.esprit.controllers.User.loginController;
import tn.esprit.services.livraison.ServiceCommande;
import tn.esprit.services.livraison.ServiceLivraison;
import tn.esprit.services.livraison.ServiceRéclamation;
import tn.esprit.services.livraison.Service_FeedBack_livraison;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

public class LivreurController {
    @FXML
    private TableView<livraison> table;
    @FXML
    private TableColumn<livraison, User> chefColumn;
    @FXML
    private TableColumn<livraison, String> adresse_source;

    @FXML
    private TableColumn<livraison, String> adresse_destination;
    @FXML
    private TableColumn<livraison, tn.esprit.models.livraison.commande> commande;
    @FXML
    private TableColumn<livraison,Void> accepter;

    @FXML
    private Button tri_par_FB_livreur;
    @FXML
    private Button tri_par_FB_temps;
    @FXML
    private Button tri_par_debut_livraison;
    @FXML
    private Button avec_réclamation;




    /*private void openNewStage(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void openAddLivraisonForm(ActionEvent event) {
        openNewStage("/livraison/add_livraison.fxml");
    }*/

    @FXML
    public void initialize() {
        table.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());


        chefColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getChef()));
        adresse_source.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresse_source()));
        adresse_destination.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresse_destination()));
        commande.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCommande()));
        chefColumn.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Show Details");

            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                } else {
                    btn.setText(item.getNom());
                    btn.setOnAction(event -> {
                        try {
                            // Get the livreur details
                            UserService serviceChef = new UserService();
                            User chefdetails = serviceChef.getUserById(item.getUserID());

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DetailsChef.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailsChefController controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setChef(chefdetails);

                            // Create the new stage
                            Stage detailsStage = new Stage();
                            detailsStage.setTitle("Chef Details");
                            detailsStage.setScene(new Scene(root));

                            // Show the new stage
                            detailsStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    setGraphic(btn);
                }
                setText(null);
            }
        });
        commande.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("commande");

            @Override
            public void updateItem(commande item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        try {
                            // Get the livreur details
                            ServiceCommande serviceCommande = new ServiceCommande();
                            System.out.println(item.getId());
                            commande commandedetails = serviceCommande.getById(item.getId());

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DétailsCommande.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailsCommande controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setFeedback(commandedetails);

                            // Create the new stage
                            Stage detailsStage = new Stage();
                            detailsStage.setTitle("Commande Details");
                            detailsStage.setScene(new Scene(root));

                            // Show the new stage
                            detailsStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    setGraphic(btn);
                }
                setText(null);
            }
        });
        // Add cell value factories for other columns
        accepter.setCellFactory(param -> new TableCell<>() {
            private final Button accepterbutton = new Button("Accepter");


            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    accepterbutton.setOnAction(event -> {
                        livraison liv = getTableView().getItems().get(getIndex());

                        // Delete the livraison
                        ServiceLivraison serviceLivraison = new ServiceLivraison();
                        System.out.println(liv.getId());
                        serviceLivraison.updateLivraisonField(liv.getId(),"livreur",1/*loginController.logged_in_user.getUserID()*/);
                        System.out.println("success");

                        // Update the table view

                        getTableView().refresh();
                        try {
                            // Load the FXML file for the new view
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/livraison/livreur/livraison.fxml"));

                            // Create the Scene
                            Scene scene = new Scene(loader.load());

                            // Get the controller of the new view
                            DeliveryController controller = loader.getController();

                            // Pass the livraison object to the controller
                            controller.setLivraison(liv);

                            // Create the Stage
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    setGraphic(accepterbutton);
                }
                setText(null);
            }
        });


        // Load the data
        table.setItems(FXCollections.observableArrayList(new ServiceLivraison().getLivraisonsWithNullLivreur()));
    }
}
