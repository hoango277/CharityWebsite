package com.javaweb.service.impl;

import com.javaweb.model.response.PaymentResponse;
import com.javaweb.config.VNPAYConfig;
import com.javaweb.service.PaymentServices;
import com.javaweb.utils.VNPayUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentServices {

    @Autowired
    private VNPAYConfig vnpayConfig;

    @Override
    public ResponseEntity<PaymentResponse> createVnPayPayment(HttpServletRequest request, String money) {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", vnpayConfig.getVnp_Version());
        vnpParamsMap.put("vnp_Command", vnpayConfig.getVnp_Command());
        vnpParamsMap.put("vnp_TmnCode", vnpayConfig.getVnp_TmnCode());
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef", VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderInfo", "Thanh toan don hang:" + VNPayUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", vnpayConfig.getOrderType());
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", vnpayConfig.getVnp_ReturnUrl());

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnpCreateDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        calendar.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(calendar.getTime());
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        Long amount = Long.parseLong(money) * 100;
        String bankCode = request.getParameter("bankCode");
        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPayUtil.getIpAddress(request));

        // Build query URL
        String queryUrl = VNPayUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPayUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPayUtil.hmacSHA512(vnpayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnpayConfig.getVnp_PayUrl() + "?" + queryUrl;

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setStatus("OK");
        paymentResponse.setMessage("Payment Successful");
        paymentResponse.setUrl(paymentUrl);

        return ResponseEntity.ok(paymentResponse);
    }
}
