package com.scaler.paymentservicejuly24.services;

import com.razorpay.Order;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service("RazorpayPaymentGateway")
public class RazorPayPaymentGateway implements PaymentService {
      private RazorpayClient razorpayClient;
      public RazorPayPaymentGateway(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
      }

  @Override
    public String generatePaymentLink(Long orderId) throws RazorpayException {
      //make a call to razorpay to generate the payment link


    JSONObject paymentLinkRequest = new JSONObject();

    paymentLinkRequest.put("amount", 1000L); //10.00 //98.76 = 98.76 * 100 => 9876
    paymentLinkRequest.put("currency","INR");
//        paymentLinkRequest.put("accept_partial",true);
//        paymentLinkRequest.put("first_min_partial_amount",100);
    paymentLinkRequest.put("expire_by", System.currentTimeMillis() + 10 * 60 * 1000);
    paymentLinkRequest.put("reference_id", orderId.toString());
    paymentLinkRequest.put("description","Payment for a chocolate.");
    JSONObject customer = new JSONObject();

    //Call the OrderService to get the Order details.
    //Order order = restTemplate.getForObject("orderService URL", Order.class)

    customer.put("name","Nasrin Fathima");
    customer.put("contact","+919879102308");
    customer.put("email","nasrinkodai@gmail.com");
    paymentLinkRequest.put("customer", customer);
    JSONObject notify = new JSONObject();
    notify.put("sms",true);
    notify.put("email",true);
    paymentLinkRequest.put("reminder_enable",true);
    JSONObject notes = new JSONObject();
//        notes.put("policy_name","Jeevan Bima");
//        paymentLinkRequest.put("notes",notes);
    paymentLinkRequest.put("callback_url","https://scaler.com/");
    paymentLinkRequest.put("callback_method","get");

    PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);

    return payment.get("short_url");
//      return null;
  }

}
