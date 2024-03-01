package tn.esprit.services.livraison;

import tn.esprit.interfaces.IService;
import tn.esprit.models.User.User;
import tn.esprit.models.livraison.*;
import tn.esprit.services.User.UserService;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceLivraison implements IService<livraison> {
    private Connection cnx ;
    public ServiceLivraison(){
        cnx = MyDataBase.getInstance().getCnx();
    }
    public void add (livraison livraison){
        String qry ="INSERT INTO `livraison`(`livreur`,`chef`,`adresse_source`,`adresse_destination`,`Feedback_liv`,`Réclamation`,`state_reception`,`state_delivery`,`time_start`,`time_end`,`commande`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            if (livraison.getLivreur() != null) {
                stm.setInt(1,livraison.getLivreur().getUserID());
            } else {
                stm.setNull(1, Types.INTEGER);
            }

            if (livraison.getChef() != null) {
                stm.setInt(2,livraison.getChef().getUserID());
            } else {
                stm.setNull(2, Types.INTEGER);
            }

            stm.setString(3,livraison.getAdresse_source());
            stm.setString(4,livraison.getAdresse_destination());

            if (livraison.getFeedback_liv() != null) {
                stm.setInt(5,livraison.getFeedback_liv().getId());
            } else {
                stm.setNull(5, Types.INTEGER);
            }

            if (livraison.getRéclamation() != null) {
                stm.setInt(6,livraison.getRéclamation().getId());
            } else {
                stm.setNull(6, Types.INTEGER);
            }

            stm.setBoolean(7,livraison.isState_reception());
            stm.setBoolean(8,livraison.isState_delivery());
            stm.setTimestamp(9,livraison.getTime_start());
            stm.setTimestamp(10,livraison.getTime_end());

            if (livraison.getCommande() != null) {
                stm.setInt(11,livraison.getCommande().getId());
            } else {
                stm.setNull(11, Types.INTEGER);
            }

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void updateLivraisonField(int livraisonId, String fieldName, Object newValue) {
        String qry = "UPDATE `livraison` SET `" + fieldName + "` = ? WHERE `id` = ?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            if (newValue instanceof Integer) {
                stm.setInt(1, (Integer) newValue);
            } else if (newValue instanceof String) {
                stm.setString(1, (String) newValue);
            } else if (newValue instanceof Boolean) {
                stm.setBoolean(1, (Boolean) newValue);
            } else if (newValue instanceof Timestamp) {
                stm.setTimestamp(1, (Timestamp) newValue);

            } else {
                stm.setObject(1, newValue);
            }

            stm.setInt(2, livraisonId);

            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public livraison getLastInsertedLivraison() {
        livraison liv = null;
        try {
            String qry = "SELECT * FROM `livraison` ORDER BY `id` DESC LIMIT 1";
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                liv = new livraison();
                // Assuming you have setters for all these fields in your livraison class
                liv.setId(rs.getInt("id"));
                liv.setLivreur(new UserService().getUserById(rs.getInt("livreur")));
                liv.setChef(new UserService().getUserById(rs.getInt("chef")));
                liv.setAdresse_source(rs.getString("adresse_source"));
                liv.setAdresse_destination(rs.getString("adresse_destination"));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt("Feedback_liv")));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt("Réclamation")));
                liv.setState_reception(rs.getBoolean("state_reception"));
                liv.setState_delivery(rs.getBoolean("state_delivery"));
                liv.setTime_start(rs.getTimestamp("time_start"));
                liv.setTime_end(rs.getTimestamp("time_end"));
                liv.setCommande(new ServiceCommande().getById(rs.getInt("commande")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return liv;
    }
    public List<livraison> getLivraisonsWithNullLivreur() {
        List<livraison> livraisons = new ArrayList<>();

        String qry = "SELECT * FROM `livraison` WHERE `livreur` IS NULL";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                livraison liv = new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return livraisons;
    }



    public ArrayList<livraison> getAll(){
        ArrayList<livraison> livraisons= new ArrayList();
        String qry ="SELECT * FROM `livraison`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()){
                livraison liv= new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return livraisons;
    };
    public void update(livraison livraison) {
        String qry = "UPDATE `livraison` SET `livreur`=?,`chef`=?,`adresse_source`=?,`adresse_destination`=?,`Feedback_liv`=?,`Réclamation`=?,`state_reception`=?,`state_delivery`=?,`time_start`=?,`time_end`=?,`commande`=? WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);

            if (livraison.getLivreur() != null) {
                stm.setInt(1, livraison.getLivreur().getUserID());
            } else {
                stm.setNull(1, Types.INTEGER);
            }

            if (livraison.getChef() != null) {
                stm.setInt(2, livraison.getChef().getUserID());
            } else {
                stm.setNull(2, Types.INTEGER);
            }

            stm.setString(3, livraison.getAdresse_source());
            stm.setString(4, livraison.getAdresse_destination());

            if (livraison.getFeedback_liv() != null) {
                stm.setInt(5, livraison.getFeedback_liv().getId());
            } else {
                stm.setNull(5, Types.INTEGER);
            }

            if (livraison.getRéclamation() != null) {
                stm.setInt(6, livraison.getRéclamation().getId());
            } else {
                stm.setNull(6, Types.INTEGER);
            }

            stm.setBoolean(7, livraison.isState_reception());
            stm.setBoolean(8, livraison.isState_delivery());
            stm.setTimestamp(9, livraison.getTime_start());
            stm.setTimestamp(10, livraison.getTime_end());

            if (livraison.getCommande() != null) {
                stm.setInt(11, livraison.getCommande().getId());
            } else {
                stm.setNull(11, Types.INTEGER);
            }

            stm.setInt(12, livraison.getId());

            stm.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void delete(int id) {
        try {
            // Get the livraison
            String qryLivraison = "SELECT * FROM `livraison` WHERE `id`=?";
            PreparedStatement stmLivraison = cnx.prepareStatement(qryLivraison);
            stmLivraison.setInt(1, id);
            ResultSet rs = stmLivraison.executeQuery();
            if (rs.next()) {
                int feedbackId = rs.getInt("Feedback_liv");
                int reclamationId = rs.getInt("Réclamation");


                String qryDeleteLivraison = "DELETE FROM `livraison` WHERE `id`=?";
                PreparedStatement stmDeleteLivraison = cnx.prepareStatement(qryDeleteLivraison);
                stmDeleteLivraison.setInt(1, id);
                stmDeleteLivraison.executeUpdate();


                String qryReclamation = "DELETE FROM `réclamation` WHERE `id`=?";
                PreparedStatement stmReclamation = cnx.prepareStatement(qryReclamation);
                stmReclamation.setInt(1, reclamationId);
                stmReclamation.executeUpdate();


                String qryFeedback = "DELETE FROM `feedback_livraison` WHERE `id`=?";
                PreparedStatement stmFeedback = cnx.prepareStatement(qryFeedback);
                stmFeedback.setInt(1, feedbackId);
                stmFeedback.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public livraison getById(int id) {
        livraison livraison = null;
        String qry = "SELECT * FROM `livraison` WHERE `id`=?";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                UserService sliv=new UserService();
                User livreur =sliv.getUserById(rs.getInt("livreur"));
                UserService schef=new UserService();
                User chef = schef.getUserById(rs.getInt("chef"));
                String adresse_source = rs.getString("adresse_source");
                String adresse_destination = rs.getString("adresse_destination");
                Service_FeedBack_livraison FB=new Service_FeedBack_livraison();
                Feedback_livraison feedback_liv = FB.getById(rs.getInt("Feedback_liv"));
                ServiceRéclamation srec=new ServiceRéclamation();
                Réclamation reclamation = srec.getById(rs.getInt("Réclamation"));
                boolean state_reception = rs.getBoolean("state_reception");
                boolean state_delivery = rs.getBoolean("state_delivery");
                Timestamp time_start = rs.getTimestamp("time_start");
                Timestamp time_end = rs.getTimestamp("time_end");
                ServiceCommande scom=new ServiceCommande();
                commande commande = scom.getById(rs.getInt("commande"));

                livraison = new livraison(id, livreur, chef, adresse_source, adresse_destination, feedback_liv, reclamation, state_reception, state_delivery, time_start, time_end, commande);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livraison;
    }
    public List<livraison> getLivraisonsSortedByFBLivreur() {
        List<livraison> livraisons = new ArrayList<>();
        String qry = "SELECT * FROM `livraison` INNER JOIN `feedback_livraison` ON `livraison`.`Feedback_liv` = `feedback_livraison`.`id` ORDER BY `feedback_livraison`.`FB_livreur` ASC";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                livraison liv= new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livraisons;
    }
    public List<livraison> getLivraisonsSortedByFBtemps() {
        List<livraison> livraisons = new ArrayList<>();
        String qry = "SELECT * FROM `livraison` INNER JOIN `feedback_livraison` ON `livraison`.`Feedback_liv` = `feedback_livraison`.`id` ORDER BY `feedback_livraison`.`FB_duration` ASC";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                livraison liv= new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livraisons;
    }
    public List<livraison> getLivraisonsSortedByTimeStart() {
        List<livraison> livraisons = new ArrayList<>();
        String qry = "SELECT * FROM `livraison` ORDER BY `time_start` ASC";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                livraison liv= new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livraisons;
    }
    public List<livraison> getLivraisonsWithReclamation() {
        List<livraison> livraisons = new ArrayList<>();
        String qry = "SELECT * FROM `livraison` WHERE `Réclamation` IS NOT NULL";
        try {
            PreparedStatement stm = cnx.prepareStatement(qry);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                livraison livraison = new livraison();
                livraison liv= new livraison();
                liv.setId(rs.getInt(1));
                liv.setLivreur(new UserService().getUserById(rs.getInt(2)));
                liv.setChef(new UserService().getUserById(rs.getInt(3)));
                liv.setAdresse_source(rs.getString(4));
                liv.setAdresse_destination(rs.getString(5));
                liv.setFeedback_liv(new Service_FeedBack_livraison().getById(rs.getInt(6)));
                liv.setRéclamation(new ServiceRéclamation().getById(rs.getInt(7)));
                liv.setState_reception(rs.getBoolean(8));
                liv.setState_delivery(rs.getBoolean(9));
                liv.setTime_start(rs.getTimestamp(10));
                liv.setTime_end(rs.getTimestamp(11));
                liv.setCommande(new ServiceCommande().getById(rs.getInt(12)));
                livraisons.add(liv);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return livraisons;
    }

    public boolean delete(livraison c1){ return false;};




}

