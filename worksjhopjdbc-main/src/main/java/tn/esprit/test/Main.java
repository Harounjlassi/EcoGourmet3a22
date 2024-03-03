package tn.esprit.test;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import tn.esprit.models.Personne;
import tn.esprit.services.ServicePersonne;
import tn.esprit.services.livraison.ServiceCommande;
import tn.esprit.utils.MyDataBase;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) {

        /*VonageClient client = VonageClient.builder().apiKey("34699909").apiSecret("B2F7w5g3aUpI7j7c").build();

        TextMessage message = new TextMessage(
                "Vonage APIs",
                "+21696489286",
                "Hello from Vonage!");

        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

        if (response.getMessages().get(0).getStatus().name().equals("0")) {
            System.out.println("Message sent successfully.");
        } else {
            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
        }*/
        ServiceCommande scmd=new ServiceCommande();
        System.out.println(scmd.getCommandeDetails(scmd.getLastInsertedCommande().getId_commande()));
    }

    //System.out.println(sp.getAll());


}