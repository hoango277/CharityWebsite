package com.javaweb.service.impl;

import com.javaweb.converter.CharityProgramConverter;
import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.exception.InvalidDataException;
import com.javaweb.model.response.CharityProgramResponse;
import com.javaweb.exception.ResourceNotFoundException;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.repository.CharityProgramRepository;
import com.javaweb.service.CharityProgramService;
import jakarta.validation.Valid;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

@Service
public class CharityProgramServiceImpl implements CharityProgramService {
    @Autowired
    private CharityProgramRepository charityProgramRepository;

    @Autowired
    private CharityProgramConverter charityProgramConverter;


    @SneakyThrows
    @Override
    public Page<CharityProgramResponse> getAllCharityPrograms(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CharityProgramEntity> charityProgramsPage = charityProgramRepository.findAll(pageable);

        return charityProgramsPage.map(charityProgram -> {
            try {
                return charityProgramConverter.convertToResponse(charityProgram);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing CharityProgram", e);
            }
        });
    }





    @Override
    public CharityProgramResponse getCharityProgramById(Long id) throws ParseException {
        CharityProgramEntity charityProgram = charityProgramRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Charity program with ID " + id + " not found"));

        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(charityProgram);

        return charityProgramResponse;
    }


    @Override
    public CharityProgramResponse createCharityProgram(@Valid CharityProgramEntity charityProgramEntity) throws ParseException {

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

        return charityProgramResponse;
    }

    @Override
    public CharityProgramResponse updateCharityProgram(@Valid Long id, CharityProgramEntity charityProgramEntity) throws ParseException {

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
        updatedCharityProgram.setImage(charityProgramEntity.getImage());

        charityProgramRepository.save(updatedCharityProgram);
        CharityProgramResponse charityProgramResponse = charityProgramConverter.convertToResponse(updatedCharityProgram);

        return charityProgramResponse;
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

    @Override
    public Page<CharityProgramResponse> getCharityProgramByKeyword(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);

        Page<CharityProgramEntity> charityProgramsPage = charityProgramRepository.findCharityProgramByKeyword(keyword,pageable);

        return charityProgramsPage.map(charityProgram -> {
            try {
                return charityProgramConverter.convertToResponse(charityProgram);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing CharityProgram", e);
            }
        });
    }
}
