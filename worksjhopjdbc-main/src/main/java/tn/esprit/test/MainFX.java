
package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFX extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static final String CURRENCY = "$";

    @Override
    public void start(Stage primaryStage) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/livraison/livreur/livreur_acceuil.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/livraison/chef/annonce.fxml"));
        //FXMLLoader loader=new FXMLLoader(getClass().getResource("/livraison/client/commande.fxml"));
        try {
            Parent root = loader.load();
            Scene scene =new Scene(root);
            primaryStage.setTitle("livreur");
            //primaryStage.setTitle("chef");
            //primaryStage.setTitle("client");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

}
