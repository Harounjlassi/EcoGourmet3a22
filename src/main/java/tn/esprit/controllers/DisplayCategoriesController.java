package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tn.esprit.models.Categories;
import tn.esprit.services.ServiceCategories;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayCategoriesController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> combo_table;

    @FXML
    private TableColumn<?, ?> t_ldescription;

    @FXML
    private TableColumn<?, ?> t_name;



    @FXML
    private Button listeCommande;

    @FXML
    private BorderPane eventPane;
    @FXML
    private Button panier;







    @FXML

    public void openAdmin(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));
            Parent admin = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private Button annonce;
    @FXML
    void   comboEvents(){}
    @FXML
    private void openAjouterPersonne(ActionEvent event) {
        try {
            // Load AjouterPersonne.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Annonce.fxml"));
            Parent ajouterPersonnePane = loader.load();
            AnnonceController annonceController = new AnnonceController();

            // Instantiate AjouterAnnonceController
            AjouterAnnonceController ajouterAnnonceController = new AjouterAnnonceController();

            // Set the reference to AnnonceController in AjouterAnnonceController
            ajouterAnnonceController.setAnnonceController(annonceController);

            // Set AjouterPersonne.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(ajouterPersonnePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void openListeCommande(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openPanier(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/panier.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void   openCommandeArchive(){
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commandeArchive.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            eventPane.setCenter(null);
            eventPane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void Decon(ActionEvent actionEvent) {
        try {
            // Load the previous page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Get the stage from the event source
            Stage stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();

            // Set the scene to the previous stage
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private TableColumn<?, ?> t_vote;

    @FXML
    private TableView<Categories> table;
    public ObservableList<Categories> listView = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> items = FXCollections.observableArrayList("Events", "Categories", "Votes");
        combo_table.setItems(items);
        combo_table.setOnAction(event -> {
            String selectedItem = combo_table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                // Perform action based on the selected item
                switch (selectedItem) {
                    case "Events":
                        System.out.println("Events selected. Perform action for Events.");
                        loadDisplayEvent();
                        break;
                    case "Categories":
                        System.out.println("Categories selected. Perform action for Categories.");
                        loadDisplayCategories();
                        break;
                    case "Votes":
                        System.out.println("Votes selected. Perform action for Votes.");
                        loadDisplayVote();
                        break;
                    default:
                        System.out.println("Unknown selection");
                }
            }
        });

        display();
    }
    void loadDisplayCategories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root = loader.load();
            DisplayCategoriesController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void loadDisplayEvent() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            DisplayEventController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void loadDisplayVote() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayVote.fxml"));
        try {
            Parent root = loader.load();
            DisplayVoteController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btn_addCat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterCategories.fxml"));
        try {

            Parent root = loader.load();
            AjouterCategoriesController controller = loader.getController();
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    void display() {
        listView.clear();
        ServiceCategories sc = new ServiceCategories();
        listView.addAll(sc.getAll());
        t_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        t_ldescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        boolean actionColAdded = false;
        for (TableColumn<?, ?> col : table.getColumns()) {
            if (col.getText().equals("Action")) {
                actionColAdded = true;
                break;
            }
        }

        if (!actionColAdded) {
            TableColumn<Categories, Void> actionCol = new TableColumn<>("Action");
            actionCol.setCellFactory(param -> new TableCell<Categories, Void>() {
                private final Button deleteButton = new Button("Supprimer");
                private final Button updateButton = new Button("Modifier");

                {
                    deleteButton.getStyleClass().add("custom-button");
                    updateButton.getStyleClass().add("custom-button");

                    deleteButton.setOnAction(event -> {
                        Categories ct = getTableView().getItems().get(getIndex());
                        System.out.println(ct);
                        sc.delete(ct);
                        display();
                    });
                    updateButton.setOnAction(event -> {
                        Categories ct = getTableView().getItems().get(getIndex());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateCategories.fxml"));
                        try {
                            Parent root = loader.load();
                            UpdateCategoriesController controller = loader.getController();
                            System.out.println(ct);
                            controller.initData(ct);
                            System.out.println(3);

                            table.getScene().setRoot(root);
                        } catch (IOException e) {
                            throw new RuntimeException("Error loading updateEvents.fxml", e);

                        }

                    });
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        HBox buttons = new HBox(deleteButton, updateButton); // Place buttons horizontally
                        buttons.setSpacing(5); // Adjust spacing between buttons if needed
                        setGraphic(buttons);
                    }
                }
            });

            table.getColumns().add(actionCol);
        }
        table.setItems(listView);
    }


}