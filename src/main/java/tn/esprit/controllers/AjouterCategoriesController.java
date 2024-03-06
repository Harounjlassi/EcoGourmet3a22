package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Popup;
import javafx.stage.Screen;
import tn.esprit.models.Categories;
import tn.esprit.services.ServiceCategories;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AjouterCategoriesController implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField t_Cdescription;
    @FXML
    private ComboBox<String> combo_table;

    @FXML
    private Pane t_cdescription;

    @FXML
    private TextField t_cnom;
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
    }

    void loadDisplayCategories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root = loader.load();
            DisplayCategoriesController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            t_Cdescription.getScene().setRoot(root);

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
            t_Cdescription.getScene().setRoot(root);

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
            t_Cdescription.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void AjouterCategories(ActionEvent event) {
        ServiceCategories sc = new ServiceCategories();
        if (t_cnom.getText().isEmpty()) {
            Errorpop("Nom n'est pas définie! \uD83D\uDE16");

        } else if (t_Cdescription.getText().isEmpty()) {
            Errorpop("Description n'est pas définie! \uD83D\uDE16");


        }else if (isNumeric(t_cnom.getText())) { // Check if t_nom is a number
            Errorpop("Nom doit être un texte! \uD83D\uDE16");
        } else if (isNumeric(t_Cdescription.getText())) { // Check if t_nom is a number
            Errorpop("Description doit être un texte! \uD83D\uDE16");
        } else if (t_cnom.getText().length() <= 3) {
            Errorpop("Le nom doit contenir plus de 3 caractères! \uD83D\uDE16");
        } else if (t_Cdescription.getText().length() <= 5) {
            Errorpop("La Description doit contenir plus de 5 caractères! \uD83D\uDE16");
        }else  {
        sc.add(new Categories( 0,t_cnom.getText(), t_Cdescription.getText()));
            succesPop("Ajouté avec succès 😊");


        }
    }
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        return pattern.matcher(str).matches();
    }

    @FXML
    void afficherPersonne(ActionEvent event) {
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root=loader.load();
            DisplayCategoriesController dc= loader.getController();
            //dc.se(nomtf.getText());
            t_Cdescription.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    void ajouter_btn(ActionEvent event) {


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

        popup.show(t_Cdescription.getScene().getWindow(), centerX, centerY);
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

        popup.show(t_Cdescription.getScene().getWindow(), centerX, centerY);
    }

}
