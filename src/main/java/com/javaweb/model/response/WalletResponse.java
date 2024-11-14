package com.javaweb.model.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class WalletResponse {
    private Long totalAmount;
    private List<TransactionResponse> list;
}
