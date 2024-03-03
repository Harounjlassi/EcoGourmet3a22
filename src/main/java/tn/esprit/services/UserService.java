package tn.esprit.services;

import tn.esprit.interfaces.IService;
import tn.esprit.models.User;
import tn.esprit.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService implements IService<User> {

    Connection cnx;

    public UserService() {
        cnx = MyDataBase.getInstance().getCnx();
    }

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

    @Override
    public ArrayList<User> getAll() {
        return null;
    }
}
