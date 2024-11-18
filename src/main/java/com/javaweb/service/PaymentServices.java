package com.javaweb.service;

import com.javaweb.model.response.PaymentResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;



public interface PaymentServices {
    public ResponseEntity<PaymentResponse> createVnPayPayment(HttpServletRequest request, String amount);
}
