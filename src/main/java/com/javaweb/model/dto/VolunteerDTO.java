package com.javaweb.model.dto;


import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VolunteerDTO {
    private Date donateDate;
    private Long moneyDonated;
    private String usernameDonate;
    private String charityProgramName;
}
