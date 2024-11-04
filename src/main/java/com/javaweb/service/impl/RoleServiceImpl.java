package com.javaweb.service.impl;

import com.javaweb.repository.RoleRepository;
import com.javaweb.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
}
