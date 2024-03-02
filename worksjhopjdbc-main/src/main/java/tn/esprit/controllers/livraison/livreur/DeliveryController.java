package tn.esprit.controllers.livraison.livreur;
import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import tn.esprit.controllers.livraison.AddRéclamation;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.User.UserService;
import tn.esprit.services.livraison.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class DeliveryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        button2.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        button3.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        reclamation.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label2.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label3.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label4.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        ch.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        cl.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        ServiceLivraison sliv=new ServiceLivraison();
        livraison liv = sliv.getLastInsertedLivraison();
        label1.setText("nom :" + liv.getChef().getNom());
        label2.setText("prenom :" + liv.getChef().getPrenom());
        label3.setText("tel :" + String.valueOf(liv.getChef().getNumero()));
        label4.setText("adresse:"+liv.getAdresse_source());
    }
    private livraison liv;

    // Method to set the livraison object
    public void setLivraison(livraison liv) {
        this.liv = liv;

        // Now you can use this.liv in your controller
    }
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;
    @FXML
    private Button reclamation;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;
    @FXML
    private Label label4;
    @FXML
    private Label ch;
    @FXML
    private Label cl;
    @FXML
    private WebView webView;



    @FXML
    void Delivery_Done(ActionEvent event) {
        ServiceLivraison sliv=new ServiceLivraison();
        System.out.println(liv);
        sliv.updateLivraisonField(liv.getId(),"state_delivery",1);
        sliv.updateLivraisonField(liv.getId(),"time_end",new Timestamp(System.currentTimeMillis()));
        try {
            // Load the FXML file for the new view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/livreur/livreur_acceuil.fxml"));

            // Create a new scene
            Scene scene = new Scene(fxmlLoader.load());

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

            // Show the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void open_maps(ActionEvent event) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Load OpenStreetMap
        webEngine.load("https://www.google.com/maps");

        // Create a button to close the new window
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> ((Node)(e.getSource())).getScene().getWindow().hide());

        // Create a VBox to hold the WebView and the close button
        VBox vBox = new VBox(webView, closeButton);

        Scene scene = new Scene(vBox, 600, 350);

        // Create a new Stage
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }



    @FXML
    void order_received(ActionEvent event) {
        ServiceLivraison sliv=new ServiceLivraison();
        sliv.updateLivraisonField(liv.getId(),"state_reception",1);
        sliv.updateLivraisonField(liv.getId(),"time_start",new Timestamp(System.currentTimeMillis()));
        label1.setText("nom :" + new UserService().getUserById(4).getNom());
        label2.setText("prenom :" + new UserService().getUserById(4).getPrenom());
        label3.setText("tel :" + String.valueOf(new UserService().getUserById(4).getNumero()));
        label4.setText("adresse:"+liv.getAdresse_destination());
        button1.setVisible(false);
        button2.setVisible(true);
        ch.setVisible(false);
        cl.setVisible(true);
    }
    @FXML
    void reclamation(ActionEvent event) {
        try {

            // Load the FXML file for the new view
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/add_réclamation.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            AddRéclamation controller = fxmlLoader.getController();
            controller.setLivraison(liv);

            // Create a new scene

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene to the stage
            stage.setScene(scene);

            // Show the new scene
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
