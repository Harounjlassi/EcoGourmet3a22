package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Screen;
import tn.esprit.models.Votes;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServiceVotes;
import tn.esprit.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UpdateVoteController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField t_commentaire;
    @FXML
    private ComboBox<String> combo_table;

    @FXML
    private ComboBox<String> t_event;


    @FXML
    private ComboBox<String> t_user;

    @FXML
    private ComboBox<String> t_value;
     private Votes vote;

    @FXML
    void UpdateVote(ActionEvent event) {
        ServiceEvents se = new ServiceEvents();
        ServiceCategories sc = new ServiceCategories();
        ServiceVotes sv = new ServiceVotes();
        UserService su= new UserService();
        if (t_commentaire.getText()== null) {
            Errorpop("Commentaire n'est pas définie! \uD83D\uDE16");

        }else if ( t_value.getValue()== null){
            Errorpop("Vote Value n'est pas définie! \uD83D\uDE16");

        } else if  (t_user.getValue()== null  ) {
            Errorpop("User n'est pas définie! \uD83D\uDE16");

        } else if  (t_event.getValue()== null){
            Errorpop("Evenement n'est pas définie! \uD83D\uDE16");

        } else {
        int  rating = Integer.parseInt(t_value.getValue().split(" ")[0]);

        sv.update(new Votes(vote.getId(), t_commentaire.getText(),rating,su.getIdByName(t_user.getValue()),se.getIdByName(t_event.getValue())));
        //sv.add(new Votes(0,"",se.getIdByName(t_nom.getText()),Integer.parseInt(t_vote.getValue()),25));
        System.out.println("ajouté");
        succesPop("mettre à jour avec succès😊");

    }}

    @FXML
    void afficherVote(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayVote.fxml"));
        try {
            Parent root = loader.load();
            DisplayVoteController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_event.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }

    public void initData(Votes v) {
        this.vote = v;
    }
    @FXML
    void initialize() {

    }
        public void succesPop(String s){
            Label label = new Label(s); // "Successfully added!" in French
            label.setStyle("-fx-background-color:  #7A8727;-fx-text-fill: #FFB347; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
            StackPane stackPane = new StackPane(label);
            stackPane.setPrefSize(400, 200); // Set preferred size of the StackPane

            Screen screen = Screen.getPrimary();
            double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
            double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

            Popup popup = new Popup();
            popup.getContent().add(stackPane);
            popup.setAutoHide(true);

            popup.show(t_commentaire.getScene().getWindow(), centerX, centerY);
        }
        public void Errorpop(String s)
        {
            Label label = new Label(s); // "Successfully added!" in French
            label.setStyle("-fx-background-color:  #FF5733;-fx-text-fill: white; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
            StackPane stackPane = new StackPane(label);
            stackPane.setPrefSize(400, 200); // Set preferred size of the StackPane

            Screen screen = Screen.getPrimary();
            double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
            double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

            Popup popup = new Popup();
            popup.getContent().add(stackPane);
            popup.setAutoHide(true);

            popup.show(t_commentaire.getScene().getWindow(), centerX, centerY);
        }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        t_value.getItems().addAll("1 star", "2 stars", "3 stars", "4 stars", "5 stars");

        t_value.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    int rating = Integer.parseInt(item.split(" ")[0]);
                    HBox stars = new HBox();
                    for (int i = 0; i < rating; i++) {
                        Text star = new Text("\u2605"); // Unicode for star
                        star.setFill(Color.GOLD); // Set color to gold
                        stars.getChildren().add(star);                    }
                    setText(null);
                    setGraphic(stars);
                }
            }
        });

        t_value.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    int rating = Integer.parseInt(item.split(" ")[0]);
                    HBox stars = new HBox();
                    for (int i = 0; i < rating; i++) {
                        Text star = new Text("\u2605"); // Unicode for star
                        star.setFill(Color.GOLD); // Set color to gold
                        stars.getChildren().add(star);

                    }
                    setText(null);
                    setGraphic(stars);
                }
            }
        });


        ServiceCategories sc= new ServiceCategories();
        ServiceVotes sv= new ServiceVotes();
        UserService su= new UserService();
        ServiceEvents se=new ServiceEvents();

        t_user.getItems().addAll(su.getAllName());
        t_event.getItems().addAll(se.getAllName());
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
                        // Call method or perform action for Categories
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
    }
    void loadDisplayCategories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root = loader.load();
            DisplayCategoriesController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_event.getScene().setRoot(root);

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
            t_commentaire.getScene().setRoot(root);

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
            t_commentaire.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
