package com.javaweb.service.impl;

import com.javaweb.repository.VolunteerRepository;
import com.javaweb.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRepository volunteerRepository;
}
