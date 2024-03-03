package tn.esprit.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FacebookAnnouncementHandler {

    // Your Facebook access token
    private static final String API_URL = "https://graph.facebook.com/%s/photos";
    private static final String ACCESS_TOKEN = "EAAF4PQJaaJsBO8fLmoJAyOke0gHvlD2Q0Q6VZAesCSYZBPNnJXHLfblM3mknXwtBljgIs8st4Rjh6cEOIGkOWNtDdBlduFDGSwZBmsA5CUgjN1hoMJbssm8yMPjRJlVqsI5sEiKl6Ia6WK3arpZChRFwbksZBVgeBTZChci8w0b6XeuTcsPXe15tO0LLfskiiaLEkzZCWJxwBF5kcpNTqGaEXQ9xgZDZD";
    private static final String PAGE_ID = "193731830479603";

    // Method to post an announcement to Facebook page
    @FXML
   public static void postAnnouncementOnFacebook(String message, String imagePath) {
        try {
            // Construct the API URL
            String apiUrl = String.format(API_URL, PAGE_ID);

            // Create the HTTP connection
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Set request parameters
            String boundary = "----Boundary" + System.currentTimeMillis();
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
            connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);

            // Construct the multipart request body
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
            outputStream.write(("Content-Disposition: form-data; name=\"message\"\r\n\r\n").getBytes(StandardCharsets.UTF_8));
            outputStream.write((message + "\r\n").getBytes(StandardCharsets.UTF_8));

            // Add the image file if provided
            if (imagePath != null) {
                File imageFile = new File(imagePath);
                outputStream.write(("--" + boundary + "\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(("Content-Disposition: form-data; name=\"source\"; filename=\"" + imageFile.getName() + "\"\r\n").getBytes(StandardCharsets.UTF_8));
                outputStream.write(("Content-Type: image/jpeg\r\n\r\n").getBytes(StandardCharsets.UTF_8));
                FileInputStream fileInputStream = new FileInputStream(imageFile);
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                fileInputStream.close();
                outputStream.write(("\r\n").getBytes(StandardCharsets.UTF_8));
            }

            // Finish the request
            outputStream.write(("--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();

            // Read the response
            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            // Show success or failure message based on the response code
            if (responseCode == HttpURLConnection.HTTP_OK) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Announcement posted on Facebook successfully", "");
            } else {
                showAlert(Alert.AlertType.ERROR, "Facebook Posting Error", "Failed to post announcement on Facebook.", "Please try again later.");
            }

            // Close the connection
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception
            showAlert(Alert.AlertType.ERROR, "Facebook Posting Error", "Failed to post announcement on Facebook.", "Please try again later.");
        }
    }

    private static void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.show();
    }
}