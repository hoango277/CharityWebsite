package com.javaweb.controller.restcontroller;

import com.javaweb.model.response.VolunteerResponse;
import com.javaweb.service.VolunteerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/donate")
@Slf4j
public class VolunteerController {
    @Autowired
    private VolunteerService volunteerService;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProjectVolunteers(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) throws ParseException {
        Page<VolunteerResponse> volunteerResponse = volunteerService.getAllVolunteers(id, page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("volunteers", volunteerResponse.getContent());
        response.put("page", volunteerResponse.getNumber());
        response.put("totalPages", volunteerResponse.getTotalPages());
        response.put("totalItems", volunteerResponse.getTotalElements());
        return ResponseEntity.ok(response);
    }

}
