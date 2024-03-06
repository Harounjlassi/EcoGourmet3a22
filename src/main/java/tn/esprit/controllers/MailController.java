package tn.esprit.controllers;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;
public class MailController   {
    public MailController() {
    }

    public void mailsend(String username, String password, String fromEmail, String toEmail, String subject, String mbodyext, String filep) {


        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        //properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");



        Session session;
        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        //Start our mail message
        MimeMessage msg = new MimeMessage(session);
        try {
            msg.setFrom(new InternetAddress(fromEmail));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            msg.setSubject(subject);

            Multipart emailContent = new MimeMultipart();

            //Text body part
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(mbodyext);

            //Attachment body part.
            MimeBodyPart pdfAttachment = new MimeBodyPart();
            if(filep!=null) {
                try {
                    pdfAttachment.attachFile(filep);
                    emailContent.addBodyPart(pdfAttachment);
                } catch (IOException ex) {
                    System.out.println("ddd");
                    System.out.println(ex.getMessage());
                }
            }
            //Attach body parts
            emailContent.addBodyPart(textBodyPart);


            //Attach multipart to message
            msg.setContent(emailContent);

            Transport.send(msg);
            System.out.println("Sent message");
//            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
//            successAlert.setTitle("Email Sent");
//            successAlert.setContentText("The email was sent successfully.");
//            successAlert.showAndWait();
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());

//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setTitle("Email Sending Error");
//            errorAlert.setContentText("An error occurred while sending the email:\n" + e.getMessage());
//            errorAlert.showAndWait();
        }
    }

//    @FXML
//    private void addfileaction(ActionEvent event) {
//
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setInitialDirectory(new File("C:\\")); // Set the initial directory
//
//        // Show the file dialog and get the selected file
//        File selectedFile = fileChooser.showOpenDialog(new Stage());
//
//        if (selectedFile != null) {
//            filePath = selectedFile.getAbsolutePath();
//            //System.out.println("Chosen File: " + filePath);
//
//            // Now you can use the filePath for further processing
//        } else {
//            //System.out.println("No file selected.");
//        }
//
//    }
//
//    @FXML
//    private void backMailmethod(ActionEvent event) {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
//        try {
//            Parent root = loader.load();
//            bsend.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.out.println("Error:" + ex.getMessage());
//        }
//    }
//
//    @FXML
//    private void cancelmailfunction(ActionEvent event) {
//    }
//
//    @FXML
//    private void closemethod(ActionEvent event) {
//        Stage stage = (Stage) bsend.getScene().getWindow();
//        stage.close();
//    }

}