package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.livraison.Réclamation;
import tn.esprit.models.livraison.livreur;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceLivreur implements IService<livreur> {
    private Connection cnx ;
    public ServiceLivreur(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void add (livreur livreur){
        String qry ="INSERT INTO `livreur`(`nom`, `prénom`, `age`,`tel`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1,livreur.getNom());
            stm.setString(2,livreur.getPrénom());
            stm.setInt(3,livreur.getAge());
            stm.setInt(4,livreur.getTel());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
    public Réclamation getReclamationId(Réclamation reclamation) {
        String qry = "SELECT `id` FROM `réclamation` WHERE `date_réclamation` = ? AND `cause_réclamation` = ?";
        int id = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setTimestamp(1, reclamation.getDate_Réclamation());
            stm.setString(2, reclamation.getCause_Réclamation());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Réclamation(id);
    }
    public ArrayList<livreur> getAll(){
        ArrayList<livreur> livreurs= new ArrayList();
        String qry ="SELECT * FROM `livreur`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                livreur lv = new livreur();
                lv.setId(rs.getInt(1));
                lv.setNom(rs.getString("nom"));
                lv.setPrénom(rs.getString(3));
                lv.setAge(rs.getInt("age"));
                lv.setTel(rs.getInt("tel"));
                livreurs.add(lv);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return livreurs;
    };
    public void update(livreur lv){};
    public boolean delete(livreur lv){ return false;};
    public livreur getById(int cin) {
        livreur livreur = null;
        String qry = "SELECT * FROM `livreur` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, cin);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prénom");
                int age = rs.getInt("age");
                int tel = rs.getInt("tel");

                livreur = new livreur(cin, nom, prenom, age, tel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livreur;
    }
    public livreur getLivreurId(livreur livreur) {
        String qry = "SELECT `id` FROM `livreur` WHERE `nom` = ? AND `prénom` = ? AND `age` = ? AND `tel` = ?";
        int id = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, livreur.getNom());
            stm.setString(2, livreur.getPrénom());
            stm.setInt(3, livreur.getAge());
            stm.setInt(4, livreur.getTel());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new livreur(id);
    }



}
