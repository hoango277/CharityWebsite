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
public class VolunteerResponse {
    private boolean anonymous;
    private String donateDate;
    private Long moneyDonated;
    private String username;
}
