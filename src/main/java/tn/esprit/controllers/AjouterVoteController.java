package tn.esprit.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
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
import javafx.util.Duration;
import tn.esprit.models.Votes;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServiceVotes;
import tn.esprit.services.UserService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterVoteController  implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField t_commentaire;

    @FXML
    private ComboBox<String> t_event;

    @FXML
    private ComboBox<String> combo_table;

    @FXML
    private ComboBox<String> t_user;

    @FXML
    private ComboBox<String> t_value;
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

    @FXML
    void AjouterVote(ActionEvent event) {
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

        } else if (isNumeric(t_commentaire.getText())) { // Check if t_nom is a number
            Errorpop("Commentaire doit être un texte! \uD83D\uDE16");
        } else if (t_commentaire.getText().length() <= 5) { // Adjust this condition as needed
            Errorpop("La location doit contenir plus de 5 caractères! \uD83D\uDE16");
        }else {

        int  rating = Integer.parseInt(t_value.getValue().split(" ")[0]);
        sv.add(new Votes(0, t_commentaire.getText(),rating,su.getIdByName(t_user.getValue()),se.getIdByName(t_event.getValue())));
        //sv.add(new Votes(0,"",se.getIdByName(t_nom.getText()),Integer.parseInt(t_vote.getValue()),25));
        System.out.println("ajoutooé");


            MailController mc = new MailController();
            String to = "taki54214@gmail.com";
            PauseTransition pause = new PauseTransition(Duration.seconds(10));
            Alert confirmationmail = new Alert(Alert.AlertType.CONFIRMATION);
            //Duration duration = Duration.seconds(1);
                succesPop("Ajouté avec succès 😊");

//            pause.setOnFinished(e -> {
//
//                // Code to show alert
//            });
//            pause.play();


                confirmationmail.setTitle("Confirmation par courrier électronique");
                confirmationmail.setHeaderText("Confirmation par courrier électronique");
                confirmationmail.setContentText("Voulez-vous recevoir un e-mail?");
                confirmationmail.show();

            // Add Yes and No buttons to the confirmation dialog
            confirmationmail.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType userChoiceMail = confirmationmail.showAndWait().orElse(ButtonType.NO);
            if (userChoiceMail == ButtonType.YES) {

            // Create a confirmation dialog to ask the user if they want to add an attachment
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirmation de pièce jointe");
            confirmation.setHeaderText("Voulez-vous ajouter une pièce jointe ?");
            confirmation.setContentText("Voulez-vous ajouter une pièce jointe à cet e-mail?");

            // Add Yes and No buttons to the confirmation dialog
            confirmation.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

            // Show the dialog and wait for user response
            ButtonType userChoice = confirmation.showAndWait().orElse(ButtonType.NO);

            // If the user chooses to add an attachment, proceed
            if (userChoice == ButtonType.YES) {

                try {
                // Here, you can add code to prompt the user to select a file to attach
                // For simplicity, let's assume you already have the file path
                    String currentPath = System.getProperty("user.dir");

                mc.mailsend("yhhhgtub", "fonf ieev iuxr pqgl", "yhhhgtub@gmail.com",
                        su.getMailById(su.getIdByName(t_user.getValue())), "Vous avez voté avec succès", "Si vous souhaitez voter ou participer à notre événement, merci de télécharger le fichier ci-dessous",
                        currentPath+"\\events.pdf");
                    succesPop("E-mail envoyé avec succès");

                } catch (Exception e) {
                    // Handle the exception
                    Errorpop("Échec de l'envoi de l'e-mail : " + e.getMessage());
                    System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
                }
            } else {


                try {
                // Send email without attachment
                mc.mailsend("yhhhgtub", "fonf ieev iuxr pqgl", "yhhhgtub@gmail.com",
                        su.getMailById(su.getIdByName(t_user.getValue())), "subjectMail.getText(", "bodyText.getText()",
                        null); // Pass null for attachmentFilePath

                    succesPop("E-mail envoyé avec succès");

                } catch (Exception e) {
                    // Handle the exception
                    Errorpop("Échec de l'envoi de l'e-mail : " + e.getMessage());
                    System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
                }


        }
        }
        }
    }



    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(str).matches();
    }
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

        popup.show(t_user.getScene().getWindow(), centerX, centerY);

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

        popup.show(t_event.getScene().getWindow(), centerX, centerY);
    }


}
