package tn.esprit.services.Annonce;

import tn.esprit.models.Annonce.Annonce;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class annonceService {
    private static Connection cnx ;
    public  annonceService(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public static List<Annonce> getAllAnnonces() {
        ArrayList<Annonce> annonces = new ArrayList();
        String qry ="SELECT * FROM `annonce`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Annonce annonce = new Annonce();
                annonce.setId_annonce(rs.getInt(1));
                annonce.setNom_annonce(rs.getString(2));
                annonce.setDescription_du_plat(rs.getString(3));
                annonce.setPrix(rs.getInt(4));
                annonce.setId_du_chef(rs.getInt(5));
                annonce.setIngredients(rs.getString(6));
                annonce.setCategorie_de_plat(rs.getString(6));
                annonces.add(annonce);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return annonces;
    }
}
