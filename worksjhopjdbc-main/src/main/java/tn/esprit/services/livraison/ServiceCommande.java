package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Commande.Commande;
import tn.esprit.models.livraison.commande;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.esprit.models.User.User;

public class ServiceCommande implements IService<commande> {
    private Connection cnx ;
    public ServiceCommande(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public commande getById(int id) {
        commande commande = null;
        String qry = "SELECT * FROM `commande` WHERE `id` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                commande = new commande();
                commande.setId(rs.getInt("id"));
                // Set other fields as needed
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return commande;
    }
    public Map<String, Object> getCommandeDetails(int idCommande) {
        String selectCommandeDetailsQuery = "SELECT a.Nom_du_plat, pa.quantite, c.prix_total, c.adresse " +
                "FROM Commande c JOIN Panier_Annonce pa ON c.id_panier = pa.id_panier " +
                "JOIN Annonce a ON pa.id_annonce = a.id_annonce " +
                "WHERE c.id_commande = ?";

        Map<String, Object> commandeDetails = new HashMap<>();

        try {
            PreparedStatement pst = cnx.prepareStatement(selectCommandeDetailsQuery);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                commandeDetails.put("Nom_du_plat", rs.getString("Nom_du_plat"));
                commandeDetails.put("quantite", rs.getInt("quantite"));
                commandeDetails.put("prix_total", rs.getInt("prix_total"));
                commandeDetails.put("adresse", rs.getString("adresse"));
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des détails de la commande : " + e.getMessage());
        }

        return commandeDetails;
    }
    public User getChefDetailsFromCommande(int idCommande) {
        String selectChefDetailsQuery = "SELECT u.* FROM User u " +
                "JOIN Annonce a ON u.UserId = a.id_du_chef " +
                "JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "JOIN Commande c ON pa.id_panier = c.id_panier " +
                "WHERE c.id_commande = ?";

        User chefDetails = null;

        try {
            PreparedStatement pst = cnx.prepareStatement(selectChefDetailsQuery);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("UserId");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String email = rs.getString("Email");
                String numero = rs.getString("Numero");
                String password = rs.getString("Password");
                String role = rs.getString("Role");

                chefDetails = new User(userId, nom, prenom, email, numero, password, role);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération des détails du chef : " + e.getMessage());
        }

        return chefDetails;
    }
    public String getAdresseFromCommande(int idCommande) {
        String selectAdresseQuery = "SELECT a.adresse " +
                "FROM Annonce a JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "JOIN Panier p ON pa.id_panier = p.id_panier " +
                "JOIN Commande c ON p.id_panier = c.id_panier " +
                "WHERE c.id_commande = ?";

        String adresse = "";

        try {
            PreparedStatement pst = cnx.prepareStatement(selectAdresseQuery);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                adresse = rs.getString("adresse");
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de l'adresse : " + e.getMessage());
        }

        return adresse;
    }
    public Commande getLastInsertedCommande() {
        String selectLastInsertedCommandeQuery = "SELECT * FROM Commande ORDER BY id_commande DESC LIMIT 1";

        Commande lastInsertedCommande = null;

        try {
            PreparedStatement pst = cnx.prepareStatement(selectLastInsertedCommandeQuery);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idCommande = rs.getInt("id_commande");
                int idClient = rs.getInt("id_client");
                int idPanier = rs.getInt("id_panier");
                int prixTotal = rs.getInt("prix_total");
                String adresse = rs.getString("adresse");


                lastInsertedCommande = new Commande(idCommande, idClient,prixTotal, adresse);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la dernière commande : " + e.getMessage());
        }

        return lastInsertedCommande;
    }
    public void add (commande commande){
    };
    public ArrayList<commande> getAll(){
        return null;
    }
    public void update(commande c1){};
    public boolean delete(commande c1){ return false;};

}
