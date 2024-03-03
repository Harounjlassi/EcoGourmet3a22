/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tn.esprit.utils.MyDataBase;

import javax.swing.JOptionPane;



public class RestController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private TextField question;
    @FXML
    private TextField reponse;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button modifier;
    @FXML
    private Button annuler;
    @FXML
    private Button chercher;
    Connection cnx ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
        cnx = MyDataBase.getInstance().getCnx();
        Statement st = cnx.createStatement();
        String req ="Select * from user where Email='"+email.getText()+"'  ";
        ResultSet rs = st.executeQuery(req);
        if (rs.next())
        {
            st.executeUpdate("update user set mdp ='"+ cryptage(mdp.getText())+"' where Email = '"+email.getText()+"'");
            JOptionPane.showMessageDialog(null, "Votre nouveau mot de passe est validé vous allez "
                    + "automatiquement accéder à la page de  connexion!");
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
                Parent root = loader.load();
                modifier.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Votre réponse est incorrecte  !");

        }



    }

    @FXML
    private void annuler(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Connexion.fxml"));
            Parent root = loader.load();
            annuler.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(RestController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void chercher(ActionEvent event) throws SQLException {
        cnx = MyDataBase.getInstance().getCnx();
        Statement st = cnx.createStatement();
        String req ="Select question,question from utilisateur where Email='"+email.getText()+"' ";
        ResultSet rs = st.executeQuery(req);
        if (rs.next())
        {
            question.setText(rs.getString(1));
            System.out.println(rs.getString(1));
        } else {
            JOptionPane.showMessageDialog(null, "Email incorrect !");

        }


    }
    public String cryptage(String pass)
    {
        try {
            MessageDigest msg = MessageDigest.getInstance("MD5");
            msg.update(pass.getBytes());
            byte [] rs = msg.digest();
            StringBuilder sb = new  StringBuilder();
            for (byte b :rs)
            {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {

        }return "" ;
    }


}