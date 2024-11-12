package com.javaweb.service.impl;

import com.javaweb.converter.VolunteerConverter;
import com.javaweb.entity.*;
import com.javaweb.model.dto.VolunteerDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.repository.*;
import com.javaweb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    private WalletRepository walletRepository;
    @Autowired
    private TransactionRepository transactionRepository;


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

    @Override
    public StatusResponse addVolunteer(long charityProgramID, long moneyVolunteer, long userID) {
        CharityProgramEntity charityProgram = charityProgramRepository.findById(charityProgramID)
                .orElseThrow(() -> new NoSuchElementException("Charity Program not found with ID: " + charityProgramID));

        UserEntity user = userRepository.findById(userID)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userID));

        WalletEntity walletEntity = walletRepository.findByUserID(userID);

        if(walletEntity.getTotalAmount() < moneyVolunteer){
            return StatusResponse.builder()
                    .message("Không đủ số dư")
                    .status(HttpStatus.OK.value()).build();
        }

        TransactionEntity transactionToAdd = new TransactionEntity();
        transactionToAdd.setTransactionAmount(-1 * moneyVolunteer);
        transactionToAdd.setUser(user);
        transactionToAdd.setTransactionDate(Date.from(Instant.now()));
        transactionToAdd.setTransactionType("CHARITY_PROGRAM");
        transactionRepository.save(transactionToAdd);


        walletEntity.setTotalAmount(walletEntity.getTotalAmount() - moneyVolunteer);
        walletRepository.save(walletEntity);


        VolunteerEntity volunteerEntity = new VolunteerEntity();
        volunteerEntity.setMoneyDonated(moneyVolunteer);
        volunteerEntity.setUser(user);
        volunteerEntity.setDonateDate(Date.from(Instant.now()));
        volunteerEntity.setCharityProgram(charityProgram);
        volunteerRepository.save(volunteerEntity);

        return StatusResponse.builder()
                .message("Khuyên góp thành công cho dự án " + charityProgram.getName())
                .status(HttpStatus.OK.value()).build();
    }


}
