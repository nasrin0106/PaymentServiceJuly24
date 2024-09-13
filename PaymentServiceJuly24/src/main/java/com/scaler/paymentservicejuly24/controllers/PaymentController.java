package com.scaler.paymentservicejuly24.controllers;

import com.razorpay.RazorpayException;
import com.scaler.paymentservicejuly24.dtos.GeneratePaymentLinkRequestDto;
import com.scaler.paymentservicejuly24.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/payments")
public class PaymentController {
     private PaymentService paymentService;

     public PaymentController(@Qualifier("RazorpayPaymentGateway")PaymentService paymentService){
         this.paymentService=paymentService;
     }
    //POST->//http://localhost:8080/payments
    @PostMapping
    public String generatePaymentLink(@RequestBody GeneratePaymentLinkRequestDto requestDto) throws RazorpayException, StripeException {
           return paymentService.generatePaymentLink(requestDto.getOrderId());
    }
    @PostMapping("/webhook")
    public void webHookEvent(@RequestBody Object object){
          System.out.println("webhook triggered");
    }
}
