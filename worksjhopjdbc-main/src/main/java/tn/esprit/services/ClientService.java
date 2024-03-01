package tn.esprit.services;

import tn.esprit.models.Client;
import tn.esprit.utils.MyDataBase;

import java.sql.*;

public class ClientService {
    private static Connection cnx;

    public ClientService() {
        cnx = MyDataBase.getInstance().getCnx();
    }
    public Client afficherInformationsClient(int idClient) {
        String qry = "SELECT * FROM `client` WHERE id_client = ?";
        Client client = null;

        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, idClient);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String numeroTelephone = rs.getString("numero_telephone");
                String adresseEmail = rs.getString("adresse_email");

                // Créer un nouvel objet Client avec les données récupérées
                client = new Client(idClient, nom, prenom, numeroTelephone, adresseEmail);
            } else {
                // Gérer le cas où aucun client n'est trouvé avec l'ID spécifié
                System.out.println("Aucun client trouvé avec l'ID : " + idClient);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }
}