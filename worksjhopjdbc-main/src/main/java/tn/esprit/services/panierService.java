package tn.esprit.services;


import tn.esprit.interfaces.IPService;
import tn.esprit.models.Annonce;
import tn.esprit.models.Panier;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class panierService implements IPService<Panier> {
    private Connection cnx ;
    public panierService(){
        cnx = MyDataBase.getInstance().getCnx();
    }


    @Override
    public void Ajouter(Panier panier) {
        String qry = "INSERT INTO panier (id_client) VALUES (?)";
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, panier.getId_panier());
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
            pst.setInt(1,id_panier);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void Modifier(Panier panier ) {
        String qry= "UPDATE panier SET id_client = ? WHERE id_panier = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1,panier.getId_client());
            pst.setInt(2,panier.getId_panier());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ArrayList<Panier> getAll() {
        ArrayList<Panier> paniers= new ArrayList<>();
        String qry="SELECT * FROM `panier`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while(rs.next()){
                Panier panier = new Panier();
                panier.setId_panier(rs.getInt(1));
                panier.setId_client(rs.getInt(2));
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
        try  {
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

    public void ajouterAnnonceAuPanier(int id_panier, int id_annonce) {
        String qry = "INSERT INTO Panier_Annonce (id_panier, id_annonce) VALUES (?, ?)";
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, id_panier);
            pst.setInt(2, id_annonce);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*public void ajouterAnnonceAuPanier(Annonce annonce) {
        try {
            // Vérifier si l'annonce existe déjà dans la table Annonce
            String checkAnnonceQuery = "SELECT COUNT(*) FROM Annonce WHERE id_annonce = ?";
            PreparedStatement checkAnnonceStatement = cnx.prepareStatement(checkAnnonceQuery);
            checkAnnonceStatement.setInt(1, annonce.getId_annonce());
            ResultSet resultSet = checkAnnonceStatement.executeQuery();
            resultSet.next();
            int annonceCount = resultSet.getInt(1);

            // Ajouter l'annonce au panier dans la table Panier
            String addAnnonceToPanierQuery = "INSERT INTO Panier (id_client, id_annonce, nom_annonce, annonce_img, quantite) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement addAnnonceToPanierStatement = cnx.prepareStatement(addAnnonceToPanierQuery);
            addAnnonceToPanierStatement.setInt(1, annonce.getId_client());
            addAnnonceToPanierStatement.setInt(2, annonce.getId_annonce());
            addAnnonceToPanierStatement.setString(3, annonce.getNom_du_plat());
            addAnnonceToPanierStatement.setString(4, annonce.getAnnonce_img());
            addAnnonceToPanierStatement.setInt(5, annonce.getQuantite());
            addAnnonceToPanierStatement.executeUpdate();

            System.out.println("Annonce ajoutée au panier avec succès !");
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'ajout de l'annonce au panier : " + e.getMessage());
        }
    }*/
    public void supprimerAnnonceDuPanier(int id_panier,int id_annonce) {
        String qry = "DELETE FROM Panier_Annonce WHERE id_panier = ? AND id_annonce = ?";
        try {
            PreparedStatement pst= cnx.prepareStatement(qry);
            pst.setInt(1, id_panier);
            pst.setInt(2, id_annonce);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erreur lors de la suppression de l'annonce du panier : " + e.getMessage());
            e.printStackTrace();
        }

    }
    public void mettreAJourQuantiteAnnonceDansPanier(int id_panier, int id_annonce, int nouvelleQuantite) {
        String qry = "UPDATE Panier_Annonce SET quantite = ? WHERE id_panier = ? AND id_annonce = ?";
        try  {
            PreparedStatement pst = cnx.prepareStatement(qry);
            pst.setInt(1, nouvelleQuantite);
            pst.setInt(2, id_panier);
            pst.setInt(3, id_annonce);
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

    public List<Annonce> rechercherAnnoncesDansPanierParNom(int idPanier, String nomAnnonce) {
        List<Annonce> annoncesTrouvees = new ArrayList<>();
        String qry = "SELECT a.id_annonce, a.nom_du_plat, a.description_du_plat, a.prix, a.id_du_chef, a.ingredients, a.categorie_de_plat " +
                "FROM Annonce a " +
                "JOIN Panier_Annonce pa ON a.id_annonce = pa.id_annonce " +
                "JOIN Panier p ON pa.id_panier = p.id_panier " +
                "WHERE p.id_panier = ? AND a.nom_du_plat LIKE ?";
        try {
            PreparedStatement pst= cnx.prepareStatement(qry);
            pst.setInt(1, idPanier);
            pst.setString(2, "%" + nomAnnonce + "%");
            try {
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    Annonce annonce = new Annonce();
                    annonce.setId_annonce(rs.getInt("id_annonce"));
                    annonce.setNom_annonce(rs.getString("nom_du_plat"));
                    annonce.setDescription_du_plat(rs.getString("description_du_plat"));
                    annonce.setPrix(rs.getInt("prix"));
                    annonce.setId_du_chef(rs.getInt("id_du_chef"));
                    annonce.setIngredients(rs.getString("ingredients"));
                    annonce.setCategorie_de_plat(rs.getString("categorie_de_plat"));
                    annoncesTrouvees.add(annonce);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return annoncesTrouvees;
    }
}
