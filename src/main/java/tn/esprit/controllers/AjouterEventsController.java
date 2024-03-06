package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Screen;
import tn.esprit.models.Events;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceEvents;
import tn.esprit.services.ServiceVotes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class AjouterEventsController    implements Initializable {

    @FXML
    private Button add;

    @FXML
    private ComboBox<String> t_categorie;

    @FXML
    private DatePicker t_date;

    @FXML
    private TextField t_location;

    @FXML
    private TextField t_nom;

    @FXML
    private Button t_photo;
    @FXML
    private ComboBox<String> combo_table;


    File selectedFile = null;

    private final ServiceEvents ps = new ServiceEvents();

    @Override
    public void initialize(URL url, ResourceBundle rb) {







        ServiceCategories sc= new ServiceCategories();
        ServiceVotes sv= new ServiceVotes();

        t_categorie.getItems().addAll(sc.getAllName());
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
            t_nom.getScene().setRoot(root);

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
            t_nom.getScene().setRoot(root);

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
            t_nom.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    @FXML
    void ajouter_btn(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AjouterEvents.fxml"));
        try {
            Parent root = loader.load();
            AjouterEventsController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_nom.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML

    void AjouterEvent(ActionEvent event) throws FileNotFoundException {
        ServiceEvents se = new ServiceEvents();
        ServiceCategories sc = new ServiceCategories();
        ServiceVotes sv = new ServiceVotes();


// Inside the method where you want to perform the check
    if( t_nom.getText().isEmpty()){
            Errorpop("Nom n'est pas définie! \uD83D\uDE16");


        }else if(t_date.getValue() == null){

            Errorpop("Date n'est pas définie! \uD83D\uDE16");


        }else if(t_categorie.getValue() == null){
            Errorpop("Categorie n'est pas définie! \uD83D\uDE16");

        }  else     if(t_location.getText().isEmpty()) {
        Errorpop("Location n'est pas définie! \uD83D\uDE16");

    }else if(selectedFile == null){
        Errorpop("Le Fichier n'est pas définie! \uD83D\uDE16");

    } else if (isNumeric(t_nom.getText())) { // Check if t_nom is a number
        Errorpop("Nom doit être un texte! \uD83D\uDE16");
    } else if (t_nom.getText().length() <= 3) {
        Errorpop("Le nom doit contenir plus de 3 caractères! \uD83D\uDE16");
    }else if (t_date.getValue().isBefore(LocalDate.now())) {
        Errorpop("La date ne peut pas être dans le Pasee! \uD83D\uDE16");
    } else if (t_location.getText().length() <= 5) { // Adjust this condition as needed
        Errorpop("La location doit contenir plus de 5 caractères! \uD83D\uDE16");
    } else {


            System.out.println(new Events(0, t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName(t_categorie.getValue()), new FileInputStream(selectedFile)));

            try (FileInputStream fileInputStream = new FileInputStream(selectedFile)) {
                se.add(new Events(0, t_nom.getText(), java.sql.Date.valueOf(t_date.getValue()), t_location.getText(), sc.getIdByName(t_categorie.getValue()), new FileInputStream(selectedFile)));
                //sv.add(new Votes(0,"",se.getIdByName(t_nom.getText()),Integer.parseInt(t_vote.getValue()),25));
                System.out.println("ajouté");
                succesPop("Ajouté avec succès 😊");

            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception

        }}
    }
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(str).matches();
    }

    private void setupTextFormatter(String regexPattern, String errorMessage, TextField textField) {
        // Create a filter based on the regular expression
        UnaryOperator<TextFormatter.Change> filter = change -> {
            if (Pattern.matches(regexPattern, change.getControlNewText())) {
                return change; // Allow the change if it matches the regular expression
            } else {
                Errorpop(errorMessage);
                textField.clear(); // Clear the text field

                return null; // Otherwise, ignore the change
            }
        };

        // Create the TextFormatter with the filter
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);

        // Apply the TextFormatter to the TextField
        textField.setTextFormatter(textFormatter);
    }
    @FXML
    void addPicture( ActionEvent event  ) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        fileChooser.setTitle("Choose an image");
        selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            System.out.println(selectedFile.toString());
        }
    }





    @FXML
    void afficherPersonne(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            DisplayEventController dc = loader.getController();
            //dc.se(nomtf.getText());
            t_nom.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void initialize() {



        //assert ageTF != null : "fx:id=\"ageTF\" was not injected: check your FXML file 'AjouterEvents.fxml'.";

        //assert prenomTF != null : "fx:id=\"prenomTF\" was not injected: check your FXML file 'AjouterEvents.fxml'.";

    }

    public void setLbname(String name) {

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

        popup.show(t_nom.getScene().getWindow(), centerX, centerY);
    }
    public void Errorpop(String s)
    {
        Label label = new Label(s); // "Successfully added!" in French
        label.setStyle("-fx-background-color:  #FF5733;-fx-text-fill: white; -fx-padding: 20px; -fx-font-size: 24px;"); // Increase font size and padding
        StackPane stackPane = new StackPane(label);
        stackPane.setPrefSize(400, 400); // Set preferred size of the StackPane

        Screen screen = Screen.getPrimary();
        double centerX = (screen.getVisualBounds().getWidth() - stackPane.getPrefWidth()) / 2;
        double centerY = (screen.getVisualBounds().getHeight() - stackPane.getPrefHeight()) / 2;

        Popup popup = new Popup();
        popup.getContent().add(stackPane);
        popup.setAutoHide(true);

        popup.show(t_nom.getScene().getWindow(), centerX, centerY);
    }


}
