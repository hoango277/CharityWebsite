package com.javaweb.service.impl;

import com.javaweb.repository.CharityProgramRepository;
import com.javaweb.service.CharityProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CharityProgramServiceImpl implements CharityProgramService {
    @Autowired
    private CharityProgramRepository charityProgramRepository;
}
