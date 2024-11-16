package com.javaweb.model.response;

import com.javaweb.exception.InvalidDataException;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class CharityProgramResponse {
    private Long id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private String address;
    private Long amountNeeded;
    private Long totalAmount;
    private String image;
    private Double fundingPercentage;


}
