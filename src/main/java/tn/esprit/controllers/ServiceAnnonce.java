package tn.esprit.controllers;

import tn.esprit.interfaces.IServiceAnnonce;
import tn.esprit.models.Annonce;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAnnonce implements IServiceAnnonce<Annonce> {
    private Connection cnx;

    public ServiceAnnonce() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Annonce annonce) {
        String qry = "INSERT INTO annonce (id_Annonce, Nom_du_plat, Description_du_plat, prix, UserID, Ingredients, Categorie_de_plat, image_plat, quantite, adresse) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, annonce.getId_Annonce());
            stm.setString(2, annonce.getNom_du_plat());
            stm.setString(3, annonce.getDescription_du_plat());
            stm.setFloat(4, annonce.getPrix());
            stm.setInt(5, annonce.getUserID());
            stm.setString(6, annonce.getIngredients());
            stm.setString(7, annonce.getCategorie_de_plat());
            stm.setString(8, annonce.getImage_plat());
            stm.setInt(9, annonce.getQuantite());
            stm.setString(10, annonce.getAdresse());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public ArrayList<Annonce> getAll() {
        List<Annonce> annonces = new ArrayList<>();
        String qry = "SELECT * FROM annonce";
        try (Statement stm = cnx.createStatement();
             ResultSet rs = stm.executeQuery(qry)) {
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId_Annonce(rs.getInt("id_Annonce"));
                a.setNom_du_plat(rs.getString("Nom_du_plat"));
                a.setDescription_du_plat(rs.getString("Description_du_plat"));
                a.setPrix(rs.getFloat("prix"));
                a.setUserID(rs.getInt("userID"));
                a.setIngredients(rs.getString("Ingredients"));
                a.setCategorie_de_plat(rs.getString("Categorie_de_plat"));
                a.setImage_plat(rs.getString("image_plat"));
                a.setQuantite(rs.getInt("quantite"));
                a.setIngredients(rs.getString("adresse"));
                annonces.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ArrayList<Annonce>) annonces;
    }





    @Override
    public void update(Annonce annonce) {
        String qry = "UPDATE annonce SET Nom_du_plat = ?, Description_du_plat = ?, prix = ?, userID = ?, Ingredients = ?, Categorie_de_plat = ?, image_plat = ?,quantite = ? , adresse = ? WHERE id_Annonce = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, annonce.getNom_du_plat());
            stm.setString(2, annonce.getDescription_du_plat());
            stm.setFloat(3, annonce.getPrix());
            stm.setInt(4, annonce.getUserID());
            stm.setString(5, annonce.getIngredients());
            stm.setString(6, annonce.getCategorie_de_plat());
            stm.setString(7, annonce.getImage_plat());
            stm.setInt(8, annonce.getId_Annonce());
            stm.setInt(9, annonce.getQuantite());
            stm.setString(10, annonce.getAdresse());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Annonce annonce) {
        String qry = "DELETE FROM annonce WHERE id_Annonce = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, annonce.getId_Annonce());
            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Annonce> searchByNomPlat(String keyword) {
        List<Annonce> filteredList = new ArrayList<>();
        String qry = "SELECT * FROM annonce WHERE Nom_du_plat LIKE ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, "%" + keyword + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Annonce annonce = new Annonce(
                        rs.getInt("id_Annonce"),
                        rs.getString("Nom_du_plat"),
                        rs.getString("Description_du_plat"),
                        rs.getFloat("prix"),
                        rs.getInt("UserID"),
                        rs.getString("Ingredients"),
                        rs.getString("Categorie_de_plat"),
                        rs.getString("image_plat"),
                        rs.getInt("quantite"),
                        rs.getString("adresse")
                );
                filteredList.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredList;
    }
}
