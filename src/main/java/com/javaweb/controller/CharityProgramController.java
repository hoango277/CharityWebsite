package com.javaweb.controller;

import com.javaweb.entity.CharityProgramEntity;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StatusResponse;
import com.javaweb.service.CharityProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CharityProgramController {

    @Autowired
    private CharityProgramService charityProgramService;

    @GetMapping("/charityprograms")
    public ResponseEntity<ResponseDTO> getAllCharityPrograms() {
        ResponseDTO responseDTO = charityProgramService.getAllCharityPrograms();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/charityprograms/{charityProgramId}")
    public ResponseEntity<ResponseDTO> getCharityProgramById(@PathVariable Long charityProgramId) {
        ResponseDTO responseDTO = charityProgramService.getCharityProgramById(charityProgramId);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("charityprograms")
    public ResponseEntity<ResponseDTO> createCharityProgram(@RequestBody CharityProgramEntity charityProgramEntity) {
        ResponseDTO responseDTO = charityProgramService.createCharityProgram(charityProgramEntity);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/charityprograms/{charityProgramId}")
    public ResponseEntity<ResponseDTO> updateCharityProgram(@PathVariable Long charityProgramId,
                                                            @RequestBody CharityProgramEntity charityProgramEntity) {
        ResponseDTO responseDTO = charityProgramService.updateCharityProgram(
                charityProgramId,
                charityProgramEntity);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/charityprograms/{charityProgramId}")
    public ResponseEntity<StatusResponse> deleteCharityProgram(@PathVariable Long charityProgramId) {
        charityProgramService.deleteCharityProgram(charityProgramId);
        return ResponseEntity.noContent().build();
    }
}
