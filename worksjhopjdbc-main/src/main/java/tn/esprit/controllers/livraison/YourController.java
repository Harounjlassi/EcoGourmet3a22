package tn.esprit.controllers.livraison;

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
import javafx.stage.Stage;
import tn.esprit.models.Commande.Commande;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.*;
import tn.esprit.services.User.UserService;
import tn.esprit.services.commande.commandeService;
import tn.esprit.services.livraison.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;


public class YourController {

    @FXML
    private TableView<livraison> table;
    @FXML
    private TableColumn<livraison, Integer> idColumn;
    @FXML
    private TableColumn<livraison, User> livreurColumn;
    @FXML
    private TableColumn<livraison, User> chefColumn;
    @FXML
    private TableColumn<livraison, String> adresse_source;

    @FXML
    private TableColumn<livraison, String> adresse_destination;
    @FXML
    private TableColumn<livraison, Feedback_livraison> Feedback_liv;
    @FXML
    private TableColumn<livraison, Réclamation> réclamation;
    @FXML
    private TableColumn<livraison, Timestamp> time_start;
    @FXML
    private TableColumn<livraison, Commande> commande;
    @FXML
    private TableColumn<livraison,Void> update;
    @FXML
    private TableColumn<livraison,Void> delete;

    @FXML
    private Button tri_par_FB_livreur;
    @FXML
    private Button tri_par_FB_temps;
    @FXML
    private Button tri_par_debut_livraison;
    @FXML
    private Button avec_réclamation;




    private void openNewStage(String fxmlFile) {
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
    }

    @FXML
    public void initialize() {
        table.getStylesheets().add(getClass().getResource("/css/livraison/livraison.css").toExternalForm());

        tri_par_FB_livreur.setOnAction(event -> {

            ServiceLivraison serviceLivraison = new ServiceLivraison();
            List<livraison> sortedList = serviceLivraison.getLivraisonsSortedByFBLivreur();


            ObservableList<livraison> data = FXCollections.observableArrayList(sortedList);
            table.setItems(data);
        });
        tri_par_FB_temps.setOnAction(event -> {

            ServiceLivraison serviceLivraison = new ServiceLivraison();
            List<livraison> sortedList = serviceLivraison.getLivraisonsSortedByFBtemps();

            ObservableList<livraison> data = FXCollections.observableArrayList(sortedList);
            table.setItems(data);
        });
        tri_par_debut_livraison.setOnAction(event -> {

            ServiceLivraison serviceLivraison = new ServiceLivraison();
            List<livraison> sortedList = serviceLivraison.getLivraisonsSortedByTimeStart();


            ObservableList<livraison> data = FXCollections.observableArrayList(sortedList);
            table.setItems(data);
        });
        avec_réclamation.setOnAction(event -> {

            ServiceLivraison serviceLivraison = new ServiceLivraison();
            List<livraison> sortedList = serviceLivraison.getLivraisonsWithReclamation();


            ObservableList<livraison> data = FXCollections.observableArrayList(sortedList);
            table.setItems(data);
        });


        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        livreurColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getLivreur()));
        chefColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getChef()));
        adresse_source.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresse_source()));
        adresse_destination.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getAdresse_destination()));
        Feedback_liv.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getFeedback_liv()));
        réclamation.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getRéclamation()));
        time_start.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getTime_start()));
        commande.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCommande()));
        livreurColumn.setCellFactory(param -> new TableCell<>() {
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
                            System.out.println(item);
                            UserService userService = new UserService();
                            User livreurDetails = userService.getUserById(item.getUserID());
                            System.out.println(livreurDetails);

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/Detailslivreur.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailslivreurController controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setLivreur(livreurDetails);

                            // Create the new stage
                            Stage detailsStage = new Stage();
                            detailsStage.setTitle("Livreur Details");
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
        Feedback_liv.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("feedback");

            @Override
            public void updateItem(Feedback_livraison item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        try {
                            // Get the livreur details
                            Service_FeedBack_livraison serviceFeedBackLivraison = new Service_FeedBack_livraison();
                            System.out.println(item.getId());
                            Feedback_livraison feedbackdetails = serviceFeedBackLivraison.getById(item.getId());

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DetailsFeedback.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailsFeedbackController controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setFeedback(feedbackdetails);

                            // Create the new stage
                            Stage detailsStage = new Stage();
                            detailsStage.setTitle("Feedback Details");
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
        réclamation.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Réclamation");

            @Override
            public void updateItem(Réclamation item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        try {
                            // Get the livreur details
                            ServiceRéclamation serviceRéclamation = new ServiceRéclamation();
                            System.out.println(item.getId());
                            Réclamation réclamationdetails = serviceRéclamation.getById(item.getId());

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DetailsRéclamation.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailsReclamationController controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setFeedback(réclamationdetails);

                            // Create the new stage
                            Stage detailsStage = new Stage();
                            detailsStage.setTitle("Réclamation Details");
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
            public void updateItem(Commande item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item==null) {
                    setGraphic(null);
                } else {
                    btn.setOnAction(event -> {
                        try {
                            // Get the livreur details
                            commandeService serviceCommande = new commandeService();
                            List<CommandeDetail> commandedetails = serviceCommande.getCommandeDetails(item.getId_commande());

                            // Load the FXML file for the new stage
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/DétailsCommande.fxml"));
                            Parent root = fxmlLoader.load();

                            // Get the controller of the new stage
                            DetailsCommande controller = fxmlLoader.getController();

                            // Pass the livreur details to the controller
                            controller.setCommande(commandedetails);

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
        delete.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    deleteButton.setOnAction(event -> {
                        livraison liv = getTableView().getItems().get(getIndex());

                        // Delete the livraison
                        ServiceLivraison serviceLivraison = new ServiceLivraison();
                        System.out.println(liv.getId());
                        serviceLivraison.delete(liv.getId());
                        System.out.println("success");

                        // Update the table view
                        getTableView().getItems().remove(liv);
                        getTableView().refresh();
                    });

                    setGraphic(deleteButton);
                }
                setText(null);
            }
        });


        // Load the data
        table.setItems(FXCollections.observableArrayList(new ServiceLivraison().getAll()));
    }

}
