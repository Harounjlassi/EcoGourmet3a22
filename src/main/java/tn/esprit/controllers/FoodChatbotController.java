package tn.esprit.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class FoodChatbotController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField userInputField;

    @FXML
    private Button sendButton;

    @FXML
    private void initialize() {
        // Set focus to the input field when the window is initialized
        userInputField.requestFocus();
    }

    @FXML
    private void sendMessage() {
        String userInput = userInputField.getText().trim();
        if (!userInput.isEmpty()) {
            System.out.println("Sending message: " + userInput); // Debug print
            processUserInput(userInput);
            userInputField.clear(); // Clear the input field after sending the message
        }
    }
    @FXML
    private void processUserInput(String userInput) {
        try {
            // Construct the URL with the query parameters
            String url = "https://api.spoonacular.com/recipes/findByIngredients?ingredients=" + userInput + "&apiKey=0419ed65b9cb4efa991f83fc6fc43652";

            // Create an OkHttpClient instance
            OkHttpClient client = new OkHttpClient();

            // Create a request to the Spoonacular API
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            // Execute the request and retrieve the response
            Response response = client.newCall(request).execute();

            // Check if the response is successful
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            // Parse the response JSON
            String responseBody = response.body().string();
            System.out.println("Response body: " + responseBody); // Print the response body

            // Check if the response body is empty or contains an empty array
            if (responseBody.isEmpty() || responseBody.equals("[]")) {
                chatArea.appendText("\nSorry, no recipes found for the given ingredients.");
                return; // Exit method if no recipes found
            }

            // Parse the response JSON
            // Parse the response JSON
            JSONArray jsonArray = new JSONArray(responseBody);

            // Append each recipe to the chat area
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject recipe = jsonArray.getJSONObject(i);
                String recipeTitle = recipe.getString("title");
                String recipeLink = "";

                // Check for different possible field names for the recipe link
                if (recipe.has("sourceUrl")) {
                    recipeLink = recipe.getString("sourceUrl");
                } else if (recipe.has("spoonacularSourceUrl")) {
                    recipeLink = recipe.getString("spoonacularSourceUrl");
                } else if (recipe.has("spoonacularSourceUrl")) {
                    recipeLink = recipe.getString("spoonacularSourceUrl");
                }

                // Append the recipe information to the chat area, including the link if available
                String recipeInfo = "\nRecipe: " + recipeTitle;
                if (!recipeLink.isEmpty()) {
                    recipeInfo += "\nLink: " + recipeLink;
                }
                chatArea.appendText(recipeInfo + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            chatArea.appendText("\nSorry, an error occurred while processing your request: " + e.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
            chatArea.appendText("\nSorry, an error occurred while parsing the response: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            chatArea.appendText("\nSorry, an unexpected error occurred: " + e.getMessage());
        }
    }


    @FXML
    private void displayMessage(String message) {
        chatArea.appendText("\n" + message); // Append the message to the chat area
    }
}
