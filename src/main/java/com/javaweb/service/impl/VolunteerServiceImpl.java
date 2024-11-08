package com.javaweb.service.impl;

import com.javaweb.converter.VolunteerConverter;
import com.javaweb.entity.VolunteerEntity;
import com.javaweb.model.dto.VolunteerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.VolunteerRepository;
import com.javaweb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerConverter volunteerConverter;

    @Override
    public ResponseDTO getAllVolunteers(long charityProgramID) {
        List<VolunteerEntity> listVolunteersByID = volunteerRepository
                .findVolunteersByCharityProgramID(charityProgramID);
        List<VolunteerDTO> volunteerDTOS = new ArrayList<>();
        for(VolunteerEntity v : listVolunteersByID){
            VolunteerDTO volunteerDTO = volunteerConverter.covertToDTO(v);
            volunteerDTOS.add(volunteerDTO);
        }
        return ResponseDTO.builder()
                .data(volunteerDTOS)
                .detail("OK")
                .message("Find succeeded").build();
    }
}
