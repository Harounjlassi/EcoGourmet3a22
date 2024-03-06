package tn.esprit.services;

import tn.esprit.interfaces.IEService;
import tn.esprit.interfaces.IService;
import tn.esprit.models.Votes;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;

public class ServiceVotes  implements IEService<Votes> {

    private Connection cnx;

    public ServiceVotes() {
        cnx = MyDataBase.getInstance().getCnx();
    }


    @Override
    public void add(Votes v) {
        // ajouter une Events dans la bd
        //1 - req SQL done
        //2 - executer la req SQL done
        String qry = "INSERT INTO `Votes`(`commentaire`, `vote_value`, `UserID`,`event_id`) VALUES (?,?,?,?)";
        try {

            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setString(1, v.getCommentaire());

            stm.setInt(2, v.getVote_value());
            stm.setInt(3, v.getUser_id());
            stm.setInt(4, v.getEvent_id());






            stm.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }


    }
    public ArrayList<Integer > getAllByVoteVal() {
        ArrayList<Integer> votesArray = new ArrayList();
        String qry = "SELECT vote_value FROM `Votes`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                //e.setButton(new Button("Supprimer"));
                votesArray.add(rs.getInt("vote_value"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return votesArray;
    }

    @Override
    public  ArrayList<Votes> getAll() {
        ArrayList<Votes> votesArray = new ArrayList();
        String qry = "SELECT * FROM `Votes`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                Votes v = new Votes();
                v.setId(rs.getInt(1));
                v.setCommentaire(rs.getString("commentaire"));
                v.setVote_value(rs.getInt(3));
                v.setEvent_id(rs.getInt(4));
                v.setUser_id(rs.getInt(5));
                //e.setButton(new Button("Supprimer"));
                votesArray.add(v);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return votesArray;
    }

    @Override
    public void update(Votes v) {
        try {

            String req = "UPDATE `Votes` SET `commentaire` = '" + v.getCommentaire() +
                    "', `vote_value` = '" + v.getVote_value() +"', `UserID` = '" + v.getUser_id()+
                    "', `event_id` = '" + v.getEvent_id()+
                    "' WHERE `Votes`.`vote_id` = " + v.getId();
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Votes updated successfully!");
            } else {
                System.out.println("No Votes updated!");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean delete(Votes v) {
        try {
            String req = "DELETE FROM `Votes` WHERE vote_id = " + v.getId();
            ;
            Statement st = cnx.createStatement();
            if (st.executeUpdate(req) > 0) {
                System.out.println("Votes deleted successfully!");
            } else {
                System.out.println("No Votes deleted!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;

    }


}