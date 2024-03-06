package tn.esprit.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import tn.esprit.services.ServiceCategories;
import tn.esprit.services.ServiceVotes;

import javax.swing.table.TableModel;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DisplayPersonneController implements Initializable {


    @FXML
    private ComboBox<String> combo_table;
    @FXML
    private Button userid;
    @FXML
    private Button listeCommande2;
    @FXML
    private Button buttonAdmin;
    @FXML
    private Button panier;
    @FXML
    private Button listeCommande;
    @FXML
    private Button add;
    @FXML
    private BorderPane annoncePane;
    @FXML
    private Button Decon;
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableColumn<TableModel, String> t_action;

    @FXML
    private TableColumn<TableModel, String> t_age;
    @FXML
    private Button annonce;

    @FXML
    private TableColumn<TableModel, String> t_nom;
    @FXML
    private Button importImageButton;
    @FXML
    private TableColumn<TableModel, String> t_prenom;
    private loginController loginControllerInstance;
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
        // Hide the "Annonce" button if the current user role is "livreur"
        if (loginController.role.equals("livreur")) {
            annonce.setVisible(false);
            panier.setVisible(false);
            listeCommande.setVisible(false);
            listeCommande2.setVisible(false);
            Decon.setTranslateY(Decon.getTranslateY() -250);
        }
        if (loginController.role.equals("chef")) {
            panier.setVisible(false);
            listeCommande.setVisible(false);
            listeCommande2.setVisible(false);
            Decon.setTranslateY(Decon.getTranslateY() -185);
        }

        if (!Objects.equals(loginController.role, "admin")) {
            buttonAdmin.setVisible(false);
        } else {
            buttonAdmin.setVisible(true);
        }
    }

//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // Hide the "Annonce" button if the current user role is "livreur"
//        if (loginController.role.equals("livreur")) {
//            annonce.setVisible(false);
//        }
//
//        ServiceCategories sc= new ServiceCategories();
//        ServiceVotes sv= new ServiceVotes();
//
//        combo_table.getItems().addAll(sc.getAllName());
//        ObservableList<String> items = FXCollections.observableArrayList("Events", "Categories", "Votes");
//        combo_table.setItems(items);
//        combo_table.setOnAction(event -> {
//            String selectedItem = combo_table.getSelectionModel().getSelectedItem();
//            if (selectedItem != null) {
//                // Perform action based on the selected item
//                switch (selectedItem) {
//                    case "Events":
//                        System.out.println("Events selected. Perform action for Events.");
//                        loadDisplayEvent();
//                        break;
//                    case "Categories":
//                        System.out.println("Categories selected. Perform action for Categories.");
//                        // Call method or perform action for Categories
//                        loadDisplayCategories();
//
//                        break;
//                    case "Votes":
//                        System.out.println("Votes selected. Perform action for Votes.");
//                        loadDisplayVote();
//                        break;
//                    default:
//                        System.out.println("Unknown selection");
//                }
//            }
//        });
//    }

    void loadDisplayCategories() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayCategories.fxml"));
        try {
            Parent root = loader.load();
            DisplayCategoriesController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            combo_table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




    void loadDisplayEvent() {

        System.out.println("trr");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        try {
            Parent root = loader.load();
            DisplayEventController dc = loader.getController();
            //dc.setLbname(table.getSelectionModel().getSelectedItem().getName());
            combo_table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
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
    void loadDisplayVote() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/DisplayVote.fxml"));
        try {
            Parent root = loader.load();
            DisplayVoteController dc = loader.getController();

            combo_table.getScene().setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void ajouter_btn(ActionEvent event) {

    }






    @FXML

   void  comboEvents(){


    }
    public void setLbname(String text) {
    }

    private int userId;

    // Constructor with userId parameter
    public void AjouterAnnonceController(int userId) {
        this.userId = userId;
    }

    // Setter method to set the reference to the loginController
    public void setLoginController(loginController loginControllerInstance) {
        this.loginControllerInstance = loginControllerInstance;
    }

    // Setter method to set userId
    public void setUserId(int userId) {
        this.userId = userId;
    }





    @FXML
    void useraction(ActionEvent event) {
        annoncePane.setCenter(null);

    }

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
            annoncePane.setCenter(null);
            annoncePane.setCenter(ajouterPersonnePane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void openAnnonces(ActionEvent event) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/annonce.fxml"));
            Parent annonce = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(annonce);
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
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openListeCommande(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ListeCommande.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openCommandeArchive(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commandeArchive.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void openAdmin(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));
            Parent admin = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(null);
            annoncePane.setCenter(admin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    @FXML
    void ExportPdf(ActionEvent event) {

    }



    @FXML
    void chercherNomEvent(ActionEvent event) {

    }

    @FXML
    void comboEvents(ActionEvent event) {

    }




}

