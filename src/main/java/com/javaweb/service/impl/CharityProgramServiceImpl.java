package com.javaweb.service.impl;

import com.javaweb.converter.CharityProgramConverter;
import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.exception.InvalidDataException;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.repository.CharityProgramRepository;
import com.javaweb.service.CharityProgramService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CharityProgramServiceImpl implements CharityProgramService {
    @Autowired
    private CharityProgramRepository charityProgramRepository;

    @Autowired
    private CharityProgramConverter charityProgramConverter;


    @Override
    public ResponseDTO getAllCharityPrograms() {
        List<CharityProgramEntity> list = charityProgramRepository.findAll();
        List<CharityProgramResponse> responseList = new ArrayList<>();
        for (CharityProgramEntity charityProgramEntity : list) {
            CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(charityProgramEntity);
            responseList.add(charityProgramResponse);
        }

        return ResponseDTO.builder()
                .data(responseList)
                .message("Successfully retrieved all charity programs")
                .detail(null)
                .build();
    }



    @Override
    public ResponseDTO getCharityProgramById(Long id) {
        CharityProgramEntity charityProgram = charityProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Charity program with ID " + id + " not found"));

        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(charityProgram);

        return ResponseDTO.builder()
                .data(charityProgramResponse)
                .message("Charity program found successfully")
                .detail(null)
                .build();
    }


    @Override
    public ResponseDTO createCharityProgram(@Valid CharityProgramEntity charityProgramEntity) {

        if(charityProgramEntity.getAmountNeeded() <= 0) {
            throw new InvalidDataException("Amount needed cannot be zero.");
        }

        if (charityProgramEntity.getStartDate().before(new Date())) {
            throw new InvalidDataException("Start date cannot be in the past.");
        }
        if (charityProgramEntity.getEndDate().before(charityProgramEntity.getStartDate())) {
            throw new InvalidDataException("End date must be after start date.");
        }

        CharityProgramEntity savedCharityProgram = charityProgramRepository.save(charityProgramEntity);
        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(savedCharityProgram);

        return ResponseDTO.builder()
                .data(charityProgramResponse)
                .message("Charity program created successfully")
                .detail(null)
                .build();
    }

    @Override
    public ResponseDTO updateCharityProgram(@Valid Long id, CharityProgramEntity charityProgramEntity) {

        if(charityProgramEntity.getAmountNeeded() <= 0) {
            throw new InvalidDataException("Amount needed cannot be zero.");
        }

        if (charityProgramEntity.getStartDate().before(new Date())) {
            throw new InvalidDataException("Start date cannot be in the past.");
        }
        if (charityProgramEntity.getEndDate().before(charityProgramEntity.getStartDate())) {
            throw new InvalidDataException("End date must be after start date.");
        }

        Optional<CharityProgramEntity> existingCharityProgram = charityProgramRepository.findById(id);

        if (!existingCharityProgram.isPresent()) {
            throw new ResourceNotFoundException("Charity program not found with id: " + id);
        }

        CharityProgramEntity updatedCharityProgram = existingCharityProgram.get();
        updatedCharityProgram.setName(charityProgramEntity.getName());
        updatedCharityProgram.setDescription(charityProgramEntity.getDescription());
        updatedCharityProgram.setStartDate(charityProgramEntity.getStartDate());
        updatedCharityProgram.setEndDate(charityProgramEntity.getEndDate());
        updatedCharityProgram.setAddress(charityProgramEntity.getAddress());
        updatedCharityProgram.setAmountNeeded(charityProgramEntity.getAmountNeeded());
        updatedCharityProgram.setTotalAmount(charityProgramEntity.getTotalAmount());

        charityProgramRepository.save(updatedCharityProgram);
        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(updatedCharityProgram);

        return ResponseDTO.builder()
                .data(charityProgramResponse)
                .message("Charity program updated successfully")
                .detail("Details of the updated charity program")
                .build();
    }


    @Override
    public StatusResponse deleteCharityProgram(Long id) {
        Optional<CharityProgramEntity> existingCharityProgram = charityProgramRepository.findById(id);
        if (!existingCharityProgram.isPresent()) {
            throw new ResourceNotFoundException("Charity program not found with id: " + id);
        }
        CharityProgramEntity deletedCharityProgram = existingCharityProgram.get();
        charityProgramRepository.delete(deletedCharityProgram);
        return StatusResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Delete successfully")
                .build();
    }
}
