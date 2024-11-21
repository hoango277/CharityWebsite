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
public class InfoUserResponse {
    private String userName;
    private String phoneNumber;
    private String email;
    private Integer status;
    private List<VolunteerResponse> volunteers;
    private List<TransactionResponse> transactions;
    private WalletResponse wallet;
}
