package com.javaweb.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String status;
    private String message;
    private String url;

}
