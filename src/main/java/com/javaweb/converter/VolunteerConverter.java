package com.javaweb.converter;

import com.javaweb.entity.VolunteerEntity;
import com.javaweb.model.dto.VolunteerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VolunteerConverter {
    @Autowired
    private ModelMapper modelMapper;

    public VolunteerDTO covertToDTO(VolunteerEntity volunteerEntity) {
        VolunteerDTO volunteerDTO =  new VolunteerDTO();
        volunteerDTO.setDonateDate(volunteerEntity.getDonateDate());
        volunteerDTO.setMoneyDonated(volunteerEntity.getMoneyDonated());
        return volunteerDTO;
    }
}
