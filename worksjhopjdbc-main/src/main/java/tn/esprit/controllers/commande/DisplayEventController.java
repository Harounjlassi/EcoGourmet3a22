package tn.esprit.controllers.commande;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.List;

public class DisplayEventController {

    @FXML
    private Button add;

    @FXML
    private BorderPane annoncePane;

    @FXML
    private BorderPane panierPane;


    @FXML
    private Button panier;

    @FXML
    private TableColumn<?, ?> t_categorie;
    @FXML
    private Button button;

    @FXML
    private TableColumn<?, ?> t_date;

    @FXML
    private TableColumn<?, ?> t_location;

    @FXML
    private TableColumn<?, ?> t_name;

    @FXML
    private TableColumn<?, ?> t_photo;

    @FXML
    private TableColumn<?, ?> t_vote;

    @FXML
    private TableView<?> table;

    //@FXML
    //private ListView<String> listView;

    @FXML
    void ajouter_btn(ActionEvent event) {
    }


    // This method returns a list of strings
    public List<String> getStringList() {
        // Your logic to fetch or generate the list of strings
        return List.of("String 1", "String 2", "String 3");
    }

    /*@FXML
    public void initialize() {
        List<Annonce> listeAnnonce = annonceService.getAllAnnonces() ;
        List<String> b = new ArrayList<>();
        listeAnnonce.stream().map(annonce -> b.add(annonce.getNom_annonce())).collect(Collectors.toList());
        listView.getItems().addAll(b);
    }*/
    @FXML
    private void openAnnonces(ActionEvent event) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commande/annonce.fxml"));
            Parent annonce = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(annonce);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void openPanier(ActionEvent actionEvent) {
        try {
            // Load annonce.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/commande/panier.fxml"));
            Parent panier = loader.load();

            // Set annonce.fxml as the center of the mainBorderPane
            annoncePane.setCenter(panier);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
