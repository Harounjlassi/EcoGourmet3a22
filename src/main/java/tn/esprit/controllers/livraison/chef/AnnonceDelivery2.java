package tn.esprit.controllers.livraison.chef;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.controllers.livraison.DetailsCommande;
import tn.esprit.models.livraison.CommandeDetail;
import tn.esprit.models.livraison.livraison;
import tn.esprit.services.commandeService;
import tn.esprit.services.livraison.ServiceLivraison;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnnonceDelivery2 implements Initializable {
    private livraison liv;
    // Method to set the livraison object
    public void setLivraison(livraison liv) {
        this.liv = liv;

        // Now you can use this.liv in your controller
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label2.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label3.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label4.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label5.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        label6.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        button1.getStylesheets().add(getClass().getResource("/css/sample1.css").toExternalForm());
        ServiceLivraison sliv=new ServiceLivraison();
        livraison liv = sliv.getLastInsertedLivraison();
        label4.setText("Nom:"+liv.getLivreur().getNom());
        label5.setText("Prenom:"+liv.getLivreur().getPrenom());
        label6.setText("tel"+liv.getLivreur().getNumero());
        timeline = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            ServiceLivraison sliv2 = new ServiceLivraison();
            if(sliv2.getLastInsertedLivraison().getRéclamation()==null){
                System.out.println(sliv.getById(liv.getId()));
                System.out.println(sliv.getById(liv.getId()).isState_reception());
                if( sliv.getById(liv.getId()).isState_reception()){
                    timeline.stop();
                    label1.setVisible(false);
                    label2.setVisible(true);
                    button1.setVisible(false);
                    PauseTransition pause = new PauseTransition(Duration.seconds(3));
                    pause.setOnFinished(e -> {
                        try {
                            Stage currentStage = (Stage) button1.getScene().getWindow();
                            currentStage.close();
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Annonce.fxml"));
                            Parent root = fxmlLoader.load();

                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    });
                    pause.play();
                }}
            else{
                timeline.stop();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Réclamation is done by delivery man!");

                    alert.show();

                try {
                    // Get the current stage
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                    Stage newStage = new Stage();

                    // Load the FXML file for the new view
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/chef/annonce.fxml"));
                    Parent root = fxmlLoader.load();

                    // Set the scene on the new stage
                    newStage.setScene(new Scene(root));

                    // Show the new stage
                    newStage.show();

                    // Close the current stage
                    stage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


    }

    @FXML
    private Button button1;


    @FXML
    private Label label1;

    @FXML
    private Label label2;
    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;
    private Timeline timeline;
    /*void refresh() {
        if(new ServiceLivraison().getLastInsertedLivraison().getRéclamation()==null){
        ServiceLivraison sliv=new ServiceLivraison();
        System.out.println(sliv.getById(liv.getId()));
        System.out.println(sliv.getById(liv.getId()).isState_reception());
        if( sliv.getById(liv.getId()).isState_reception()){
            timeline.stop();
            label1.setVisible(false);
            label2.setVisible(true);
            button1.setVisible(false);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                try {
                    Stage currentStage = (Stage) button1.getScene().getWindow();
                    currentStage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/chef/annonce.fxml"));
                    Parent root = fxmlLoader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();
        }}
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Réclamation is done by delivery man!");

            alert.showAndWait();

            // Get the current stage
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Close the stage
            stage.close();
        }
    }

    /*@FXML
    void Refresh(ActionEvent event) {
        ServiceLivraison sliv=new ServiceLivraison();
        if(sliv.getById(liv.getId()).isState_reception()){
            label1.setVisible(false);
            label2.setVisible(true);
            button1.setVisible(false);
            button2.setVisible(false);
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                try {
                    Stage currentStage = (Stage) button1.getScene().getWindow();
                    currentStage.close();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/livraison/chef/annonce.fxml"));
                    Parent root = fxmlLoader.load();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            pause.play();

        }
    }*/



    @FXML
    void show_command_Details(ActionEvent event) {
        try {
            // Get the livreur details
            commandeService serviceCommande = new commandeService();
            List<CommandeDetail> commandedetails = serviceCommande.getCommandeDetails(liv.getCommande().getId_commande());

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

    }
}
