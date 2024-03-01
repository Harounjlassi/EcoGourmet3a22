package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import tn.esprit.models.Client;
import tn.esprit.models.Commande;
import tn.esprit.services.ClientService;
import tn.esprit.services.commandeService;
import tn.esprit.services.panierService;

public class CommandeController {
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
    private panierService panierService;
    private commandeService commandeService;

    public CommandeController() {
        this.panierService = new panierService();
        this.commandeService = new commandeService();
    }


    // Instancier le service Client
    private ClientService clientService = new ClientService();

    @FXML
    private void initialize() {
        // Appel de la fonction pour récupérer les informations du client
        int idClient = 1; // Remplacez 1 par l'ID du client dont vous avez besoin
        afficherInformationsClient(idClient);
    }
    private void afficherInformationsClient(int idClient) {
        // Appel de la fonction du service pour récupérer les informations du client
        Client client = clientService.afficherInformationsClient(idClient);

        // Vérifier si le client existe
        if (client != null) {
            // Remplir les champs avec les informations du client
            nomField.setText(client.getNom());
            prenomField.setText(client.getPrenom());
            numeroField.setText(client.getNumero_telephone());
            adresseEmailField.setText(client.getAdresse_email());
        } else {
            System.out.println("Aucun client trouvé avec l'ID : " + idClient);
        }
    }

    @FXML
    private void finaliserCommande() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String numero = numeroField.getText();
        String adresseLivraison = adresse.getText();

        // Récupérer l'id du client, l'id du panier, et le prix total à partir d'autres sources si nécessaire

        Commande commande = new Commande();
        // Set les attributs de la commande avec les informations récupérées
        commande.setAdresse(adresseLivraison);
        // Set d'autres attributs de la commande si nécessaire

        int idClient = 1;
        int idPanier=1;
        int prixTotal= panierService.calculerPrixTotalPanier(idPanier);
        // Appeler la méthode pour ajouter la commande
        commandeService commandeService = new commandeService();
        commandeService.ajouterCommande(idClient, idPanier, prixTotal, adresseLivraison);

    }

}

