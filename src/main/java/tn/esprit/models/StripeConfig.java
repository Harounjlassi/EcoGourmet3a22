package tn.esprit.models;
import com.stripe.Stripe;

public class StripeConfig {
    public static final String STRIPE_SECRET_KEY = "sk_test_51OpfHFILsmRV4nqPsSywa4szvWj4tbLuAKXd2ASLPTHOZSEyzKwdq67s2M5ePFSDnddYqSSht0Ol7AlmxqwP2zbQ00HyYh2tk1";

    public static void init() {
        Stripe.apiKey = STRIPE_SECRET_KEY;
    }
}
