package com.javaweb.service.impl;

import com.javaweb.converter.VolunteerConverter;
import com.javaweb.entity.*;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.model.response.VolunteerResponse;
import com.javaweb.repository.*;
import com.javaweb.service.VolunteerService;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private VolunteerConverter volunteerConverter;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CharityProgramRepository charityProgramRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    @SneakyThrows
    @Override
    public Page<VolunteerResponse> getAllVolunteers(long charityProgramID, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<VolunteerEntity> listVolunteersByID = volunteerRepository
                .findVolunteersByCharityProgramID(charityProgramID, pageable);
        return listVolunteersByID.map(volunteerEntity -> {
            try {
                return volunteerConverter.convertToResponse(volunteerEntity);
            } catch (ParseException e) {
                throw new RuntimeException("Error parsing volunteer entity", e);
            }
        });
    }


    @Override
    @Transactional
    public void addVolunteer(long charityProgramID, long moneyVolunteer, long userID, boolean anonymous) {
        CharityProgramEntity charityProgram = charityProgramRepository.findById(charityProgramID)
                .orElseThrow(() -> new NoSuchElementException("Charity Program not found with ID: " + charityProgramID));

        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userID));


        charityProgram.setTotalAmount(charityProgram.getTotalAmount() + moneyVolunteer);
        charityProgramRepository.save(charityProgram);

        TransactionEntity transactionToAdd = new TransactionEntity();
        transactionToAdd.setTransactionAmount(moneyVolunteer);
        transactionToAdd.setUser(user);
        transactionToAdd.setTransactionDate(Date.from(Instant.now()));
        transactionToAdd.setTransactionType("Chương trình " + charityProgram.getName().toLowerCase());
        transactionRepository.save(transactionToAdd);


        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setMoneyDonated(moneyVolunteer);
        volunteerEntity.setUser(user);
        volunteerEntity.setAnonymous(anonymous);
        volunteerEntity.setDonateDate(Date.from(Instant.now()));
        volunteerEntity.setCharityProgram(charityProgram);
        volunteerRepository.save(volunteerEntity);

        StatusResponse.builder()
                .message("Khuyên góp thành công cho dự án " + charityProgram.getName())
                .status(HttpStatus.OK.value()).build();
    }


}
