package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.Annonce;
import tn.esprit.models.Events;
import tn.esprit.models.User;
import tn.esprit.utils.MyDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements IService<User> {

    Connection cnx;

    public UserService() {
        cnx = MyDataBase.getInstance().getCnx();
    }




    public String getMailById(int id) {
        String qry = "SELECT Email FROM `users` WHERE UserID = " + id;
        String mail="";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while ( rs.next() ){
                mail = rs.getString("email");
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return mail;
    }

    public int getIdByName(String value) {
        int id = 0;
        String qry = "SELECT `UserID` FROM `user` WHERE Nom = ?";
        try {
            PreparedStatement pstmt = cnx.prepareStatement(qry);
            pstmt.setString(1, value);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("UserID");
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }


    public ArrayList<String> getAllName() {
        ArrayList<String> categorieArray = new ArrayList();
        String qry = "SELECT Nom FROM `user`";
        try {
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(qry);
            while (rs.next()) {
                // e.setName(rs.getString("name"));




                //e.setButton(new Button("Supprimer"));


                categorieArray.add(rs.getString("Nom"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return categorieArray;
    }

    public  String fetchuserNameFromDatabase(int userId) {
        String userName = null;
        try {

            String sql = "SELECT name FROM users WHERE user_id  = ?";
            PreparedStatement stm = cnx.prepareStatement(sql);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            System.out.println("User name is : " + userName);


            if (rs.next()) {
                userName = rs.getString("name");
                System.out.println("User name is : " + userName);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return userName;
        }

    }
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        try {
            String req = "SELECT * FROM user WHERE Role = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserId"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setNumero(rs.getString("Numero"));
                users.add(user);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
    public List<User> getAllChefs() {
        return getUsersByRole("chef");
    }

    public List<User> getAllLivreurs() {
        return getUsersByRole("livreur");
    }


//    @Override
//    public void add(Events events) {
//
//    }
//
//    @Override
//    public ArrayList<Events> getAll() {
//        return null;
//    }
//
//    @Override
//    public void update(Events events) {
//
//    }
//
//    @Override
//    public boolean delete(Events events) {
//        return false;
//    }

    @Override
    public void add(User t) {
        try {
            String req = "INSERT INTO user (Nom, Prenom, Email, Numero, Password, Role) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
            ps.setString(2, t.getPrenom());
            ps.setString(3, t.getEmail());
            ps.setString(4, t.getNumero());
            ps.setString(5, t.getPassword());
            ps.setString(6, t.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifier(User t) {
        try {
            String req1 = "UPDATE user SET Nom=?, Prenom=?, Email=?, Numero=?, Password=?, Role=? WHERE UserID = ?";
            PreparedStatement ps1= cnx.prepareStatement(req1);
            ps1.setString(1, t.getNom());
            ps1.setString(2, t.getPrenom());
            ps1.setString(3, t.getEmail());
            ps1.setString(4, t.getNumero());
            ps1.setString(5, t.getPassword());
            ps1.setString(6, t.getRole());
            ps1.setInt(7, t.getUserID());
            ps1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int userID) {
        try {
            String req = "DELETE FROM user WHERE UserID = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, userID);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Annonce> getAll() {
        return null;
    }

    @Override
    public void update(Annonce annonce) {

    }

    @Override
    public boolean delete(Annonce annonce) {
        return false;
    }



    public void getall() {
        try {
            String selectQuery = "SELECT * FROM user"; // Assuming "user" is the table name
            PreparedStatement preparedStatement = cnx.prepareStatement(selectQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                String prenom = resultSet.getString(3);
                String email = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(6);

                System.out.println("ID: " + id + ", Name: " + nom + " " + prenom + ", email: " + email + ", pass: " + password + ", role: " + role );
            }

            resultSet.close();
            preparedStatement.close();
            cnx.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public User getUserById(int id) {
        User user = null;
        try {
            String req = "SELECT * FROM user WHERE UserId = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserID(rs.getInt("UserId"));
                user.setNom(rs.getString("Nom"));
                user.setPrenom(rs.getString("Prenom"));
                user.setEmail(rs.getString("Email"));
                user.setNumero(rs.getString("Numero"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
}