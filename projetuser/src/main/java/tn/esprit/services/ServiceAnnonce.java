package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Annonce;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceAnnonce implements IService<Annonce> {
    private Connection cnx;

    public ServiceAnnonce() {
        cnx = MyDataBase.getInstance().getCnx();
    }

    @Override
    public void add(Annonce annonce) {
        String qry = "INSERT INTO annonce (Nom_du_plat, Description_du_plat, prix, ID_du_chef, Ingredients, Categorie_de_plat, image_plat) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, annonce.getNom_du_plat());
            stm.setString(2, annonce.getDescription_du_plat());
            stm.setFloat(3, annonce.getPrix());
            stm.setInt(4, annonce.getID_du_chef());
            stm.setString(5, annonce.getIngredients());
            stm.setString(6, annonce.getCategorie_de_plat());
            stm.setString(7, annonce.getImagePlat()); // Assuming getImagePlat returns the image data as a string

            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getall() {

    }

    @Override
    public void modifier(Annonce element) {

    }

    @Override
    public void supprimer(int id) {

    }

    @Override
    public List<Annonce> getAll() {
        List<Annonce> annonces = new ArrayList<>();
        String qry = "SELECT * FROM `annonce`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Annonce a = new Annonce();
                a.setId_Annonce(rs.getInt("id_Annonce"));
                a.setNom_du_plat(rs.getString("Nom_du_plat"));
                a.setDescription_du_plat(rs.getString("Description_du_plat"));
                a.setPrix(rs.getFloat("Prix"));
                a.setID_du_chef(rs.getInt("ID_du_chef"));
                a.setIngredients(rs.getString("Ingredients"));
                a.setCategorie_de_plat(rs.getString("Categorie_de_plat"));
                a.setImagePlat(rs.getString("image_plat"));

                annonces.add(a);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return annonces;
    }

    @Override
    public void update(Annonce annonce) {
        String qry = "UPDATE `annonce` SET `Nom_du_plat` = ?, `Description_du_plat` = ?, `prix` = ?, `ID_du_chef` = ?, `Ingredients` = ?, `Categorie_de_plat` = ?, `image_plat` = ? WHERE `id_Annonce` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, annonce.getNom_du_plat());
            stm.setString(2, annonce.getDescription_du_plat());
            stm.setFloat(3, annonce.getPrix());
            stm.setInt(4, annonce.getID_du_chef());
            stm.setString(5, annonce.getIngredients());
            stm.setString(6, annonce.getCategorie_de_plat());
            stm.setString(7, annonce.getImagePlat());
            stm.setInt(8, annonce.getId_Annonce());

            int rowsAffected = stm.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Aucune ligne mise à jour. Vérifiez l'id_Annonce.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean delete(Annonce annonce) {
        String qry = "DELETE FROM `annonce` WHERE `id_Annonce` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, annonce.getId_Annonce());

            int rowsAffected = stm.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
                        rs.getInt("prix"),
                        rs.getString("Ingredients"),
                        rs.getString("categorie_de_plat")
                );
                filteredList.add(annonce);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredList;
    }
}



