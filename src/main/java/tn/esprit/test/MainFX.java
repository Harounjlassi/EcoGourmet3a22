
package tn.esprit.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent root = loader.load();

            // Get the controller for the market interface

            Scene scene = new Scene(root);
            primaryStage.setTitle("EcoGourmé");

            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            // Configurez la taille de la fenêtre principale
            primaryStage.setWidth(1109);
            primaryStage.setHeight(699);
            System.out.println(screenBounds.getWidth()+"'''"+screenBounds.getHeight());
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
