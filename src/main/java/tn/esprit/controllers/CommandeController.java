package tn.esprit.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import tn.esprit.models.Annonce;
import tn.esprit.models.Commande;
import tn.esprit.services.UserService;
import tn.esprit.services.commandeService;
import tn.esprit.services.panierService;

import java.io.IOException;
import java.util.List;

public class CommandeController {
    @FXML
    private TableView<Annonce> annonceTable;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Annonce, String> nomAnnonceColumn;

    @FXML
    private TableColumn<Annonce, Integer> quantiteColumn;

    @FXML
    private TableColumn<Annonce, Void> supprimerColumn;
    public TextField adresse;
    @FXML
    private Label idCommandeLabel;

    @FXML
    private TextField nomField;

    @FXML
    private TextField prenomField;

    @FXML
    private TextField numeroField;

    @FXML
    private TextField adresseEmailField;

    @FXML
    private TextField cardNumberField;

    @FXML
    private TextField expiryDateField;

    @FXML
    private TextField cvvField;

    @FXML
    private TextField amountField;

    @FXML
    private CheckBox paiementALivraisonCheckBox;

    @FXML
    private CheckBox paiementEnLigneCheckBox;


    private panierService panierService;
    private commandeService commandeService;

    private String etatLivraison;
    public CommandeController() {
        this.panierService = new panierService();
        this.commandeService = new commandeService();
    }


    // Instancier le service Client
    private UserService clientService = new UserService();

    @FXML
    private Button finaliserCommandeButton;

    @FXML
    private void initialize() {
        int idClient = loginController.id; // Remplacez 1 par l'ID du client dont vous avez besoin
        afficherInformationsClient(idClient);

        panierService = new panierService();

        supprimerColumn.setCellFactory(new SupprimerAnnonceDuPanier(panierService));

        nomAnnonceColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue().getNom_du_plat());
        });
        quantiteColumn.setCellValueFactory(cellData -> {
            return new SimpleIntegerProperty(cellData.getValue().getQuantite()).asObject();
        });

        // Chargez les annonces du panier et peuplez votre TableView
        int idPanier = loginController.id; // Remplacez cela par l'identifiant du panier dont vous souhaitez afficher les annonces
        List<Annonce> annonces = panierService.getAllAnnoncesFromPanier(idPanier);
        annonceTable.getItems().addAll(annonces);
    }

    private void afficherInformationsClient(int idClient) {
        // Appel de la fonction du service pour récupérer les informations du client
//        Client client = clientService.afficherInformationsClient(idClient);

        // Vérifier si le client existe

        // Remplir les champs avec les informations du client
        nomField.setText(loginController.nom);
        prenomField.setText(loginController.prenom);
        numeroField.setText(loginController.Num);
        adresseEmailField.setText(loginController.email);
    }

    @FXML
    private void finaliserCommande(ActionEvent event) {

        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String numero = numeroField.getText();
        String adresseLivraison = adresse.getText();

        String etatLivraison = null;
        if (ramassageDirectCheckBox.isSelected()) {
            etatLivraison = "Ramassage direct";
        } else if (livraisonLivreurCheckBox.isSelected()) {
            etatLivraison = "Livraison par livreur";
        }
        Commande commande = new Commande();
        commande.setAdresse(adresseLivraison);

        int idClient = loginController.id;
        int idPanier=loginController.id;
        int prixTotal= panierService.calculerPrixTotalPanier(idPanier);


        // Appeler la méthode pour ajouter la commande
        commandeService commandeService = new commandeService();
        if (adresse.getText().isEmpty() ||
                (!ramassageDirectCheckBox.isSelected() && !livraisonLivreurCheckBox.isSelected()) ||
                (!paiementALivraisonCheckBox.isSelected() && !paiementEnLigneCheckBox.isSelected())) {
            // Afficher un message d'erreur ou effectuer une action appropriée
            showAlert("Veuillez remplir tous les champs obligatoires.");
            return; // Sortir de la méthode si un champ requis est manquant
        } else if (paiementEnLigneCheckBox.isSelected() && (cardNumberField.getText().isEmpty() || expiryDateField.getText().isEmpty() || cvvField.getText().isEmpty())) {
            // Afficher un message d'erreur spécifique pour les champs de paiement en ligne
            showAlert("Veuillez remplir tous les champs de paiement en ligne.");
            return; // Sortir de la méthode si les champs de paiement en ligne sont manquants
        } else {
            commandeService.ajouterCommande(idClient, idPanier, prixTotal, adresseLivraison, etatLivraison);
        }

        try {
            Stripe.apiKey = "sk_test_51OpfHFILsmRV4nqPsSywa4szvWj4tbLuAKXd2ASLPTHOZSEyzKwdq67s2M5ePFSDnddYqSSht0Ol7AlmxqwP2zbQ00HyYh2tk1";

            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount((long) prixTotal)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println("PaymentIntent créé : " + paymentIntent.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
            Parent panierParent = loader.load();
            Scene panierScene = new Scene(panierParent);
            Stage panierStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            panierStage.setScene(panierScene);
            panierStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private CheckBox ramassageDirectCheckBox;

    @FXML
    private CheckBox livraisonLivreurCheckBox;


    public void handleLivraisonSelection(ActionEvent event) {
        CheckBox selectedCheckBox = (CheckBox) event.getSource();
        if (selectedCheckBox == ramassageDirectCheckBox && ramassageDirectCheckBox.isSelected()) {
            livraisonLivreurCheckBox.setSelected(false);
            etatLivraison = "Ramassage direct";
        } else if (selectedCheckBox == livraisonLivreurCheckBox && livraisonLivreurCheckBox.isSelected()) {
            ramassageDirectCheckBox.setSelected(false);
            etatLivraison = "Livraison par livreur";
        }
//        CheckBox selectedCheckBox = (CheckBox) event.getSource();
//        if (selectedCheckBox == ramassageDirectCheckBox && ramassageDirectCheckBox.isSelected()) {
//            livraisonLivreurCheckBox.setSelected(false);
//        } else if (selectedCheckBox == livraisonLivreurCheckBox && livraisonLivreurCheckBox.isSelected()) {
//            ramassageDirectCheckBox.setSelected(false);
//        }
    }
    public String getEtatLivraison() {
        return etatLivraison;
    }

    @FXML
    private void handlePaiementSelection(ActionEvent event) {
        CheckBox selectedCheckBox = (CheckBox) event.getSource();
        if (selectedCheckBox == paiementALivraisonCheckBox && paiementALivraisonCheckBox.isSelected()) {
            paiementEnLigneCheckBox.setSelected(false);
            cardNumberField.setDisable(true);
            expiryDateField.setDisable(true);
            cvvField.setDisable(true);
            // Faites quelque chose pour le paiement à la livraison
        } else if (selectedCheckBox == paiementEnLigneCheckBox && paiementEnLigneCheckBox.isSelected()) {
            paiementALivraisonCheckBox.setSelected(false);
            cardNumberField.setDisable(false);
            expiryDateField.setDisable(false);
            cvvField.setDisable(false);
            // Faites quelque chose pour le paiement en ligne
        }
    }

    @FXML
    private void retourPanierButtonClicked(ActionEvent actionEvent) throws IOException {
        // Charger l'interface du panier
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/displayEvent.fxml"));
        Parent root = loader.load();

        // Créer une nouvelle scène avec l'interface du panier
        Scene scene = new Scene(root);

        // Récupérer la fenêtre actuelle
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        // Définir la nouvelle scène sur la fenêtre
        stage.setScene(scene);

        // Afficher la fenêtre
        stage.show();
    }



}
