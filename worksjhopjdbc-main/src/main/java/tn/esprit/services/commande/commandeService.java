package tn.esprit.services.commande;


import tn.esprit.interfaces.ICService;
import tn.esprit.models.Commande.Commande;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.commande;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class commandeService implements ICService<Commande> {
    private Connection cnx ;
    public commandeService(){
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void Ajouter(Commande commande) {
        try {
            String qry1 ="INSERT INTO Commande (id_client, id_panier, prix_total, adresse) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(qry1);
            pst.setInt(1,commande.getId_client());
            pst.setInt(2,commande.getId_panier());
            pst.setInt(3,commande.getPrix_total());
            pst.setString(4,commande.getAdresse());
            pst.executeUpdate();
            System.out.println("commande ajouté !");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.out.println("non commande ajouté !");
        }
    }

    @Override
    public void Supprimer(int id_commande) {
        try {
            String qry="DELETE FROM `commande` WHERE id_commande = ?";
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1,id_commande);
            pst.executeUpdate();
            System.out.println("commande a été supprimée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la commandes");
        }
    }

    public void ajouterCommande(int idClient, int idPanier, int prixTotal, String adresse, String etatCommande) {
        String insertCommandeQuery = "INSERT INTO Commande (id_client, id_panier, prix_total, adresse, etatcommande) " +
                "VALUES (?, ?, ?, ?, ?)";
        String selectAnnoncesQuery = "SELECT a.* FROM Annonce a JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "WHERE pa.id_panier = ?";
        String deletePanierAnnonceQuery = "DELETE FROM Panier_Annonce WHERE id_panier = ?";

        try  {
            PreparedStatement pst1 = cnx.prepareStatement(insertCommandeQuery);
            PreparedStatement pst2 = cnx.prepareStatement(selectAnnoncesQuery);
            PreparedStatement pst3 = cnx.prepareStatement(deletePanierAnnonceQuery);
            // Ajout de la commande
            pst1.setInt(1, idClient);
            pst1.setInt(2, idPanier);
            pst1.setInt(3, prixTotal);
            pst1.setString(4, adresse);
            pst1.executeUpdate();

            // Récupération des annonces associées au panier
            pst2.setInt(1, idPanier);
            ResultSet rs = pst2.executeQuery();
            List<Integer> annonceIds = new ArrayList<>();
            while (rs.next()) {
                annonceIds.add(rs.getInt("id_annonce"));
            }

            // Suppression des annonces du panier
            pst3.setInt(1, idPanier);
            pst3.executeUpdate();

            System.out.println("Commande ajoutée avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de la commande : " + e.getMessage());
        }
    }
    public Commande getCommandeById(int idCommande) {
        String selectCommandeQuery = "SELECT * FROM Commande WHERE id_commande = ?";

        Commande commande = null;

        try {
            PreparedStatement pst = cnx.prepareStatement(selectCommandeQuery);
            pst.setInt(1, idCommande);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                int idClient = rs.getInt("id_client");
                int idPanier = rs.getInt("id_panier");
                int prixTotal = rs.getInt("prix_total");
                String adresse = rs.getString("adresse");

                commande = new Commande(idCommande, idClient, idPanier,adresse,idPanier);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la récupération de la commande : " + e.getMessage());
        }

        return commande;
    }

    public int prixCommande(int id_panier) {
        String qry = "SELECT SUM(a.prix) FROM Annonce a " +
                "JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "WHERE pa.id_panier = ?";
        int prixTotal = 0;
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, id_panier);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    prixTotal = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erreur de la somme ddes prix des annonce d'un panier");
        }
        return prixTotal;
    }


    @Override
    public void Modifier(int id_commande,int prix_total, String adresse, String etatcommande) {
        try {
            //String qry ="UPDATE commande SET prix_total= ?,adresse= ?,etatcommande= ? WHERE id_commande = ? ";
            String qry="UPDATE Commande SET prix_total = ?, adresse = ?, etat_commande = ? WHERE id_commande = ?";
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1,prix_total);
            pst.setString(2,adresse);
            pst.setString(3, etatcommande);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Commande> getAll() {
        ArrayList<Commande> commandes = new ArrayList();
        String qry ="SELECT * FROM `commande`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Commande c = new Commande();
                c.setId_commande(rs.getInt(1));
                c.setId_client(rs.getInt(2));
                c.setPrix_total(rs.getInt(3));
                c.setAdresse(rs.getString(4));
                c.setId_panier(rs.getInt(5));
                commandes.add(c);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commandes;
    }

    public Commande readCommande(int id_commande) {
        Commande commande = null;
        String qry = "SELECT * FROM `commande` WHERE id_commande = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id_commande);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                commande = new Commande();
                commande.setId_commande(rs.getInt(1));
                commande.setId_client(rs.getInt(2));
                commande.setPrix_total(rs.getInt(3));
                commande.setAdresse(rs.getString(4));
                commande.setId_panier(rs.getInt(5));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return commande;
    }
    public ArrayList<Commande> trierCommandesParPrixDecroissant() {
        ArrayList<Commande> commandesTriees = new ArrayList<>();

        try {
            String query = "SELECT * FROM commande ORDER BY prix_total DESC";
            PreparedStatement pst = cnx.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Commande commande = new Commande();
                commande.setId_commande(rs.getInt("id_commande"));
                commande.setId_client(rs.getInt("id_client"));
                commande.setPrix_total(rs.getInt("prix_total"));
                commande.setAdresse(rs.getString("adresse"));
                commande.setId_commande(rs.getInt("id_panier"));
                commandesTriees.add(commande);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return commandesTriees;
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

    public ArrayList<Commande> rechercherCommandesParPrix(int prixMin, int prixMax) {
        ArrayList<Commande> commandesTrouvees = new ArrayList<>();

        try {
            String query = "SELECT * FROM commande WHERE prix_total >= ? AND prix_total <= ?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, prixMin);
            pst.setInt(2, prixMax);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Commande commande = new Commande();
                commande.setId_commande(rs.getInt("id_commande"));
                commande.setId_client(rs.getInt("id_client"));
                commande.setPrix_total(rs.getInt("prix_total"));
                commande.setAdresse(rs.getString("adresse"));
                commandesTrouvees.add(commande);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return commandesTrouvees;
    }

}
