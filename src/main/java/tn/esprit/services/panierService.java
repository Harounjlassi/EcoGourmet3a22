package tn.esprit.services;


import tn.esprit.interfaces.IPService;
import tn.esprit.models.Annonce;
import tn.esprit.models.Panier;
import tn.esprit.utils.MyDataBase;
import tn.esprit.controllers.loginController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class panierService implements IPService<Panier> {
    private Connection cnx;

    public panierService() {
        cnx = MyDataBase.getInstance().getCnx();
    }


    @Override
    public void Ajouter(Panier panier) {
        String qry = "INSERT INTO panier (id_client, dateCreation, dateModification) VALUES (?, ?, ?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, panier.getId_client());
            pst.setDate(2, new Date(panier.getDateCreation().getTime()));
            pst.setDate(3, new Date(panier.getDateModification().getTime()));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Supprimer(int id_panier) {
        String qry = "DELETE FROM panier WHERE id_panier = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, id_panier);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Modifier(Panier panier) {
        String qry = "UPDATE panier SET id_client = ?, dateModification = ? WHERE id_panier = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, panier.getId_client());
            pst.setDate(2, new Date(panier.getDateModification().getTime()));
            pst.setInt(3, panier.getId_panier());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ArrayList<Panier> getAll() {
        ArrayList<Panier> paniers = new ArrayList<>();
        String qry = "SELECT id_panier, id_client, dateCreation, dateModification FROM panier";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Panier panier = new Panier();
                panier.setId_panier(rs.getInt(1));
                panier.setId_client(rs.getInt(2));
                panier.setDateCreation(rs.getDate(3));
                panier.setDateModification(rs.getDate(4));
                paniers.add(panier);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paniers;
    }

    public Panier trouverPanierParId(int id_panier) throws SQLException {
        Panier panier = null;
        String query = "SELECT * FROM panier WHERE id_panier = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setInt(1, id_panier);
            try (ResultSet resultSet = pst.executeQuery()) {
                if (resultSet.next()) {
                    panier = new Panier();
                    panier.setId_panier(resultSet.getInt("id_panier"));
                    panier.setId_client(resultSet.getInt("id_client"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return panier;
    }

//    public void ajouterAnnonceAuPanier(int id_panier, int id_annonce) {
//        String qry = "INSERT INTO Panier_Annonce (id_panier, id_annonce) VALUES (?, ?)";
//        try {
//            PreparedStatement pst = cnx.prepareStatement(qry);
//            pst.setInt(1, id_panier);
//            pst.setInt(2, id_annonce);
//            pst.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void ajouterAnnonceAuPanier(int id_panier, int id_annonce) {
        // Vérifier si l'annonce existe déjà dans le panier
        String selectQuery = "SELECT quantite FROM Panier_Annonce WHERE id_panier = ? AND id_annonce = ?";
        try {
            PreparedStatement selectStatement = cnx.prepareStatement(selectQuery);
            selectStatement.setInt(1, id_panier);
            selectStatement.setInt(2, id_annonce);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                // L'annonce existe déjà dans le panier, incrémenter la quantité
                int quantite = rs.getInt("quantite") + 1;
                String updateQuery = "UPDATE Panier_Annonce SET quantite = ? WHERE id_panier = ? AND id_annonce = ?";
                try  {
                    PreparedStatement updateStatement = cnx.prepareStatement(updateQuery);
                    updateStatement.setInt(1, quantite);
                    updateStatement.setInt(2, id_panier);
                    updateStatement.setInt(3, id_annonce);
                    updateStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // L'annonce n'existe pas dans le panier, l'ajouter avec une quantité de 1
                String insertQuery = "INSERT INTO Panier_Annonce (id_panier, id_annonce, quantite) VALUES (?, ?, 1)";
                try (PreparedStatement insertStatement = cnx.prepareStatement(insertQuery)) {
                    insertStatement.setInt(1, id_panier);
                    insertStatement.setInt(2, id_annonce);

                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Annonce> getAllAnnoncesFromPanier(int idPanier) {
        List<Annonce> annonces = new ArrayList<>();
        String qry = "SELECT a.id_annonce, a.nom_du_plat, a.description_du_plat, a.prix, a.userID, a.ingredients, a.categorie_de_plat, pa.quantite " +
                "FROM Annonce a " +
                "JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "JOIN Panier p ON pa.id_panier = p.id_panier " +
                "WHERE p.id_panier = ?";
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, idPanier); // Lier le paramètre à la requête
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_Annonce(resultSet.getInt("id_annonce"));
                annonce.setNom_du_plat(resultSet.getString("nom_du_plat"));
                annonce.setDescription_du_plat(resultSet.getString("description_du_plat"));
                annonce.setPrix(resultSet.getInt("prix"));
                annonce.setUserID(resultSet.getInt("userID"));
                annonce.setIngredients(resultSet.getString("ingredients"));
                annonce.setCategorie_de_plat(resultSet.getString("categorie_de_plat"));
                annonce.setQuantite(resultSet.getInt("quantite")); // Ajouter la quantité à l'objet Annonce
                annonces.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annonces;
    }

    public int calculerPrixTotalPanier(int idPanier) {
        int prixTotal = 0;
        String qry = "SELECT SUM(a.prix * pa.quantite) AS prix_total " +
                "FROM Annonce a " +
                "JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "WHERE pa.id_panier = ?";
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, loginController.id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                prixTotal = resultSet.getInt("prix_total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prixTotal;
    }



    public void supprimerAnnonceDuPanier ( int id_panier, int id_annonce){
            String qry = "DELETE FROM Panier_Annonce WHERE id_panier = ? AND id_annonce = ?";
            try {
                PreparedStatement pst = cnx.prepareStatement(qry);
                pst.setInt(1, id_panier);
                pst.setInt(2, id_annonce);
                pst.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Erreur lors de la suppression de l'annonce du panier : " + e.getMessage());
                e.printStackTrace();
            }

        }
    public void mettreAJourQuantiteAnnonceDansPanier( int id_panier, int id_Annonce, int nouvelleQuantite){
            String qry = "UPDATE Panier_Annonce SET quantite = ? WHERE id_panier = ? AND id_annonce = ?";
            try {
                PreparedStatement pst = cnx.prepareStatement(qry);
                pst.setInt(1, nouvelleQuantite);
                pst.setInt(2, id_panier);
                pst.setInt(3, id_Annonce);
                int rowsAffected = pst.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Quantité de l'annonce mise à jour dans le panier avec succès.");
                } else {
                    System.out.println("Aucune annonce correspondante trouvée dans le panier.");
                }
            } catch (SQLException e) {
                System.err.println("Erreur lors de la mise à jour de la quantité de l'annonce dans le panier : " + e.getMessage());
                e.printStackTrace();
            }
        }
}

