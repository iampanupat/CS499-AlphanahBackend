package com.alphanah.alphanahbackend.model.order;

import com.google.gson.annotations.SerializedName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ConfirmPaymentRequest {

    @SerializedName("payment_method_id")
    private String paymentMethodId;

    @SerializedName("payment_intent_id")
    private String paymentIntentId;

    @NotBlank(message = "Recipient 'firstname' cannot be null or empty.")
    @Size(max = 50, message = "Recipient 'firstname' cannot be longer than {max} characters.")
    private String firstname;

    @NotBlank(message = "Recipient 'lastname' cannot be null or empty.")
    @Size(max = 50, message = "Recipient 'lastname' cannot be longer than {max} characters.")
    private String lastname;

    @NotBlank(message = "Recipient 'address' cannot be null or empty.")
    @Size(max = 2048, message = "Recipient 'address' cannot be longer than {max} characters.")
    private String phone;

    @NotBlank(message = "Recipient 'phone' cannot be null or empty.")
    @Size(max = 19, message = "Recipient 'phone' cannot be longer than {max} characters.")
    private String address;

}
