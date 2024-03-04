package tn.esprit.models;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;

public class StripePayment {
    public static void main(String[] args) {
        StripeConfig.init();

        try {
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setCurrency("usd")
                    .setAmount(1000L)
                    .build();

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            System.out.println("PaymentIntent créé : " + paymentIntent.getId());
        } catch (StripeException e) {
            e.printStackTrace();
        }
    }
}
