package tn.esprit.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tn.esprit.utils.MyDataBase;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.Properties;
import java.util.ResourceBundle;

public class ParmailController  implements Initializable {
    Connection connection ;

    public TextField email;
    public Button send;
    private static final String ALGORITHM = "AES";

    // Cette clé est utilisée pour chiffrer et déchiffrer. Gardez-la en sécurité.
    private static final String SECRET_KEY = "SALAH";


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public void send(ActionEvent actionEvent) {
        try {
            connection = MyDataBase.getInstance().getCnx();

            String req = "select Password from user where Email = '" + email.getText() + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);
            if (rs.next()) {
                String r = rs.getString("Password");
                sendPasswordResetEmail(r, email.getText()); // Pass the email address to the sendPasswordResetEmail method
            } else {
                showAlert("Adresse e-mail non trouvée dans la base de données !");
            }
        } catch (SQLException e) {
            showAlert("Erreur lors de la récupération du mot de passe.");
            e.printStackTrace();
        }

    }


    public void sendPasswordResetEmail(String password, String recipientEmail) {
        final String username = "lefriiw@gmail.com"; // Replace with your email address
        final String emailPassword = "thls hgwa itcs xksn"; // Replace with your email password

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, emailPassword);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(recipientEmail)
            );
            message.setSubject("Réinitialisation du mot de passe");
            message.setText("Votre nouveau mot de passe est : " + password);

            Transport.send(message);

            showAlert("Un e-mail de réinitialisation du mot de passe a été envoyé à votre adresse e-mail.");
        } catch (MessagingException e) {
            showAlert("Erreur lors de l'envoi de l'e-mail. Veuillez réessayer plus tard. Détails: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}