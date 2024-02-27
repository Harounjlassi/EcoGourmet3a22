package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.livraison.Feedback_livraison;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class Service_FeedBack_livraison implements IService<Feedback_livraison> {
    private Connection cnx ;
    public Service_FeedBack_livraison(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void add (Feedback_livraison feedbackLivraison){
        String qry ="INSERT INTO `feedback_livraison`(`FB_livreur`, `FB_duration`) VALUES (?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1,feedbackLivraison.getFB_livreur());
            stm.setInt(2,feedbackLivraison.getFB_duration());
            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }

    }
    public Feedback_livraison getLastInsertedFeedback() {
        String qry = "SELECT * FROM `feedback_livraison` ORDER BY `id` DESC LIMIT 1";
        Feedback_livraison lastInsertedFeedback = null;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lastInsertedFeedback = new Feedback_livraison(
                        rs.getInt("id"),
                        rs.getInt("FB_livreur"),
                        rs.getInt("FB_duration")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastInsertedFeedback;
    }

    public ArrayList<Feedback_livraison> getAll(){
        ArrayList<Feedback_livraison> Feed_backs= new ArrayList();
        String qry ="SELECT * FROM `feedback_livraison`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                Feedback_livraison FB= new Feedback_livraison();
                FB.setId(rs.getInt(1));
                FB.setFB_livreur(rs.getInt(2));
                FB.setFB_duration(rs.getInt(3));
                Feed_backs.add(FB);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return Feed_backs;
    };
    public void update(Feedback_livraison FB){};
    public boolean delete(Feedback_livraison FB){ return false;};
    public Feedback_livraison getById(int id) {
        Feedback_livraison feedbackLivraison = null;
        String qry = "SELECT * FROM `feedback_livraison` WHERE `id` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                feedbackLivraison = new Feedback_livraison();
                feedbackLivraison.setId(id);
                feedbackLivraison.setFB_livreur(rs.getInt("FB_livreur"));
                feedbackLivraison.setFB_duration(rs.getInt("FB_duration"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return feedbackLivraison;
    }
    public Feedback_livraison getFeedbackLivraisonId(Feedback_livraison feedbackLivraison) {
        String qry = "SELECT `id` FROM `feedback_livraison` WHERE `FB_livreur` = ? AND `FB_duration` = ?";
        int id = -1;
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, feedbackLivraison.getFB_livreur());
            stm.setInt(2, feedbackLivraison.getFB_duration());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new Feedback_livraison(id);
    }






}
