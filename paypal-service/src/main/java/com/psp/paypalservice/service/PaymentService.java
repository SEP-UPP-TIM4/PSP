package com.psp.paypalservice.service;

import com.paypal.api.payments.Transaction;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import com.psp.paypalservice.dto.CredentialsDto;
import com.psp.paypalservice.dto.PaymentRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PaymentService {

    public Payment createPayment(PaymentRequestDto paymentRequestDto, CredentialsDto credentials) throws PayPalRESTException {
        Payment payment = new Payment();

        payment.setIntent("sale");

        payment.setTransactions(createTransactions(paymentRequestDto));

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payment.setPayer(payer);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paymentRequestDto.getFailedUrl());
        redirectUrls.setReturnUrl(paymentRequestDto.getSuccessUrl());
        payment.setRedirectUrls(redirectUrls);

        APIContext apiContext = new APIContext(credentials.getMerchantId(), credentials.getMerchantPassword(), "sandbox");
        return payment.create(apiContext);

    }

    private List<Transaction> createTransactions(PaymentRequestDto paymentRequestDto){
        List<Transaction> transactions = new ArrayList<>();

        Amount amount = new Amount();
        amount.setCurrency("USD");
        Double total = paymentRequestDto.getAmount().setScale(2, RoundingMode.HALF_UP).doubleValue();
        amount.setTotal(String.format("%.2f", total));

        Transaction transaction = new Transaction();
        transaction.setDescription("description");
        transaction.setAmount(amount);

        transactions.add(transaction);
        return transactions;
    }

}
