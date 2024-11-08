package com.javaweb.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CharityProgramResponse {
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;
    private String address;
    private Long amountNeeded;
    private Long totalAmount;
}
