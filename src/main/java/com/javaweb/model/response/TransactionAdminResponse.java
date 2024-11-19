package com.javaweb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionAdminResponse {
    private Long transactionId;;
    private String username;
    private String transactionAmount;
    private String transactionDate;
    private String transactionType;
}
