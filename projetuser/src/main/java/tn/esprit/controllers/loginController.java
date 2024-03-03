    package tn.esprit.controllers;

    import javafx.fxml.FXML;
    import javafx.scene.control.*;
    import javafx.fxml.FXMLLoader;
    import javafx.fxml.Initializable;
    import javafx.scene.Parent;
    import javafx.event.ActionEvent;
    import javafx.scene.input.MouseEvent;
    import tn.esprit.utils.MyDataBase;

    import java.io.IOException;
    import java.net.URL;
    import java.security.InvalidKeyException;
    import java.security.NoSuchAlgorithmException;
    import java.sql.Connection;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import java.util.*;
    import java.util.logging.Level;
    import java.util.logging.Logger;

    import javafx.scene.control.Alert;

    import javax.crypto.BadPaddingException;
    import javax.crypto.Cipher;
    import javax.crypto.IllegalBlockSizeException;
    import javax.crypto.NoSuchPaddingException;
    import javax.crypto.spec.SecretKeySpec;
    import javax.mail.*;
    import javax.mail.internet.InternetAddress;
    import javax.mail.internet.MimeMessage;

    import static io.jsonwebtoken.JwsHeader.ALGORITHM;


    public class loginController implements Initializable {
        String r;
        Connection connection;
        private static final String ALGORITHM = "AES";

        // Cette clé est utilisée pour chiffrer et déchiffrer. Gardez-la en sécurité.
        private static final String SECRET_KEY = "SALAH";

        public Label mdpoublier;
        public TextField Email;
        public Button connexion;
        public Label cnx;
        public PasswordField mdp;
        @FXML
        private ToggleButton showPasswordToggleButton;
        @FXML
        private Label captchaLabel;
        @FXML
        private TextField userCaptchaAnswerField;

        private int num1;
        private int num2;
        private int result;

        @Override
        public void initialize(URL url, ResourceBundle rb) {
            connection = MyDataBase.getInstance().getCnx();
            generateCaptcha(); // Générer le test CAPTCHA au démarrage de l'application
        }

        public void connexion(ActionEvent actionEvent) {
            if (!Email.getText().isEmpty() && !mdp.getText().isEmpty() && !userCaptchaAnswerField.getText().isEmpty()) {
                connection = MyDataBase.getInstance().getCnx();

                try {
                    String req = "select Role from user where Email = '" + Email.getText() + "' AND Password='" + mdp.getText() +"'";
                    Statement st = connection.createStatement();
                    ResultSet rs = st.executeQuery(req);
                    if (rs.next()) {
                        r = rs.getString("Role");
                        System.out.println(r);

                        // Vérifier la réponse au CAPTCHA
                        int userAnswer;
                        try {
                            userAnswer = Integer.parseInt(userCaptchaAnswerField.getText());
                        } catch (NumberFormatException e) {
                            showAlert("Veuillez entrer un nombre valide pour la réponse au test CAPTCHA !");
                            return;
                        }

                        if (userAnswer == result) {
                            // La réponse au test CAPTCHA est correcte
                            // Redirection vers la page suivante en fonction du rôle de l'utilisateur
                            switch (r) {
                                case "admin":
                                    loadFXML("/admin.fxml");
                                    break;
                                case "client":
                                    loadFXML("/market.fxml");
                                    break;
                                case "chef":
                                    loadFXML("/displayEvent.fxml");
                                    break;
                                case "livreur":
                                    loadFXML("/livreur.fxml");
                                    break;
                            }
                        } else {
                            // La réponse au test CAPTCHA est incorrecte
                            showAlert("La réponse au test CAPTCHA est incorrecte !");
                            // Régénérer le test CAPTCHA
                            generateCaptcha();
                        }
                    } else {
                        showAlert("Vérifiez vos paramètres s'il vous plaît !");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    showAlert("Erreur lors de la connexion. Veuillez réessayer.");
                }
            } else {
                showAlert("Remplissez vos coordonnées et répondez au test CAPTCHA s'il vous plaît !");
            }
        }

        // Méthode pour générer le test CAPTCHA (équation mathématique simple)
        private void generateCaptcha() {
            Random random = new Random();
            num1 = random.nextInt(10); // Générer un nombre aléatoire entre 0 et 9
            num2 = random.nextInt(10);
            int operatorIndex = random.nextInt(2); // Générer un opérateur aléatoire : 0 pour +, 1 pour -
            if (operatorIndex == 0) {
                result = num1 + num2;
                captchaLabel.setText("Veuillez résoudre le test CAPTCHA : " + num1 + " + " + num2 + " = ?");
            } else {
                result = num1 - num2;
                captchaLabel.setText("Veuillez résoudre le test CAPTCHA : " + num1 + " - " + num2 + " = ?");
            }
        }

        public void inscription(MouseEvent mouseEvent) {
            loadFXML("/signup.fxml");
        }

        public void mdpoubliee(MouseEvent mouseEvent) {
            loadFXML("/Methode.fxml");

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

        private void loadFXML(String fxmlFile) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                Parent root = loader.load();
                connexion.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public void togglePasswordVisibility(ActionEvent event) {
            if (showPasswordToggleButton.isSelected()) {
                mdp.setPromptText(mdp.getText());
                mdp.setText("");
                mdp.setDisable(true);
            } else {
                mdp.setText(mdp.getPromptText());
                mdp.setPromptText("");
                mdp.setDisable(false);
            }
        }


    }
