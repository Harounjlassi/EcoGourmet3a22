package tn.esprit.controllers;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.esprit.models.Votes;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServiceVotes;
import tn.esprit.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayVoteController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> combo_table;

    @FXML
    private TableView<Votes> table;

    @FXML
    private TableColumn<?, ?> tv_commentaire;

    @FXML
    private TableColumn<Votes, String> tv_event;

    @FXML
    private TableColumn<Votes, String> tv_user;





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
    private TableColumn<Votes,Double> tv_value;
    public ObservableList<Votes> listView = FXCollections.observableArrayList();

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterVote.fxml"));
        try {
            Parent root = loader.load();
            AjouterVoteController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void display() {
        table.getItems().clear();

        listView.clear();
        ServiceVotes sv = new ServiceVotes();
        listView.addAll(sv.getAll());

        tv_commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaire"));

//        tv_value.setCellValueFactory(new PropertyValueFactory<>("vote_value"));
//        tv_value.setCellFactory(column -> new TableCell<Votes, Double>() {
//            @Override
//            protected void updateItem(Double item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    System.out.println("Item value: " + item); // Debugging print statement
//                    // Clear previous content
//                    getChildren().clear();
//
//                    // Create a Text object to represent the rating stars
//                    Text ratingStars = new Text("★".repeat(item.intValue()));
//                    ratingStars.setFill(Color.GOLD); // Set color of stars
//                    ratingStars.setFont(Font.font(18)); // Set font size of stars
//
//                    // Add the Text object to the cell
//                    setGraphic(ratingStars);
//                }
//            }
//        });

        boolean voteColAdded = false;
        for (TableColumn<?, ?> col : table.getColumns()) {
            if (col.getText().equals("Vote")) {
                voteColAdded = true;
                break;
            }
        }


        if (!voteColAdded) {
            TableColumn<Votes, String> ratingColumn = new TableColumn<>("Vote");


            ratingColumn.setCellValueFactory(cellData -> {
                Double ratingValue = (double) cellData.getValue().getVote_value();
                if (ratingValue == null) {
                    return null;
                } else {
                    return new SimpleStringProperty("★".repeat(ratingValue.intValue()));
                }
            });
            ratingColumn.setCellFactory(column -> new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null); // Clear the graphic when item is null or empty
                    } else {
                        Text ratingStars = new Text(item);
                        ratingStars.setFill(Color.GOLD); // Set color of stars
                        ratingStars.setFont(Font.font(18)); // Set font size of stars
                        setGraphic(ratingStars);
                    }
                }
            });

            table.getColumns().add(ratingColumn);

        }






        ServiceEvents se = new ServiceEvents();
        UserService su = new UserService();
        //tv_event.setCellValueFactory(new PropertyValueFactory<>("event_id"));
        tv_event.setCellValueFactory(cellData -> {
            Votes vote = cellData.getValue();
            int eventId = vote.getEvent_id();
            System.out.println(eventId);// Assuming getEventId() returns the event ID associated with the vote
            String eventName = se.fetchEventNameFromDatabase(eventId);
            System.out.println(eventName);
            return new SimpleStringProperty(eventName);
        });

        tv_user.setCellValueFactory(cellData -> {
            Votes vote =  cellData.getValue();
            int UserId = vote.getUser_id();
            System.out.println(UserId);
            String userName = su.fetchuserNameFromDatabase(UserId);
            System.out.println(userName);
            return new SimpleStringProperty(userName);
        });





        boolean actionColAdded = false;
        for (TableColumn<?, ?> col : table.getColumns()) {
            if (col.getText().equals("Action")) {
                actionColAdded = true;
                break;
            }
        }
        if (!actionColAdded) {
            TableColumn<Votes, Void> actionCol = new TableColumn<>("Action");
            actionCol.setCellFactory(param -> new TableCell<Votes, Void>() {
                private final Button deleteButton = new Button("Supprimer");
                private final Button updateButton = new Button("Modifier");

                {
                    deleteButton.getStyleClass().add("custom-button");
                    updateButton.getStyleClass().add("custom-button");

                    deleteButton.setOnAction(event -> {
                        Votes v = getTableView().getItems().get(getIndex());
                        System.out.println(v);
                        sv.delete(v);
                        table.getItems().remove(v);


                        display();
                    });
                    updateButton.setOnAction(event -> {
                        Votes v= getTableView().getItems().get(getIndex());
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UpdateVote.fxml"));
                        try {
                            Parent root = loader.load();
                            UpdateVoteController controller = loader.getController();
                            controller.initData(v);

                            System.out.println(v.getEvent_id());
                            System.out.println(3);

                            // Setting the root of the scene to the loaded FXML
                            table.getScene().setRoot(root);
                        } catch (IOException e) {
                            // Throw a more descriptive exception, capturing the root cause
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