package tn.esprit.test;

import tn.esprit.services.commande.commandeService;

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
        commandeService scmd=new commandeService();
        System.out.println(scmd.getCommandeDetails(scmd.getLastInsertedCommande().getId_commande()));
    }

    //System.out.println(sp.getAll());


}