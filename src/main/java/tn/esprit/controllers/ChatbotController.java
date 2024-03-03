package tn.esprit.controllers;

import com.google.cloud.dialogflow.v2.*;
import javafx.event.ActionEvent;

import java.io.IOException;

public class ChatbotController {
    private final SessionsClient sessionsClient;
    private final SessionName session;

    public ChatbotController(String projectId, String sessionId) throws IOException {
        sessionsClient = SessionsClient.create();
        session = SessionName.ofProjectSessionName(projectId, sessionId);
    }

    public String sendMessage(String userInput) {
        try {
            TextInput.Builder textInput = TextInput.newBuilder().setText(userInput).setLanguageCode("en-US");
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            QueryResult queryResult = response.getQueryResult();
            return queryResult.getFulfillmentText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I couldn't process your request.";
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
    }
}
