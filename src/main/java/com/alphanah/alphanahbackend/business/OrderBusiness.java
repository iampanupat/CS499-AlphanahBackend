package com.alphanah.alphanahbackend.business;

import com.alphanah.alphanahbackend.entity.Account;
import com.alphanah.alphanahbackend.entity.Order;
import com.alphanah.alphanahbackend.entity.OrderItem;
import com.alphanah.alphanahbackend.enumerate.PayType;
import com.alphanah.alphanahbackend.exception.AlphanahBaseException;
import com.alphanah.alphanahbackend.exception.OrderException;
import com.alphanah.alphanahbackend.model.order.CartResponseM2;
import com.alphanah.alphanahbackend.model.order.ConfirmPaymentRequest;
import com.alphanah.alphanahbackend.model.order.PaidResponseM2;
import com.alphanah.alphanahbackend.service.OrderService;
import com.alphanah.alphanahbackend.utility.AccountUtils;
import com.google.gson.Gson;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.net.RequestOptions;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderBusiness {

    @Autowired
    private OrderService service;

    @Value("${Stripe.apiKey}")
    private String stripeApiKey;

    public CartResponseM2 updateCoupon(UUID creatorUuid, UUID couponUuid) throws AlphanahBaseException {
        Order response = service.updateCoupon(creatorUuid, couponUuid);
        return response.toCartResponseM2(null);
    }

    public CartResponseM2 getCartOrder(UUID accountUuid) throws AlphanahBaseException, InterruptedException {
        Order response = service.getOrCreateCart(accountUuid);
        return response.toCartResponseM2(null);
    }

    public List<PaidResponseM2> getAllPaidOrders(UUID accountUuid) throws AlphanahBaseException {
        List<PaidResponseM2> responses = new ArrayList<>();

        List<Order> paidOrders = service.getAllPaidOrders(accountUuid);
        Collections.sort(paidOrders);
        for (Order paidOrder: paidOrders)
            responses.add(paidOrder.toPaidResponseM2(null));
        return responses;
    }

    public ResponseEntity<Map<String, Object>> checkout(UUID accountUuid, byte[] stripeRequest) {
        Stripe.apiKey = stripeApiKey;
        Gson gson = new Gson();

        ConfirmPaymentRequest confirmRequest = gson.fromJson(new String(stripeRequest), ConfirmPaymentRequest.class);
        PaymentIntent intent = new PaymentIntent();

        try {
            Account account = AccountUtils.findAccount(accountUuid);
            if (account.getCartUuid() == null)
                throw OrderException.updateWithNullCartUuid();

            Order cart = service.getOrCreateCart(accountUuid);
            if (cart.getOrderItems().size() == 0)
                throw OrderException.updateWithEmptyCart();

            RequestOptions requestOptions = RequestOptions.builder()
                    .setApiKey(stripeApiKey)
                    .build();

            double totalPrice = cart.calculateTotalPriceWithCoupon() + cart.calculateDeliveryFeeWithCoupon();
            if (confirmRequest.getPaymentMethodId() != null) {
                PaymentIntentCreateParams createParams = PaymentIntentCreateParams.builder()
                        .setAmount((long) totalPrice * 100)
                        .setCurrency("thb")
                        .setConfirm(true)
                        .setPaymentMethod(confirmRequest.getPaymentMethodId())
                        .setConfirmationMethod(PaymentIntentCreateParams.ConfirmationMethod.MANUAL)
                        .build();
                intent = PaymentIntent.create(createParams, requestOptions);
            } else if (confirmRequest.getPaymentIntentId() != null) {
                intent = PaymentIntent.retrieve(confirmRequest.getPaymentIntentId(), requestOptions);
                intent = intent.confirm();
            }
            Map<String, Object> response = generateResponse(intent, accountUuid, confirmRequest.getFirstname(), confirmRequest.getLastname(), confirmRequest.getPhone(), confirmRequest.getAddress());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(500));
        }
    }

    private Map<String, Object> generateResponse(PaymentIntent intent, UUID accountUuid, String firstname, String lastname, String phone, String address) throws AlphanahBaseException, StripeException {
        Map<String, Object> responseData = new HashMap<>();
        if (intent.getStatus().equals("requires_action") && intent.getNextAction().getType().equals("use_stripe_sdk")) {
            responseData.put("requires_action", true);
            responseData.put("payment_intent_client_secret", intent.getClientSecret());
        } else if (intent.getStatus().equals("succeeded")) {
            responseData.put("success", true);

            // UPDATE Cart to Paid
            service.updateCartToPaid(accountUuid, PayType.CARD, firstname, lastname, phone, address);

        } else {
            throw OrderException.checkoutFailure();
        }
        return responseData;
    }

}
