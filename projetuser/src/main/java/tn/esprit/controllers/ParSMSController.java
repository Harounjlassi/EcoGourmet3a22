package tn.esprit.controllers;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.ResourceBundle;

public class ParSMSController implements Initializable {
    private Connection connection;
    private static final String ACCOUNT_SID = "AC1fd5a66cdcc0350ae50a9596b0e227b8";
    private static final String AUTH_TOKEN = "1c24a162045281f8db9cc275accb36e0";
    @FXML
    private TextField email;
    @FXML
    private Button send;
    private static final String ALGORITHM = "AES";

    // Cette clé est utilisée pour chiffrer et déchiffrer. Gardez-la en sécurité.
    private static final String SECRET_KEY = "SALAH";


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        connection = MyDataBase.getInstance().getCnx();
    }

    public void send(ActionEvent actionEvent) {
        try {
            connection = MyDataBase.getInstance().getCnx();

            String req = "select Password,Numero from user where Email = '" + email.getText() + "'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(req);

            if (rs.next()) {
                String password = rs.getString("Password");
                String phoneNumber = rs.getString("Numero");
                System.out.println(phoneNumber);
                envoyerSms(password, phoneNumber);
            } else {
                showAlert("Adresse e-mail non trouvée dans la base de données !");
            }
        } catch (SQLException e) {
            showAlert("Erreur lors de la récupération du mot de passe.");
            e.printStackTrace();
        }
    }

    public void envoyerSms(String password, String phoneNumber) {
        try {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new PhoneNumber("+216" + phoneNumber),
                    new PhoneNumber("+19282272339"),
                    "Votre mot de passe est : " + password
            ).create();
        } catch (ApiException e) {
            showAlert("Erreur lors de l'envoi du SMS.");
            e.printStackTrace();
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    public static String decrypter(String chaineCryptee) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] encryptedBytes = Base64.getDecoder().decode(chaineCryptee);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
}
