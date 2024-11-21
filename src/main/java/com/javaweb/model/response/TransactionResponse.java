package com.javaweb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TransactionResponse {
    private String transactionAmount;
    private String transactionDate;
    private String transactionType;
}
